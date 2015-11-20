tree grammar gen;

options{
    tokenVocab=APL;
    ASTLabelType=CommonTree;
    backtrack=true;
}

@members {
    // String to hold the user-defined operator (UDO) definitions
    String udo = "";

    HashMap<String,Integer> functionCode = new HashMap<String,Integer>();
    HashMap<String,String> functionName = new HashMap<String,String>();
    HashMap<String,Integer> variableCode = new HashMap<String,Integer>();
    HashMap<String,String> variableName = new HashMap<String,String>();

    // ID for unique temporary variable names
    int tempID = 0;

    int indent = 0;
    public String indent(int ix) {
        String str = "";
        for (int i = 0; i < ix; i++)
            str += "   ";
        return str;
    }

    // Create unique temporary variable name
    public String getTempVar() {
        return "_T__var_" + (tempID++) + "_";
    }
    
    // Create unique temporary variable name
    public String getVariable(String str) {
        String name = str;
        Integer code = variableCode.get(name);
        if (code == null) {
            variableCode.put(name, 0);
        } else {
            variableCode.put(name, code+1);
            name += "_" + (code+1);
        }
        name = "_V__" + name + "_";
        variableName.put(str,name);
        return name;
    }

    // Create unique temporary variable name
    public String getFunction(String str) {
        String name = str;
        Integer code = functionCode.get(name);
        if (code == null) {
            functionCode.put(name,0);
        } else {
            functionCode.put(name, code+1);
            name += "_" + (code+1);
        }
        name = "_F__" + name + "_";
        functionName.put(str,name);
        return name;
    }
    
    // Accessor method for user-defined method definitions
    public String functions() {
        return udo;
    }
}

prog returns [String code]
    :   stmt_list { $code = $stmt_list.code; }
    ;

stmt_list returns [String code]
        :   {$code = "";} ^(STMTLIST (s=stmt {$code += $s.code;})+)
    ;

stmt returns [String code]
    :   { indent = 3; }
        ^(FUNC t=TARGET o=operator_definition)
        {
            String f1 = $t.text;
            String f2 = getFunction(f1);
            
            // Method header
            indent = 1;
            udo += "\n" + indent(indent);
            udo += "// Function associated with " + f1;
            udo += "\n" + indent(indent);
            udo += "static class " + f2 + " extends APLOps.Operation ";
            udo += "{\n" + indent(++indent);
            udo += "public double[] exec";
            udo += "(double[] _A__left_, double[] _A__right_)";
            udo += "{\n" + indent(++indent);

            // Left and right running variables
            udo += "double[] _T__left_, _T__right_;\n";
            
            // Method body
            udo += $o.code;

            // Return the result
            udo += indent(indent);
            udo += "return _T__right_;\n";
            udo += indent(--indent) + "}\n";
            udo += indent(--indent) + "}\n";

            // No code in the main method
            indent = 2;
            $code = "";
        }
    |   e=expression {
            $code = $e.code;
            $code += indent(indent);
            $code += "System.out.println(APLOps.toString(_T__right_));\n";
        }
    ;

expression returns [String code]
    :   ^(VAR t=TARGET e=expression)
        {
            String v1 = $t.text;
            String v2 = getVariable(v1);
            $code = $e.code + indent(indent);
            $code += "double[] " + v2 + " = _T__right_;\n";
        }
    |   dyadic_operation    { $code = $dyadic_operation.code; }
    |   monadic_operation   { $code = $monadic_operation.code; }
    |   niladic_operation   { $code = $niladic_operation.code; }
    |   simple_expression  { $code = $simple_expression.code; }
    ;

operator_definition returns [String code]
    :   s=operator_body {$code = $s.code; }
    ;

operator_body returns [String code]
    :   {$code = "";} ^(STMTLIST (s=operator_body_stmt {$code += $s.code;})+)
    ;

operator_body_stmt returns [String code]
    :   { indent = 3; }
        ^(FUNC t=TARGET o=operator_definition)
        {
            String f1 = $t.text;
            String f2 = getFunction(f1);

            // Method header
            indent = 1;
            udo += "\n" + indent(indent);
            udo += "// Function associated with " + f1;
            udo += "\n" + indent(indent);
            udo += "static class " + f2 + " extends APLOps.Operation ";
            udo += "{\n" + indent(++indent);
            udo += "public double[] exec";
            udo += "(double[] _A__left_, double[] _A__right_) ";
            udo += "{\n" + indent(++indent);

            // Left and right running variables
            udo += "double[] _T__left_, _T__right_;\n";

            // Method body
            udo += $o.code;

            // Return the result
            udo += indent(indent);
            udo += "return _T__right_;\n";
            udo += indent(--indent) + "}\n";
            udo += indent(--indent) + "}\n";

            // No code in the main method
            indent = 3;
            $code = "";
        }
    |   e=expression {
            $code = $e.code;
        }
    ;

simple_expression returns [String code]
    :   atom { $code = $atom.code; }
    |   ^(PAREN e=expression) { $code = $e.code; }
    ;

atom returns [String code]
    :   array {$code = $array.code; }
    |   niladic_operation   { $code = $niladic_operation.code; }
    |   '⍵' { $code = indent(indent) + "_T__right_ = _A__right_;\n"; }
    |   '⍺' { $code = indent(indent) + "_T__right_ = _A__left_;\n"; }
    |   t=TARGET
        {
            String v1 = $t.text;
            if (variableName.get(v1) == null) {
                System.err.println("Compile error: use of undefined variable");
                System.exit(1);
            }
            String v2 = variableName.get(v1);
            $code = indent(indent) + "_T__right_ = " + v2 + ";\n";
        }
    ;

array returns [String code]
    :   ^(ARRAY d=DECIMAL 
            {
                $code = indent(indent);
                $code += "_T__right_ = new double[] {" + $d.text;
            }
            (d=DECIMAL { $code += ", " + $d.text; })*) 
        { $code += "};\n"; }
    ;
    
niladic_operation returns [String code]
    :   ^(OP t=TARGET)
        {
            String f1 = $t.text;
            if (functionName.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionName.get(f1);
            $code = indent(indent);
            $code += "_T__right_ = APLOps.exec(new " + f2 + "());\n";
        }
    ;

monadic_operation returns [String code]
    :   ^(OP o=monadic_operator e=expression)
        {
            $code = $e.code + indent(indent) + "_T__right_ = APLOps.exec(";
            $code += $o.code + ", _T__right_);\n";
        }
    ;


// TODO: Split operators into separate rule, so adverbs can be more robust
// TODO: Replace methods with Operation objects
monadic_operator returns [String code]
    :   ^(ADV '/' ^(OP o=dyadic_operator))
        {
            $code = "new APLOperators.across(" + $o.code + ")";
        }
    |   ^(ADV '¨' ^(OP o=monadic_operator))
        {
            $code = "new APLOperators.each(" + $o.code + ")";
        }
    |   t=TARGET
        {
            String f1 = $t.text;
            if (functionName.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionName.get(f1);
            $code = "new " + f2 + "()";
        }
    |   '~'
        {
            $code = "new APLOperators.not()";
        }
    |   '⍋' 
        { 
            $code = "new APLOperators.sort()";
        }
    |   '⍒' 
        { 
            $code = "new APLOperators.sortdown()";
        }
    |   '⍎'
        {
            $code = "new APLOperators.exec()";
        }
    |   '?'
        {
            $code = "new APLOperators.roll()";
        }
    |   '⌈' 
        {
            $code = "new APLOperators.ceil()";
        }
    |   '⌊' 
        {
            $code = "new APLOperators.floor()";
        }
    |   '⍴' 
        {
            $code = "new APLOperators.shape()";
        }
    |   '|'
        {
            $code = "new APLOperators.abs()";
        }
    |   '⍳' 
        {
            $code = "new APLOperators.indices()";
        }
    |   '*' 
        {
            $code = "new APLOperators.exp()";
        }
    |   '-' 
        {
            $code = "new APLOperators.neg()";
        }
    |   '+' 
        {
            $code = "new APLOperators.clone()";
        }
    |   '×' 
        {
            $code = "new APLOperators.signum()";
        }
    |   '÷'
        {
            $code = "new APLOperators.reciprocal()";
        }
    |   ',' 
        {
            $code = "new APLOperators.cat()";
        }
    |   '○' 
        {
            $code = "new APLOperators.pi()";
        }
    |   '⍟'
        {
            $code = "new APLOperators.ln()";
        }
    |   '⌽'
        {
            $code = "new APLOperators.reverse()";
        }
    |   '⊖'
        {
            $code = "new APLOperators.reverse2()";
        }
    |   '⍕' 
        {
            $code = "new APLOperators.format()";
        }
    |   '⍉' 
        { 
            $code = "new APLOperators.trans()";
        }
    |   '!' 
        {
            $code = "new APLOperators.factorial()";
        }
    ;

dyadic_operation returns [String code]
    :   ^(OP o=dyadic_operator s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + indent(indent);
            $code += "double[] " + temp + "=_T__right_;\n" + $s.code;
            $code += indent(indent);
            $code += "_T__left_ = _T__right_; _T__right_ = " + temp + ";\n";
            $code += indent(indent) + "_T__right_ = APLOps.exec(" + $o.code;
            $code += ", _T__left_, _T__right_);\n";
}
    ;

dyadic_operator returns [String code]
    :   ^(OP ^(CONJ . . .) . .)
        {
            $code = "\t\t// WARNING: conjugates currently unsupported\n";
        }
    |   t=TARGET
        {
            String f1 = $t.text;
            if (functionName.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionName.get(f1);
            String temp = getTempVar();
            $code = "new " + f2 + "()";
        }
    |   '∊'
        {
            $code = "new APLOperators.contains()"; 
        }
    |   '↑'
        { 
            $code = "new APLOperators.take()"; 
        }
    |   '↓'
        { 
            $code = "new APLOperators.drop()"; 
        }
    |   '/'
        { 
            $code = "new APLOperators.compress()"; 
        }
    |   '<'
        { 
            $code = "new APLOperators.less()"; 
        }
    |   '≤'
        { 
            $code = "new APLOperators.leq()"; 
        }
    |   '='
        { 
            $code = "new APLOperators.equ()"; 
        }
    |   '≥'
        { 
            $code = "new APLOperators.greq()"; 
        }
    |   '>'
        { 
            $code = "new APLOperators.greater()";
        }
    |   '≠'
        { 
            $code = "new APLOperators.neq()";
        }
    |   '∨'
        { 
            $code = "new APLOperators.or()"; 
        }
    |   '∧'
        { 
            $code = "new APLOperators.and()"; 
        }
    |   '⍱'
        { 
            $code = "new APLOperators.nor()"; 
        }
    |   '⍲'
        { 
            $code = "new APLOperators.nand()"; 
        }
    |   '⊥'
        { 
            $code = "new APLOperators.decode()"; 
        }
    |   '⊤'
        { 
            $code = "new APLOperators.encode()"; 
        }
    |   '\\'
        { 
            $code = "new APLOperators.expand()"; 
        }
    |   '?'
        { 
            $code = "new APLOperators.deal()"; 
        }
    |   '⌈'
        { 
            $code = "new APLOperators.max()";
        }
    |   '⌊'
        { 
            $code = "new APLOperators.min()";
        }
    |   '⍴'
        { 
            $code = "new APLOperators.reshape()";
        }
    |   '|'
        { 
            $code = "new APLOperators.mod()"; 
        }
    |   '⍳'
        { 
            $code = "new APLOperators.indexof()"; 
        }
    |   '*'
        { 
            $code = "new APLOperators.pow()"; 
        }
    |   '-'
        { 
            $code = "new APLOperators.sub()"; 
        }
    |   '+'
        { 
            $code = "new APLOperators.add()"; 
        }
    |   '×'
        { 
            $code = "new APLOperators.mul()"; 
        }
    |   '÷'
        { 
            $code = "new APLOperators.div()"; 
        }
    |   ','
        { 
            $code = "new APLOperators.concat()";
        }
    |   '○'
        { 
            $code = "new APLOperators.trig()"; 
        }
    |   '⍟'
        { 
            $code = "new APLOperators.log()";
        }
    |   '⌽'
        {
            $code = "new APLOperators.rot1()";
        }
    |   '⊖'
        {
            $code = "new APLOperators.rot2()";
        }
    |   '⍕'
        {
            $code = "new APLOperators.format()";
        }
    |   '⍉'
        {
            $code = "new APLOperators.transpose()";
        }
    |   '!'
        { 
            $code = "new APLOperators.combinations()"; 
        }
    ;

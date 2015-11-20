tree grammar gen;

options{
    tokenVocab=APL;
    ASTLabelType=CommonTree;
    backtrack=true;
}

@members {
    // String to hold the user-defined method definitions
    String user_defined_functions = "";
    
    HashMap<String,String> functionCode = new HashMap<String,String>();
    HashMap<String,String> variableCode = new HashMap<String,String>();
    
    // ID for unique temporary variable names
    int tempID = 0;
    int variableID = 0;
    int functionID = 0;
    
    // Create unique temporary variable name
    public String getTempVar() {
        return "temp" + tempID++;
    }
    
    // Create unique temporary variable name
    public String getVariable() {
        return "var" + variableID++;
    }
    
    // Create unique temporary variable name
    public String getFunction() {
        return "func" + functionID++;
    }
    
    // Accessor method for user-defined method definitions
    public String functions() {
        return user_defined_functions;
    }
}

prog returns [String code]
    :   stmt_list { $code = $stmt_list.code; }
    ;

stmt_list returns [String code]
        :   {$code = "";} ^(STMTLIST (s=stmt {$code += $s.code;})+)
    ;

stmt returns [String code]
    :   ^(FUNC t=TARGET o=operator_definition)
        {
            String f1 = $t.text;
            String f2 = getFunction();
            functionCode.put(f1, f2);
            
            // Method header
            user_defined_functions += "\n\t// Function associated with " + f1;
            user_defined_functions += "\n\tstatic class " + f2;
            user_defined_functions += " extends APLOperators.Operation {\n";
            user_defined_functions += "\t\tpublic double[] exec";
            user_defined_functions += "(double[] left_arg, double[] right_arg)";
            
            // Left and right running variables
            user_defined_functions += "{\n\t\t\tdouble[] left, right;\n";
            
            // Method body
            user_defined_functions += $o.code;
            
            // Return the result
            user_defined_functions += "\t\t\treturn right;\n";
            user_defined_functions += "\t\t}\t}\n";

            // No code in the main method
            $code = "";
        }
    |   e=expression {
            $code = $e.code;
            $code += "\t\tSystem.out.println(APLOperators.toString(right));\n";
        }
    ;

expression returns [String code]
    :   ^(VAR t=TARGET e=expression)
        {
            String v1 = $t.text;
            String v2 = getVariable();
            variableCode.put(v1, v2);
            $code = $e.code + "\t\tdouble[] " + v2 + " = right;\n";
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
    :   ^(FUNC t=TARGET o=operator_definition)
        {
            String f1 = $t.text;
            String f2 = getFunction();
            functionCode.put(f1, f2);
            
            // Method header
            user_defined_functions += "\n\t// Function associated with " + f1;
            user_defined_functions += "\n\tstatic class " + f2;
            user_defined_functions += " extends APLOperators.Operation {\n";
            user_defined_functions += "\t\tpublic double[] exec";
            user_defined_functions += "(double[] left_arg, double[] right_arg)";
            
            // Left and right running variables
            user_defined_functions += "{\n\t\t\tdouble[] left, right;\n";
            
            // Method body
            user_defined_functions += $o.code;
            
            // Return the result
            user_defined_functions += "\t\t\treturn right;\n";
            user_defined_functions += "\t\t}\t}\n";

            // No code in the main method
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
    |   '⍵' { $code = "\t\tright = right_arg;\n"; }
    |   '⍺' { $code = "\t\tright = left_arg;\n"; }
    |   t=TARGET
        {
            String v1 = $t.text;
            if (variableCode.get(v1) == null) {
                System.err.println("Compile error: use of undefined variable");
                System.exit(1);
            }
            String v2 = variableCode.get(v1);
            $code = "\t\tright = " + v2 + ";\n";
        }
    ;

array returns [String code]
    :   ^(ARRAY d=DECIMAL 
            { $code = "\t\tright = new double[] {" + $d.text; }
            (d=DECIMAL { $code += ", " + $d.text; })*) 
        { $code += "};\n"; }
    ;
    
niladic_operation returns [String code]
    :   ^(OP t=TARGET)
        {
            String f1 = $t.text;
            if (functionCode.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionCode.get(f1);
            $code = "\t\tright = APLOperators.exec(new " + f2 + "());\n";
        }
    ;

monadic_operation returns [String code]
    :   ^(OP o=monadic_operator e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.exec(";
            $code += $o.code + ", right);\n";
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
            if (functionCode.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionCode.get(f1);
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
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.exec(" + $o.code;
            $code += ", left, right);\n";
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
            if (functionCode.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionCode.get(f1);
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

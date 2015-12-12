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
    int function_level = 0;

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

    public String output() {
        String code = "";
        code += indent(indent);
        code += "System.out.println();\n";
        code += indent(indent);
        code += "if (_T__right_ == null)\n" + indent(indent+1);
        code += "APLOps.flush();\n" + indent(indent);
        code += "else\n" + indent(indent+1);
        code += "System.out.println(_T__right_.toString());\n";
        code += indent(indent);
        code += "System.out.println(sep);\n";
        return code;
    }
}

prog [String name] returns [String code]
    :   {
            indent = 0;
            $code = "import java.util.*;\n";
            $code += "public class " + name;
            $code += " {\n" + indent(++indent);
            $code += "private static String sep = (char)27 + \"[4m\" + ";
            $code += "String.format(\"\%80s\",\"\") + (char)27 + \"[24m\";\n";
            $code += indent(indent);
            $code += "public static void main(String[] args)";
            $code += " {\n" + indent(++indent);
            $code += "APLTensor _T__left_, _T__right_;\n";
        }
        stmt_list
        {
            $code += $stmt_list.code;
            $code += indent(--indent) + "}\n" + udo + "}";
        }
    ;

stmt_list returns [String code]
    :   {$code = "";} ^(STMTLIST (s=stmt {$code += $s.code;})+)
    ;

stmt returns [String code]
    :   { indent = 3; function_level++; }
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
            udo += "public String symbol() { return \"" + f1 + "\"; }\n";
            udo += indent(indent);
            udo += "public APLTensor exec";
            udo += "(APLTensor _A__left_, APLTensor _A__right_)";
            udo += "{\n" + indent(++indent);

            // Left and right running variables
            udo += "APLTensor _T__left_, _T__right_;\n";
            
            // Method body
            udo += $o.code;

            // Return the result
            udo += indent(indent);
            udo += "return _T__right_;\n";
            udo += indent(--indent) + "}\n";
            udo += indent(--indent) + "}\n";

            // No code in the main method
            indent = 2;
            function_level--;
            $code = "";
        }
    |   ^(SUPPRESS e=expression) {
            $code = $e.code;
        }
    |   e=expression {
            $code = $e.code;
            if (function_level == 0) {
                $code += output();
            }
        }
    ;

expression returns [String code]
    :   ^(VAR t=TARGET e=expression)
        {
            String v1 = $t.text;
            String v2 = getVariable(v1);
            $code = $e.code + indent(indent);
            $code += "APLTensor " + v2 + " = _T__right_;\n";
        }
    |   dyadic_operation    { $code = $dyadic_operation.code; }
    |   monadic_operation   { $code = $monadic_operation.code; }
    |   niladic_operation   { $code = $niladic_operation.code; }
    |   simple_expression  { $code = $simple_expression.code; }
    ;

operator_definition returns [String code]
    :   s=stmt_list {$code = $s.code; }
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
                $code += "_T__right_ = new APLTensor(" + $d.text;
            }
            (d=DECIMAL { $code += ", " + $d.text; })*) 
        { $code += ");\n"; }
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
            $code = "new APLOps.reduce(" + $o.code + ")";
        }
    |   ^(ADV '⌿' ^(OP o=dyadic_operator))
        {
            $code = "new APLOps.reduce1(" + $o.code + ")";
        }
    |   ^(ADV BACKSLASH ^(OP o=dyadic_operator))
        {
            $code = "new APLOps.scan(" + $o.code + ")";
        }
    |   ^(ADV '⍀' ^(OP o=dyadic_operator))
        {
            $code = "new APLOps.scan1(" + $o.code + ")";
        }
    |   ^(ADV '¨' ^(OP o=monadic_operator))
        {
            $code = "new APLOps.each(" + $o.code + ")";
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
            $code = "new APLOps.not()";
        }
    |   '⍋' 
        { 
            $code = "new APLOps.sort()";
        }
    |   '⍒' 
        { 
            $code = "new APLOps.sortdown()";
        }
    |   '?'
        {
            $code = "new APLOps.roll()";
        }
    |   '⌈' 
        {
            $code = "new APLOps.ceil()";
        }
    |   '⌊' 
        {
            $code = "new APLOps.floor()";
        }
    |   '↑'
        {
            $code = "new APLOps.head()";
        }
    |   '⍴'
        {
            $code = "new APLOps.shape()";
        }
    |   '|'
        {
            $code = "new APLOps.abs()";
        }
    |   '⍳' 
        {
            $code = "new APLOps.indices()";
        }
    |   '*' 
        {
            $code = "new APLOps.exp()";
        }
    |   '-' 
        {
            $code = "new APLOps.neg()";
        }
    |   '+' 
        {
            $code = "new APLOps.clone()";
        }
    |   '×' 
        {
            $code = "new APLOps.signum()";
        }
    |   '÷'
        {
            $code = "new APLOps.reciprocal()";
        }
    |   ',' 
        {
            $code = "new APLOps.cat()";
        }
    |   '○' 
        {
            $code = "new APLOps.pi()";
        }
    |   '⍟'
        {
            $code = "new APLOps.ln()";
        }
    |   '⌽'
        {
            $code = "new APLOps.reverse()";
        }
    |   '⊖'
        {
            $code = "new APLOps.reverse2()";
        }
    |   '⍉' 
        { 
            $code = "new APLOps.trans()";
        }
    |   '!' 
        {
            $code = "new APLOps.factorial()";
        }
    ;

dyadic_operation returns [String code]
    :   ^(OP o=dyadic_operator s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + indent(indent);
            $code += "APLTensor " + temp + " = _T__right_;\n";
            $code += $s.code;
            $code += indent(indent);
            $code += "_T__left_ = _T__right_; _T__right_ = " + temp + ";\n";
            $code += indent(indent) + "_T__right_ = APLOps.exec(" + $o.code;
            $code += ", _T__left_, _T__right_);\n";
        }
    ;

dyadic_operator returns [String code]
    :   ^(CONJ '.' ^(OP '∘') ^(OP o=dyadic_operator))
        {
            $code = "new APLOps.null_tie(" + $o.code + ")";
        }
    |   ^(CONJ '.' ^(OP o1=dyadic_operator) ^(OP o2=dyadic_operator))
        {
            $code = "new APLOps.tie(" + $o1.code + "," + $o2.code + ")";
        }
    |   ^(CONJ . . .)
        {
            $code = indent(indent) + "// WARNING: conjugates unsupported\n";
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
            $code = "new APLOps.contains()"; 
        }
    |   '↑'
        { 
            $code = "new APLOps.take()"; 
        }
    |   '↓'
        { 
            $code = "new APLOps.drop()"; 
        }
    |   '<'
        { 
            $code = "new APLOps.less()"; 
        }
    |   '≤'
        { 
            $code = "new APLOps.leq()"; 
        }
    |   '='
        { 
            $code = "new APLOps.equ()"; 
        }
    |   '≥'
        { 
            $code = "new APLOps.greq()"; 
        }
    |   '>'
        { 
            $code = "new APLOps.greater()";
        }
    |   '≠'
        { 
            $code = "new APLOps.neq()";
        }
    |   '∨'
        { 
            $code = "new APLOps.or()"; 
        }
    |   '∧'
        { 
            $code = "new APLOps.and()"; 
        }
    |   '⍱'
        { 
            $code = "new APLOps.nor()"; 
        }
    |   '⍲'
        { 
            $code = "new APLOps.nand()"; 
        }
    |   '⊥'
        { 
            $code = "new APLOps.decode()"; 
        }
    |   '⊤'
        { 
            $code = "new APLOps.encode()"; 
        }
    |   '?'
        { 
            $code = "new APLOps.deal()"; 
        }
    |   '⌈'
        { 
            $code = "new APLOps.max()";
        }
    |   '⌊'
        { 
            $code = "new APLOps.min()";
        }
    |   '⍴'
        { 
            $code = "new APLOps.reshape()";
        }
    |   '|'
        { 
            $code = "new APLOps.mod()"; 
        }
    |   '⍳'
        { 
            $code = "new APLOps.indexof()"; 
        }
    |   '*'
        { 
            $code = "new APLOps.pow()"; 
        }
    |   '-'
        { 
            $code = "new APLOps.sub()"; 
        }
    |   '+'
        { 
            $code = "new APLOps.add()"; 
        }
    |   '×'
        { 
            $code = "new APLOps.mul()"; 
        }
    |   '÷'
        { 
            $code = "new APLOps.div()"; 
        }
    |   ','
        { 
            $code = "new APLOps.concat()";
        }
    |   '○'
        { 
            $code = "new APLOps.trig()"; 
        }
    |   '⍟'
        { 
            $code = "new APLOps.log()";
        }
    |   '⌽'
        {
            $code = "new APLOps.rot1()";
        }
    |   '⊖'
        {
            $code = "new APLOps.rot2()";
        }
    |   '⍉'
        {
            $code = "new APLOps.transpose()";
        }
    |   '!'
        { 
            $code = "new APLOps.combinations()"; 
        }
    ;

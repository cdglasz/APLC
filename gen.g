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
            user_defined_functions += "\n\tpublic static double[] " + f2;
            user_defined_functions += "(double[] left_arg, double[] right_arg)";

            // Left and right running variables
            user_defined_functions += "{\n\t\tdouble[] left, right;\n";

            // Method body
            user_defined_functions += $o.code;

            // Return the result
            user_defined_functions += "\t\treturn right;\n";
            user_defined_functions += "\t}\n";

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
    |   dyadic_operator    { $code = $dyadic_operator.code; }
    |   monadic_operator   { $code = $monadic_operator.code; }
    |   niladic_operator   { $code = $niladic_operator.code; }
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
            user_defined_functions += "\tpublic static double[] " + $t.text;
            user_defined_functions += "(double[] left_arg, double[] right_arg)";
            user_defined_functions += "{\n\t\tdouble[] left, right;\n";
            user_defined_functions += $o.code;
            user_defined_functions += "\t\treturn right;\n";
            user_defined_functions += "\t}\n\n";
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
    |   niladic_operator   { $code = $niladic_operator.code; }
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

niladic_operator returns [String code]
    :   ^(OP t=TARGET)
        {
            String f1 = $t.text;
            if (functionCode.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionCode.get(f1);
            $code = "\t\tright = " + f2 + "(null,null);\n";
        }
    ;
// TODO: Split operators into separate rule, so adverbs can be more robust
// TODO: Replace methods with Operation objects
monadic_operator returns [String code]
    :   ^(OP ^(ADV '/' ^(OP '+')) e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.across(new APLOperators.Add(), right);\n";
        }
    |   ^(OP ^(ADV . .) .)
        {
            $code = "\t\t// WARNING: This adverb is currently unsupported\n";
        }
    |   ^(OP t=TARGET e=expression)
        {
            String f1 = $t.text;
            if (functionCode.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionCode.get(f1);
            $code = $e.code + "\t\tright = " + f2 + "(null, right);\n";
        }
    |   ^(OP '~' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.not(right);\n";
        }
    |   ^(OP '⍋' e=expression) 
        { 
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.sort(right);\n";
        }
    |   ^(OP '⍒' e=expression) 
        { 
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.reverse(APLOperators.sort(right));\n";
        }
    |   ^(OP '⍎' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.exec(right);\n";
        }
    |   ^(OP '?' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.roll(right);\n";
        }
    |   ^(OP '⌈' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.ceil(right);\n";
        }
    |   ^(OP '⌊' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.floor(right);\n";
        }
    |   ^(OP '⍴' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.shape(right);\n";
        }
    |   ^(OP '|' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.abs(right);\n";
        }
    |   ^(OP '⍳' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.indices(right);\n";
        }
    |   ^(OP '*' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.exp(right);\n";
        }
    |   ^(OP '-' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.neg(right);\n";
        }
    |   ^(OP '+' e=expression) 
        {
            $code = $e.code; 
        }
    |   ^(OP '×' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.signum(right);\n";
        }
    |   ^(OP '÷' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.reciprocal(right);\n";
        }
    |   ^(OP ',' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.cat(right);\n";
        }
    |   ^(OP '○' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.pi(right);\n";
        }
    |   ^(OP '⍟' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.ln(right);\n";
        }
    |   ^(OP '⌽' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.reverse(right);\n";
        }
    |   ^(OP '⊖' e=expression)
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.reverse2(right);\n";
        }
    |   ^(OP '⍕' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.format(right);\n";
        }
    |   ^(OP '⍉' e=expression) 
        { 
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.trans(right);\n";
        }
    |   ^(OP '!' e=expression) 
        {
            $code = $e.code + "\t\tright = ";
            $code += "APLOperators.factorial(right);\n";
        }
    ;

dyadic_operator returns [String code]
    :   ^(OP ^(CONJ . . .) . .)
        {
            $code = "\t\t// WARNING: conjugates currently unsupported\n";
        }
    |   ^(OP t=TARGET s=simple_expression e=expression)
        {
            String f1 = $t.text;
            if (functionCode.get(f1) == null) {
                System.err.println("Compile error: use of undefined function");
                System.exit(1);
            }
            String f2 = functionCode.get(f1);
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = " + f2 + "(left, right);\n";
        }
    |   ^(OP '∊' s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.contains(left, right);\n"; 
        }
    |   ^(OP '↑' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.take(left, right);\n"; 
        }
    |   ^(OP '↓' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.drop(left, right);\n"; 
        }
    |   ^(OP '/' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.compress(left, right);\n"; 
        }
    |   ^(OP '<' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.less(left, right);\n"; 
        }
    |   ^(OP '≤' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.leq(left, right);\n"; 
        }
    |   ^(OP '=' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.equ(left, right);\n"; 
        }
    |   ^(OP '≥' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.greq(left, right);\n"; 
        }
    |   ^(OP '>' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.greater(left, right);\n";
        }
    |   ^(OP '≠' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.neq(left, right);\n";
        }
    |   ^(OP '∨' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.or(left, right);\n"; 
        }
    |   ^(OP '∧' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.and(left, right);\n"; 
        }
    |   ^(OP '⍱' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.nor(left, right);\n"; 
        }
    |   ^(OP '⍲' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.nand(left, right);\n"; 
        }
    |   ^(OP '⊥' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.decode(left, right);\n"; 
        }
    |   ^(OP '⊤' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.encode(left, right);\n"; 
        }
    |   ^(OP '\\'s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.expand(left, right);\n"; 
        }
    |   ^(OP '?' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.deal(left, right);\n"; 
        }
    |   ^(OP '⌈' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.max(left, right);\n";
        }
    |   ^(OP '⌊' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.min(left, right);\n";
        }
    |   ^(OP '⍴' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.reshape(left, right);\n";
        }
    |   ^(OP '|' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.mod(left, right);\n"; 
        }
    |   ^(OP '⍳' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.indexof(left, right);\n"; 
        }
    |   ^(OP '*' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.pow(left, right);\n"; 
        }
    |   ^(OP '-' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.sub(left, right);\n"; 
        }
    |   ^(OP '+' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.add(left, right);\n"; 
        }
    |   ^(OP '×' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.mul(left, right);\n"; 
        }
    |   ^(OP '÷' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.div(left, right);\n"; 
        }
    |   ^(OP ',' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.concat(left, right);\n";
        }
    |   ^(OP '○' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.trig(left, right);\n"; 
        }
    |   ^(OP '⍟' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.log(left, right);\n";
        }
    |   ^(OP '⌽' s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.rot1(left, right);\n";
        }
    |   ^(OP '⊖' s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.rot2(left, right);\n";
        }
    |   ^(OP '⍕' s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.format(left, right);\n";
        }
    |   ^(OP '⍉' s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.transpose(left, right);\n";
        }
    |   ^(OP '!' s=simple_expression e=expression)
        { 
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = APLOperators.combinations(left, right);\n"; 
        }
    ;

tree grammar gen;

options{
    tokenVocab=APL;
    ASTLabelType=CommonTree;
    backtrack=true;
}

@members {
    String user_defined_functions = "";
    int code = 0;
    public String getTempVar() {
        return "temp" + code++;
    }
    public String functions() {
        return user_defined_functions;
    }
}

prog returns [String code]
    :   { $code = "";} stmt_list { $code += $stmt_list.code; }
    ;

stmt_list returns [String code]
    :   {$code = "";} ^(STMTLIST (s=stmt {$code += $s.code;})+)
    ;

stmt returns [String code]
    :   ^(FUNC t=TARGET o=operator_definition)
        {
            user_defined_functions += "\n\tpublic static double[] " + $t.text;
            user_defined_functions += "(double[] left_arg, double[] right_arg)";
            user_defined_functions += "{\n\t\tdouble[] left, right;\n";
            user_defined_functions += $o.code;
            user_defined_functions += "\t\treturn right;\n";
            user_defined_functions += "\t}\n";
            $code = "";
        }
    |   e=expression {
            $code = $e.code;
            $code += "\t\tSystem.out.println(Arrays.toString(right));\n";
        }
    ;

expression returns [String code]
    :   ^(VAR t=TARGET e=expression)
        {
            $code = $e.code + "\t\tdouble[] " + $t.text + "=right;\n";
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
    |   TARGET { $code = "\t\tright = " + $TARGET.text + ";\n"; }
    ;

array returns [String code]
    :   ^(ARRAY d=DECIMAL 
            { $code = "\t\tright = new double[] {" + $d.text; }
            (d=DECIMAL { $code += ", " + $d.text; })*) 
        { $code += "};\n"; }
    ;

niladic_operator returns [String code]
    :   ^(OP TARGET) { $code = "\t\tright = " + $TARGET.text + "(null,null);\n"; }
    ;

monadic_operator returns [String code]
    :   ^(OP ^(ADV '/' ^(OP '+')) e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.add(null, right);\n";
        }
    |   ^(OP ^(ADV . .) .)
        {
            $code = "\t\t// WARNING: This adverb is currently unsupported\n";
        }
    |   ^(OP '~' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.not(null, right);\n";
        }
    |   ^(OP '⍋' e=expression) 
        { 
            $code = $e.code + "\t\tright = APLOperators.sortup(null, right);\n";
        }
    |   ^(OP '⍒' e=expression) 
        { 
            $code = $e.code + "\t\tright = APLOperators.sortdown(null, right);\n";
        }
    |   ^(OP '⍎' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.execute(null, right);\n";
        }
    |   ^(OP '⊂' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.contain(null, right);\n";
        }
    |   ^(OP '?' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.roll(null, right);\n";
        }
    |   ^(OP '⌈' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.ceil(null, right);\n";
        }
    |   ^(OP '⌊' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.floor(null, right);\n";
        }
    |   ^(OP '⍴' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.shape(null, right);\n";
        }
    |   ^(OP '|' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.mag(null, right);\n";
        }
    |   ^(OP '⍳' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.indices(null, right);\n";
        }
    |   ^(OP '*' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.exp(null, right);\n";
        }
    |   ^(OP '-' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.neg(null, right);\n";
        }
    |   ^(OP '+' e=expression) 
        {
            $code = $e.code; 
        }
    |   ^(OP '×' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.sign(null, right);\n";
        }
    |   ^(OP '÷' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.recip(null, right);\n";
        }
    |   ^(OP ',' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.cat(null, right);\n";
        }
    |   ^(OP '○' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.pi(null, right);\n";
        }
    |   ^(OP '⍟' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.log(null, right);\n";
        }
    |   ^(OP '⌽' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.rev1(null, right);\n";
        }
    |   ^(OP '⊖' e=expression)
        {
            $code = $e.code + "\t\tright = APLOperators.rev2(null, right);\n";
        }
    |   ^(OP '⍕' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.format(null, right);\n";
        }
    |   ^(OP '⍉' e=expression) 
        { 
            $code = $e.code + "\t\tright = APLOperators.transpose(null, right);\n";
        }
    |   ^(OP '!' e=expression) 
        {
            $code = $e.code + "\t\tright = APLOperators.factorial(null, right);\n";
        }
    |   ^(OP t=TARGET e=expression)
        { 
            $code = $e.code + "\t\tright = " + $t.text + "(null, right);\n"; 
        }
    ;

dyadic_operator returns [String code]
    :   ^(OP ^(CONJ . . .) . .)
        {
            $code = "\t\t// WARNING: conjugates currently unsupported\n";
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
            $code += "\t\tright = APLOperators.logb(left, right);\n";
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
    |   ^(OP t=TARGET s=simple_expression e=expression)
        {
            String temp = getTempVar();
            $code = $e.code + "\t\tdouble[] " + temp + "=right;\n" + $s.code;
            $code += "\t\tleft = right; right = " + temp + ";\n";
            $code += "\t\tright = " + $t.text + "(left, right);\n"; 
        }
;

grammar APL;

options {
output=AST;
ASTLabelType=CommonTree;
backtrack=true;
}

tokens{
STMTLIST;ARRAY;PAREN;VAR;FUNC;OP;ADV;CONJ;
}

@members{
    ArrayList<String> user_defined_variables = new ArrayList<String>();
    HashMap<String,Integer> user_defined_functions = new HashMap<String,Integer>();
    int current_arity = 0;

    public HashMap<String,Integer> user_functions() {
        return user_defined_functions;
    }

    public ArrayList<String> user_variables() {
        return user_defined_variables;
    }

    // override the default error reporting TARGETs
    public void reportError(RecognitionException e) {
        // call the Parser member TARGET to report the error
        displayRecognitionError(this.getTokenNames(), e);
        // exit with error
        System.exit(1);
    }
}

prog
    :   '\n'* stmt_list '\n'* EOF -> stmt_list
    ;

stmt_list
    :   stmt (('\n'|'◊')+ stmt)* -> ^(STMTLIST stmt+)
    ;

stmt
:   f=TARGET '←' o=operator_definition
    {
        if (user_defined_variables.contains($f.text))
            user_defined_variables.remove($f.text);
        user_defined_functions.put($f.text,(Integer)current_arity);
    } -> ^(FUNC $f $o)
|   expression
;

expression
    :   t=TARGET '←' e=expression
        {
            if (user_defined_functions.get($t.text) != null)
                user_defined_functions.remove($t.text);
            user_defined_variables.add($t.text);
        } -> ^(VAR $t $e)
    |   dyad
    |   monad
    |   nilad
    |   simple_expression
    ;

operator_definition
    :   { current_arity = 0; }
        '{' stmt_list '}' -> stmt_list
    ;

nilad
    :   o=niladic_operator -> ^(OP $o)
    ;
monad
    :   o=monadic_operator e=expression -> ^(OP $o $e)
    ;
dyad
    :   s=simple_expression o=dyadic_operator e=expression -> ^(OP $o $s $e)
    ;

simple_expression
    :   atom
    |   '(' expression ')'              -> ^(PAREN expression)
    ;
atom
    :   array
    |   nilad
    |   '⍵' { current_arity = current_arity < 1 ? 1 : current_arity; }
    |   '⍺' { current_arity = 2; }
    |   TARGET {user_defined_variables.contains($TARGET.text) }?
    ;
array
    :   DECIMAL+                        -> ^(ARRAY DECIMAL+)
    ;

niladic_operator
:   o=niladic_base
    ;
monadic_operator
:   o=monadic_base
|   a=adverb
    ;
dyadic_operator
:   o=dyadic_base
|   c=conjunction
    ;

niladic_base
    :   TARGET { user_defined_functions.get($TARGET.text).equals(0) }?
    ;
monadic_base
    :   TARGET { user_defined_functions.get($TARGET.text).equals(1) }?
    |   m_symbols
    |   n_symbols
    ;
dyadic_base
    :   TARGET { user_defined_functions.get($TARGET.text).equals(2) }?
    |   d_symbols
    |   n_symbols
    ;

adverb
    :   o=monadic_base a=('/' | '¨' | '\\')     -> ^(ADV $a ^(OP $o))
    ;
// (⍺+.×⍵) = (+/⍺×⍵), (⍺×.+⍵) = (×/⍺+⍵)
conjunction
    :   '∘' c=('.') o=dyadic_operator               -> ^(CONJ $c ^(OP '∘') $o)
    |   d1=dyadic_base c=('.')
        d2=dyadic_operator     -> ^(CONJ $c ^(OP $d1) $d2)
    ;

m_symbols : '~' | '⍋' | '⍒' | '⍎' | '⊂' ;
d_symbols  : '∊' | '↑' | '↓' | '/' | '<' | '≤' | '=' | '≥' | '>' | '≠' | '∨' | '∧'
        | '⍱' | '⍲' | '⊥' | '⊤' | '\\' ;
n_symbols : '?' | '⌈' | '⌊' | '⍴' | '|' | '⍳' | '*' | '-' | '+' | '×' | '÷' | ','
        | '○' | '⍟' | '⌽' | '⊖' | '⍕' | '⍉' | '!' ;
DECIMAL : '¯'? ('0' .. '9')+  ('.' ('0' .. '9')+)? ;
TARGET  : ('A' .. 'Z' | 'a' .. 'z')('A' .. 'Z' | 'a' .. 'z' | '0' .. '9')* ;
COMMENT : '⍝' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};
WS      : ( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;};
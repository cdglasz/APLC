grammar APL;

options {
    output=AST;
    ASTLabelType=CommonTree;
    backtrack=true;
}

tokens{
    STMTLIST;ARRAY;PAREN;VAR;FUNC;OP;ADV;CONJ;BACKSLASH;SUPPRESS;
}

@members{
    // Names of variables
    ArrayList<String> user_defined_variables = new ArrayList<String>();
    
    // HashMap of functions, associated with their arity
    HashMap<String,Integer> function_arity = new HashMap<String,Integer>();
    
    // The arity of the function currently being parsed. 
    //  (This is hacky in my opinion)
    int current_arity = 0;
    
    // Accessor method for user defined functions
    public HashMap<String,Integer> user_functions() {
        return function_arity;
    }
    
    // Accessor method for user defined variables
    public ArrayList<String> user_variables() {
        return user_defined_variables;
    }
    
    // override the default error reporting functions
    public void reportError(RecognitionException e) {
        // call the Parser member function to report the error
        displayRecognitionError(this.getTokenNames(), e);
        // exit with error
        System.exit(1);
    }
}

prog
    :   stmt_list EOF -> stmt_list
    ;

stmt_list
    :   // Statements are separated by newlines or ◊'s
        '\n'* stmt (('\n'|'◊')+ stmt)* '\n'* -> ^(STMTLIST stmt+)
    ;

stmt
    :   f=TARGET '←' o=operator_definition
        {
            // If a variable exists with this name, overwrite it
            if (user_defined_variables.contains($f.text))
                user_defined_variables.remove($f.text);
            function_arity.put($f.text,(Integer)current_arity);
        } -> ^(FUNC $f $o)
    |   expression ';'      ->  ^(SUPPRESS expression)
    |   expression
    ;

expression
    :   t=TARGET '←' e=expression
        {
            // If a function exists with this name, overwrite it
            if (function_arity.get($t.text) != null)
                function_arity.remove($t.text);
            user_defined_variables.add($t.text);
        } -> ^(VAR $t $e)
    |   dyad
    |   monad
    |   nilad
    |   simple_expression
    ;

operator_definition
    :   // We're starting a function, so reset the current arity
        { current_arity = 0; }
        '{' stmt_list '}' -> stmt_list
    ;

dyad
    :   s=simple_expression o=dyadic_operator e=expression -> ^(OP $o $s $e)
    ;
monad
    :   o=monadic_operator e=expression -> ^(OP $o $e)
    ;
nilad
    :   o=niladic_operator -> ^(OP $o)
    ;

simple_expression
    :   atom
    |   '(' expression ')'              -> ^(PAREN expression)
    ;
atom
    :   array
    |   nilad
    |   '⍵'
        // A right argument means this function is has at least an arity of 1
        { current_arity = current_arity < 1 ? 1 : current_arity; }
    |   '⍺'
        // A left argument means this function has an arity of 2
        { current_arity = 2; }
    |   TARGET
        // Only use a target in this context if it is a variable
        {user_defined_variables.contains($TARGET.text) }?
    ;
array
    :   num+                        -> ^(ARRAY num+)
    ;

niladic_operator
    :   o=niladic_base
    ;
monadic_operator
    :   a=adverb
    |   o=monadic_base
    ;
dyadic_operator
    :   o=dyadic_base
    |   c=conjunction
    ;

niladic_base
    :   TARGET
        {
            // Operator is niladic if its arity is 0
            function_arity.get($TARGET.text) != null &&
            function_arity.get($TARGET.text).equals(0)
        }?
    ;
monadic_base
    :   TARGET
        {
            // Operator is monadic if its arity is 1
            function_arity.get($TARGET.text) != null &&
            function_arity.get($TARGET.text).equals(1)
        }?
    |   m_symbols
    |   n_symbols
    ;
dyadic_base
    :   TARGET
        {
            // Operator is dyadic if its arity is 2
            function_arity.get($TARGET.text) != null &&
            function_arity.get($TARGET.text).equals(2)
        }?
    |   d_symbols
    |   n_symbols
    ;

adverb
    :   o=dyadic_base a=d_adverbs        -> ^(ADV $a ^(OP $o))
    |   o=monadic_base '¨'                  -> ^(ADV '¨' ^(OP $o))
    ;
conjunction
    :   '∘' c=('.') o=dyadic_operator               -> ^(CONJ $c ^(OP '∘') ^(OP $o))
    |   d1=dyadic_base c=('.')
        d2=dyadic_operator     -> ^(CONJ $c ^(OP $d1) ^(OP $d2))
    ;

d_symbols : '∊' | '↓' | '/' | '<' | '≤' | '=' | '≥' | '>' | '≠' | '∨'
| '∧' | '⍱' | '⍲' | '⊥' | '⊤' | '\\' ;
n_symbols : '?' | '⌈' | '⌊' | '↑' | '⍴' | '|' | '⍳' | '*' | '-' | '+' | '×'
| '÷' | ',' | '○' | '⍟' | '⌽' | '⊖' | '⍕' | '⍉' | '!' ;
m_symbols : '~' | '⍋' | '⍒' | '⍎' | '⊂' ;

d_adverbs : '/' | '\\' -> BACKSLASH ;

num
    :   '¯ '                         -> ^(DECIMAL["Double.POSITIVE_INFINITY"])
    |   '¯¯ '                        -> ^(DECIMAL["Double.NEGATIVE_INFINITY"])
    |   DECIMAL
    |   '¯' d=DECIMAL                           -> ^(DECIMAL["-"+$d.text])
    |   i=INTEGER                               -> ^(DECIMAL[$i.text+".0"])
    |   '¯' i=INTEGER                           -> ^(DECIMAL["-"+$i.text+".0"])
    ;

DECIMAL : ('0' .. '9')+ '.' ('0' .. '9')+ ;
INTEGER : ('0' .. '9')+;
TARGET  : ('A' .. 'Z' | 'a' .. 'z')('A' .. 'Z' | 'a' .. 'z' | '0' .. '9')* ;
COMMENT : '⍝' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};
WS      : ( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;};
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
    Stack<Integer> current_arity = new Stack<Integer>();
    
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
    :   operator_def
    |   expression ';'      ->  ^(SUPPRESS expression)
    |   expression
    ;

operator_def
    :   // We're starting a function, so reset the current arity
        { current_arity.push(0); }
        f=TARGET '←' '{' s=stmt_list '}'
        {   // If a variable exists with this name, overwrite it
            if (user_defined_variables.contains($f.text))
                user_defined_variables.remove($f.text);
            function_arity.put($f.text,current_arity.pop());
        }
        -> ^(FUNC $f $s)
    ;

expression
    :   t=TARGET '←' e=expression
        {   // If a function exists with this name, overwrite it
            if (function_arity.get($t.text) != null)
                function_arity.remove($t.text);
            user_defined_variables.add($t.text);
        } -> ^(VAR $t $e)
    |   dyad
    |   monad
    |   atom
    ;

dyad
    :   a=atom o=dyadic_operator e=expression -> ^(OP $o $a $e)
    ;
monad
    :   o=monadic_operator e=expression -> ^(OP $o $e)
    ;

atom
    :   '(' expression ')'              -> ^(PAREN expression)
    |   array
    |   nilad
    |   '⍵'
        { current_arity.size() >= 0 }?
        {   // A right argument means this function is at least arity 1
            current_arity.push(Math.max(current_arity.pop(), 1));
        }
    |   '⍺'
        { current_arity.size() >= 0 }?
        {   // A left argument means this function is arity 2
            current_arity.pop(); current_arity.push(2);
        }
    |   {   // Only use variable if it exists here
            user_defined_variables.contains(input.LT(1).getText())
        }? TARGET
    ;
array
    :   num+                        -> ^(ARRAY num+)
    ;

nilad
    :   o=niladic_operator -> ^(OP $o)
    ;
niladic_operator
    :   {   // Operator is niladic if its arity is 0
            function_arity.get(input.LT(1).getText()) != null &&
            function_arity.get(input.LT(1).getText()).equals(0)
        }? TARGET
    ;
monadic_operator
    :   a=adverb
    |   o=monadic_base
    ;
dyadic_operator
    :   o=dyadic_base
    |   c=conjunction
    ;

monadic_base
    :   {   // Operator is monadic if its arity is 1
            function_arity.get(input.LT(1).getText()) != null &&
            function_arity.get(input.LT(1).getText()).equals(1)
        }? TARGET
    |   m_symbols
    ;
dyadic_base
    :   {   // Operator is dyadic if its arity is 2
            function_arity.get(input.LT(1).getText()) != null &&
            function_arity.get(input.LT(1).getText()).equals(2)
        }? TARGET
    |   d_symbols
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

d_symbols : '∊' | '↓' | '<' | '≤' | '=' | '≥' | '>' | '≠' | '∨' | '∧'
| '⍱' | '⍲' | '⊥' | '⊤' | '?' | '⌈' | '⌊' | '↑' | '⍴' | '|' | '⍳' | '*'
| '-' | '+' | '×' | '÷' | ',' | '○' | '⍟' | '⌽' | '⊖' | '⍉' | '!' ;
m_symbols : '~' | '⍋' | '⍒' | '?' | '⌈' | '⌊' | '↑' | '⍴' | '|'
| '⍳' | '*' | '-' | '+' | '×' | '÷' | ',' | '○' | '⍟' | '⌽' | '⊖'
| '⍉' | '!';

d_adverbs : '/' | '⌿' | '⍀' | '\\' -> BACKSLASH ;

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
TARGET  : ('A' .. 'Z' | 'a' .. 'z')('A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )* ;
COMMENT : '⍝' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;};
WS      : ( ' ' | '\t' | '\r' | '\n' ) {$channel=HIDDEN;};
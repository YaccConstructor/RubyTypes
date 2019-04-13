grammar RubyTypes;

annotation      : BEGIN typeDeclaration EOF ;
additional      : BEGIN type EOF ;
typeDeclaration : identifier COLON type ;
type            : LPAREN type RPAREN    # nestedType
                | identifier            # identifierType
                | tuple ARROW type      # functionalType
                | tuple                 # tupleType
                | array                 # arrayType
                | type OR type          # unionType ;

typesList : type (COMMA type)* ;

tuple   : LPAREN typesList RPAREN ;
array   : LBRACE typesList RBRACE ;

identifier : ATOM (DCOLON ATOM)* ;

ARROW      : '->'   ;
LPAREN     : '('    ;
RPAREN     : ')'    ;
LBRACE     : '['    ;
RBRACE     : ']'    ;
COMMA      : ','    ;
UNDERSCORE : '_'    ;
OR         : '|'    ;
COLON      : ':'    ;
SEMICOLON  : ';'    ;
DCOLON     : '::'   ;
BEGIN      : '##t ' ;

WHITESPACE : [\t ]+ -> skip;
NEWLINE   : '\r' '\n' | '\n' | '\r' ;

fragment LOWERCASE : [a-z] ;
fragment UPPERCASE : [A-Z] ;
fragment DIGIT     : [0-9] ;

ATOM        : FIRSTSYMBOL (ANYSYMBOL)* ;
FIRSTSYMBOL : 'a'..'z' | 'A'..'Z' ;
ANYSYMBOL   : FIRSTSYMBOL | UNDERSCORE | DIGIT ;

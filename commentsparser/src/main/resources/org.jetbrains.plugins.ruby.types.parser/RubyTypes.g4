grammar RubyTypes;

annotation      : BEGIN typeDeclaration EOF ;
additional      : BEGIN type EOF ;
typeDeclaration : identifier COLON type ;
type            : ftuple ARROW type     # functionalType
                | tuple                 # tupleType
                | LPAREN type RPAREN    # nestedType
                | array                 # arrayType
                | identifier            # identifierType
                | type OR type          # unionType ;


tuple   : LPAREN (type COMMA)+ RPAREN ;
ftuple  : LPAREN farg (COMMA farg)* RPAREN ;
farg    : STAR? type QMARK? ;
array   : LBRACE type (COMMA type)* RBRACE ;

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
STAR       : '*'    ;
QMARK      : '?'    ;

WHITESPACE : [\t ]+ -> channel(HIDDEN) ;
NEWLINE   : '\r' '\n' | '\n' | '\r' ;

fragment LOWERCASE : [a-z] ;
fragment UPPERCASE : [A-Z] ;
fragment DIGIT     : [0-9] ;

ATOM        : FIRSTSYMBOL (ANYSYMBOL)* ;
FIRSTSYMBOL : 'a'..'z' | 'A'..'Z' ;
ANYSYMBOL   : FIRSTSYMBOL | UNDERSCORE | DIGIT ;

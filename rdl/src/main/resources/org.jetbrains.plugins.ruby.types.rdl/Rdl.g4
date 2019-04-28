grammar Rdl;

annotation            : methodTypeDeclaration | varTypeDeclaration ;
methodTypeDeclaration : METHOD_TYPE_DECL (receiver COMMA)? (externalMethod COMMA)? QUOTE type QUOTE ;
varTypeDeclaration    : VAR_TYPE_DECL AT ATOM COMMA QUOTE type QUOTE ;

receiver: identifier;
externalMethod : COLON identifier;

// todo named types, attributes access
type            : type OR type          # unionType
                | ftuple ARROW type     # functionalType
                | tuple                 # tupleType
                | LPAREN type RPAREN    # nestedType
                | array                 # arrayType
                | identifier            # identifierType;


ftuple  : LPAREN farg (COMMA farg)* RPAREN ;
tuple   : LPAREN type (COMMA type)* RPAREN ;
farg    : STAR? QMARK? type ;
array   : ARRAY LANGLE type RANGLE ;

METHOD_TYPE_DECL : 'type'                ;
VAR_TYPE_DECL    : 'var_type'            ;
ARRAY            : 'Array'               ;

identifier : ATOM (DCOLON ATOM)* ;

AT         : '@'    ;
ARROW      : '->'   ;
LPAREN     : '('    ;
RPAREN     : ')'    ;
LBRACE     : '['    ;
RBRACE     : ']'    ;
LANGLE     : '<'    ;
RANGLE     : '>'    ;
COMMA      : ','    ;
UNDERSCORE : '_'    ;
OR         : 'or'   ;
COLON      : ':'    ;
SEMICOLON  : ';'    ;
DCOLON     : '::'   ;
BEGIN      : '##t ' ;
STAR       : '*'    ;
QMARK      : '?'    ;
QUOTE      : '\''   ;

WHITESPACE : [\t ]+ -> channel(HIDDEN) ;
NEWLINE   : '\r' '\n' | '\n' | '\r' ;

fragment LOWERCASE : [a-z] ;
fragment UPPERCASE : [A-Z] ;
fragment DIGIT     : [0-9] ;

ATOM        : (ANYSYMBOL)+ ;
FIRSTSYMBOL : 'a'..'z' | 'A'..'Z' ;
ANYSYMBOL   : FIRSTSYMBOL | UNDERSCORE | DIGIT | LBRACE | RBRACE ;
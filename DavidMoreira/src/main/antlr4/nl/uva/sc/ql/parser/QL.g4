grammar QL;


ql
    : form
    ;


form
    : 'form' IDENTIFIER block
    ;

block
	: '{' list_statments '}'					#blockScope
	;

list_statments
    : statment*
    ;


statment
    : declaration
    | assignment
    | if_stat
    ;

declaration
    : String IDENTIFIER ':' type				#declarationVariable
    ;
    
assignment
	: declaration EQUAL expression				#declareAssignVariable
	;

type
	: 'boolean'		#booleanType
	| 'String'		#stringType
	| 'money'		#moneyType
	;

if_stat
    : 'if' condition_block ('else' 'if' condition_block)* ('else' stat_block)?
    ;


condition_block
    : expression stat_block
    ;


stat_block
    : block
    | statment
    ;


expression
    : '(' expression ')'    								#parentisisExpression
    | NOT expression                                  		#notExpression
    | MINUS expression										#minusExpression
    | expression op=(MULT | DIV | MOD) expression      		#multDivModExpression
 	| expression op=(PLUS | MINUS) expression          		#additiveExpression
    | expression op=(LTEQ | GTEQ | LT | GT) expression      #relationalExpression
    | expression op=(EQ | NEQ) expression                   #equalityExpression
    | expression AND expression                             #andExpression
    | expression OR expression                              #orExpression
    | unity                                     			#unityExpression
    ;


unity
    : (TRUE | FALSE)    #booleanUnity
    | IDENTIFIER        #identifierUnity
    | String			#stringUnity
    | money				#moneyUnity
    ;


money
	: INT+ '.' INT+
	| INT+
	;


OR : '||';
AND : '&&';
EQ : '==';
NEQ : '!=';
GT : '>';
LT : '<';
GTEQ : '>=';
LTEQ : '<=';
NOT : '!';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
MOD : '%';
EQUAL : '=';

TRUE : 'true';
FALSE : 'false';


IDENTIFIER: ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

INT: ('0'..'9')+;


String: '"' .*? '"';

COMMENT
    : ( '//' ~[\r\n]* '\r'? '\n'
    | '/*' .*? '*/' ) -> channel(HIDDEN)
    ;

// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

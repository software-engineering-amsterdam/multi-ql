grammar QL;

ql
    : form
    ;


form
    : 'form' IDENTIFIER block
    ;

block
	: '{' statement* '}'
	;

statement
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
	: 'boolean'
	| 'string'
	| 'int'
	;

if_stat
    : 'if' condition_block ('else' 'if' condition_block)* ('else' stat_block)?
    ;


condition_block
    : expression stat_block
    ;


stat_block
    : block					#blockStat_Block
    | statement				#statStat_Block
    ;


expression
    : '(' expression ')'    										#parentisisExpression
    | '!' expression                                  				#notExpression
    | '-' INT														#minusExpression
    | expression op=(MULT | DIV) expression      					#multDivExpression
 	| expression op=(PLUS | MINUS) expression          				#additiveExpression
    | expression op=(LTEQ | GTEQ | LT | GT | EQ | NEQ) expression   #relationalExpression
    | expression '&&' expression                            		#andExpression
    | expression '||' expression                            		#orExpression
    | unity                                     					#unityExpression
    ;


unity
    : ('true' | 'false')    #booleanUnity
    | IDENTIFIER        	#identifierUnity
    | String				#stringUnity
    | INT					#integerUnity
    ;


EQ : '==';
NEQ : '!=';
GT : '>';
LT : '<';
GTEQ : '>=';
LTEQ : '<=';
PLUS : '+';
MINUS : '-';
MULT : '*';
DIV : '/';
EQUAL : '=';

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

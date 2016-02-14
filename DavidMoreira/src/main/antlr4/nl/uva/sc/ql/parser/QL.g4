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
	: declaration '=' expr						#assignmentVariable
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
    : expr stat_block
    ;


stat_block
    : block
    | statment
    ;


expr
    : '(' expr ')'    							#parentisisExpression
    | NOT expr                                  #notExpression
    | expr op=(MULT | DIV | MOD) expr      		#multDivModExpression
 	| expr op=(PLUS | MINUS) expr          		#additiveExpression
    | expr op=(LTEQ | GTEQ | LT | GT) expr      #relationalExpression
    | expr op=(EQ | NEQ) expr                   #equalityExpression
    | expr AND expr                             #andExpression
    | expr OR expr                              #orExpression
    | unity                                     #unityExpression
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

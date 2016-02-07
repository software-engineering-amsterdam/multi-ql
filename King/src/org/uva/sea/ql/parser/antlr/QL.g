grammar QL;
//C:\Users\Kinson\Desktop\UVA\Masters Software Engineering\Software Construction\King
//java -jar lib\antlr-4.5.2-complete.jar src\org\uva\sea\ql\parser\antlr\QL.g

@parser::header
{
package org.uva.sea.ql.parser.antlr;
import org.uva.sea.ql.ast.*;
}

@lexer::header
{
package org.uva.sea.ql.parser.antlr;
}

/************************************
			LEXER RULES
************************************/

DIGIT			: [0-9] ;

SMALLER_THAN	: '<' ;
GREATER_THAN	: '>' ;
SMALLER_EQUAL	: '<=' ;
GREATER_EQUAL	: '>=' ;
EQUAL			: '==' ;
NOT_EQUAL		: '!=' ;

AND			: '&&' ;
OR			: '||' ;
NOT			: '!' ;

ASSIGN		: '=' ;
MINUS		: '-' ;
ADD			: '+' ;
MULTIPLY	: '*' ;
DIVIDE		: '/' ;


BOOLEAN		: 'boolean' ;
STRING		: '"' .*? '"';
INT			: DIGIT+ | 'integer' ;
MONEY		: 'money' ;
DATE		: 'date' ;

ID			: [a-z] [a-zA-Z0-9]* ;
FORM 		: 'form';

IF 			: 'if';
ELSE 		: 'else';

COLON 		: ':';

LEFT_CURLY_BRACKET 	: '{';
RIGHT_CURLY_BRACKET : '}';

LEFT_PARENTHESES 	: '(';
RIGHT_PARENTHESES 	: ')';
	
COMMENT 	: '//' .+? ('\n'|EOF) -> skip ;
WS			: [ \t\r\u000C\n]+ -> skip ;
NEW_LINE	: '\r'? '\n';

/************************************
			PARSER RULES
************************************/
form : FORM ID block ;
block: LEFT_CURLY_BRACKET ( question | condition )+ RIGHT_CURLY_BRACKET ;
question : label ID COLON question_response_type  (ASSIGN expression+ )? ;
label : STRING (DIGIT+)? ('?'|':')? ;
question_response_type : INT|STRING|BOOLEAN|MONEY;
expression 
	: DIGIT+
	| ID
	| expression (MULTIPLY | DIVIDE) expression 
	| expression (ADD | MINUS) expression 
	| NOT expression 
	| expression (AND | OR) expression 
	| expression (SMALLER_THAN | GREATER_THAN | SMALLER_EQUAL | GREATER_EQUAL | NOT_EQUAL) expression 
	;
	
condition 
	: IF expression+ block	
		(
			(ELSE block)
			|
			(ELSE condition)+
		)? #ifCondition
	;	


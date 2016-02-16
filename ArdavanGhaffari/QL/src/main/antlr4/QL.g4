grammar QL;

form : 'form' IDENTIFIER box ;

box : '{' statement* '}' ;

statement
 : 'if' '(' expression ')' box								#ifStatement
 | 'if' '(' expression ')' ifBox = box 'else' elseBox = box	#ifElseStatement
 | IDENTIFIER ':' STRINGLITERAL type						#question
 | IDENTIFIER ':' STRINGLITERAL type '(' expression ')'		#computedQuestion
 ;

type : 'integer'|'string'|'boolean'|'money' ;

expression
 : NOT expression																							#notExpression
 | leftOp = expression operation = (MUL | DIV | PLUS | MINUS) rightOp = expression												#mathExpression
 | leftOp = expression operation = (EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL) rightOp = expression					#compareExpression
 | leftOp = expression operation = (AND | OR) rightOp = expression																			#boolExpression
 | '(' expression ')'																						#parExpression
 | literal																									#literalExpression
 ;

literal
 : IDENTIFIER		#identifierLiteral
 | INTEGER			#integerLiteral
 | BOOLEAN			#booleanLiteral
 | STRINGLITERAL	#stringLiteral
 ;

NOT : '!' ; 
MUL : '*' ;
DIV : '/' ;
PLUS : '+' ;
MINUS : '-' ;
EQUAL : '==';
NOT_EQUAL : '!=';
GREATER : '>';
GREATER_EQUAL : '>='; 
LESS : '<';
LESS_EQUAL : '<=';
AND : '&&';
OR : '||';

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
INTEGER: [0-9]+;
BOOLEAN : ('true'|'false');
STRINGLITERAL: '"' .*? '"';
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' .*? '/n' -> skip;
WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> skip;
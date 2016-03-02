grammar QL;

form : 'form' IDENTIFIER box ;

box : '{' statement* '}' ;

statement
 : 'if' '(' expression ')' box								#ifStatement
 | 'if' '(' expression ')' ifBox = box 'else' elseBox = box	#ifElseStatement
 | IDENTIFIER ':' STRINGLITERAL type						#question
 | IDENTIFIER ':' STRINGLITERAL type '(' expression ')'		#computedQuestion
 ;

expression
 : '!' expression									#Negation
 | leftOp = expression '+' rightOp = expression		#Addition 
 | leftOp = expression '-' rightOp = expression		#Subtraction
 | leftOp = expression '*' rightOp = expression		#Multiplication
 | leftOp = expression '/' rightOp = expression		#Division
 | leftOp = expression '==' rightOp = expression	#Equal
 | leftOp = expression '!=' rightOp = expression	#NotEqual
 | leftOp = expression '>' rightOp = expression		#GreaterThan
 | leftOp = expression '>=' rightOp = expression	#GreaterThanEqual
 | leftOp = expression '<' rightOp = expression		#LessThan
 | leftOp = expression '<=' rightOp = expression	#LessThanEqual
 | leftOp = expression '&&' rightOp = expression	#Conjunction
 | leftOp = expression '||' rightOp = expression	#Disjunction
 | '(' expression ')'								#Parenthesis
 | literal											#LiteralExpression
 ;
 
literal
 : BOOLEAN			#booleanLiteral
 | INTEGER			#integerLiteral
 | IDENTIFIER		#identifierLiteral
 | STRINGLITERAL	#stringLiteral
 | DECIMALLITERAL	#decimalLiteral
 ; 														
 
type : 'integer'|'string'|'boolean'|'money' ; 

BOOLEAN : ('true'|'false');
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
INTEGER: [0-9]+;
STRINGLITERAL: '"' .*? '"';
DECIMALLITERAL: [0-9]+'.'[0-9][0-9];
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' .*? '/n' -> skip;
WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> skip;
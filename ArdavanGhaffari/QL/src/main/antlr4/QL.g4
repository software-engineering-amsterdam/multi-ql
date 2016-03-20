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
 : '(' expression ')'								#parenthesis
 | literal											#literalExpression
 | '!' expression									#negation
 | leftOp = expression operation = ('*' | '/') rightOp = expression		#multiplyDivision
 | leftOp = expression operation = ('+' | '-') rightOp = expression		#additionSubtraction
 | leftOp = expression operation = ('>' | '>=' | '<' | '<=') rightOp = expression	#comparison
 | leftOp = expression operation = ('==' | '!=') rightOp = expression	#equality
 | leftOp = expression operation = ('&&' | '||') rightOp = expression	#andOr
 ;
 
literal
 : BOOLEAN			#booleanLiteral
 | INTEGER			#integerLiteral
 | IDENTIFIER		#identifierLiteral
 | STRINGLITERAL		#stringLiteral
 | MONEYLITERAL		#moneyLiteral
 ; 														
 
type : 'integer'|'string'|'boolean'|'money' ; 

BOOLEAN : ('true'|'false');
IDENTIFIER: [a-zA-Z][a-zA-Z0-9]*;
INTEGER: [0-9]+;
STRINGLITERAL: '"' .*? '"';
MONEYLITERAL: [0-9]+'.'[0-9][0-9];
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' .*? '/n' -> skip;
WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> skip;
grammar MyGrammer;

form: ('form' LABEL que=queries) ;

queries: ( que=question | que=ifstmt )*;

question: (questionText questionLabel DELIMITER questionValue) ;

questionText: STRING ;

questionLabel: LABEL ;

questionValue: 	TYPE
				| TYPE '=' expr
				;

ifstmt: 'if' expr que=queries els=elsestmt? ;

elsestmt: 'else' que=queries ;

expr returns[JSExpr result]
	: a1=BOOLSTMT {$result = 'true' == $a1.text} #booleanLiteral
	| a2=LABEL {$result = $a2.text} #labelLiteral
	| a3=NUMBER {$result = parseInt($a3.text)} #numberLiteral
	| '(' a4=expr ')' {$result = new JSExpr($a4.result)} #parenthesisExpr
	| a5=expr a6=op a7=expr {$result = new JSOpExpr($a5.result, $a6.text, $a7.result)} #opExpr
	| nooperator a8=expr {$result = new JSNoOpExpr($a8.result)} #negationExpr
	;

BOOLSTMT: ('true' | 'false');

TYPE :	('decimal' | 'integer' | 'boolean' | 'string' | 'money' | 'currency' | 'date') ;

op: compop #compareOp
	| boolop #boolOp
	| mathop #mathOp
 ;


mathop: '+' #additionOp
		| '-' #substractionOp
		| '*' #multiplicationOp
		| '/' #divisionOp
		 ;

boolop: '&&' #andOp 
		| '||' #orOp
		;


nooperator: '!' #noOp
;

compop: '<' #ltOp
		| '<=' #ltEqOp
		| '>' #gtOp
		| '>=' #gtEqOp
		| '!=' #nEqOp
		| '==' #eqOp
		; 

DELIMITER: ':' ;

LABEL :	[A-Za-z][A-Za-z0-9]* ;

NEWLINE : [\r\n]+ -> skip ;

NUMBER     : [0-9]+ '.'? ','? [0-9]* ;

WHITESPACE : ( '\t' | ' ' )+ -> skip ;

BRACKETS: ('}' | '{') -> skip ;

STRING : '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"' ;
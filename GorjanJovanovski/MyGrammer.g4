grammar MyGrammer;

form: ('form' LABEL queries) ;

queries: '{' ( question | ifstmt )* '}' ;

question: (questionText questionLabel DELIMITER questionValue) ;

questionText: STRING ;

questionLabel: LABEL ;

questionValue: 	TYPE
				| TYPE '=' expr
				;

ifstmt: ('if' expr queries elsestmt) ;

elsestmt: ('else' queries)? ;

expr: BOOLSTMT
	| LABEL
	| NUMBER
	| '(' expr ')'
	| expr OP expr
	| NOOP expr
	;

BOOLSTMT: ('true' | 'false');

TYPE :	('decimal' | 'integer' | 'boolean' | 'string' | 'money' | 'currency' | 'date') ;

OP: ( COMPOP | BOOLOP | MATHOP ) ;

MATHOP: ('+' | '-' | '*' | '/') ;

BOOLOP: ('&&' | '||') ;

NOOP: '!';

COMPOP: ('<' | '<=' | '>' | '>=' | '!=' | '==') ;

DELIMITER: ':' ;

LABEL :	[A-Za-z][A-Za-z0-9]+ ;

NEWLINE : [\r\n]+ -> skip ;

NUMBER     : [0-9]+ '.'? ','? [0-9]* ;

WHITESPACE : ( '\t' | ' ' )+ -> skip ;

STRING : '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"' ;
grammar QL;

@parser::header {
    package org.uva.sea.ql.parser.antlr;
}

@lexer::header {
    package org.uva.sea.ql.parser.antlr;
}

form : 'form' IDENTIFIER block ;

block : '{' statement* '}' ;

statement
: STRING IDENTIFIER ':' type														// question
| STRING IDENTIFIER ':' type '=' '(' expression ')'									// answer
| 'if' '(' expression ')' ifBlock = block											// if statement
| 'if' '(' expression ')' ifBlock = block 'else' elseBlock = block					// if-else statement
;

expression
: '(' expression ')'																// parenthesis
| literal																			// literal
| '!' expression																	// negation
| left = expression operation = ('*' | '/') right = expression						// multiply / divide
| left = expression operation = ('+' | '-') right = expression						// add / subtract
| left = expression operation = ('>' | '=>' | '<' | '<=') right = expression		// compare
| left = expression operation = ('==' | '!=') right = expression					// qqual / not equal
| left = expression operation = ('&&' | '||') right = expression					// and / or
;

literal : BOOLEAN | INTEGER | STRING | MONEY | IDENTIFIER ;

type : 'boolean' | 'integer' | 'string' | 'money' ;

// Tokens
WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN) ;
COMMENT : ( '//' ~[\r\n]+ | '/*' .*? '*/') -> channel(HIDDEN) ;
BOOLEAN : ('true' | 'false') ;
IDENTIFIER : [a-z][a-zA-Z0-9]* ;
INTEGER : [0-9]+ ;
STRING : '"' .*? '"' ;
MONEY : [0-9]+ '.' [0-9][0-9] ;
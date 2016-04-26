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
: STRING IDENTIFIER ':' type															// Question
| STRING IDENTIFIER ':' type '=' '(' expression ')'										// Answer
| 'if' '(' expression ')' ifBlock = block												// IF statement
| 'if' '(' expression ')' ifBlock = block 'else' elseBlock = block						// IF-ELSE statement
;

expression
: '(' expression ')'																	// Parenthesis
| literal																				// Literal
| '!' expression																		// Negation
| left = expression operation = ('*' | '/') right = expression							// Multiply / Divide
| left = expression operation = ('+' | '-') right = expression							// Add / Subtract
| left = expression operation = ('>' | '=>' | '<' | '<=') right = expression			// Compare
| left = expression operation = ('==' | '!=') right = expression						// Equal / Not equal
| left = expression operation = ('&&' | '||') right = expression						// AND / OR
;

literal : BOOLEAN | INTEGER | STRING | MONEY | IDENTIFIER ;

type : 'boolean' | 'integer' | 'string' | 'money' ;

// Tokens
WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN) ;
COMMENT : ( '//' ~[\r\n]+ | '/*' .*? '*/') -> channel(HIDDEN) ;
BOOLEAN : ('true' | 'false') ;
IDENTIFIER : [a-zA-Z][a-zA-Z0-9]* ;
INTEGER : [0-9]+ ;
STRING : '"' .*? '"' ;
MONEY : [0-9]+ '.' [0-9][0-9] ;
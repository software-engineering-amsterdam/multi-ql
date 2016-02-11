grammar QL;
options { }

@parser::header
{
	package ql.parser;
	import ql.ast.*;
	
}

@lexer::header

{
	package ql.parser;
}

form : 'form' WS*? Ident WS*? block
	;

block :  '{' expr* '}'
	;

// todo expr
expr : question
	;

question	: WS*? Str WS*? Ident WS*? ':' WS*? question_type { }
	;



// Tokens
question_type :
	| STRING_TYPE
	| INTEGER_TYPE
	| MONEY_TYPE
	| BOOLEAN_TYPE
	;

BOOLEAN_TYPE : 'boolean';
MONEY_TYPE : 'money';
INTEGER_TYPE : 'integer';
STRING_TYPE : 'string';


WS  :	(' ' | '\t' | '\n' | '\r')
    ;

COMMENT 
     : '/*' .* '*/' { }
    ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Money: Int ',' Int;

Str: '"' .* '"';
IDENTIFIER : [a-zA-Z]+;

/* WS: [ \n\t\r]+ -> skip; */
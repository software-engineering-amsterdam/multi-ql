grammar QL;
options { }

@parser::header
{

	import ql.ast.*;
}

@lexer::header

{

}
 
form : 'form' Ident block
	;

block :  '{' expr* '}'
	;
stat: question
	;

// todo expr
expr : question
	;
 
/* question	: WS*? Str WS*? Ident WS*? ':' WS*? question_type WS*? { }
	;
*/
question: Str Ident ':' question_type 
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

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Money: Int ',' Int;

Str: '"' .* '"';

ID  :  ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

WS  :	(' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);

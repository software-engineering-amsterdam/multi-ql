grammar QL;

form
	: FORM block
	;

block
	: LEFT_BRACE statement* RIGHT_BRACE
	;

statement
	: ifStatement
	| question SEMICOL
	;

ifStatement
	: IF LEFT_PAREN expr RIGHT_PAREN block (ELSE block)?
	;

question
	: STRING_LITERAL IDENTIFIER type
	;

expr
	: LEFT_PAREN expr RIGHT_PAREN
    | literal
	| IDENTIFIER
	| ( PLUS | MINUS ) expr
	| expr ( STAR | DIV ) expr
	| expr ( PLUS | MINUS ) expr
	| expr ( EQ | NOT_EQ | GT | GT_EQ | LT | LT_EQ) expr
	;

literal
	: BOOLEAN_LITERAL
	| STRING_LITERAL
	| INTEGER_LITERAL
	| FLOAT_LITERAL
	| MONEY_LITERAL
	;

type
	: TYPE_BOOLEAN
	| TYPE_STRING
	| TYPE_INTEGER
	| TYPE_FLOAT
	| TYPE_MONEY
	;

FORM : 'Form';
IF : 'if';
ELSE : 'else';

TYPE_BOOLEAN : 'boolean';
TYPE_STRING : 'string';
TYPE_INTEGER : 'integer';
TYPE_FLOAT : 'float';
TYPE_MONEY : 'money';

LEFT_BRACE : '{';
RIGHT_BRACE : '}';
LEFT_PAREN : '(';
RIGHT_PAREN : ')';
SEMICOL : ';';

EQ : '==';
NOT_EQ : '!=';
GT : '>';
GT_EQ : '>=';
LT : '<';
LT_EQ : '<=';

PLUS : '+';
MINUS : '-';
STAR : '*';
DIV : '/';

BOOLEAN_LITERAL
	: 'true'
	| 'false'
	;
STRING_LITERAL
	: '"' ~["]* '"'
	;
INTEGER_LITERAL
	: DIGIT+
	;
FLOAT_LITERAL
	: INTEGER_LITERAL '.' INTEGER_LITERAL
	;
MONEY_LITERAL
	: INTEGER_LITERAL ',' MONEY_LITERAL_CENTS
	;
MONEY_LITERAL_CENTS
	: DIGIT DIGIT
	| '-'
	;

IDENTIFIER
	: [a-zA-Z_]+
	;

WHITESPACE
	: [ \t\r\n]+ -> skip
	;

fragment DIGIT : [0-9];
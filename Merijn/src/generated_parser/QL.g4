grammar QL;

form
	: FORM LEFT_BRACE statements? RIGHT_BRACE
	;

statements
	: statement+
	;

statement
	: if_statement
	| question_statement SEMICOL
	;

if_statement
	: IF LEFT_PAREN condition RIGHT_PAREN LEFT_BRACE statements? RIGHT_BRACE else_statement?
	;

else_statement
	: ELSE LEFT_BRACE statements? RIGHT_BRACE
	;

condition
	: expr comp expr
	;

expr
	: IDENTIFIER
	| literal
	;

literal
	: BOOLEAN_LITERAL
	| STRING_LITERAL
	| INTEGER_LITERAL
	| FLOAT_LITERAL
	| MONEY_LITERAL
	;

comp
	: EQ
	| NOT_EQ
	| GT
	| GT_EQ
	| LT
	| LT_EQ
	;

question_statement
	: STRING_LITERAL IDENTIFIER type
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
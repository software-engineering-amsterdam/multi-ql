grammar QL;

form
	: FORM block
	;

block
	: LEFT_BRACE statement* RIGHT_BRACE
	;

statement
	: if_                   # ifStatementCase
	| question SEMICOL      # questionStatementCase
	;

if_
	: IF LEFT_PAREN expr RIGHT_PAREN block (ELSE block)?
	;

question
	: STRING_LITERAL IDENTIFIER type
	;

expr
	: LEFT_PAREN expr RIGHT_PAREN                           # parenExprCase
    | literal                                               # literalExprCase
	| IDENTIFIER                                            # identifierExprCase
	| ( PLUS | MINUS ) expr                                 # unaryPrefixExprCase
	| expr ( STAR | DIV ) expr                              # infixExprCase
	| expr ( PLUS | MINUS ) expr                            # infixExprCase
	| expr ( EQ | NOT_EQ | GT | GT_EQ | LT | LT_EQ) expr    # infixExprCase
	;

literal
	: booleanLiteral    # booleanLiteralCase
	| STRING_LITERAL    # stringLiteralCase
	| INTEGER_LITERAL   # integerLiteralCase
	| FLOAT_LITERAL     # floatLiteralCase
	| MONEY_LITERAL     # moneyLiteralCase
	;

booleanLiteral
	: BOOLEAN_TRUE      # booleanLiteralTrueCase
	| BOOLEAN_FALSE     # booleanLiteralFalseCase
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

BOOLEAN_TRUE : 'true';
BOOLEAN_FALSE : 'false';

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
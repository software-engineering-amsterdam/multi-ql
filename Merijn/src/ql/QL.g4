grammar QL;

form
	: FORM STRING_LITERAL block
	;

block
	: LEFT_BRACE statement* RIGHT_BRACE
	;

statement
	: if_                   # ifStatementCase
	| question SEMICOL      # questionStatementCase
	;

if_
	: IF LEFT_PAREN expr RIGHT_PAREN block              # ifCase
	| IF LEFT_PAREN expr RIGHT_PAREN block ELSE block   # ifElseCase
	;

question
	: STRING_LITERAL IDENTIFIER type                # questionCase
	| STRING_LITERAL IDENTIFIER type ASSIGN expr    # exprQuestionCase
	;

expr
	: LEFT_PAREN expr RIGHT_PAREN                           # parenExprCase
    | literal                                               # literalExprCase
	| IDENTIFIER                                            # identifierExprCase
	| ( NOT | MINUS ) expr                                  # unaryPrefixExprCase
	| expr ( MUL | DIV ) expr                               # infixExprCase
	| expr ( PLUS | MINUS ) expr                            # infixExprCase
	| expr ( EQ | NOT_EQ | GT | GT_EQ | LT | LT_EQ) expr    # infixExprCase
	| expr AND expr                                         # infixExprCase
	| expr OR expr                                          # infixExprCase
	;

literal
	: BOOLEAN_LITERAL   # booleanLiteralCase
	| STRING_LITERAL    # stringLiteralCase
	| INTEGER_LITERAL   # integerLiteralCase
	| FLOAT_LITERAL     # floatLiteralCase
	| MONEY_LITERAL     # moneyLiteralCase
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
MUL : '*';
DIV : '/';

ASSIGN : '=';

AND : '&&';
OR : '||';
NOT : '!';

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
BOOLEAN_LITERAL : 'true' | 'false';

IDENTIFIER
	: [a-zA-Z_]+
	;

WHITESPACE
	: [ \t\r\n]+ -> skip
	;

fragment DIGIT : [0-9];
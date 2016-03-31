grammar QL;
// RUN USING: java org.antlr.v4.Tool QL.g4 -visitor
@parser::header {
	package nl.nicasso.ql.antlr;
}

@lexer::header {
	package nl.nicasso.ql.antlr;
}

options { 
	language=Java;
}

/************************************
			PARSER RULES
************************************/
    
form
  : 'form' identifier = IDENTIFIER block
  ;

block
  : '{' statement* '}'
  ;

statement
  : label = STRING identifier = IDENTIFIER ':' type = questionType #questionStatement
  | label = STRING identifier = IDENTIFIER ':' type = questionType '=' '(' expr = expression ')' #computedQuestionStatement
  | 'if' '(' expr = expression ')' ifBody = block #ifStatement
  | 'if' '(' expr = expression ')' ifBody = block 'else' elseBody = block #ifElseStatement
  ;

expression
  : '!' expr = expression #notExpression
  | '(' expr = expression ')' #parenthesisExpression
  | left = expression op = ('*'|'/') right = expression #multiplicativeExpressions
  | left = expression op = ('+'|'-') right = expression #additiveExpressions
  | left = expression op = ('>'|'>='|'<'|'<=') right = expression #relationalExpressions
  | left = expression op = ('=='|'!=') right = expression #equalityExpressions
  | left = expression op = ('&&'|'||') right = expression #conditionalExpressions
  | literalValue = literal #literalExpression
  | identifier = IDENTIFIER #identifierExpression
  ;

literal
  : MONEY #moneyLiteral 
  | INTEGER #integerLiteral
  | BOOLEAN #booleanliteral
  | STRING #stringLiteral
  ;

questionType 
  : 'integer' #integerType
  | 'string' #stringType
  | 'boolean' #booleanType
  | 'money' #moneyType
  ;

/************************************
			LEXER RULES
************************************/


WHITESPACE : (' '|'\t'|'\n'|'\r')+ -> channel(HIDDEN);
COMMENT : '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT : '//' ~[\r\n]+ -> channel(HIDDEN);
BOOLEAN : ('true'|'false');
IDENTIFIER: [a-z][a-zA-Z0-9]+;
INTEGER: [0-9]+;
MONEY: [0-9]+ '.' [0-9][0-9];
STRING: '"' .*? '"';
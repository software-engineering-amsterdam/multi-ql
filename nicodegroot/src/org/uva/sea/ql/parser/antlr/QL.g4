grammar QL;
// RUN USING: java org.antlr.v4.Tool QL.g4 -visitor
@parser::header {
	package org.uva.sea.ql.parser.antlr;
}

@lexer::header {
	package org.uva.sea.ql.parser.antlr;
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
  : '!' expr = expression #notExpr
  | left = expression op = '*' right = expression #mulExpression
  | left = expression op = '/' right = expression #divExpression
  | left = expression op = '+' right = expression #addExpression
  | left = expression op = '-' right = expression #subExpression
  | left = expression op = '==' right = expression #eqExpression
  | left = expression op = '!=' right = expression #noteqExpression
  | left = expression op = '>' right = expression #greatExpression
  | left = expression op = '>=' right = expression #greatEqExpression
  | left = expression op = '<' right = expression #lessExpression
  | left = expression op = '<=' right = expression #lessEqExpression
  | left = expression op = '&&' right = expression #andExpression
  | left = expression op = '||' right = expression #orExpression
  | '(' expr = expression ')' #parenExpression
  | lit = literal #litExpression
  ;

literal
  : IDENTIFIER #identifierLiteral
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
LINE_COMMENT : '//' .*? '/n' -> channel(HIDDEN);
BOOLEAN : ('true'|'false');
IDENTIFIER: [a-z][a-zA-Z0-9]+;
INTEGER: [0-9]+;
STRING: '"' .*? '"';
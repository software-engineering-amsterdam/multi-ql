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
  : label = STRING identifier = IDENTIFIER ':' type = questionType #questionExpr
  | label = STRING identifier = IDENTIFIER ':' type = questionType '=' '(' expr = expression ')' #computedQuestionExpr
  | 'if' '(' expression ')' ifBody = block #ifStatementExpr
  | 'if' '(' expression ')' ifBody = block 'else' elseBody = block #ifElseStatementExpr
  ;

expression
  : '!' expr = expression #notExpr
  | left = expression op = ('*'|'/') right = expression #mathLowExpr
  | left = expression op = ('+'|'-') right = expression #mathHighExpr
  | left = expression op = ('&&'|'||') right = expression #boolExpr
  | left = expression op = ('=='|'!='|'>'|'>='|'<'|'<=') right = expression #relExpr
  | '(' expr = expression ')' #parenExpr
  | lit = literal #litExpr
  ;

literal
  : IDENTIFIER #litIdExpr
  | INTEGER #litIntExpr
  | BOOLEAN #litBoolExpr
  | STRING #litStringExpr
  ;
    
questionType 
  : 'integer' #typeIntExpr
  | 'string' #typeStrExpr
  | 'boolean' #typeBoolExpr
  | 'money' #typeMonExpr
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
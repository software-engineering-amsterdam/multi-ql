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
  : FORM IDENTIFIER block
  ;

block
  : LEFT_CURLY_BRACKET statement* RIGHT_CURLY_BRACKET
  ;

statement
  : question
  | computedQuestion
  | ifStatement
  ;

ifStatement
  : IF LEFT_PARENTHESES expression RIGHT_PARENTHESES ifBody = block
  | IF LEFT_PARENTHESES expression RIGHT_PARENTHESES ifBody = block ELSE elseBody = block
  ;
  
computedQuestion
  : label = questionLabel identifier = questionIdentifier COLON type = QUESTION_TYPE IS LEFT_PARENTHESES expr = expression RIGHT_PARENTHESES
  ;

question
  : label = questionLabel identifier = questionIdentifier COLON type = QUESTION_TYPE
  ;

questionIdentifier
  : IDENTIFIER
  ;
  
questionLabel
  : STRING
  ;

expression
  : NOT expression
  | left = expression MATHEMATICAL_OPERATOR_HIGH right = expression
  | left = expression MATHEMATICAL_OPERATOR_LOW right = expression
  | left = expression BOOLEAN_OPERATOR right = expression
  | left = expression RELATION_OPERATOR right = expression
  | LEFT_PARENTHESES expression RIGHT_PARENTHESES
  | literal
  ;

literal
  : IDENTIFIER
  | INTEGER
  | BOOLEAN
  | STRING
  ;
    
/************************************
			LEXER RULES
************************************/

FORM : 'form';

IF : 'if';
ELSE : 'else';

QUESTION_TYPE : 'integer'|'string'|'boolean'|'money'; 

COLON : ':';

LEFT_CURLY_BRACKET : '{';
RIGHT_CURLY_BRACKET : '}';

LEFT_PARENTHESES : '(';
RIGHT_PARENTHESES : ')';

NOT : '!';
IS : '=';

MATHEMATICAL_OPERATOR_HIGH : '*'|'/';
MATHEMATICAL_OPERATOR_LOW : '+'|'-';

BOOLEAN_OPERATOR : '&&'|'||';

RELATION_OPERATOR : '=='|'!='|'>'|'>='|'<'|'<=';

WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);
COMMENT : '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT : '//' .*? '/n' -> channel(HIDDEN);
BOOLEAN : ('true'|'TRUE'|'false'|'FALSE');
IDENTIFIER: [a-z][a-zA-Z0-9]+;
INTEGER: [0-9]+;
STRING: '"' .*? '"';
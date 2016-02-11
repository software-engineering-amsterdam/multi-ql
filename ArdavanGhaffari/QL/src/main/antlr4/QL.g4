grammar QL;

form : FORM IDENTIFIER block ;

block : '{' statement* '}' ;

statement
 : question
 | ifstatement
 ;
 
 question
  : questionidentifier ':' questiontext questiontype 
  | questionidentifier ':' questiontext questiontype '(' expression ')' 
  ;

 ifstatement
 : 'if' '(' expression ')' block (ELSE block)?
 ;
 
questionidentifier : IDENTIFIER ;
 
questiontext : STRINGLITERAL ;

questiontype : 'integer'|'string'|'boolean'|'money' ;

expression
 : NOT expression
 | expression (MUL | DIV) expression
 | expression (PLUS | MINUS) expression
 | expression (EQUAL | NOT_EQUAL | GREATER | GREATER_EQUAL | LESS | LESS_EQUAL | AND | OR) expression
 | '(' expression ')'
 | literal
 ;

literal
 : IDENTIFIER
 | INTEGER
 | BOOLEAN
 | STRINGLITERAL
 ;
 
FORM : 'form' ;
IF : 'if' ;
ELSE : 'else' ; 

NOT : '!' ; 
MUL : '*' ;
DIV : '/' ;
PLUS : '+' ;
MINUS : '-' ;
EQUAL : '==';
NOT_EQUAL : '!=';
GREATER : '>';
GREATER_EQUAL : '>='; 
LESS : '<';
LESS_EQUAL : '<=';
AND : '&&';
OR : '||';

WHITESPACE : (' ' | '\t' | '\n' | '\r')+ -> skip;
COMMENT : '/*' .*? '*/' -> skip;
LINE_COMMENT : '//' .*? '/n' -> skip;
BOOLEAN : ('true'|'false');
IDENTIFIER: [a-z][a-zA-Z0-9]+;
INTEGER: [0-9]+;
STRINGLITERAL: '"' .*? '"';
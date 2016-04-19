grammar QL;
//options {backtrack=true; memoize=true;}

@parser::header
{
package org.uva.sea.ql.parser.antlr;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;
}

@lexer::header
{
package org.uva.sea.ql.parser.antlr;
}

form : form IDENTIFIER block ;

block : '{' statement* '}' ;

statement
: IDENTIFIER ':' STRING type
| IDENTIFIER ':' STRING type '(' expression ')'
| 'if' '(' expression ')'
| 'if' '(' expression ')' '{' block '}' 'else' '{' block '}'
;

expression :  ;

type : 'boolean' | 'integer' | 'string' | 'money' ;

// Tokens
WS :	(' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);
COMMENT :   ( '//' ~[\r\n]+ | '/*' .*? '*/') -> channel(HIDDEN);
BOOLEAN : ('true' | 'false');
IDENTIFIER : [a-z][a-zA-Z0-9]+;
INTEGER : [0-9]+;
STRING : '"' .*? '"';
MONEY : [0-9]+ '.' [0-9][0-9];
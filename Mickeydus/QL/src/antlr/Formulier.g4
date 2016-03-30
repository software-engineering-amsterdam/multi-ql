grammar Formulier;

@parser::header
{
package antlr;
import AST.expressions.*;
import AST.types.*;
import ql.*;
}

@lexer::header
{
package org.uva.sea.ql.parser.antlr;
}

form returns [Form result]
	: FORM formName '{' b = block '}' EOF {
		$result = new Form($formName.text, $b.result);		
	};

formName : Ident;


//block returns [Block result]
//	@init {
//		$result = new Block();	
//	}
//	: (ifStatement[$result] | elseStatement[$result] | question[$result])+;


///* Statement Grammar Rules */

//// statement returns [Block result]
////	: ifStatement[$result]
////	| elseStatement[$result]
////	| question[$result]
////	;
	
//ifStatement [Expr result]
//	: IF '(' orExpr ')' '{' block '}' {
//		$result.add(new IfStatement($block.result, $orExpr.result));
//	}
//	;
	
//elseStatement [Block result]
//	: ELSE '{' b = block '}' {
//		$result.add(new ElseStatement($b.result));	
//	}
//	;
	
question [Block result]
	: variable ':' label t = type ('(' orExpr ')')+ {
		$result.add(new Question($variable.text, $label.text, $t.result, $orExpr.result));}
	| variable ':' label t = type {
		$result.add(new Question($variable.text, $label.text, $t.result, null));}
	;







primary returns [Expr result]
  : Int   { $result = new Int(Integer.parseInt($Int.text)); }
  | Ident { $result = new Ident($Ident.text); }
  | Str   { $result = new Str($Str.text); }
  | bool  { $result = $bool.result; }
  | '(' x=orExpr ')'{ $result = $x.result; }
  ;

unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  p=primary    { $result = $p.result; }
    ;    

bool returns [Expr result]
  : t='true'  { $result = new Bool(true); }
  | t='false' { $result = new Bool(false); }
  ;



mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new Div($result, $rhs.result);      
      }
    })*
    ;
    
  
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, $rhs.result);      
      }
    })*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LT($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GT($result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Eq($result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($result, $rhs.result);
      }
    })*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, $rhs.result); } )*
    ;
    

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, $rhs.result); } )*
    ;

    
// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> skip;
    

//COMMENT 
//     : '/*' .* '*/' -> skip;
    

//Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

//Int: ('0'..'9')+;

//Str: '"' .* '"';


DIGIT		: [0-9] ;

SMALLERTHAN	: '<' ;
BIGGERTHAN	: '>' ;
SMALLER_EQUAL	: '<=' ;
BIGGER_EQUAL	: '>=' ;
EQUAL		: '==' ;
NOT_EQUAL	: '!=' ;

AND		: '&&' ;
OR		: '||' ;
NOT		: '!' ;

ASSIGN		: '=' ;
MINUS		: '-' ;
ADD             : '+' ;
MULTIPLY	: '*' ;
DIVIDE		: '/' ;
BOOLEAN		: 'true' | 'false';  
STRING		: '"' .*? '"'; 
INT	        : DIGIT+ | 'integer' ;

FLOAT
	: DIGIT+ [.,] DIGIT*
	| DIGIT* [.,] DIGIT+
	;
MONEY		: 'money' ;
DATE		: 'date' ;

ID	: [a-z] [a-zA-Z0-9]* ;

COMMA   :   ',';

COMMENT 
        : '/*'.*'*/' /*single comment*/
        | '//'~('\r' | '\n')*; /* multiple comment*/

NEW_LINE	: '\r'? '\n';

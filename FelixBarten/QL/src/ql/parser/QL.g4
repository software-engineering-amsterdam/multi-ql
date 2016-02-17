grammar QL;
options { }

@parser::header
{

	import ql.ast.*;
	import ql.ast.expression.*;
	
	
}

@lexer::header

{

}
  
form returns [Form result]
	: 'form' Ident block
	;

block :  '{' statement* '}'
	;
	
statement: question
	| ifstatement
	| ifelsestatement
	;

// TODO expr
expr : question
	;
 
/* question	: WS*? Str WS*? Ident WS*? ':' WS*? question_type WS*? { }
	;
*/
question_type returns [QuestionType result] 
	: STRING_TYPE
	| INTEGER_TYPE
	| MONEY_TYPE
	| BOOLEAN_TYPE
	;
	
question returns [Question result]
	: q_text=Str identity=Ident ':' qt=question_type { $result = new Question($q_text, $identity, $qt.result); }
	;

ifstatement returns [IfStatement result] 
	:	'if' '(' cond=conditions ')' bloc=block { }
	;
	
ifelsestatement returns [IfElseStatement result]
	: 'if' '(' cond=conditions ')' bloc=block 'else' block {}
	;



conditions returns [Expr result]
	: relExpr conditions*
	| andExpr conditions*
	| orExpr conditions*
	;

unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
 //   |  x=primary    { $result = $x.result; }
    ;
    
primary: ;
    
    
mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, rhs);
      }
      if ($op.text.equals("<=")) {
        $result = new Div($result, rhs);      
      } 
    })*
    ;
    
  
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($result, rhs);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($result, rhs);      
      }
    })*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LT($result, rhs);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($result, rhs);      
      }
      if ($op.text.equals(">")) {
        $result = new GT($result, rhs);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($result, rhs);      
      }
      if ($op.text.equals("==")) {
        $result = new Eq($result, rhs);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($result, rhs);
      }
    })*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, rhs); } )*
    ;
    

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, rhs); } )*
    ;


// Tokens

BOOLEAN_TYPE : 'boolean';
MONEY_TYPE : 'money';
INTEGER_TYPE : 'integer';
STRING_TYPE : 'string';

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Money: Int ',' Int;

Str: '"' .* '"';

ID  :  ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

WS  :	(' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);

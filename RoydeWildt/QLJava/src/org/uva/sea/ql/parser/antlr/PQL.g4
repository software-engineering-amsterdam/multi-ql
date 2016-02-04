grammar QL;
options {backtrack=true; memoize=true;}

@parser::header
{
import org.uva.sea.ql.ast.expr.*;
}

primary returns [int result]
    :   x=Int {$result = Integer.parseInt($x.text);}
    ;

unExpr returns [int result]
    :   x=primary    { $result = $x.result; }
    ;    
    
mulExpr returns [int result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result *= $rhs.result;
      }
      if ($op.text.equals("/")) {
        $result /= $rhs.result;
      }
    })*
    ;
    
  
addExpr returns [int result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result += $rhs.result;
      }
      if ($op.text.equals("-")) {
        $result -= $rhs.result;
      }
    })*
    ;

prog : x=addExpr {System.out.println($x.result);}
    ;
    
// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

COMMENT 
     : '/*' .* '*/' -> channel(HIDDEN)
    ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .* '"';

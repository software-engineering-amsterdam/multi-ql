grammar QL;

@parser::header
{
    package ql.antlr;
    import ql.ast.*;
    import ql.ast.expression.*;
    import ql.ast.type.*;
    import ql.ast.form.*;
    import ql.ast.literal.*;
}

@lexer::header
{
    package ql.antlr;
}

form returns [Form result]
: 'form' Ident { $result = new Form(new Ident($Ident.text)); }
;

primary returns [Expr result]
  : Int   { $result = new IntegerLiteral(Integer.parseInt($Int.text)); }
  | Ident { $result = new Ident($Ident.text); }
  | Str   { $result = new StringLiteral($Str.text); }
  | bool  { $result = $bool.result; }
  | '(' x=orExpr ')'{ $result = $x.result; }
  ;

bool returns [Expr result]
  : t='true'  { $result = new BoolLiteral(true); }
  | t='false' { $result = new BoolLiteral(false); }
  ; 
    
unaryExpr returns [Expr result]
    :  '+' x=unaryExpr { $result = new Pos($x.result); }
    |  '-' x=unaryExpr { $result = new Neg($x.result); }
    |  '!' x=unaryExpr { $result = new Not($x.result); }
    |  p=primary    { $result = $p.result; }
    ;   

mulExpr returns [Expr result]
    :   lhs=unaryExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unaryExpr 
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
WS  :	(' ' | '\t' | '\n' | '\r') -> skip
    ;

COMMENT : ('/*' .* '*/') -> skip
    ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Int: ('0'..'9')+;

Str: '"' .* '"';
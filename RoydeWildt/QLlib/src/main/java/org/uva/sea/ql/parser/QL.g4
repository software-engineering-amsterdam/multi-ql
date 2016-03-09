grammar QL;

@parser::header
{
import java.util.List;

import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.val.*;
import org.uva.sea.ql.ast.tree.form.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Text;
import org.uva.sea.ql.ast.tree.type.Type;
}

@parser::members
{

}

/*
 * Parser
 */

//Form Grammar

form returns [Form result]
    : 'form' i=Ident '{' s=stats '}' {$result = new Form($i.getLine(), $i.text, $s.result);}
    ;


//Stat Grammar
varDecl returns [Var result]
    : i=Ident {$result = new Var($i.getLine(), $i.text);}
    ;

varAss returns [Expr result]
    : '=' '(' x=orExpr ')' {$result = $x.result;}
    ;

question returns [Stat result]
    : l=Str v=varDecl ':'  t=type  {$result = new Question($l.getLine(), $l.text, $v.result, $t.result);}
    | l=Str v=varDecl ':'  t=type e=varAss {$result = new Question($l.getLine(), $l.text, $v.result, $t.result, $e.result);}
    ;

stat returns [Stat result]
    : q=question {$result = $q.result;}
    | 'if' '(' c=orExpr ')' '{' s=stats '}' {$result = new If($c.start.getLine(), $c.result, $s.result);}
    | 'if' '(' c=orExpr ')' '{' i=stats '}' 'else' '{' e=stats '}'{$result = new IfElse($c.start.getLine(), $c.result, $i.result, $e.result);}
    ;

stats returns [List<Stat> result]
    @init{$result = new ArrayList<Stat>();}
    : (stat {$result.add($stat.result);} )+
    ;


//Expression Grammar
primary returns [Expr result]
    :   x1=num   {$result = new Primary($x1.start.getLine(), $x1.result);}
    |   x2=id    {$result = new Primary($x2.start.getLine(), $x2.result);}
    |   x3=str   {$result = new Primary($x3.start.getLine(), $x3.result);}
    |   x4=bool  {$result = new Primary($x4.start.getLine(), $x4.result);}
    ;

unExpr returns [Expr result]
    :  '+' value=unExpr { $result = new Pos($value.start.getLine(), $value.result); }
    |  '-' value=unExpr { $result = new Neg($value.start.getLine(), $value.result); }
    |  '!' value=unExpr { $result = new Not($value.start.getLine(), $value.result); }
    |  y=primary    { $result = $y.result; }
    ;
    
mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Div($lhs.start.getLine(), $result, $rhs.result);
      }
    })*
    ;

addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($lhs.start.getLine(), $result, $rhs.result);
      }
    })*
    ;


relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LT($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals(">")) {
        $result = new GT($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals("==")) {
        $result = new Eq($lhs.start.getLine(), $result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($lhs.start.getLine(), $result, $rhs.result);
      }
    })*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr
        { $result = new And($lhs.start.getLine(), $result, $rhs.result); } )*
    ;
    

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr
        { $result = new Or($lhs.start.getLine(), $result, $rhs.result); } )*
    ;


//ValueType Grammar

type returns [Type result]
    : x=Boolean  {$result = new Boolean($x.getLine());}
    | x=Money    {$result = new Money($x.getLine());}
    | x=Text     {$result = new Text($x.getLine());}
    ;

bool returns [Val result]
    : value=True  {$result = new Bool($value.getLine(), $value.text);}
    | value=False {$result = new Bool($value.getLine(), $value.text);}
    ;

num returns [Val result]
    : value=Int {$result = new Int($value.getLine(), $value.text); }
    ;

str returns [Val result]
    : value=Str {$result = new Str($value.getLine(), $value.text); }
    ;

id returns [Val result]
    : value=Ident {$result = new Var($value.getLine(), $value.text); }
    ;
    
/*
 * Tokens
 */
WHITESPACE  :	( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> channel(HIDDEN);
COMMENT     : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> channel(HIDDEN) ;

True        : 'true';
False       : 'false';

Boolean     : 'boolean';
Money       : 'money';
Text        : 'text';

Ident       :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Str         : '"' ~('\n'|'\r')*? '"';
Int         : ('0'..'9')+;

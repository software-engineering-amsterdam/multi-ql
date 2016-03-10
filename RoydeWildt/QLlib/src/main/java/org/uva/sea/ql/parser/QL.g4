grammar QL;

@parser::header
{
import java.util.List;

import org.uva.sea.ql.ast.tree.form.*;
import org.uva.sea.ql.ast.tree.stat.*;
import org.uva.sea.ql.ast.tree.expr.Expr;
import org.uva.sea.ql.ast.tree.expr.binary.*;
import org.uva.sea.ql.ast.tree.expr.unary.*;
import org.uva.sea.ql.ast.tree.type.Boolean;
import org.uva.sea.ql.ast.tree.type.Money;
import org.uva.sea.ql.ast.tree.type.Number;
import org.uva.sea.ql.ast.tree.type.Text;
import org.uva.sea.ql.ast.tree.type.Type;
import org.uva.sea.ql.ast.tree.atom.var.*;
import org.uva.sea.ql.ast.tree.atom.val.*;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Float;
import org.uva.sea.ql.ast.tree.atom.val.numeric.Int;
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
    : '=' x=expr {$result = $x.result;}
    ;

question returns [Stat result]
    : l=Str v=varDecl ':'  t=type  {$result = new Question($l.getLine(), $l.text, $v.result, $t.result);}
    | l=Str v=varDecl ':'  t=type e=varAss {$result = new Question($l.getLine(), $l.text, $v.result, $t.result, $e.result);}
    ;

stat returns [Stat result]
    : q=question {$result = $q.result;}
    | 'if' '(' c=expr ')' '{' s=stats '}' {$result = new If($c.start.getLine(), $c.result, $s.result);}
    | 'if' '(' c=expr ')' '{' i=stats '}' 'else' '{' e=stats '}'{$result = new IfElse($c.start.getLine(), $c.result, $i.result, $e.result);}
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

expr returns [Expr result]
    :  op=('+' | '-') value=expr
        {
          if ($op.text.equals("+")) {
            $result = new Pos($value.start.getLine(), $value.result);
          }
          if ($op.text.equals("-")) {
            $result = new Neg($value.start.getLine(), $value.result);
          }
        }
    |   lhs=expr op=( '*' | '/' ) rhs=expr
        {
          if ($op.text.equals("*")) {
            $result = new Mul($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals("/")) {
            $result = new Div($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
        }
    |   lhs=expr op=('+' | '-') rhs=expr
        {
          if ($op.text.equals("+")) {
            $result = new Add($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals("-")) {
            $result = new Sub($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
        }
    |   lhs=expr op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=expr
        {
          if ($op.text.equals("<")) {
            $result = new LT($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals("<=")) {
            $result = new LEq($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals(">")) {
            $result = new GT($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals(">=")) {
            $result = new GEq($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals("==")) {
            $result = new Eq($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
          if ($op.text.equals("!=")) {
            $result = new NEq($lhs.start.getLine(), $lhs.result, $rhs.result);
          }
        }
    |   lhs=expr '&&' rhs=expr
        {
            $result = new And($lhs.start.getLine(), $lhs.result, $rhs.result);
        }
    |   lhs=expr '||' rhs=expr
        {
            $result = new Or($lhs.start.getLine(), $lhs.result, $rhs.result);
        }
    |  '!' value=expr
        {
            $result = new Not($value.start.getLine(), $value.result);
        }
    |  '(' value=expr ')'
        {
            $result = $value.result;
        }
    |  y=primary
        {
            $result = $y.result;
        }
    ;


//ValueType Grammar

type returns [Type result]
    : x=Boolean  {$result = new Boolean($x.getLine());}
    | x=Money    {$result = new Money($x.getLine());}
    | x=Number   {$result = new Number($x.getLine());}
    | x=Text     {$result = new Text($x.getLine());}
    ;

bool returns [Val result]
    : value=True  {$result = new Bool($value.getLine(), $value.text);}
    | value=False {$result = new Bool($value.getLine(), $value.text);}
    ;

num returns [Val result]
    : value=Int    {$result = new Int($value.getLine(), $value.text); }
    | value=Double {$result = new Float($value.getLine(), $value.text); }
    ;

str returns [Val result]
    : value=Str {$result = new Str($value.getLine(), $value.text); }
    ;

id returns [Var result]
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
Number      : 'number';
Text        : 'text';

Ident       :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Str         : '"' ~('\n'|'\r')*? '"';
Int         : '0'..'9'+;
Double      : '0'..'9'+'.''0'..'9'+;

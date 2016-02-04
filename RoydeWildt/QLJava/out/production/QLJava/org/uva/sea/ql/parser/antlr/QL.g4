grammar QL;

@parser::header
{
import java.util.List;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.val.*;
import org.uva.sea.ql.ast.form.*;
}

/*
 * Parser
 */

//Form Grammar

form returns [Form result]
    : 'form' i=Ident '{' s=stats '}' {$result = new Form($i.text, $s.result);}
    ;

forms returns [List<Form> result]
    @init{$result = new ArrayList<Form>();}
    : (form {$result.add($form.result);} )+
    ;


//Stat Grammar

varDecl returns [Expr result]
    : ('=' '(' x=orExpr ')') {$result = $x.result;}
    ;

question returns [Stat result]
    : l=Str v=Ident ':'  t=type {$result = new Question($l.text, $v.text, $t.text);}
    | l=Str v=Ident ':'  t=type e=varDecl {$result = new Question($l.text, $v.text, $t.text, $e.result);}
    ;

stat returns [Stat result]
    : q=question {$result = $q.result;}
    | 'if' '(' c=orExpr ')' '{' s=stats '}' {$result = new If($c.result, $s.result);}
    | 'if' '(' c=orExpr ')' '{' i=stats '}' 'else' '{' e=stats '}' {$result = new IfElse($c.result, $i.result, $e.result);}
    ;

stats returns [List<Stat> result]
    @init{$result = new ArrayList<Stat>();}
    : (stat {$result.add($stat.result);} )+
    ;


//Expression Grammar
primary returns [Expr result]
    :   x=Int   {$result = new Prim(new Int($x.text));}
    |   x=Ident {$result = new Prim(new Var($x.text));}
    |   y=bool  {$result = new Prim($y.result);}
    ;

unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  y=primary    { $result = $y.result; }
    ;
    
mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("/")) {
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


//Type Grammar

type returns [Stat result]
    : 'boolean' | 'money'
    ;

bool returns [Val result]
    : x=True  {$result = new Bool($x.text);}
    | x=False {$result = new Bool($x.text);}
    ;
    
/*
 * Tokens
 */
WHITESPACE  :	( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> channel(HIDDEN);
COMMENT     : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> channel(HIDDEN) ;

True        : 'true';
False       : 'false';

Ident       :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

Str         : '"' ~('\n'|'\r')*? '"';
Int         : ('0'..'9')+;

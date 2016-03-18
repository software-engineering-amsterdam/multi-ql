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
    import org.uva.sea.ql.ast.tree.atom.val.Double;
    import org.uva.sea.ql.ast.tree.atom.val.Int;
}

/*
 * Parser
 */

//Form Grammar

form returns [Form result]
    : 'form' i=IDENT '{' s=stats '}'
        {
            $result = new Form($i, $i.text, $s.result);
        }
    ;


//Stat Grammar
stats returns [List<Stat> result]
    @init
        {
            $result = new ArrayList<Stat>();
        }
    : (s=stat
        {
            $result.add($s.result);
        }
      )+
    ;

stat returns [Stat result]
    : q=question
        {
            $result = $q.result;
        }
    | 'if' '(' c=expr ')' '{' s=stats '}'
        {
            $result = new If($c.start, $c.result, $s.result);
        }
    | 'if' '(' c=expr ')' '{' i=stats '}' 'else' '{' e=stats '}'
        {
            $result = new IfElse($c.start, $c.result, $i.result, $e.result);
        }
    ;

question returns [Stat result]
    : label=STR decl=IDENT ':' typ=type
        {
            $result = new Question($label, $label.text, new Var($decl, $decl.text), $typ.result);
        }
    | label=STR decl=IDENT ':' typ=type '=' exp=expr
        {
            $result = new Question($label, $label.text, new Var($decl, $decl.text), $typ.result, $exp.result);
        }
    ;

//Expression Grammar
expr returns [Expr result]
    :  op=('+' | '-') value=expr
        {
          if ($op.text.equals("+")) {
            $result = new Pos($value.start, $value.result);
          }
          if ($op.text.equals("-")) {
            $result = new Neg($value.start, $value.result);
          }
        }
    |   lhs=expr op=( '*' | '/' ) rhs=expr
        {
          if ($op.text.equals("*")) {
            $result = new Mul($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals("/")) {
            $result = new Div($lhs.start, $lhs.result, $rhs.result);
          }
        }
    |   lhs=expr op=('+' | '-') rhs=expr
        {
          if ($op.text.equals("+")) {
            $result = new Add($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals("-")) {
            $result = new Sub($lhs.start, $lhs.result, $rhs.result);
          }
        }
    |   lhs=expr op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=expr
        {
          if ($op.text.equals("<")) {
            $result = new LT($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals("<=")) {
            $result = new LEq($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals(">")) {
            $result = new GT($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals(">=")) {
            $result = new GEq($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals("==")) {
            $result = new Eq($lhs.start, $lhs.result, $rhs.result);
          }
          if ($op.text.equals("!=")) {
            $result = new NEq($lhs.start, $lhs.result, $rhs.result);
          }
        }
    |   lhs=expr '&&' rhs=expr
        {
            $result = new And($lhs.start, $lhs.result, $rhs.result);
        }
    |   lhs=expr '||' rhs=expr
        {
            $result = new Or($lhs.start, $lhs.result, $rhs.result);
        }
    |  '!' value=expr
        {
            $result = new Not($value.start, $value.result);
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

primary returns [Expr result]
    :   x1=num
        {
            $result = new Primary($x1.start, $x1.result);
        }
    |   x2=id
        {
            $result = new Primary($x2.start, $x2.result);
        }
    |   x3=str
        {
            $result = new Primary($x3.start, $x3.result);
        }
    |   x4=bool
        {
            $result = new Primary($x4.start, $x4.result);
        }
    ;

//ValueType Grammar

type returns [Type result]
    : x=BOOLEAN
        {
            $result = new Boolean($x);
        }
    | x=MONEY
        {
            $result = new Money($x);
        }
    | x=NUMBER
        {
            $result = new Number($x);
        }
    | x=TEXT
        {
            $result = new Text($x);
        }
    ;

bool returns [Val result]
    : value=TRUE
        {
            $result = new Bool($value, $value.text);
        }
    | value=FALSE
        {
            $result = new Bool($value, $value.text);
        }
    ;

num returns [Val result]
    : value=INT
        {
            $result = new Int($value, $value.text);
        }
    | value=FLOAT
        {
            $result = new Double($value, $value.text);
        }
    ;

str returns [Val result]
    : value=STR
        {
            $result = new Str($value, $value.text);
        }
    ;

id returns [Var result]
    : value=IDENT
        {
            $result = new Var($value, $value.text);
        }
    ;
    
/*
 * Tokens
 */
WHITESPACE  :	( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> channel(HIDDEN);
COMMENT     : ( '//' ~[\r\n]* '\r'? '\n' | '/*' .*? '*/') -> channel(HIDDEN) ;

TRUE        : 'true';
FALSE       : 'false';

BOOLEAN     : 'boolean';
MONEY       : 'money';
NUMBER      : 'number';
TEXT        : 'text';

IDENT       :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

STR         : '"' ~('\n'|'\r')*? '"';
INT         : '0'..'9'+;
FLOAT       : '0'..'9'+'.''0'..'9'+;

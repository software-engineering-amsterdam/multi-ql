grammar QL;

@parser::header
{
import java.util.Map;
import java.util.HashMap;
import org.uva.ql.ast.*;
import org.uva.ql.ast.expr.*;
import org.uva.ql.ast.stat.*;
import org.uva.ql.ast.type.*;
import org.uva.ql.ast.form.*;
}

@parser::members {
    private ASTSourceInfo src(ParserRuleContext context){
        return new ASTSourceInfo(context);
    }
}


file :  form EOF
     ;

form returns [QLForm result]
    :   'form' + ID + block[new BooleanLiteral(null, true)] { $result = new QLForm(src($ctx), $ID.text, $block.result); }
    ;
    
block[Expr condition] returns [QLBlock result]
    locals [
      List<QLQuestion> questions = new ArrayList<>();
      List<QLIFStatement> statements = new ArrayList<>();
    ]
    @after{
        $result = new QLBlock(src($ctx), $ctx.questions, $ctx.statements);
    }
    : '{' + (ifStat[$condition] { $ctx.statements.add($ifStat.result); } | question[$condition] { $ctx.questions.add($question.result); } )+ '}'
    
    ;
    
ifStat[Expr condition] returns [QLIFStatement result]
    : 'if' + '(' + orExpr + ')' + block[new And(null, condition, $orExpr.result)] 
    { 
        $result = new QLIFStatement(src($ctx), $orExpr.result, $block.result);
    }
    ;

question[Expr condition] returns [QLQuestion result]
    : variableType + ID + STR + orExpr
    {
        $result = new QLQuestionComputed(src($ctx), $variableType.result, $ID.text,  $STR.text, $condition, $orExpr.result);
    }
    | variableType + ID + STR 
    { 
        $result = new QLQuestionInput(src($ctx), $variableType.result, $ID.text, $STR.text, $condition);
    }
    ;
    
variableType returns [QLType result]
    : BOOLEAN   { $result = new QLBooleanType(src($ctx)); }
    | STRING    { $result = new QLStringType(src($ctx));  }
    | INTEGER   { $result = new QLIntegerType(src($ctx)); }
    ;
   
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add(src($ctx), $result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Subtract(src($ctx), $result, $rhs.result);      
      }
    })*
    ;

mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Multiply(src($ctx), $result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Divide(src($ctx), $result, $rhs.result);      
      }
    })*
    ;


unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Positive(src($ctx), $x.result); }
    |  '-' x=unExpr { $result = new Negative(src($ctx), $x.result); }
    |  '!' x=unExpr { $result = new Not(src($ctx), $x.result); }
    |  z=primary    { $result = $z.result; }
    ;    
    
primary returns [Expr result]
    : literal        { $result = $literal.result; }
    | ID             { $result = new VariableExpr(src($ctx), $ID.text); }
    | '(' orExpr ')' { $result = $orExpr.result; }
    ;
    
literal returns [Expr result]
    : INT   { $result = new IntegerLiteral(src($ctx), Integer.valueOf($INT.text)); }
    | STR   { $result = new StringLiteral(src($ctx), $STR.text); }
    | BOOL  { $result = new BooleanLiteral(src($ctx), Boolean.valueOf($BOOL.text)); }
    ;

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or(src($ctx), $result, $rhs.result); } )*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And(src($ctx), $result, $rhs.result); } )*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LessThan(src($ctx), $result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LessThanOrEquals(src($ctx), $result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GreaterThan(src($ctx), $result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GreaterThanOrEquals(src($ctx), $result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Equals(src($ctx), $result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new EqualsNot(src($ctx), $result, $rhs.result);
      }
    })*
    ;
    
// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT 
    :   '/*' .*? '*/' -> channel(HIDDEN);
    
LINE_COMMENT 
    :   '//' ~[\r\n]* -> channel(HIDDEN);
    
BOOLEAN :   'bool';
INTEGER :   'int';
STRING  :   'str';

BOOL :  'true' | 'false';
INT  :   ('0'..'9')+;
STR  :   '"' .*? '"';

ID   :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;


grammar QL;

@parser::header
{
import java.util.Map;
import java.util.HashMap;
import org.uva.ql.ast.*;
import org.uva.ql.ast.expr.*;
import org.uva.ql.ast.expr.math.*;
import org.uva.ql.ast.expr.rel.*;
import org.uva.ql.ast.stat.*;
import org.uva.ql.ast.type.*;
import org.uva.ql.ast.form.*;
import org.uva.ql.ast.literal.*;
}

file :  questionnaire EOF
     ;
     
questionnaire returns [QLQuestionnaire result]
    locals [
      List<QLForm> forms = new ArrayList<>();
    ]
    @after{
        $result = new QLQuestionnaire($ctx, $ctx.forms);
    }
    :   (form{ $ctx.forms.add($form.result); })+
    ;  

form returns [QLForm result]
    :   'form' + ID + block { $result = new QLForm($ctx, $ID.text, $block.result); }
    ;
    
block returns [QLBlock result]
    locals [
      List<QLQuestion> questions = new ArrayList<>();
      List<QLIFStatement> statements = new ArrayList<>();
    ]
    @after{
        $result = new QLBlock($ctx, $ctx.questions, $ctx.statements);
    }
    : '{' + (ifStat { $ctx.statements.add($ifStat.result); } | question { $ctx.questions.add($question.result); } )+ '}'
    
    ;
    
ifStat returns [QLIFStatement result]
    : 'if' + '(' + orExpr + ')' + block 
    { 
        $result = new QLIFStatement($ctx, $orExpr.result, $block.result);
    }
    ;

question returns [QLQuestion result]
    : variableType + ID + STR + orExpr
    {
        $result = new QLQuestionComputed($ctx, $variableType.result, $ID.text,  $STR.text, $orExpr.result);
    }
    | variableType + ID + STR 
    { 
        $result = new QLQuestionInput($ctx, $variableType.result, $ID.text, $STR.text);
    }
    ;
    
variableType returns [QLType result]
    : BOOLEAN   { $result = new QLBooleanType($ctx); }
    | STRING    { $result = new QLStringType($ctx);  }
    | INTEGER   { $result = new QLIntegerType($ctx); }
    ;
   
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Subtract($ctx, $result, $rhs.result);      
      }
    })*
    ;

mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Multiply($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Divide($ctx, $result, $rhs.result);      
      }
    })*
    ;


unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Positive($ctx, $x.result); }
    |  '-' x=unExpr { $result = new Negative($ctx, $x.result); }
    |  '!' x=unExpr { $result = new Not($ctx, $x.result); }
    |  z=primary    { $result = $z.result; }
    ;    
    
primary returns [Expr result]
    : literal        { $result = $literal.result; }
    | ID             { $result = new VariableExpr($ctx, $ID.text); }
    | '(' orExpr ')' { $result = $orExpr.result; }
    ;
    
literal returns [Expr result]
    : INT   { $result = new IntegerLiteral($ctx, Integer.valueOf($INT.text)); }
    | STR   { $result = new StringLiteral($ctx, $STR.text); }
    | BOOL  { $result = new BooleanLiteral($ctx, Boolean.valueOf($BOOL.text)); }
    ;

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($ctx, $result, $rhs.result); } )*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($ctx, $result, $rhs.result); } )*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new LessThan($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LessThanOrEquals($ctx, $result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GreaterThan($ctx, $result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GreaterThanOrEquals($ctx, $result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Equals($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new EqualsNot($ctx, $result, $rhs.result);
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


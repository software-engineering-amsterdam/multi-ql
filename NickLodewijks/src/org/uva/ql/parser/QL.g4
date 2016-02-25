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
     
questionnaire returns [Questionnaire result]
    locals [
      List<Form> forms = new ArrayList<>();
    ]
    @after{
        $result = new Questionnaire($ctx, $ctx.forms);
    }
    :   (form{ $ctx.forms.add($form.result); })+
    ;  

form returns [Form result]
    :   'form' + ID + block { $result = new Form($ctx, $ID.text, $block.result); }
    ;
    
block returns [Block result]
    locals [
      List<Question> questions = new ArrayList<>();
      List<IFStat> statements = new ArrayList<>();
    ]
    @after{
        $result = new Block($ctx, $ctx.questions, $ctx.statements);
    }
    : '{' + (ifStat { $ctx.statements.add($ifStat.result); } | question { $ctx.questions.add($question.result); } )+ '}'
    
    ;
    
ifStat returns [IFStat result]
    : 'if' + '(' + orExpr + ')' + block 
    { 
        $result = new IFStat($ctx, $orExpr.result, $block.result);
    }
    ;

question returns [Question result]
    : variableType + ID + STR + orExpr
    {
        $result = new ComputedQuestion($ctx, $variableType.result, $ID.text,  $STR.text, $orExpr.result);
    }
    | variableType + ID + STR 
    { 
        $result = new InputQuestion($ctx, $variableType.result, $ID.text, $STR.text);
    }
    ;
    
variableType returns [VariableType result]
    : BOOLEAN   { $result = new BooleanType($ctx); }
    | STRING    { $result = new StringType($ctx);  }
    | INTEGER   { $result = new IntegerType($ctx); }
    ;
   
addExpr returns [Expr result]
    :   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
    { 
      if ($op.text.equals("+")) {
        $result = new Add($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Sub($ctx, $result, $rhs.result);      
      }
    })*
    ;

mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Div($ctx, $result, $rhs.result);      
      }
    })*
    ;


unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($ctx, $x.result); }
    |  '-' x=unExpr { $result = new Neg($ctx, $x.result); }
    |  '!' x=unExpr { $result = new Not($ctx, $x.result); }
    |  z=primary    { $result = $z.result; }
    ;    
    
primary returns [Expr result]
    : literal        { $result = new LiteralExpr($ctx, $literal.result); }
    | ID             { $result = new VariableExpr($ctx, $ID.text); }
    | '(' orExpr ')' { $result = $orExpr.result; }
    ;
    
literal returns [Literal result]
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
        $result = new LT($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LEq($ctx, $result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GT($ctx, $result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GEq($ctx, $result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Eq($ctx, $result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NEq($ctx, $result, $rhs.result);
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


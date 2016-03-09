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

form returns [QLForm result]
    :   'form' + ID + block { $result = new QLForm(src($ctx), $ID.text, $block.result); }
    ;
    
block returns [QLBlock result]
    locals [
      List<QLQuestion> questions = new ArrayList<>();
      List<QLIFStatement> statements = new ArrayList<>();
    ]
    @after{
        $result = new QLBlock(src($ctx), $ctx.questions, $ctx.statements);
    }
    : '{' + (ifStat { $ctx.statements.add($ifStat.result); } | question { $ctx.questions.add($question.result); } )+ '}'
    
    ;
    
ifStat returns [QLIFStatement result]
    : 'if' + '(' + expr + ')' + block
    { 
        $result = new QLIFStatement(src($ctx), $expr.result, $block.result);
    }
    ;

question returns [QLQuestion result]
    : variableType + ID + STR + expr
    {
        $result = new QLQuestionComputed(src($ctx), $variableType.result, $ID.text,  $STR.text, $expr.result);
    }
    | variableType + ID + STR 
    { 
        $result = new QLQuestionInput(src($ctx), $variableType.result, $ID.text, $STR.text);
    }
    ;
    
variableType returns [QLType result]
    : BOOLEAN   { $result = new QLBooleanType(src($ctx)); }
    | STRING    { $result = new QLStringType(src($ctx));  }
    | INTEGER   { $result = new QLIntegerType(src($ctx)); }
    ;
    
expr returns [Expr result]
    : op=('+' | '-') exp=expr
    { 
      if ($op.text.equals("+")) {
        $result = new Positive(src($ctx), $exp.result);
      }
      if ($op.text.equals("-")) {
        $result = new Negative(src($ctx), $exp.result);
      }
    }
    | lhs=expr op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=expr 
    { 
      if ($op.text.equals("<")) {
        $result = new LessThan(src($ctx), $lhs.result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new LessThanOrEqual(src($ctx), $lhs.result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GreaterThan(src($ctx), $lhs.result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GreaterThanOrEqual(src($ctx), $lhs.result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Equals(src($ctx), $lhs.result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new EqualsNot(src($ctx), $lhs.result, $rhs.result);
      }
    }
    | lhs=expr op=('*' | '/') rhs=expr
    { 
      if ($op.text.equals("*")) {
        $result = new Multiply(src($ctx), $lhs.result, $rhs.result);
      }
      if ($op.text.equals("/")) {
        $result = new Divide(src($ctx), $lhs.result, $rhs.result);      
      }
    }
    | lhs=expr op=('+' | '-') rhs=expr
    { 
      if ($op.text.equals("+")) {
        $result = new Add(src($ctx), $lhs.result, $rhs.result);
      }
      if ($op.text.equals("-")) {
        $result = new Subtract(src($ctx), $lhs.result, $rhs.result);      
      }
    }
    | lhs=expr '&&' rhs=expr 
    { 
        $result = new And(src($ctx), $lhs.result, $rhs.result);
    }
    | lhs=expr '||' rhs=expr 
    { 
        $result = new Or(src($ctx), $lhs.result, $rhs.result);
    }
    | '!' exp=expr       { $result = new Not(src($ctx), $exp.result); }
    | '(' lhs=expr ')'   { $result = $lhs.result; }
    | literal            { $result = $literal.result; }
    | ID                 { $result = new VariableExpr(src($ctx), $ID.text); }
    ;
    
literal returns [Expr result]
    : INT   { $result = new IntegerLiteral(src($ctx), Integer.valueOf($INT.text)); }
    | STR   { $result = new StringLiteral(src($ctx), $STR.text); }
    | BOOL  { $result = new BooleanLiteral(src($ctx), Boolean.valueOf($BOOL.text)); }
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


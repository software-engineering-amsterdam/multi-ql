grammar QL;

@parser::header
{
import java.util.Map;
import java.util.HashMap;
import sc.ql.ast.*;
import sc.ql.ast.expr.*;
import sc.ql.ast.stat.*;
import sc.ql.ast.type.*;
import sc.ql.ast.form.*;
}

@parser::members {
    private <T extends ASTNode> T addSource(ParserRuleContext context, T node){
        node.setSourceInfo(new ASTSourceInfo(context));
        return (T) node;
    }
    
    private String unQuote(String text){
        return text.substring(1, text.length()-1);
    }
}

form returns [QLForm result]
    :   'form' + ID + block { $result = addSource($ctx, new QLForm($ID.text, $block.result)); }
    ;
    
block returns [QLBlock result]
    locals [
      List<QLQuestion> questions = new ArrayList<>();
      List<QLIFStatement> statements = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new QLBlock($ctx.questions, $ctx.statements));
    }
    : '{' + (ifStat { $ctx.statements.add($ifStat.result); } | question { $ctx.questions.add($question.result); } )+ '}'
    
    ;
    
ifStat returns [QLIFStatement result]
    : 'if' + '(' + expr + ')' + block
    { 
        $result = addSource($ctx, new QLIFStatement($expr.result, $block.result));
    }
    ;

question returns [QLQuestion result]
    : variableType + ID + STR + expr
    {
        $result = addSource($ctx, new QLQuestionComputed($variableType.result, $ID.text,  unQuote($STR.text), $expr.result));
    }
    | variableType + ID + STR 
    { 
        $result = addSource($ctx, new QLQuestionInput($variableType.result, $ID.text, unQuote($STR.text)));
    }
    ;
    
variableType returns [QLType result]
    : BOOLEAN   { $result = addSource($ctx, new QLBooleanType()); }
    | STRING    { $result = addSource($ctx, new QLStringType());  }
    | INTEGER   { $result = addSource($ctx, new QLIntegerType()); }
    ;
    
expr returns [Expr result]
    : op=('+' | '-') exp=expr
    { 
      if ($op.text.equals("+")) {
        $result = addSource($ctx, new Positive($exp.result));
      }
      if ($op.text.equals("-")) {
        $result = addSource($ctx, new Negative($exp.result));
      }
    }
    | lhs=expr op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=expr 
    { 
      if ($op.text.equals("<")) {
        $result = addSource($ctx, new LessThan($lhs.result, $rhs.result));
      }
      if ($op.text.equals("<=")) {
        $result = addSource($ctx, new LessThanOrEqual($lhs.result, $rhs.result));      
      }
      if ($op.text.equals(">")) {
        $result = addSource($ctx, new GreaterThan($lhs.result, $rhs.result));
      }
      if ($op.text.equals(">=")) {
        $result = addSource($ctx, new GreaterThanOrEqual($lhs.result, $rhs.result));      
      }
      if ($op.text.equals("==")) {
        $result = addSource($ctx, new Equals($lhs.result, $rhs.result));
      }
      if ($op.text.equals("!=")) {
        $result = addSource($ctx, new EqualsNot($lhs.result, $rhs.result));
      }
    }
    | lhs=expr op=('*' | '/') rhs=expr
    { 
      if ($op.text.equals("*")) {
        $result = addSource($ctx, new Multiply($lhs.result, $rhs.result));
      }
      if ($op.text.equals("/")) {
        $result = addSource($ctx, new Divide($lhs.result, $rhs.result));      
      }
    }
    | lhs=expr op=('+' | '-') rhs=expr
    { 
      if ($op.text.equals("+")) {
        $result = addSource($ctx, new Add($lhs.result, $rhs.result));
      }
      if ($op.text.equals("-")) {
        $result = addSource($ctx, new Subtract($lhs.result, $rhs.result));      
      }
    }
    | lhs=expr '&&' rhs=expr 
    { 
        $result = addSource($ctx, new And($lhs.result, $rhs.result));
    }
    | lhs=expr '||' rhs=expr 
    { 
        $result = addSource($ctx, new Or($lhs.result, $rhs.result));
    }
    | '!' exp=expr       { $result = addSource($ctx, new Not($exp.result)); }
    | '(' lhs=expr ')'   { $result = $lhs.result; }
    | literal            { $result = $literal.result; }
    | ID                 { $result = addSource($ctx, new VariableExpr($ID.text)); }
    ;
    
literal returns [Expr result]
    : INT   { $result = addSource($ctx, new IntegerLiteral(Integer.valueOf($INT.text))); }
    | STR   { $result = addSource($ctx, new StringLiteral(unQuote($STR.text))); }
    | BOOL  { $result = addSource($ctx, new BooleanLiteral(Boolean.valueOf($BOOL.text))); }
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


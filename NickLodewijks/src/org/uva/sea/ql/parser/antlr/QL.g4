grammar QL;

@parser::header
{
import java.util.Map;
import java.util.HashMap;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;
}

@lexer::header
{
}

file :  form* EOF;

form returns [Form result]
    :   'form' + ID + block { $result = new Form($ID.text, $block.result); }
    ;
    
block returns [Block result]
    locals [
        Map<String, Variable> varMap = new HashMap<String, Variable>()
    ]
    @init {
        $result = new Block();
    }
    : '{' + (ifStat[$result] | question[$result] )+ '}'
    ;
    
ifStat[Block arg]
    : 'if' + '(' + unExpr + ')' + block 
        { $arg.add(new IFStat($unExpr.result, $block.result));
        }
    ;

question[Block result]
    :   variable + STR 
        { 
            $result.add(new Question($variable.result, $STR.text));
            $block::varMap.put($variable.result.getName(), $variable.result);
        }
    ;
    
variable returns [Variable result]
    :  t=(BOOLEAN|STRING|INTEGER) + ID 
    {
        if($t.type == BOOLEAN){
            $result = new BooleanVariable($ID.text);
        } else if($t.type == STRING){
            $result = new StringVariable($ID.text);
        } else if($t.type == INTEGER){
            $result = new IntegerVariable($ID.text);
        }
    }
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

mulExpr returns [Expr result]
    :   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr 
    { 
      if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new Div($result, $rhs.result);      
      }
    })*
    ;

unExpr returns [Expr result]
    locals [
        Map<String, Variable> varMap = new HashMap<String, Variable>()
    ]
    @init{
        $varMap.putAll($block::varMap);
    }
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  z=primary    { $result = $z.result; }
    ;    
    
primary returns [Expr result]
    : ID 
    {   
        $result = $unExpr::varMap.get($ID.text);
        if ($result == null){
            System.err.println("undefined variable: "+$ID.text);
        } 
    }
    ;

orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, $rhs.result); } )*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, $rhs.result); } )*
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
    
// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT 
    :   '/*' .*? '*/' -> channel(HIDDEN);
    
LINE_COMMENT 
    :   '//' ~[\r\n]* -> channel(HIDDEN);
    
BOOLEAN :   'bool';
INTEGER :   'int';
STRING  :   'str';

ID  :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;


INT :   ('0'..'9')+;
STR :   '"' .*? '"';

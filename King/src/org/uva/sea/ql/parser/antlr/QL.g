grammar QL;
//C:\Users\Kinson\Desktop\UVA\Masters Software Engineering\Software Construction\King
//java -jar lib\antlr-4.5.2-complete.jar src\org\uva\sea\ql\parser\antlr\QL.g

@parser::header
{
package org.uva.sea.ql.parser.antlr;
import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.expr.math.*;
import org.uva.sea.ql.ast.expr.unary.*;
import org.uva.sea.ql.ast.expr.binary.*;
import org.uva.sea.ql.ast.expr.literal.*;
import org.uva.sea.ql.ast.domain.*;
import org.uva.sea.ql.ast.expr.type.*;
import java.util.Map;
import java.util.HashMap;
import org.joda.money.Money;
}

@lexer::header
{
package org.uva.sea.ql.parser.antlr;
}

file :  form* EOF
     ;
/************************************
			PARSER RULES
************************************/
form returns [Form result]
	: FORM ID block { $result = new Form($ID.text, $block.result); }
	;
	
block returns [Block result]
 locals [ 
 		  List<Question> questions = new ArrayList<Question>(),
 		  List<IFblock> ifstatementblock = new ArrayList<IFblock>()
 		]
	
	: '{' + ( condition | question )+ '}' {$result = new Block($questions,$ifstatementblock);} 
	;

condition returns [IFblock result]
    : 'if' + '(' + orExpr + ')' + block 
    { 
        $result = new IFblock($orExpr.result, $block.result);
        $block::ifstatementblock.add($result);
    }
    
    ;
    
question returns [Question result]
	: STR variable orExpr 
	{
		
		$result = new ReadOnlyQuestion($variable.result, $STR.text, $orExpr.result);
		$block::questions.add($result);
    }
    | STR variable
    {
    	
        $result = new Question($variable.result, $STR.text);
        $block::questions.add($result);
    }
	;

    
variable returns [VarDeclaration result]
    :  ID COLON question_response_type
    { 
    	
        $result = new VarDeclaration($question_response_type.result, new VarIdentifier($ID.text,$question_response_type.result));
    }
    ; 
question_response_type returns [Type result]
    : BOOLEAN { $result = new BooleanType();}
    | STRING  { $result = new StringType();}
    | INTEGER { $result = new IntegerType();}
    | MONEY   { $result = new MoneyType();}
    
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
      if ($op.text.equals("/")) {
        $result = new Div($result, $rhs.result);      
      }
    })*
    ;


unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Positive($x.result); }
    |  '-' x=unExpr { $result = new Negative($x.result); }
    |  '!' x=unExpr { $result = new NOT($x.result); }
    |  z=primary    { $result = $z.result; }
    ;    

primary returns [Expr result]
    : literal        { $result = $literal.result; }
    | ID     		 { $result = new VarExpr(new VarIdentifier($ID.text));}
    | '(' orExpr ')' { $result = $orExpr.result; }
    

    ;    

    
literal returns [Expr result]
    : DIGIT   { $result = new IntegerLiteral(Integer.valueOf($DIGIT.text)); }
    | MON   { $result = new MoneyLiteral(Money.parse($MON.text)); }
    | STR   { $result = new StringLiteral($STR.text); }
    | BOOL  { $result = new BooleanLiteral(Boolean.valueOf($BOOL.text)); }
    ;
orExpr returns [Expr result]
    :   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new OR($result, $rhs.result); } )*
    ;
    
andExpr returns [Expr result]
    :   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new AND($result, $rhs.result); } )*
    ;
  
relExpr returns [Expr result]
    :   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr 
    { 
      if ($op.text.equals("<")) {
        $result = new SmallerThan($result, $rhs.result);
      }
      if ($op.text.equals("<=")) {
        $result = new SmallerOrEqual($result, $rhs.result);      
      }
      if ($op.text.equals(">")) {
        $result = new GreaterThan($result, $rhs.result);
      }
      if ($op.text.equals(">=")) {
        $result = new GreaterOrEqual($result, $rhs.result);      
      }
      if ($op.text.equals("==")) {
        $result = new Equal($result, $rhs.result);
      }
      if ($op.text.equals("!=")) {
        $result = new NotEqual($result, $rhs.result);
      }
    })*
    ;
	
	
DIGIT		: ('0'..'9')+;
FLOAT		: DIGIT+ '.' DIGIT DIGIT ;
FORM		: 'form';
BOOLEAN		: 'boolean';
STRING		: 'string';
INTEGER		: 'integer' ;
MONEY		: 'money';
STR  		:   '"' .*? '"';
BOOL		: 'true'|'false' ;
INT			: DIGIT;
MON			: 'USD '+ FLOAT;

ID   		:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
COLON 		: ':';	
COMMENT 	: '//' .+? ('\n'|EOF) -> skip ;
WS			: [ \t\r\u000C\n]+ -> skip ;
NEW_LINE	: '\r'? '\n';


    


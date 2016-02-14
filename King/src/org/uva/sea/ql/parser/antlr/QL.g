grammar QL;
//C:\Users\Kinson\Desktop\UVA\Masters Software Engineering\Software Construction\King
//java -jar lib\antlr-4.5.2-complete.jar src\org\uva\sea\ql\parser\antlr\QL.g

@parser::header
{
package org.uva.sea.ql.parser.antlr;
import org.uva.sea.ql.ast.*;
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.TaxForm.*;
import java.util.Map;
import java.util.HashMap;
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
 locals [ Map<String, ValueType> varMap = new HashMap<String, ValueType>() ]
	@init {
        $result = new Block();
    }
	: '{' + ( condition { $result.add($condition.result); }  | question{ $result.add($question.result); }  )+ '}' 
	;


question returns [Question result]
	: STRING variable orExpr 
	{
		
		$result = new ReadOnlyQuestion($variable.result, $STRING.text, $orExpr.result);
    }
    | STRING variable
    {
    	
        $result = new NormalQuestion($variable.result, $STRING.text);
    }
	;

	
    
condition returns [IFblock result]
    : 'if' + '(' + orExpr + ')' + block 
    { 
        $result = new IFblock($orExpr.result, $block.result);
    }
    
    ;
    
question_response_type returns [VarType result]
    : t=( BOOLEAN | STRING | INT | MONEY ) 
    { 
        $result = new VarType($t.text);
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
    : identifier
    {  
     	$identifier.result.setType($block::varMap.get($identifier.result.getName()));
        $result = new VarExpr($identifier.result);
    }
    | '(' orExpr ')' { $result = $orExpr.result; }
    

    ;    
        
identifier returns [VarIdentifier result]
    : ID
    {   
        $result = new VarIdentifier($ID.text);
    }
    
    ;
    
variable returns [VarDeclaration result]
    :  identifier COLON question_response_type
    { 
    	
        $result = new VarDeclaration($question_response_type.result, $identifier.result);
        $block::varMap.put($identifier.result.getName(), $result.getType().getType());
        $identifier.result.setType($result.getType().getType());
    }
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
	
	
DIGIT			: [0-9] ;

SMALLER_THAN	: '<' ;
GREATER_THAN	: '>' ;
SMALLER_EQUAL	: '<=' ;
GREATER_EQUAL	: '>=' ;
EQUAL			: '==' ;
NOT_EQUAL		: '!=' ;

AND			: '&&' ;
OR			: '||' ;
NOT			: '!' ;

ASSIGN		: '=' ;
MINUS		: '-' ;
ADD			: '+' ;
MULTIPLY	: '*' ;
DIVIDE		: '/' ;
FORM		: 'form';

BOOLEAN		: 'boolean'|'true'|'false' ;
STRING		: '"' .*? '"';
INT			: DIGIT+ | 'integer' ;
MONEY		: 'money' ;
DATE		: 'date' ;

ID			: [a-zA-Z]+;




COLON 		: ':';

LEFT_CURLY_BRACKET 	: '{';
RIGHT_CURLY_BRACKET : '}';

LEFT_PARENTHESES 	: '(';
RIGHT_PARENTHESES 	: ')';
	
COMMENT 	: '//' .+? ('\n'|EOF) -> skip ;
WS			: [ \t\r\u000C\n]+ -> skip ;
NEW_LINE	: '\r'? '\n';


    


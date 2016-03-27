grammar Formulier;

@parser::header
{
package antlr;
import AST.expressions.*;
import AST.types.*;
import ql.*;
import AST.form.*;
import AST.literals.*;
}

@lexer::header
{
package org.uva.sea.ql.parser.antlr;
}

/* Form Grammar Rules = Entry Point */
form returns [Form result]
	: FORM formName forminhoud=block {
		$result = new Form($formName.text, $forminhoud.result);		
	};

formName : ID;

block returns [Block result]
        @init {
                List<Statement> statementList = new ArrayList<Statement>();
                List<Question> questionList = new ArrayList<Question>();
        }
        : '{' (question {questionList.add($question.result); 
                        }
        | statement {statementList.add($statement.result); 
                    }
              )* '}'  {
        $result = new Block(statementList, questionList);
        }
;
    
statement returns [Statement result]
        : ifstatement { 
        $result = $ifstatement.result; 
        } 
        | ifelsestatement { 
        $result = $ifelsestatement.result; 
        }
;

ifstatement returns [IfStatement result]
         : 'if' '(' condition=orExpr ')' thenstatement=block { 
        $result = new IfStatement($orExpr.result, $thenstatement.result); 
        } 
;

ifelsestatement returns [IfElseStatement result]
         : 'if' '(' condition=orExpr ')'  thenstatement=block 'else' elsestatement=block { 
        $result = new IfElseStatement($orExpr.result, $thenstatement.result, $elsestatement.result); 
         }
;

question returns [Question result]
        : normalquestion { 
        $result = $normalquestion.result; 
        } 
        | computedquestion { 
        $result = $computedquestion.result; 
        }
;

normalquestion returns [Question result]
         : questionid=ID ':' nqlabel=label type=questiontype {
         $result = new NormalQuestion(new Ident($questionid.text), new Label($nqlabel.text), $type.result); 
        }
;

computedquestion returns [Question result]
  : normalquestion '(' orExpr ')' { 
    $result = new ComputedQuestion($normalquestion.result.getId(), $normalquestion.result.getLabel(), $normalquestion.result.getType(), $orExpr.result);
    }
  ;


variable returns [Expr result]
	: ID {
        $result = new Ident($ID.text); 
        }
;
	
label returns [Label result] 
        : STR { 
        $result = new Label($STR.text); 
        }
  ;

questiontype returns [Type result]
        : BOOL_TYPE {
        $result = new Bool();
        }
        | MONEY_TYPE {
        $result = new Money();
        }
        | STRING_TYPE {
        $result = new Str();
        }
        | INTEGER_TYPE {
        $result = new Int();
        }
;

primary returns [Expr result]
        : DIGIT   { 
        $result = new IntLiteral(Integer.parseInt($DIGIT.text)); 
        $result.setLiteral();
        }
        | ID { 
        $result = new Ident($ID.text);
        }
        | STR   { 
        $result = new StrLiteral($STR.text); 
        $result.setLiteral();
        }
        | CURRENCYSYMBOL DIGIT { 
        $result = new MoneyLiteral(Integer.parseInt($DIGIT.text)); 
        $result.setLiteral();
        }
        | bool  { 
        $result = $bool.result; 
        $result.setLiteral();
        }
        | '(' x=orExpr ')'{ 
        $result = $x.result; 
        }
;

unExpr returns [Expr result]
        :  '+' x=unExpr { 
        $result = new Pos($x.result); 
        }
        |  '-' x=unExpr { 
        $result = new Neg($x.result); 
        }
        |  '!' x=unExpr { 
        $result = new Not($x.result); 
        }
        |  y=primary { 
        $result = $y.result; 
        }
;    

bool returns [Expr result]
        : booltrue='true'  { 
        $result = new BoolLiteral(true); 
        }
        | boolfalse='false' { 
        $result = new BoolLiteral(false); 
        }
  ;



mulExpr returns [Expr result]
        :   lhs=unExpr 
    {   $result=$lhs.result; } 
    ( op=( '*' | '/' ) rhs=unExpr 
    { 
        if ($op.text.equals("*")) {
        $result = new Mul($result, $rhs.result);
        }
        if ($op.text.equals("<=")) {
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


//Lexer rules

BOOL_TYPE       : 'boolean';
MONEY_TYPE      : 'money';
STRING_TYPE     : 'string';
INTEGER_TYPE    : 'integer';
 
// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> skip
    ;

DIGIT		: [0-9] ;

SMALLERTHAN	: '<' ;
BIGGERTHAN	: '>' ;
SMALLER_EQUAL	: '<=' ;
BIGGER_EQUAL	: '>=' ;
EQUAL		: '==' ;
NOT_EQUAL	: '!=' ;

AND		: '&&' ;
OR		: '||' ;
NOT		: '!' ;
IF              : 'if' ;
ELSE            : 'else' ;
FORM            : 'form' ;

ASSIGN		: '=' ;
MINUS		: '-' ;
ADD             : '+' ;
MULTIPLY	: '*' ;
DIVIDE		: '/' ;
BOOLEAN		: 'true' | 'false';  
STR		: '"' .*? '"'; 
INT             : ('0'..'9')+;
FLOAT           : INT;
CURRENCYSYMBOL  : '€';
MONEY		: DIGIT+;
DATE		: 'date' ;

ID	:  ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

COMMA   :   ',';

COMMENT : ('/*' .* '*/') -> skip
    ;

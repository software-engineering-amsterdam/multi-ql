grammar QL;


@parser::header
{
import org.uva.sea.ql.ast.expr.*;
import org.uva.sea.ql.ast.stat.*;
import org.uva.sea.ql.ast.form.*;
}

/* From Grammar Rules = Entry Point */
form
	: FORM formName '{' statement+ '}' EOF;

formName : Ident;


block 
: statement+
;

/* Statement Grammar Rules */

statement
	: ifStatement
	| elseStatement
	| question
	;
	
ifStatement 
	: IF '(' orExpr ')' '{' block '}'
	;
	
elseStatement 
	: ELSE '{' block '}'
	;
	
question 
	: variable ':' label type ('(' orExpr ')')*
	;

variable : Ident;
label : Str;

type
	: INT
	| STR
	| BOOL
	| MONEY
	;


/* Expression Grammar Rules */
unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  y=primary    { $result = $y.result; }
    ;  
    
primary returns [Expr result]
	: literal
	| variable
	;

literal 
	: booleanLiteral
	| integerLiteral
	| stringLiteral
	;

booleanLiteral returns [Expr result]
	: Bool {$result = new BooleanLiteral(Boolean.valueOf($Bool.getText()));}
	;

integerLiteral returns [Expr result]
	: Int {$result = new IntegerLiteral(Integer.valueOf($Int.getText()));}
	;

stringLiteral returns [Expr result]
	: Str {$result = new StringLiteral($Str.getText());}
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

// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN)
    ;

COMMENT 
     : '/*' .* '*/' -> channel(HIDDEN)
    ;


/* Keyword reservation */
BOOL : 'boolean';
INT : 'int';
STR : 'str';
IF : 'if';
ELSE : 'else';
FORM : 'form';
MONEY : 'money';
     
  

/* Literals */
Bool: ('true' | 'false');
Int: ('0'..'9')+;
Str: '"' ~('"')* '"' ;

Ident:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

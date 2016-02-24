grammar QL;
options {  }

@parser::header
{

	import ql.ast.*;
	import ql.ast.expression.*;
	import ql.ast.statement.*;
	import ql.ast.literal.*;
	import ql.ast.type.*;	
	
}

@lexer::header
{

}
/*
 * PARSER RULES
 */
questionnaire returns [Questionnaire result]
	: forms EOF {}
	;

forms returns [List<Form> result]
	: form*
	;
  
form returns [Form result]
	: 'form' ID block
	;
	
block returns [Block result] 
	:  '{' statement* '}'
	;	
statement returns [Statement result]
	: question {}
	| ifstatement {}
	| ifelsestatement {}
	;

question returns [Question result]
	: inputquestion {}
	| computedquestion {}
	;

inputquestion returns [Question result]
	: question_text=STR question_name=ID ':' qt=question_type { $result = new InputQuestion($question_text.text, $question_name.text, $qt.result); }
	;

computedquestion returns [Question result]
	: q=inputquestion '=' '(' orExpr ')' { $result = new ComputedQuestion($q.result.getQuestion(), $q.result.getName(), $q.result.getType(), $orExpr.result);}
	;

// TODO expr
/*
expr returns [Expr result]
	: unExpr
	| mulExpr
	| addExpr
	| conditions
	;
 */
 
/* question	: WS*? Str WS*? Ident WS*? ':' WS*? question_type WS*? { }
	;
*/
question_type returns [QuestionType result] 
	: STRING_TYPE
	| INTEGER_TYPE
	| MONEY_TYPE
	| BOOLEAN_TYPE
	;
	
ifstatement returns [IfStatement result] 
	:	'if' '(' cond=orExpr ')' block { $result = new IfStatement($cond.result, $block.result); }
	;
	
ifelsestatement returns [IfElseStatement result]
	: 'if' '(' cond=orExpr ')' bloc=block 'else' block { $result = new IfElseStatement($cond.result, $block.result); }
	;

/*
conditions returns [Expr result]
	: relExpr conditions* {} 
	| andExpr conditions* {}
	| orExpr conditions* {}
	| unExpr conditions* {}
	;
	*
	*/
// removed | Ident {}

unExpr returns [Expr result]
    :  '+' x=unExpr { $result = new Pos($x.result); }
    |  '-' x=unExpr { $result = new Neg($x.result); }
    |  '!' x=unExpr { $result = new Not($x.result); }
    |  z=primary    { $result = $z.result; }
    ;

// Why does the primary thing need a different letter to work?
    
primary returns [Expr result]
	: literal {  $result = new LiteralExpr($literal.result); }
	| ID {  $result = new IdentityExpr($ID.text); }
    | '(' orExpr ')' { $result = $orExpr.result; }
	;
    
literal returns [Literal result] 
	: STR { $result = new StringLiteral($STR.text); }
	| INT { $result = new IntegerLiteral(Integer.parseInt($INT.text)); }
	| MONEY { $result = new MoneyLiteral(Double.parseDouble($MONEY.text)); }
	| BOOL {  
		$result = new BooleanLiteral(true);
		if ($BOOL.text == "false") {
			$result.setValue(false);				
		}	
	}
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


/*
 * LEXER RULES
 */

BOOLEAN_TYPE : 'boolean';
MONEY_TYPE : 'money';
INTEGER_TYPE : 'integer';
STRING_TYPE : 'string';

INT: ('0'..'9')+;
MONEY: INT ',' INT;
STR: '"' (.|~[\\"])*? '"';
BOOL: 'true' | 'false';
ID:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;

StringLiteral
    :   '"' StringCharacters? '"'
    ;
    
fragment
StringCharacters
    :   StringCharacter+
    ;
    
fragment
StringCharacter
    :   ~["\\]
    |   EscapeSequence
    ;
    
fragment
EscapeSequence
    :   '\\' [btnfr"'\\]
    |   OctalEscape
    |   UnicodeEscape
    ;

fragment
OctalEscape
    :   '\\' OctalDigit
    |   '\\' OctalDigit OctalDigit
    |   '\\' ZeroToThree OctalDigit OctalDigit
    ;

fragment
UnicodeEscape
    :   '\\' 'u' HexDigit HexDigit HexDigit HexDigit
    ;

fragment
ZeroToThree
    :   [0-3]
    ;

fragment
HexDigit
    :   [0-9a-fA-F]
    ;

fragment
OctalDigit
    :   [0-7]
    ;
    
WS  :	(' ' | '\t' | '\n' | '\r')+ -> channel(HIDDEN);

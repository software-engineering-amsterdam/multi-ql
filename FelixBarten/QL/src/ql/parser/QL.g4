grammar QL;
options {  }

@parser::header
{
	import java.util.ArrayList;
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
	: forms EOF { $result = new Questionnaire($forms.result); }
	;

forms returns [List<Form> result]
	locals [ 
		List<Form> formslist = new ArrayList<Form>();
	]
	@after { $result = $ctx.formslist; }
	: (form { $ctx.formslist.add($form.result);})*
	;
  
form returns [Form result]
	: 'form' ID block { $result = new Form($ID.text, $block.result); }
	;
	
// Because questions don't derive from statements split block params in two lists.
block returns [Block result] 
	locals [ 
		List<Statement> statements = new ArrayList<Statement>();
		List<Question> questions = new ArrayList<Question>();
	]
	@init { 
		$ctx.statements = new ArrayList<Statement>();
	}
	@after { $result = new Block($ctx.statements, $ctx.questions); }
	:  '{' (statement { $ctx.statements.add($statement.result); } | question { $ctx.questions.add($question.result); })* '}'
	;	
statement returns [Statement result]
	: ifstat { $result = $ifstat.result; }
	| ifelsestat { $result = $ifelsestat.result; }
	;

question returns [Question result]
	: inputquestion { $result = $inputquestion.result;}
	| computedquestion { $result = $computedquestion.result;  }
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

question_type returns [QuestionType result] 
	: STRING_TYPE { $result = new StringType(); }
	| INTEGER_TYPE { $result = new IntegerType(); }
	| MONEY_TYPE { $result = new MoneyType(); }
	| BOOLEAN_TYPE { $result = new BooleanType(); }
	;
	
ifstat returns [IfStatement result] 
	:	'if' '(' cond=orExpr ')' block { $result = new IfStatement($cond.result, $block.result); }
	;
	
ifelsestat returns [IfElseStatement result]
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
	| BOOL {  $result = new BooleanLiteral(Boolean.valueOf($BOOL.text));	}
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

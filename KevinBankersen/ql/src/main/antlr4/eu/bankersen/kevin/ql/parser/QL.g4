grammar QL;

@parser::header
{
    import eu.bankersen.kevin.ql.form.ast.expressions.*;
    import eu.bankersen.kevin.ql.form.ast.expressions.logic.*;
    import eu.bankersen.kevin.ql.form.ast.expressions.math.*;
    import eu.bankersen.kevin.ql.form.ast.statements.*;
    import eu.bankersen.kevin.ql.form.ast.types.*;
    import eu.bankersen.kevin.ql.form.ast.values.*;

}

form returns [Form result]
	:	('Form'|'form') + ID + body + EOF { $result = new Form($ID.text, new Body($body.result, $body.start.getLine()),  $ID.getLine()); }
	;

body returns [List<Statement> result]
	@init {
		$result = new ArrayList<Statement>();
	}
	: '{' + (ifStat[$result] | question[$result] ) + '}'
	;

question[List<Statement> result]

	: STR + ID + ':' + type + '=' + primary
	{
		$result.add(new ComputedQuestion($ID.text, $STR.text.substring(1, $STR.text.length()-1), $primary.result, $type.result, $ID.getLine()));
	}

	| STR + ID + ':' + type

	{
		$result.add(new UserQuestion($ID.text, $STR.text.substring(1, $STR.text.length()-1), $type.result, $ID.getLine()));
	}
	;

ifStat[List<Statement> arg]
	:	'if' + '(' + orExpr + ')' + ifBody=body + 'else' + elseBody=body
	{
	    	$arg.add(new ElseStatement($orExpr.result, new Body($ifBody.result, $ifBody.start.getLine()),new Body($elseBody.result, $elseBody.start.getLine()), $orExpr.start.getLine()));
	}
	|	'if' + '(' + orExpr + ')' + body
	{
		$arg.add(new IFStatement($orExpr.result, new Body($body.result, $body.start.getLine()), $orExpr.start.getLine()));
	}
	;

mulExpr returns [Expression result]
	:   lhs=unExpr { $result=$lhs.result; } ( op=( '*' | '/' ) rhs=unExpr
	{
		if ($op.text.equals("*")) {
			$result = new Mul($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals("/")) {
			$result = new Div($result, $rhs.result, $rhs.start.getLine());
		}
	})*
	;


addExpr returns [Expression result]
	:   lhs=mulExpr { $result=$lhs.result; } ( op=('+' | '-') rhs=mulExpr
	{
		if ($op.text.equals("+")) {
			$result = new Add($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals("-")) {
			$result = new Sub($result, $rhs.result, $rhs.start.getLine());
		}
	})*
	;

relExpr returns [Expression result]
	:   lhs=addExpr { $result=$lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpr
	{
		if ($op.text.equals("<")) {
			$result = new LT($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals("<=")) {
			$result = new LEq($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals(">")) {
			$result = new GT($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals(">=")) {
			$result = new GEq($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals("==")) {
			$result = new Eq($result, $rhs.result, $rhs.start.getLine());
		}
		if ($op.text.equals("!=")) {
			$result = new NEq($result, $rhs.result, $rhs.start.getLine());
		}
	})*
	;


unExpr returns [Expression result]
	:	'+' x=unExpr 	{ $result = new Pos($x.result, $x.start.getLine()); }
	|	'-' x=unExpr 	{ $result = new Neg($x.result, $x.start.getLine()); }
	|	'!' x=unExpr 	{ $result = new Not($x.result, $x.start.getLine()); }
	|	y=primary    	{ $result = $y.result; }
	;

orExpr returns [Expression result]
	:   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, $rhs.result, $rhs.start.getLine()); } )*
	;

andExpr returns [Expression result]
	:   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, $rhs.result, $rhs.start.getLine()); } )*
	;


primary returns [Expression result]
	: literal 		{ $result = $literal.result; }
	| identifier 		{ $result = $identifier.result; }
	| '(' + orExpr + ')' 	{ $result = $orExpr.result; }
	;

literal returns [Expression result]
	: INT 	{ $result = new Literal($INT.getLine(), new NumberValue(Integer.valueOf($INT.text)) , new NumberType()); }
	| MON 	{ $result = new Literal($MON.getLine(), new MoneyValue(Integer.valueOf($MON.text.substring(1))) , new MoneyType()); }
	| STR 	{ $result = new Literal($STR.getLine(), new TextValue($STR.text.substring(1, $STR.text.length()-1)), new TextType() ); }
	| BOOL 	{ $result = new Literal($BOOL.getLine(), new BooleanValue(Boolean.valueOf($BOOL.text)), new BooleanType()); }
	;

identifier returns [Expression result]
	: ID	{ $result = new Identifier($ID.text, $ID.getLine()); }
	;

type returns [Type result]
	: BOOLEAN		{ $result = new BooleanType(); }
	| STRING		{ $result = new TextType(); }
	| INTEGER		{ $result = new NumberType(); }
	| MONEY			{ $result = new MoneyType(); }
	;


// Tokens
COMMENT 		:   '/*' .*? '*/' -> channel(HIDDEN);
LINE_COMMENT 	:   '//' ~[\r\n]* -> channel(HIDDEN);
WS  	:	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

BOOLEAN :   'boolean';
INTEGER :   'integer';
STRING  :   'string';
MONEY  	:   'money';

BOOL	:   'true' | 'false';
INT	:   ('0'..'9')+;
MON :  '€'+('0'..'9')+;
STR 	:   '"'.*?'"';
ID	:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
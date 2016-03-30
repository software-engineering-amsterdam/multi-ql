grammar QL;

@parser::header
{
    import eu.bankersen.kevin.ql.form.ast.expr.*;
    import eu.bankersen.kevin.ql.form.ast.expr.logic.*;
    import eu.bankersen.kevin.ql.form.ast.expr.math.*;
    import eu.bankersen.kevin.ql.form.ast.stat.*;
    import eu.bankersen.kevin.ql.form.ast.form.*;
    import eu.bankersen.kevin.ql.form.ast.*;
    import eu.bankersen.kevin.ql.form.ast.types.*;
    import eu.bankersen.kevin.ql.form.ast.values.*;




}

form returns [Form result]
	:	('Form'|'form') + ID + body + EOF { $result = new Form($ID.text, new Body($body.result)); }
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
		$result.add(new NormalQuestion($ID.text, $STR.text.substring(1, $STR.text.length()-1), $type.result, $ID.getLine()));
	}
	;

ifStat[List<Statement> arg]
	:	'if' + '(' + orExpr + ')' + ifBody=body + 'else' + elseBody=body
	{
	    	$arg.add(new ElseStatement($orExpr.result, new Body($ifBody.result),new Body($elseBody.result), $orExpr.start.getLine()));
	}
	|	'if' + '(' + orExpr + ')' + body
	{
		$arg.add(new IFStatement($orExpr.result, new Body($body.result), $orExpr.start.getLine()));
	}
	;

mulExpr returns [Expr result]
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


addExpr returns [Expr result]
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

relExpr returns [Expr result]
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


unExpr returns [Expr result]
	:	'+' x=unExpr 	{ $result = new Pos($x.result, $x.start.getLine()); }
	|	'-' x=unExpr 	{ $result = new Neg($x.result, $x.start.getLine()); }
	|	'!' x=unExpr 	{ $result = new Not($x.result, $x.start.getLine()); }
	|	y=primary    	{ $result = $y.result; }
	;

orExpr returns [Expr result]
	:   lhs=andExpr { $result = $lhs.result; } ( '||' rhs=andExpr { $result = new Or($result, $rhs.result, $rhs.start.getLine()); } )*
	;

andExpr returns [Expr result]
	:   lhs=relExpr { $result=$lhs.result; } ( '&&' rhs=relExpr { $result = new And($result, $rhs.result, $rhs.start.getLine()); } )*
	;


primary returns [Expr result]
	: literal 		{ $result = $literal.result; }
	| identifier 		{ $result = $identifier.result; }
	| '(' + orExpr + ')' 	{ $result = $orExpr.result; }
	;

literal returns [Expr result]
	: INT 	{ $result = new Literal($INT.getLine(), new IntegerValue(Integer.valueOf($INT.text)) , new IntegerType()); }
	| STR 	{ $result = new Literal($STR.getLine(), new StringValue($STR.text.substring(1, $STR.text.length()-1)), new StringType() ); }
	| BOOL 	{ $result = new Literal($BOOL.getLine(), new BooleanValue(Boolean.valueOf($BOOL.text)), new BooleanType()); }
	;

identifier returns [Expr result]
	: ID	{ $result = new Identifier($ID.text, $ID.getLine()); }
	;

type returns [Type result]
	: BOOLEAN		{ $result = new BooleanType(); }
	| STRING		{ $result = new StringType(); }
	| INTEGER		{ $result = new IntegerType(); }
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
STR 	:   '"'.*?'"';
ID	:   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;
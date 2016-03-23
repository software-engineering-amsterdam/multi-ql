grammar QL;

@parser::header{
	package ql.antlr;
	import ql.ast.expression.*;
	import ql.ast.form.*;
	import ql.ast.literal.*;
	import ql.ast.statement.*;
}

@lexer::header{
	package ql.antlr;
}

file 
	: form EOF
	;

form returns [Form result]
	: 'form' ID body { $result = new Form($ID.getLine(), $ID.text, $body.result); }
	;
	
body returns [Body result]
	@init { $result = new Body(); }
	: '{' (statement[$result])* '}' 
	;

statement [Body result]
	: question { $result.add(new Statement($question.result)); }
	| ifStatement { $result.add(new Statement($ifStatement.result)); }
	;

question returns [Question result]
	: variable STR { $result = new InputQuestion($variable.start.getLine(), $variable.result, $STR.text); }
	| variable STR '(' orExpression ')' { $result = new ComputedQuestion($variable.start.getLine(), $variable.result, $STR.text, $orExpression.result); }
	;
	
ifStatement returns [IfStatement result]
	: 'if' '(' orExpression ')' body { $result = new IfStatement($orExpression.start.getLine(), $orExpression.result, $body.result); }
	;

variable returns [Variable result]
	: ID ':' variableType { $result = new Variable($ID.getLine(), $ID.text, $variableType.result); }
	;
	
variableType returns [VariableType result]
	: type=(BOOLEAN | INTEGER | STRING){ $result = new VariableType($type.getLine(), $type.text); }
	;

orExpression returns [Expression result]
	: lhs=andExpression { $result = $lhs.result; } ( '||' rhs=andExpression { $result = new OrExpression($lhs.start.getLine(), $result, $rhs.result); } )*
	;

andExpression returns [Expression result]
	: lhs=relExpression { $result = $lhs.result; } ( '&&' rhs=relExpression { $result = new AndExpression($lhs.start.getLine(), $result, $rhs.result); } )*
	;

relExpression returns [Expression result]
	: lhs=addExpression { $result = $lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpression {
			if ($op.text.equals("<")) {
			  $result = new LT($lhs.start.getLine(), $result, $rhs.result);
			}
			if ($op.text.equals("<=")) {
			  $result = new LEq($lhs.start.getLine(), $result, $rhs.result);      
			}
			if ($op.text.equals(">")) {
			  $result = new GT($lhs.start.getLine(), $result, $rhs.result);
			}
			if ($op.text.equals(">=")) {
			  $result = new GEq($lhs.start.getLine(), $result, $rhs.result);      
			}
			if ($op.text.equals("==")) {
			  $result = new Eq($lhs.start.getLine(), $result, $rhs.result);
			}
			if ($op.text.equals("!=")) {
			  $result = new NEq($lhs.start.getLine(), $result, $rhs.result);
			}
		}
	)*
	;

addExpression returns [Expression result]
	: lhs=mulExpression { $result = $lhs.result; } ( op=('+' | '-') rhs=mulExpression {
			if($op.text.equals("+")){
				$result = new Add($lhs.start.getLine(), $result, $rhs.result);
			}
			if($op.text.equals("-")){
				$result = new Sub($lhs.start.getLine(), $result, $rhs.result);
			}
		}
	)*
	;

mulExpression returns [Expression result]
	: lhs=unExpression { $result = $lhs.result; } ( op=('*' | '/') rhs=unExpression {
			if($op.text.equals("*")){
				$result = new Mul($lhs.start.getLine(), $result, $rhs.result);
			}
			if($op.text.equals("/")){
				$result = new Div($lhs.start.getLine(), $result, $rhs.result);
			}
		}
	)*
	;

unExpression returns [Expression result]
	: '+' x=unExpression { $result = new Pos($x.result); }
	| '-' x=unExpression { $result = new Neg($x.result); }
	| '!' x=unExpression { $result = new Not($x.result); }
	| y=literal { $result = $y.result; }
	;

literal returns [Literal result]
	: INT { $result = new IntLiteral(Integer.valueOf($INT.text), $INT.getLine()); }
	| BOOL { $result = new BoolLiteral(Boolean.valueOf($BOOL.text), $BOOL.getLine()); }
	| STR { $result = new StringLiteral($STR.text, $STR.getLine()); }
	| ID { $result = new VariableExpression($ID.text, $ID.getLine()); }
	;

WHITESPACE: (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
COMMENT: '/*' .*? '/*' -> channel(HIDDEN);

BOOLEAN: 'boolean';
INTEGER: 'int';
STRING: 'string';

BOOL: 'true' | 'false';
INT: ('0'..'9')+;
STR: '"' .*? '"';

ID: ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
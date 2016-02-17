grammar QL;

@parser::header{
	package antlr;
	import ast.expression.*;
	import ast.form.*;
	import ast.literal.*;
	import ast.statement.*;
}

@lexer::header{
	package antlr;
}

file 
	: form EOF
	;

form returns [Form result]
	: 'form' ID body { $result = new Form($ID.text, $body.result); }
	;
	
body returns [Body result]
	@init { $result = new Body(); }
	: '{' (statement[$result])* '}' 
	;

statement [Body result]
	: question { $result.add(new Statement($question.result)); }
	| assignmentQuestion { $result.add(new Statement($assignmentQuestion.result)); }
	| ifStatement { $result.add(new Statement($ifStatement.result)); }
	;

question returns [Question result]
	: variable STR { $result = new Question($variable.result, $STR.text); }
	;

assignmentQuestion returns [AssignmentQuestion result]
	: variable STR '(' orExpression ')' { $result = new AssignmentQuestion($variable.result, $STR.text, $orExpression.result); }
	;
	
ifStatement returns [IfStatement result]
	: 'if' '(' orExpression ')' body { $result = new IfStatement($orExpression.result, $body.result); }
	;

variable returns [Variable result]
	: ID ':' variableType { $result = new Variable($ID.text, $variableType.result); }
	;
	
variableType returns [VariableType result]
	: type=(BOOLEAN | INTEGER | STRING){ $result = new VariableType($type.text); }
	;

orExpression returns [Expression result]
	: lhs=andExpression { $result = $lhs.result; } ( '||' rhs=andExpression { $result = new OrExpression($result, $rhs.result); } )*
	;

andExpression returns [Expression result]
	: lhs=relExpression { $result = $lhs.result; } ( '&&' rhs=relExpression { $result = new AndExpression($result, $rhs.result); } )*
	;

relExpression returns [Expression result]
	: lhs=addExpression { $result = $lhs.result; } ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpression {
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
		}
	)*
	;

addExpression returns [Expression result]
	: lhs=mulExpression { $result = $lhs.result; } ( op=('+' | '-') rhs=mulExpression {
			if($op.text.equals("+")){
				$result = new Add($result, $rhs.result);
			}
			if($op.text.equals("-")){
				$result = new Sub($result, $rhs.result);
			}
		}
	)*
	;

mulExpression returns [Expression result]
	: lhs=unExpression { $result = $lhs.result; } ( op=('*' | '/') rhs=unExpression {
			if($op.text.equals("*")){
				$result = new Mul($result, $rhs.result);
			}
			if($op.text.equals("/")){
				$result = new Div($result, $rhs.result);
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
	: intLiteral { $result = new Literal($intLiteral.result); }
	| boolLiteral { $result = new Literal($boolLiteral.result); }
	| stringLiteral { $result = new Literal($stringLiteral.result); }
	| variableExpression {$result = new Literal($variableExpression.result); }
	;

intLiteral returns [IntLiteral result]
	: INT { $result = new IntLiteral(Integer.valueOf($INT.text)); }
	;
	
boolLiteral returns [BoolLiteral result]
	: BOOL { $result = new BoolLiteral(Boolean.valueOf($BOOL.text)); }
	;

stringLiteral returns [StringLiteral result]
	: STR { $result = new StringLiteral($STR.text); }
	;

variableExpression returns [VariableExpression result]
	: ID { $result = new VariableExpression($ID.text); }
	;
	
WHITESPACE: (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
COMMENT: '/*' .*? '/*' -> channel(HIDDEN);

BOOLEAN: 'boolean';
INTEGER: 'int';
STRING: 'string';

BOOL: 'true' | 'false';
INT: ('0'..'9');
STR: '"' .*? '"';

ID: ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
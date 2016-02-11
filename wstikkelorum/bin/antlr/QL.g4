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
	: '{' statement* '}' { $result.add($statement.result); }
	;

statement returns [Statement result]
	: question { $result = new Statement($question.result); }
	| assignmentQuestion { $result = new Statement($assignmentQuestion.result); }
	| ifStatement { $result = new Statement($ifStatement.result); }
	;

question returns [Question result]
	: ID ':' STRING TYPE { $result = new Question($ID.text, $STRING.text, $TYPE.text); }
	;

assignmentQuestion returns [AssignmentQuestion result]
	: ID ':' STRING TYPE '(' orExpression ')' { $result = new AssignmentQuestion($ID.text, $STRING.text, $TYPE.text, $orExpression.result); }
	;

ifStatement returns [IfStatement result]
	: 'if' '(' orExpression ')' body { $result = new IfStatement($orExpression.result, $body.result); }
	;

orExpression returns [Expression result]
	: lhs=andExpression { $result = $lhs.result; } ( '||' rhs=andExpression { $result = new OrExpression($result, $rhs.result); } )*
	;

andExpression returns [Expression result]
	: lhs=relExpression { $result = $lhs.result; } ( '&&' rhs=relExpression { $result = new AndExpresion($result, $rhs.result); } )*
	;

relExpression returns [Expression result]
	: lhs=addExpression ( op=('<'|'<='|'>'|'>='|'=='|'!=') rhs=addExpression {
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
			  $result = new Neq($result, $rhs.result);
			}
		}
	)*
	;

addExpression returns [Expression result]
	: lhs=mulExpression ( op=('+' | '-') rhs=mulExpression {
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
	: lhs=unExpression ( op=('*' | '/') rhs=unExpression {
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
	: '+' unExpression { $result = new Pos($unExpression.result); }
	| '-' unExpression { $result = new Neg($unExpression.result); }
	| '!' unExpression { $result = new Not($unExpression.result); }
	| literal { $result = $literal.result; }
	;

literal returns [Literal result]
	: intLiteral { $result = new Literal($intLiteral.result); }
	| variable {$result = new Literal($variable.result); }
	;

intLiteral returns [IntLiteral result]
	: INT { $result = new IntLiteral(Integer.valueOf($INT.text)); }
	;

variable returns [Variable result]
	: ID { $result = new Variable($ID.text); }
	;
	
WHITESPACE: (' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);
COMMENT: '/*' .*? '/*' -> channel(HIDDEN);

TYPE: 'boolean' | 'money';
INT: ('0'..'9');
STRING: '"' .*? '"';
ID: ('a'..'z' | 'A'..'Z')('a'..'z' | 'A'..'Z' | '0'..'9' | '_')*;
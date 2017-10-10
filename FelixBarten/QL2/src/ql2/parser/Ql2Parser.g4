parser grammar Ql2Parser;

options {
	tokenVocab = Ql2Lexer;
}

@header {
	package ql2.parser.generated;

	import java.util.ArrayList;

	import ql2.Currency;
	import ql2.ast.*;
	import ql2.ast.expression.*;
	import ql2.ast.expression.arithmatic.*;
	import ql2.ast.statement.*;
	import ql2.ast.literal.*;
	import ql2.ast.type.*;
}

questionnaire returns [Questionnaire result]
	:   forms { $result = new Questionnaire($forms.result); }
		EOF
	;

forms returns [List<Form> result]
	locals [ List<Form> formsList = new ArrayList<Form>(); ]
	@after { $result = $ctx.formsList; }
	:   (form{ $ctx.formsList.add($form.result); })*
	;

form returns [Form result]
	: FORM formname block { $result = new Form($formname.result, $block.result); }
	;

formname returns [String result]
	: ID { $result = $ID.text; }
	;

block returns [Block result]
	locals [
				List<Statement> statementsList = new ArrayList<Statement>();
	 		 	List<Question> questionsList = new ArrayList<Question>();
	 	   ]
	@after { $result = new Block($ctx.statementsList, $ctx.questionsList); }
	:
	LBRACE
	(question { $ctx.questionsList.add($question.result); }
	|statement {$ctx.statementsList.add($statement.result); })*?
	RBRACE
	;

statement returns [Statement result] // name wrong
	: ifstatement { $result = ($ifstatement.result); }
	| ifelsestatement { $result = ($ifelsestatement.result); }
	| s=ifelseifstatement {$result = $s.result;}
	;

question returns [Question result]
	:	inputquestion { $result = $inputquestion.result; }
	| 	calculatedquestion { $result = $calculatedquestion.result; }
	;

inputquestion returns [InputQuestion result]
	: qtext=questiontext qname=questionname COLON qtype=questiontype
	{ $result = new InputQuestion($qname.result, $qtext.result, $qtype.result); }
	;

calculatedquestion returns [CalculatedQuestion result]
	: q=inputquestion ASSIGN cond=conditions
	{ $result = new CalculatedQuestion($q.result, $cond.result); }
	;


questiontext returns [String result]
	: x=STRING_DQUOTE { $result = $x.text; }
	;

questionname returns [String result]
	: ID { $result = $ID.text; }
	;

questiontype returns [QuestionType result]
	: BOOLEAN { $result = new BooleanType(); }
	| INTEGER  { $result = new IntegerType(); }
	| DOUBLE { $result = new DoubleType(); }
	| FLOAT { $result = new FloatType(); }
	| MONEY { $result = new CurrencyType(); }
	| STRING { $result = new StringType(); }
	| LONG  { $result = new LongType(); }
	;

ifstatement returns [IfStatement result]
	: IF conditions block { $result = new IfStatement($conditions.result, $block.result);}
	;

ifelsestatement returns [IfElseStatement result]
	: x=ifstatement ELSE block { $result = new IfElseStatement($x.result, $block.result);}
	;

ifelseifstatement returns [IfElseIfStatement result]
	: x=ifstatement ELSE IF conditions block { $result = new IfElseIfStatement($x.result, $conditions.result, $block.result); }
	;

whilestatement returns [WhileStatement result]
	: WHILE conditions THEN block END { $result = new WhileStatement($conditions.result, $block.result); }
	;

conditions returns [Expr result]
	: LPAREN x=condition RPAREN {$result = $x.result; }
	;

conditionsplaceholder returns [Expr result]
	: LPAREN RPAREN
	;

condition returns [Expr result]
	: x=orExpr { $result = $x.result; }
	;

//startpoint
orExpr returns [Expr result]
	: lhs=andExpr {$result = $lhs.result; } (op=LOR rhs=andExpr
	{
		$result = new OrExpr($result, $rhs.result);
	})*

	;

andExpr returns [Expr result]
	: lhs=relExpr{$result = $lhs.result;} (LAND rhs=relExpr
	{
		$result = new AndExpr($result, $rhs.result);
	})*
	;

relExpr returns [Expr result]
	: lhs=addExpr {$result = $lhs.result; }
	(op=(EQ|NEQ|GT|GTE|LT|LTE)
	rhs=addExpr
	{
		switch($op.text) {
			case "==": {
				$result = new EqExpr($result, $rhs.result);
				break;
			}
			case "!=": {
				$result = new NEqExpr($result, $rhs.result);
				break;
			}
			case ">": {
				$result = new GeExpr($result, $rhs.result);
				break;
			}
			case ">=": {
				$result = new GEqExpr($result, $rhs.result);
				break;
			}
			case "<":
				$result = new LTExpr($result, $rhs.result);
				break;

			case "<=":
				$result = new LTeExpr($result, $rhs.result);
				break;
		}
	})*
	;

mulExpr returns [Expr result]
	: lhs=unaryExpr {$result = $lhs.result; } (op=(STAR|DIV) rhs=unaryExpr
	{
		if($op.text == "/") {
				$result = new DivExpr($result, $rhs.result);

		} else {
			$result = new MulExpr($result, $rhs.result);
		}
	})*
	;

addExpr returns [Expr result]
	: lhs=mulExpr {$result = $lhs.result; } (op=(PLUS|MINUS) rhs=mulExpr
	{
		if ($op.text == "+") {
			$result = new AddExpr($result, $rhs.result);
		} else {
			$result = new SubExpr($result, $rhs.result);
		}
	})*
	;


unaryExpr returns [Expr result]
	: MINUS x=unaryExpr	{$result = new NegExpr($x.result); }
	| LNOT x=unaryExpr	{$result = new NotExpr($x.result); }
	| PLUS x=unaryExpr 	{$result = new PosExpr($x.result); }
	| value 	  			{$result = ($value.result); }
	| LPAREN orExpr RPAREN {$result = $orExpr.result; } // parenthesis
	;

value returns [Expr result]
	: literal {$result = new LiteralExpr($literal.result); }
	| ID {$result = new IdentityExpr($ID.text); }
	;

literal returns [Literal result]
	: str=STRING_DQUOTE { $result = new StringLiteral($str.text); }
	| z=intliteral {$result = new IntegerLiteral(Integer.parseInt($z.text)); }
	| x=booleanliteral {$result = new BooleanLiteral(Boolean.valueOf($x.text)); }
	| c=currencyliteral {$result = new CurrencyLiteral($c.result);}
	;

name returns [String result]
	: ID
	;

booleanliteral returns [Boolean result]
	: TRUE {$result = true; }
	| FALSE {$result = false; }
	;

intliteral
	: (INT)+
	;

currencyliteral returns [Currency result]
	: amount=DOUBLENUM curr=(DOLLAR|EURO|YEN|GBPOUND)?
	{
		if ($curr.text != null) {
			$result = new Currency(Double.parseDouble($amount.text), $curr.text.charAt(0));
		} else {
			$result = new Currency(Double.parseDouble($amount.text));
		}

	}
	;

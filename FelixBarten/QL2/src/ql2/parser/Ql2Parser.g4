parser grammar Ql2Parser;

options {
	tokenVocab = Ql2Lexer;
}

@header {
	package ql2.parser.generated;

	import java.util.ArrayList;

	import ql2.ast.*;
}

questionnaire returns [Questionnaire result]
	:	forms { $result = new Questionnaire($forms.result); }
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
	: ID { $return = $ID.result; }
	;

block returns [Block result]
	@after { $result = new Block($statements.result, $questions.result); }
	: LBRACE (statements | questions)* RBRACE
	;

questions returns [List<Question> result]
	locals [ List<Question> questionsList = new ArrayList<Question>(); ]
	@after { $result = $ctx.questionsList; }
	: question+ { $ctx.questionsList.add($question.result); }
	;

statements returns [List<Statement> result]
	locals [ List<Statement> statementsList = new ArrayList<Statement>(); ]
	@after { $result = $ctx.statementsList; }
	: ifstatement { $ctx.statementsList.add($ifstatement.result); }
	| ifelsestatement { $ctx.statementsList.add($ifelsestatement.result); }
	| whilestatement { $ctx.statementsList.add($whilestatement.result); }
	;

conditions returns [Statement result]
	: LPAREN condition RPAREN {$result = $condition.result; }
	;

condition returns [Statement result]
	: expr { $result = $expr.result; }
	;

ifstatement returns [IfStatement result]
	: IF LPAREN conditions RPAREN block { $result = new IfStatement($conditions.result, $block.result);}
	;

ifelsestatement returns [IfElseStatemetn result]
	: ifstatement ELSE block { $result = new IfElseStatement($conditions.result, $block.result);}
	;

ifelseifstatement returns [IfElseIfStatement result]
	: ifstatement ELSE IF block { $result = new IfElseIfStatement($ifstatement.result, $block.result); }
	;

whilestatement returns [WhileStatement result]
	: WHILE conditions  THEN block END { $result = new WhileStatement($conditions.result, $block.result); }
	;

question returns [Question result]
	: inputquestion { $result = $inputquestion.result; }
	| calculatedquestion { $result = calculatedquestion.result; }
	;

inputquestion returns [InputQuestion result]
	: questiontext questionname COLON questiontype
	{ $result = new InputQuestion($questionname.result, $questiontext.result, $questiontype.result); }
	;

calculatedquestion returns [CalculatedQuestion result]
	: inputquestion EQUALS statement
	{ $result = new CalculatedQuestion($inputquestion.result, $statement.result); }
	;

questiontext returns [String result]
	: QUOTED_STRING { $result = $QUOTED_STRING.result; }
	;

questionname returns [String result]
	: ID { $result = $ID.result; }
	;

questiontype
	: BOOLEAN
	| INT
	| DOUBLE
	| FLOAT
	| MONEY
	| STRING
	| LONG
	;

statement returns [Statement result]
	: conditions { $result = $conditions.result; }
	| expr 		 { $result = $expr.result; }
	;

expr returns [Expr result]
	: ID  { $result = $ID.result; } // binary expr??????????
	| value { $result = $value.result; } //unaryexpR
	;

binaryexpr
	: and
	| or
	| ge
	| lt
	| eq
	| lte
	| gte
	| neq
	| unaryexpr // ???
	;

and : expr LAND expr	;
or  : expr LOR expr	;

eq  : expr EQ expr	;
neq : expr NEQ expr	;

ge  : expr GT expr	;
lt  : expr LT  expr	;
lte : expr LTE expr	;
gte : expr GTE expr	;




unaryexpr
	: notexpr
	| negexpr
	| value
	;

posexpr
	: PLUS value
	;

notexpr
	: MINUS value
	;

negexpr
	: LNOT value
	;

value
	: (ID|INT)+
	;

name 
	: ID
	;

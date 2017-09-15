parser grammar Ql2Parser;

options {
	tokenVocab = Ql2Lexer;
}

@header {
	package ql2.parser.generated;

	import java.util.ArrayList;

	import ql2.ast.*;
}



questionnaire
	:	forms*
		EOF
	;

forms
	:   FORM
		block
	;

block
	: LBRACE (statements | questions)* RBRACE
	;

questions
	: question+
	;

statements
	: ifstatement
	| whilestatement
	;

conditions
	: condition
	;

condition
	: expr
	;

ifstatement
	: IF LPAREN conditions RPAREN LBRACE question LBRACE
	;

whilestatement
	: WHILE LPAREN conditions RPAREN THEN LBRACE question* RBRACE END
	;

question
	: questiontext questionname COLON statement
	;

questiontext
	: STRING
	;

questionname
	: ID
	;

statement
	: expr
	;

expr
	: binaryexpr
	| unaryexpr
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

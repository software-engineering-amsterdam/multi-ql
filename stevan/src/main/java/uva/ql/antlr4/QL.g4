grammar QL;

/* Lexical rules */
DIGIT		: [0-9] ;

BOOLEAN		: 'boolean' ;
STRING		: '"' ( '\\"' | '\\\\' | ~["\\] )* '"' ;
INT			: DIGIT+ | 'integer' ;
//DATE		:  ;
FLOAT
	: DIGIT+ [.,] DIGIT*
	| DIGIT* [.,] DIGIT+
	;
MONEY		: 'money' ;
DATE		: 'date' ;

ID	: [a-z] [a-zA-Z0-9]* ;
	
COMMENT 	: '//' .+? ('\n'|EOF) -> skip ;
WS			: [ \t\r\u000C\n]+ -> skip ;
NEW_LINE	: '\r'? '\n';

/* Grammar rules */
/*
 * A questionnaire consists of a number of questions arranged in sequential and conditional
 * structures, and grouping constructs.
 * 
 * Sequential composition prescribes the order of presentation.
 */
form : 'form' varName block ;
block: '{' ( question | condition )+ '}' ;

/* 
 * QL consists of questions grouped in a top-level form construct. 
 * First, each question is identified by a name that at the same time represents the result 
 * of the question. In other words the name of the question is also the variable that holds 
 * the answer.
 * 
 * Second, a question has a label that contains the actual question text presented to the user.
 * 
 * Third, every question has a type.
 * 
 * Finally, a question can optionally be associated to an expression: this makes the question 
 * computed.
 */
label : STRING (DIGIT+)? ('?'|':')? ;
varName : ID ;
varType : ( BOOLEAN | MONEY | INT | STRING | DATE ) ;

question : label varName ':' varType ('=' expression )? ;

/*
 * Conditional structures associate an enabling condition to a question, in which
 * case the question should only be presented to the user if and when the condition becomes
 * true.
 * 
 * The expression language used in conditions is the same as the expressions used in computed 
 * questions.
 * 
 * Grouping does not have any semantics except to associate a single condition to multiple 
 * questions at once.
 */
condition 
	: 'if' expression+ block	
		(
			('else' block)
			|
			('else' condition)+
		)? #ifCondition
	;

/*
 * For expressions we restrict ourselves to booleans (e.g., && , || and ! ), comparisons ( < ,
 * > , >= , <= , != and == ) and basic arithmetic ( + , - , * and / ). The required types are:
 * boolean, string, integer, date and decimal and money/currency.
 */
 
expression 														
	: BOOLEAN													#expBool
	| (DIGIT+ | FLOAT) 											#expNum
	| varName 													#expVar
	| '(' expression ')' 										#expParentheses
	| '!' expression 											#expNot
	| expression ('*' | '/') expression 						#expMultDivide
	| expression ('+' | '-') expression 						#expPlusMinus
	| expression ('<' | '>' | '<=' | '>=' | '!=' | '==') expression 	#expEquality
	| expression ('&&' | '||') expression 						#expAndOr
	;															

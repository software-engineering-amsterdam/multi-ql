grammar MyGrammer;

form returns[FormNode]
	: 'form' lbl=LABEL que=queries {$FormNode = new FormNode($lbl.text, $que.QueriesNode)}
	;

queries returns[QueriesNode]
	@init{ $QueriesNode = new Array(); }
	: '{' (que=question {$QueriesNode.push($que.ResultNode)} | que=ifstmt {$QueriesNode.push($que.ResultNode)})* '}'
	;

question returns[ResultNode]
	: txt=STRING lbl=LABEL DELIMITER type=TYPE  				{$ResultNode = new QuestionNode($txt.text, $lbl.text, $type.text, $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER type=TYPE '=' exp=expr  	{$ResultNode = new QuestionNode($txt.text, $lbl.text, $type.text, $txt.line, $exp.ExprNode)}
	;

ifstmt returns[ResultNode]
	: 'if' exp=expr que=queries 						{$ResultNode = new ConditionNode($exp.ExprNode, $que.QueriesNode, $exp.start.line)}
	| 'if' exp=expr que=queries 'else' elseque=queries 	{$ResultNode = new ConditionNode($exp.ExprNode, $que.QueriesNode, $exp.start.line, $elseque.QueriesNode)}
	;

expr returns[ExprNode]
	: bool=BOOLEAN 								{$ExprNode = 'true' == $bool.text} 						#booleanLiteral
	| lbl=LABEL 								{$ExprNode = new LabelNode($lbl.text, $lbl.line)} 					#labelNode
	| num=NUMBER 								{$ExprNode = parseInt($num.text)} 						#numberLiteral
	| dec=DECIMAL 								{$ExprNode = parseFloat($dec.text)} 					#decimalLiteral
	| '(' exp=expr ')' 							{$ExprNode = new ExpressionNode($exp.ExprNode, $exp.start.line)} 		#parenthesisExpr
	| left=expr op=MULTDIVOPERATOR right=expr 	{$ExprNode = new OperatorExpressionNode($left.ExprNode, $op.text, $right.ExprNode, $left.start.line)} #opExpr
	| left=expr op=ADDSUBOPERATOR right=expr 	{$ExprNode = new OperatorExpressionNode($left.ExprNode, $op.text, $right.ExprNode, $left.start.line)} #opExpr
	| left=expr op=COMPAREOPERATOR right=expr 	{$ExprNode = new OperatorExpressionNode($left.ExprNode, $op.text, $right.ExprNode, $left.start.line)} #opExpr
	| left=expr op=BOOLOPERATOR right=expr 		{$ExprNode = new OperatorExpressionNode($left.ExprNode, $op.text, $right.ExprNode, $left.start.line)} #opExpr
	| NOTOPERATOR exp=expr 						{$ExprNode = new NotExpression($exp.ExprNode, $exp.start.line)} 			#negationExpr
	;



MULTDIVOPERATOR
	: '*' 
	| '/' 
	;

ADDSUBOPERATOR
	: '+' 
	| '-' 
	 ;

BOOLOPERATOR
	: '&&'  
	| '||' 
	;


NOTOPERATOR
	: '!' 
	;

COMPAREOPERATOR
	: '<' 
	| '<=' 
	| '>' 
	| '>=' 
	| '!=' 
	| '==' 
	; 

TYPE
	: ('decimal' | 'integer' | 'boolean' | 'string' | 'money' | 'currency' | 'date')
	;

BOOLEAN
	: ('true' | 'fasle')
	;

DELIMITER
	: ':'
	;

LABEL
	:[A-Za-z][A-Za-z0-9]*
	;

NEWLINE
	: [\r\n]+ -> skip
	;

NUMBER
	: [0-9]+
	;

DECIMAL
	: [0-9]+ '.'? ','? [0-9]*
	;

WHITESPACE
	: ( '\t' | ' ' )+ -> skip
	;

BRACKETS
	: ('}' | '{')
	;

STRING
	: '"' (~('"' | '\\' | '\r' | '\n') | '\\' ('"' | '\\'))* '"'
	;
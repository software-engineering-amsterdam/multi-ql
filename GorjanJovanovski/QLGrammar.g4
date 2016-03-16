grammar QLGrammar;

form returns[FormNode]
	: 'form' lbl=LABEL blk=block {$FormNode = new FormNode($lbl.text, $blk.BlockArray)}
	;

block returns[BlockArray]
	@init{ $BlockArray = new Array(); }
	: '{' (blk=blockstmt {$BlockArray.push($blk.ResultNode)})* '}'
	;

blockstmt returns[ResultNode]
	: cq=computedquestion {$ResultNode = $cq.ResultNode}
	| q=question {$ResultNode = $q.ResultNode}
	| i=ifstmt {$ResultNode = $i.ResultNode}
	;

question returns[ResultNode]
	: txt=STRING lbl=LABEL DELIMITER 'decimal'  {$ResultNode = new QuestionNode($txt.text, $lbl.text, new DecimalType(), $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER 'integer'  {$ResultNode = new QuestionNode($txt.text, $lbl.text, new NumberType(), $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER 'boolean' 	{$ResultNode = new QuestionNode($txt.text, $lbl.text, new BooleanType(), $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER 'string'  	{$ResultNode = new QuestionNode($txt.text, $lbl.text, new StringType(), $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER 'money'  	{$ResultNode = new QuestionNode($txt.text, $lbl.text, new DecimalType(), $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER 'currency' {$ResultNode = new QuestionNode($txt.text, $lbl.text, new DecimalType(), $txt.line)}
	| txt=STRING lbl=LABEL DELIMITER 'date'  	{$ResultNode = new QuestionNode($txt.text, $lbl.text, new DateType(), $txt.line)}
	;

computedquestion returns[ResultNode]
	: txt=STRING lbl=LABEL DELIMITER 'decimal' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new FloatType(), $txt.line, $exp.ExprNode)}
	| txt=STRING lbl=LABEL DELIMITER 'integer' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new NumberType(), $txt.line, $exp.ExprNode)}
	| txt=STRING lbl=LABEL DELIMITER 'boolean' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new BooleanType(), $txt.line, $exp.ExprNode)}
	| txt=STRING lbl=LABEL DELIMITER 'string' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new StringType(), $txt.line, $exp.ExprNode)}
	| txt=STRING lbl=LABEL DELIMITER 'money' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new MoneyType(), $txt.line, $exp.ExprNode)}
	| txt=STRING lbl=LABEL DELIMITER 'currency' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new CurrencyType(), $txt.line, $exp.ExprNode)}
	| txt=STRING lbl=LABEL DELIMITER 'date' '=' exp=expr  	{$ResultNode = new ComputedQuestionNode($txt.text, $lbl.text, new DateType(), $txt.line, $exp.ExprNode)}
	;

ifstmt returns[ResultNode]
	: 'if' exp=expr blk=block 						{$ResultNode = new ConditionNode($exp.ExprNode, $blk.BlockArray, $exp.start.line)}
	| 'if' exp=expr blk=block 'else' elseque=block 	{$ResultNode = new ConditionNode($exp.ExprNode, $blk.BlockArray, $exp.start.line, $elseque.BlockArray)}
	;

expr returns[ExprNode]
	: bool=BOOLEAN 								{$ExprNode = new LiteralNode('true' === $bool.text, $bool.line)}
	| num=NUMBER 								{$ExprNode = new LiteralNode(parseInt($num.text), $num.line)}
	| dec=DECIMAL 								{$ExprNode = new LiteralNode(parseFloat($dec.text), $dec.line)}
	| lbl=LABEL 								{$ExprNode = new LabelNode($lbl.text, $lbl.line)}
	| '(' exp=expr ')' 							{$ExprNode = $exp.ExprNode} 							
	| left=expr op=MULTDIVOP right=expr 	{$ExprNode = new OperatorExpressionNode($left.ExprNode, new NumOperatorNode($op.text, $left.start.line), $right.ExprNode, $left.start.line)} 			
	| left=expr op=ADDSUBOP right=expr 		{$ExprNode = new OperatorExpressionNode($left.ExprNode, new NumOperatorNode($op.text, $left.start.line), $right.ExprNode, $left.start.line)} 			
	| left=expr op=COMPOP right=expr 		{$ExprNode = new OperatorExpressionNode($left.ExprNode, new NumOperatorNode($op.text, $left.start.line), $right.ExprNode, $left.start.line)} 			
	| left=expr op=BOOLOP right=expr 		{$ExprNode = new OperatorExpressionNode($left.ExprNode, new BoolOperatorNode($op.text, $left.start.line), $right.ExprNode, $left.start.line)}		 	 
	| left=expr op=BOOLCOMOP right=expr 	{$ExprNode = new OperatorExpressionNode($left.ExprNode, new NumOrBoolOperatorNode($op.text, $left.start.line), $right.ExprNode, $left.start.line)}		  
	| NOTOP exp=expr 						{$ExprNode = new NotExpression($exp.ExprNode, $exp.start.line)} 	
	;



MULTDIVOP
	: '*' 
	| '/' 
	;

ADDSUBOP
	: '+' 
	| '-' 
	 ;

BOOLOP
	: '&&'  
	| '||' 
	;


NOTOP
	: '!' 
	;

COMPOP
	: '<' 
	| '<=' 
	| '>' 
	| '>='  
	; 

BOOLCOMOP
	:'!=' 
	| '==' 
	; 

TYPE
	: ('decimal' | 'integer' | 'boolean' | 'string' | 'money' | 'currency' | 'date')
	;

BOOLEAN
	: ('true' | 'false')
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
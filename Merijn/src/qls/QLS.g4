grammar QLS;

stylesheet
	: 'stylesheet' IDENTIFIER stylesheetBlock
	;

stylesheetBlock
	: '{' stylesheetStatement* '}'
	;

stylesheetStatement
	: 'page' IDENTIFIER pageBlock       # pageStylesheetStatement
	| typeDefaultStatement              # typeDefaultStylesheetStatement
	;

pageBlock
	: '{' pageStatement* '}'
	;

pageStatement
	: 'section' STRING_LITERAL pageBlock        # sectionPageStatement
	| 'question' IDENTIFIER ';'                 # questionPageStatement
	| 'question' IDENTIFIER widgetConfiguration # configuredQuestionPageStatement
	| typeDefaultStatement                      # typeDefaultPageStatement
	;

typeDefaultStatement
	: 'default' type widgetConfiguration
	;

widgetConfiguration
	: '{' widgetArg* 'widget' widgetType '}'
	;

widgetArg
	: IDENTIFIER ':' literal ';'
	;

widgetType
	: 'slider'                      # sliderWidgetType
//	| 'spinbox'
	| 'text'                        # textWidgetType
	| 'radio' valueOptionList       # radioWidgetType
//	| 'checkbox'
//	| 'dropdown' valueOptionList
	;

valueOptionList
	: '(' valueOptions ')'
	;

valueOptions
	: STRING_LITERAL ',' valueOptions  # moreValueOptions
	| STRING_LITERAL                   # lastValueOption
	;

literal
	: BOOLEAN_LITERAL   # booleanLiteral
	| STRING_LITERAL    # stringLiteral
	| INTEGER_LITERAL   # integerLiteral
	| FLOAT_LITERAL     # floatLiteral
	| MONEY_LITERAL     # moneyLiteral
	;

type
	: 'boolean'
	| 'string'
	| 'integer'
	| 'float'
	| 'money'
	;


STRING_LITERAL
	: '"' ~["]* '"'
	;
INTEGER_LITERAL
	: DIGIT+
	;
FLOAT_LITERAL
	: INTEGER_LITERAL '.' INTEGER_LITERAL
	;
MONEY_LITERAL
	: INTEGER_LITERAL ',' MONEY_LITERAL_CENTS
	;
MONEY_LITERAL_CENTS
	: DIGIT DIGIT
	| '-'
	;
BOOLEAN_LITERAL : 'true' | 'false';

IDENTIFIER
	: [a-zA-Z_]+
	;

WHITESPACE
	: [ \t\r\n]+ -> skip
	;

fragment DIGIT : [0-9];
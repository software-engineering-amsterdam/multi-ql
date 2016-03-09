grammar QLS;

stylesheet
	: 'stylesheet' IDENTIFIER stylesheetBlock
	;

stylesheetBlock
	: '{' stylesheetStatement* '}'
	;

stylesheetStatement
	: 'page' IDENTIFIER pageBlock
	| defaultWidgetStatement
	;

pageBlock
	: '{' pageStatement* '}'
	;

pageStatement
	: 'section' STRING_LITERAL pageBlock
	| 'question' IDENTIFIER widgetBlock
	| defaultWidgetStatement
	;

defaultWidgetStatement
	: 'default' type widgetBlock
	;

widgetBlock
	: '{' (widgetStatement ';')* '}'
	;

widgetStatement
	: 'widget' ':' widgetType
	;

type
	: 'boolean'
	| 'string'
	| 'integer'
	| 'float'
	| 'money'
	;

widgetType
	: 'slider'
	| 'spinbox'
	| 'text'
	| 'radio' '(' valueOptions ')'
	| 'checkbox'
	| 'dropdown' '(' valueOptions ')'
	;

valueOptions
	: valueOption ',' valueOptions
	| valueOption
	;

valueOption
	: STRING_LITERAL
	;

STRING_LITERAL
	: '"' ~["]* '"'
	;

IDENTIFIER
	: [a-zA-Z_]+
	;

WHITESPACE
	: [ \t\r\n]+ -> skip
	;
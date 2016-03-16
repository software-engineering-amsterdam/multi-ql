grammar QLS;

@parser::header
{
import java.util.Map;
import java.util.HashMap;
import sc.qls.ast.*;
import sc.qls.ast.literal.*;
import sc.qls.ast.page.*;
import sc.qls.ast.type.*;
import sc.qls.ast.widget.*;
}

@parser::members {
    private <T extends QLSASTNode> T addSource(ParserRuleContext context, T node){
        node.setSourceInfo(new QLSASTSourceInfo(context));
        return (T) node;
    }
    
    private String unQuote(String text){
        return text.substring(1, text.length()-1);
    }
}

stylesheet returns [QLSStyleSheet result]
    locals [
      String id;
      List<QLSPage> pages = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new QLSStyleSheet($ctx.id, $ctx.pages));
    }
    :   'stylesheet' ID { $ctx.id = $ID.text; } '{'
            (page { $ctx.pages.add($page.result); } )+ 
        '}'
    ;
    
page returns [QLSPage result]
    locals [
      String name;
      List<QLSSection> sections = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new QLSPage($ctx.name, $ctx.sections));
    }
    : 'page' STR { $ctx.name = unQuote($STR.text); } '{' 
          (section { $ctx.sections.add($section.result); } )+ 
      '}'
    ;
    
section returns [QLSSection result]
    locals [
      String name;
      List<QLSQuestion> questions = new ArrayList<>();
      List<QLSTypeDef> defaultWidgets = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new QLSSection($ctx.name, $ctx.questions, $ctx.defaultWidgets));
    }
    : 'section' STR { $ctx.name=unQuote($STR.text); } '{'
         ( question { $ctx.questions.add($question.result); } | typeDef  { $ctx.defaultWidgets.add($typeDef.result); } )+ 
       '}'
    ;

question returns [QLSQuestion result]
    : 'question' + ID + widget
    {
        $result = addSource($ctx, new QLSQuestion($ID.text, $widget.result));
    }
    | 'question' + ID
    { 
        $result = addSource($ctx, new QLSQuestion($ID.text));
    }
    ;
    
widget returns [QLSWidget result]
    : 'widget' widgetType {$result = addSource($ctx, new QLSWidget($widgetType.result)); }
    ;

widgetType returns [QLSWidgetType result]
    : 'slider'      { $result = addSource($ctx, new QLSSlider());    }
    | 'spinbox'     { $result = addSource($ctx, new QLSSpinbox());   }
    | 'text'        { $result = addSource($ctx, new QLSTextField()); }
    | 'checkbox' '(' widgetOptions ')' 'default' STR { $result = addSource($ctx, new QLSCheckBox($widgetOptions.result, unQuote($STR.text))); }
    | 'radio' '(' widgetOptions ')' 'default' STR   { $result = addSource($ctx, new QLSRadioButton($widgetOptions.result, unQuote($STR.text))); }
    | 'dropdown' '(' widgetOptions ')' 'default' STR { $result = addSource($ctx, new QLSDropDown($widgetOptions.result, unQuote($STR.text))); }
    ; 
widgetOptions returns [List<String> result]
    @init {
        $result = new ArrayList<>();
    }
    :   arg1=STR { $result.add(unQuote($arg1.text)); } (',' arg2=STR { $result.add(unQuote($arg2.text));})*
    ;
    
typeDef returns [QLSTypeDef result]
    : 'default' variableType '{' typeOptions '}' 
    {
        $result = addSource($ctx, new QLSTypeDef($variableType.result, $typeOptions.result));
    }
    ;
    
variableType returns [QLSType result]
    : BOOLEAN   { $result = addSource($ctx, new QLSBooleanType()); }
    | STRING    { $result = addSource($ctx, new QLSStringType());  }
    | INTEGER   { $result = addSource($ctx, new QLSIntegerType()); }
    ;
    
typeOptions returns [List<QLSTypeOption> result]
    :
    ;
    
literal returns [QLSLiteral result]
    : INT   { $result = addSource($ctx, new IntegerLiteral(Integer.valueOf($INT.text))); }
    | STR   { $result = addSource($ctx, new StringLiteral($STR.text)); }
    | BOOL  { $result = addSource($ctx, new BooleanLiteral(Boolean.valueOf($BOOL.text))); }
    ;
    
// Tokens
WS  :	(' ' | '\t' | '\n' | '\r') -> channel(HIDDEN);

COMMENT 
    :   '/*' .*? '*/' -> channel(HIDDEN);
    
LINE_COMMENT 
    :   '//' ~[\r\n]* -> channel(HIDDEN);
    
BOOLEAN :   'bool';
INTEGER :   'int';
STRING  :   'str';

BOOL :  'true' | 'false';
INT  :   ('0'..'9')+;
STR  :   '"' .*? '"';

ID   :   ('a'..'z'|'A'..'Z')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*;


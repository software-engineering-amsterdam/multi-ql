grammar QLS;

@parser::header
{
import java.util.Map;
import java.util.HashMap;
import sc.qls.ast.*;
import static sc.qls.ast.Rule.*;
import static sc.qls.ast.Property.*;
import static sc.qls.ast.Widget.*;

import sc.ql.ast.ASTNode;
import sc.ql.ast.ValueType;
import sc.ql.ast.Literal;
import static sc.ql.ast.ASTNode.*;
import static sc.ql.ast.ValueType.*;
import static sc.ql.ast.Literal.*;
}

@parser::members {
    private <T extends ASTNode> T addSource(ParserRuleContext context, T node){
        node.setSourceInfo(new SourceLocation(context));
        return (T) node;
    }
    
    private String unQuote(String text){
        return text.substring(1, text.length()-1);
    }
}

stylesheet returns [StyleSheet result]
    locals [
      String id;
      List<Page> pages = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new StyleSheet($ctx.id, $ctx.pages));
    }
    :   'stylesheet' ID { $ctx.id = $ID.text; } '{'
            (page { $ctx.pages.add($page.result); } )+ 
        '}'
    ;
    
page returns [Page result]
    locals [
      String name;
      List<Section> sections = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new Page($ctx.name, $ctx.sections));
    }
    : 'page' STR { $ctx.name = unQuote($STR.text); } '{' 
          (section { $ctx.sections.add($section.result); } )+ 
      '}'
    ;
    
section returns [Section result]
    locals [
      String name;
      List<Rule> rules = new ArrayList<>();
    ]
    @after{
        $result = addSource($ctx, new Section($ctx.name, $ctx.rules));
    }
    : 'section' STR { $ctx.name=unQuote($STR.text); } '{'
         ( rule0 { $ctx.rules.add($rule0.result); })+ 
       '}'
    ;

// rule0 because of name collision with Antlr rule.
rule0 returns [Rule result]
    : question {$result = $question.result; } 
    | typeDef  {$result = $typeDef.result; }
    ;

question returns [Rule result]
    locals[
        Widget widget;
        List<Property> props = new ArrayList<>();
    ]
    : 'question' + ID +  ('widget' widgetType {$ctx.widget=$widgetType.result; })?  ('{' properties {$ctx.props.addAll($properties.result); } '}')?
    {
        $result = addSource($ctx, new QuestionRule($ID.text, $ctx.widget, $ctx.props));
    }
    ;

widgetType returns [Widget result]
    : 'slider' '(' widgetOptions ')' 'default' STR   { $result = addSource($ctx, new Slider($widgetOptions.result, unQuote($STR.text)));    }
    | 'spinner' '(' widgetOptions ')' 'default' STR   { $result = addSource($ctx, new Spinbox($widgetOptions.result, unQuote($STR.text)));   }
    | 'text'                                          { $result = addSource($ctx, new TextField()); }
    | 'checkbox' '(' widgetOptions ')' 'default' STR  { $result = addSource($ctx, new CheckBox($widgetOptions.result, unQuote($STR.text))); }
    | 'radio' '(' widgetOptions ')' 'default' STR     { $result = addSource($ctx, new RadioButton($widgetOptions.result, unQuote($STR.text))); }
    | 'dropdown' '(' widgetOptions ')' 'default' STR  { $result = addSource($ctx, new DropDown($widgetOptions.result, unQuote($STR.text))); }
    ; 
    
widgetOptions returns [List<String> result]
    @init {
        $result = new ArrayList<>();
    }
    :   arg1=STR { $result.add(unQuote($arg1.text)); } (',' arg2=STR { $result.add(unQuote($arg2.text));})*
    ;
    
typeDef returns [ValueTypeRule result]
    locals[
        Widget widget = null;
    ]
    : 'default' valueType '{' p1=properties ('widget' widgetType {$widget=$widgetType.result; })? p2=properties '}' 
    {
        $result = addSource($ctx, new ValueTypeRule($ctx.widget, $valueType.result, $p1.result));
    }
    ;
    
valueType returns [ValueType result]
    : BOOLEAN   { $result = new BooleanType(); }
    | STRING    { $result = new StringType();  }
    | INTEGER   { $result = new IntegerType(); }
    ;
    
properties returns [List<Property> result]
    @init{
        $result = new ArrayList<>();
    }
    :   (property {$result.add($property.result); })*
    ;
    
property returns [Property result]
    : 'color' ':' STR {$result = addSource($ctx, new ColorProperty(new StringLiteral(unQuote($STR.text)))); }
    | 'width' ':' INT {$result = addSource($ctx, new WidthProperty(new IntegerLiteral(Integer.valueOf($INT.text)))); }
    | 'height' ':' INT {$result = addSource($ctx, new HeightProperty(new IntegerLiteral(Integer.valueOf($INT.text)))); }
    | 'fontSize' ':' INT {$result = addSource($ctx, new FontSizeProperty(new IntegerLiteral(Integer.valueOf($INT.text)))); }
    | 'font' ':' STR {$result = addSource($ctx, new FontNameProperty(new StringLiteral(unQuote($STR.text))));}
    | 'fontStyle' ':' STR {$result = addSource($ctx, new FontStyleProperty(new StringLiteral(unQuote($STR.text))));}
    ;
    
literal returns [Literal result]
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


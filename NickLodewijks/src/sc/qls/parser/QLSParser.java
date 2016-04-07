// Generated from QLS.g4 by ANTLR 4.5.3
package sc.qls.parser;

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

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QLSParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		WS=25, COMMENT=26, LINE_COMMENT=27, BOOLEAN=28, INTEGER=29, STRING=30, 
		BOOL=31, INT=32, STR=33, ID=34;
	public static final int
		RULE_stylesheet = 0, RULE_page = 1, RULE_section = 2, RULE_rule0 = 3, 
		RULE_question = 4, RULE_widgetType = 5, RULE_widgetOptions = 6, RULE_typeDef = 7, 
		RULE_valueType = 8, RULE_properties = 9, RULE_property = 10, RULE_literal = 11;
	public static final String[] ruleNames = {
		"stylesheet", "page", "section", "rule0", "question", "widgetType", "widgetOptions", 
		"typeDef", "valueType", "properties", "property", "literal"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'stylesheet'", "'{'", "'}'", "'page'", "'section'", "'question'", 
		"'widget'", "'slider'", "'('", "')'", "'default'", "'spinner'", "'text'", 
		"'checkbox'", "'radio'", "'dropdown'", "','", "'color'", "':'", "'width'", 
		"'height'", "'fontSize'", "'font'", "'fontStyle'", null, null, null, "'bool'", 
		"'int'", "'str'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "WS", "COMMENT", "LINE_COMMENT", "BOOLEAN", "INTEGER", "STRING", 
		"BOOL", "INT", "STR", "ID"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "QLS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	    private <T extends ASTNode> T addSource(ParserRuleContext context, T node){
	        node.setSourceInfo(new SourceLocation(context));
	        return (T) node;
	    }
	    
	    private String unQuote(String text){
	        return text.substring(1, text.length()-1);
	    }

	public QLSParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StylesheetContext extends ParserRuleContext {
		public StyleSheet result;
		public String id;
		      List<Page> pages =  new ArrayList<>();;
		public Token ID;
		public PageContext page;
		public TerminalNode ID() { return getToken(QLSParser.ID, 0); }
		public List<PageContext> page() {
			return getRuleContexts(PageContext.class);
		}
		public PageContext page(int i) {
			return getRuleContext(PageContext.class,i);
		}
		public StylesheetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stylesheet; }
	}

	public final StylesheetContext stylesheet() throws RecognitionException {
		StylesheetContext _localctx = new StylesheetContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_stylesheet);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(T__0);
			setState(25);
			((StylesheetContext)_localctx).ID = match(ID);
			 _localctx.id = (((StylesheetContext)_localctx).ID!=null?((StylesheetContext)_localctx).ID.getText():null); 
			setState(27);
			match(T__1);
			setState(31); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(28);
				((StylesheetContext)_localctx).page = page();
				 _localctx.pages.add(((StylesheetContext)_localctx).page.result); 
				}
				}
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 );
			setState(35);
			match(T__2);
			}
			_ctx.stop = _input.LT(-1);

			        ((StylesheetContext)_localctx).result =  addSource(_localctx, new StyleSheet(_localctx.id, _localctx.pages));
			    
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PageContext extends ParserRuleContext {
		public Page result;
		public String name;
		      List<Section> sections =  new ArrayList<>();;
		public Token STR;
		public SectionContext section;
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public PageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_page; }
	}

	public final PageContext page() throws RecognitionException {
		PageContext _localctx = new PageContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_page);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37);
			match(T__3);
			setState(38);
			((PageContext)_localctx).STR = match(STR);
			 _localctx.name = unQuote((((PageContext)_localctx).STR!=null?((PageContext)_localctx).STR.getText():null)); 
			setState(40);
			match(T__1);
			setState(44); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				((PageContext)_localctx).section = section();
				 _localctx.sections.add(((PageContext)_localctx).section.result); 
				}
				}
				setState(46); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__4 );
			setState(48);
			match(T__2);
			}
			_ctx.stop = _input.LT(-1);

			        ((PageContext)_localctx).result =  addSource(_localctx, new Page(_localctx.name, _localctx.sections));
			    
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SectionContext extends ParserRuleContext {
		public Section result;
		public String name;
		      List<Rule> rules =  new ArrayList<>();;
		public Token STR;
		public Rule0Context rule0;
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public List<Rule0Context> rule0() {
			return getRuleContexts(Rule0Context.class);
		}
		public Rule0Context rule0(int i) {
			return getRuleContext(Rule0Context.class,i);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_section);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(T__4);
			setState(51);
			((SectionContext)_localctx).STR = match(STR);
			 _localctx.name=unQuote((((SectionContext)_localctx).STR!=null?((SectionContext)_localctx).STR.getText():null)); 
			setState(53);
			match(T__1);
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(54);
				((SectionContext)_localctx).rule0 = rule0();
				 _localctx.rules.add(((SectionContext)_localctx).rule0.result); 
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__5 || _la==T__10 );
			setState(61);
			match(T__2);
			}
			_ctx.stop = _input.LT(-1);

			        ((SectionContext)_localctx).result =  addSource(_localctx, new Section(_localctx.name, _localctx.rules));
			    
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Rule0Context extends ParserRuleContext {
		public Rule result;
		public QuestionContext question;
		public TypeDefContext typeDef;
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TypeDefContext typeDef() {
			return getRuleContext(TypeDefContext.class,0);
		}
		public Rule0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule0; }
	}

	public final Rule0Context rule0() throws RecognitionException {
		Rule0Context _localctx = new Rule0Context(_ctx, getState());
		enterRule(_localctx, 6, RULE_rule0);
		try {
			setState(69);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(63);
				((Rule0Context)_localctx).question = question();
				((Rule0Context)_localctx).result =  ((Rule0Context)_localctx).question.result; 
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(66);
				((Rule0Context)_localctx).typeDef = typeDef();
				((Rule0Context)_localctx).result =  ((Rule0Context)_localctx).typeDef.result; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public Rule result;
		public Widget widget;
		        List<Property> props =  new ArrayList<>();;
		public Token ID;
		public WidgetTypeContext widgetType;
		public PropertiesContext properties;
		public List<TerminalNode> ID() { return getTokens(QLSParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(QLSParser.ID, i);
		}
		public WidgetTypeContext widgetType() {
			return getRuleContext(WidgetTypeContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(71);
				match(T__5);
				}
				}
				setState(74); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__5 );
			setState(77); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(76);
				((QuestionContext)_localctx).ID = match(ID);
				}
				}
				setState(79); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(85);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(81);
				match(T__6);
				setState(82);
				((QuestionContext)_localctx).widgetType = widgetType();
				_localctx.widget=((QuestionContext)_localctx).widgetType.result; 
				}
			}

			setState(92);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(87);
				match(T__1);
				setState(88);
				((QuestionContext)_localctx).properties = properties();
				_localctx.props.addAll(((QuestionContext)_localctx).properties.result); 
				setState(90);
				match(T__2);
				}
			}


			        ((QuestionContext)_localctx).result =  addSource(_localctx, new QuestionRule((((QuestionContext)_localctx).ID!=null?((QuestionContext)_localctx).ID.getText():null), _localctx.widget, _localctx.props));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WidgetTypeContext extends ParserRuleContext {
		public Widget result;
		public WidgetOptionsContext widgetOptions;
		public Token STR;
		public WidgetOptionsContext widgetOptions() {
			return getRuleContext(WidgetOptionsContext.class,0);
		}
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public WidgetTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetType; }
	}

	public final WidgetTypeContext widgetType() throws RecognitionException {
		WidgetTypeContext _localctx = new WidgetTypeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_widgetType);
		try {
			setState(138);
			switch (_input.LA(1)) {
			case T__7:
				enterOuterAlt(_localctx, 1);
				{
				setState(96);
				match(T__7);
				setState(97);
				match(T__8);
				setState(98);
				((WidgetTypeContext)_localctx).widgetOptions = widgetOptions();
				setState(99);
				match(T__9);
				setState(100);
				match(T__10);
				setState(101);
				((WidgetTypeContext)_localctx).STR = match(STR);
				 ((WidgetTypeContext)_localctx).result =  addSource(_localctx, new Slider(((WidgetTypeContext)_localctx).widgetOptions.result, unQuote((((WidgetTypeContext)_localctx).STR!=null?((WidgetTypeContext)_localctx).STR.getText():null))));    
				}
				break;
			case T__11:
				enterOuterAlt(_localctx, 2);
				{
				setState(104);
				match(T__11);
				setState(105);
				match(T__8);
				setState(106);
				((WidgetTypeContext)_localctx).widgetOptions = widgetOptions();
				setState(107);
				match(T__9);
				setState(108);
				match(T__10);
				setState(109);
				((WidgetTypeContext)_localctx).STR = match(STR);
				 ((WidgetTypeContext)_localctx).result =  addSource(_localctx, new Spinbox(((WidgetTypeContext)_localctx).widgetOptions.result, unQuote((((WidgetTypeContext)_localctx).STR!=null?((WidgetTypeContext)_localctx).STR.getText():null))));   
				}
				break;
			case T__12:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				match(T__12);
				 ((WidgetTypeContext)_localctx).result =  addSource(_localctx, new TextField()); 
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 4);
				{
				setState(114);
				match(T__13);
				setState(115);
				match(T__8);
				setState(116);
				((WidgetTypeContext)_localctx).widgetOptions = widgetOptions();
				setState(117);
				match(T__9);
				setState(118);
				match(T__10);
				setState(119);
				((WidgetTypeContext)_localctx).STR = match(STR);
				 ((WidgetTypeContext)_localctx).result =  addSource(_localctx, new CheckBox(((WidgetTypeContext)_localctx).widgetOptions.result, unQuote((((WidgetTypeContext)_localctx).STR!=null?((WidgetTypeContext)_localctx).STR.getText():null)))); 
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 5);
				{
				setState(122);
				match(T__14);
				setState(123);
				match(T__8);
				setState(124);
				((WidgetTypeContext)_localctx).widgetOptions = widgetOptions();
				setState(125);
				match(T__9);
				setState(126);
				match(T__10);
				setState(127);
				((WidgetTypeContext)_localctx).STR = match(STR);
				 ((WidgetTypeContext)_localctx).result =  addSource(_localctx, new RadioButton(((WidgetTypeContext)_localctx).widgetOptions.result, unQuote((((WidgetTypeContext)_localctx).STR!=null?((WidgetTypeContext)_localctx).STR.getText():null)))); 
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 6);
				{
				setState(130);
				match(T__15);
				setState(131);
				match(T__8);
				setState(132);
				((WidgetTypeContext)_localctx).widgetOptions = widgetOptions();
				setState(133);
				match(T__9);
				setState(134);
				match(T__10);
				setState(135);
				((WidgetTypeContext)_localctx).STR = match(STR);
				 ((WidgetTypeContext)_localctx).result =  addSource(_localctx, new DropDown(((WidgetTypeContext)_localctx).widgetOptions.result, unQuote((((WidgetTypeContext)_localctx).STR!=null?((WidgetTypeContext)_localctx).STR.getText():null)))); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WidgetOptionsContext extends ParserRuleContext {
		public List<String> result;
		public Token arg1;
		public Token arg2;
		public List<TerminalNode> STR() { return getTokens(QLSParser.STR); }
		public TerminalNode STR(int i) {
			return getToken(QLSParser.STR, i);
		}
		public WidgetOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_widgetOptions; }
	}

	public final WidgetOptionsContext widgetOptions() throws RecognitionException {
		WidgetOptionsContext _localctx = new WidgetOptionsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_widgetOptions);

		        ((WidgetOptionsContext)_localctx).result =  new ArrayList<>();
		    
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			((WidgetOptionsContext)_localctx).arg1 = match(STR);
			 _localctx.result.add(unQuote((((WidgetOptionsContext)_localctx).arg1!=null?((WidgetOptionsContext)_localctx).arg1.getText():null))); 
			setState(147);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__16) {
				{
				{
				setState(142);
				match(T__16);
				setState(143);
				((WidgetOptionsContext)_localctx).arg2 = match(STR);
				 _localctx.result.add(unQuote((((WidgetOptionsContext)_localctx).arg2!=null?((WidgetOptionsContext)_localctx).arg2.getText():null)));
				}
				}
				setState(149);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDefContext extends ParserRuleContext {
		public ValueTypeRule result;
		public Widget widget =  null;;
		public ValueTypeContext valueType;
		public PropertiesContext p1;
		public WidgetTypeContext widgetType;
		public PropertiesContext p2;
		public ValueTypeContext valueType() {
			return getRuleContext(ValueTypeContext.class,0);
		}
		public List<PropertiesContext> properties() {
			return getRuleContexts(PropertiesContext.class);
		}
		public PropertiesContext properties(int i) {
			return getRuleContext(PropertiesContext.class,i);
		}
		public WidgetTypeContext widgetType() {
			return getRuleContext(WidgetTypeContext.class,0);
		}
		public TypeDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDef; }
	}

	public final TypeDefContext typeDef() throws RecognitionException {
		TypeDefContext _localctx = new TypeDefContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__10);
			setState(151);
			((TypeDefContext)_localctx).valueType = valueType();
			setState(152);
			match(T__1);
			setState(153);
			((TypeDefContext)_localctx).p1 = properties();
			setState(158);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(154);
				match(T__6);
				setState(155);
				((TypeDefContext)_localctx).widgetType = widgetType();
				((TypeDefContext)_localctx).widget = ((TypeDefContext)_localctx).widgetType.result; 
				}
			}

			setState(160);
			((TypeDefContext)_localctx).p2 = properties();
			setState(161);
			match(T__2);

			        ((TypeDefContext)_localctx).result =  addSource(_localctx, new ValueTypeRule(_localctx.widget, ((TypeDefContext)_localctx).valueType.result, ((TypeDefContext)_localctx).p1.result));
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueTypeContext extends ParserRuleContext {
		public ValueType result;
		public TerminalNode BOOLEAN() { return getToken(QLSParser.BOOLEAN, 0); }
		public TerminalNode STRING() { return getToken(QLSParser.STRING, 0); }
		public TerminalNode INTEGER() { return getToken(QLSParser.INTEGER, 0); }
		public ValueTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_valueType; }
	}

	public final ValueTypeContext valueType() throws RecognitionException {
		ValueTypeContext _localctx = new ValueTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_valueType);
		try {
			setState(170);
			switch (_input.LA(1)) {
			case BOOLEAN:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				match(BOOLEAN);
				 ((ValueTypeContext)_localctx).result =  new BooleanType(); 
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(166);
				match(STRING);
				 ((ValueTypeContext)_localctx).result =  new StringType();  
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(168);
				match(INTEGER);
				 ((ValueTypeContext)_localctx).result =  new IntegerType(); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertiesContext extends ParserRuleContext {
		public List<Property> result;
		public PropertyContext property;
		public List<PropertyContext> property() {
			return getRuleContexts(PropertyContext.class);
		}
		public PropertyContext property(int i) {
			return getRuleContext(PropertyContext.class,i);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_properties);

		        ((PropertiesContext)_localctx).result =  new ArrayList<>();
		    
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(177);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(172);
					((PropertiesContext)_localctx).property = property();
					_localctx.result.add(((PropertiesContext)_localctx).property.result); 
					}
					} 
				}
				setState(179);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyContext extends ParserRuleContext {
		public Property result;
		public Token STR;
		public Token INT;
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public TerminalNode INT() { return getToken(QLSParser.INT, 0); }
		public PropertyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_property; }
	}

	public final PropertyContext property() throws RecognitionException {
		PropertyContext _localctx = new PropertyContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_property);
		try {
			setState(204);
			switch (_input.LA(1)) {
			case T__17:
				enterOuterAlt(_localctx, 1);
				{
				setState(180);
				match(T__17);
				setState(181);
				match(T__18);
				setState(182);
				((PropertyContext)_localctx).STR = match(STR);
				((PropertyContext)_localctx).result =  addSource(_localctx, new ColorProperty(new StringLiteral(unQuote((((PropertyContext)_localctx).STR!=null?((PropertyContext)_localctx).STR.getText():null))))); 
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				match(T__19);
				setState(185);
				match(T__18);
				setState(186);
				((PropertyContext)_localctx).INT = match(INT);
				((PropertyContext)_localctx).result =  addSource(_localctx, new WidthProperty(new IntegerLiteral(Integer.valueOf((((PropertyContext)_localctx).INT!=null?((PropertyContext)_localctx).INT.getText():null))))); 
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				setState(188);
				match(T__20);
				setState(189);
				match(T__18);
				setState(190);
				((PropertyContext)_localctx).INT = match(INT);
				((PropertyContext)_localctx).result =  addSource(_localctx, new HeightProperty(new IntegerLiteral(Integer.valueOf((((PropertyContext)_localctx).INT!=null?((PropertyContext)_localctx).INT.getText():null))))); 
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				setState(192);
				match(T__21);
				setState(193);
				match(T__18);
				setState(194);
				((PropertyContext)_localctx).INT = match(INT);
				((PropertyContext)_localctx).result =  addSource(_localctx, new FontSizeProperty(new IntegerLiteral(Integer.valueOf((((PropertyContext)_localctx).INT!=null?((PropertyContext)_localctx).INT.getText():null))))); 
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				setState(196);
				match(T__22);
				setState(197);
				match(T__18);
				setState(198);
				((PropertyContext)_localctx).STR = match(STR);
				((PropertyContext)_localctx).result =  addSource(_localctx, new FontNameProperty(new StringLiteral(unQuote((((PropertyContext)_localctx).STR!=null?((PropertyContext)_localctx).STR.getText():null)))));
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				setState(200);
				match(T__23);
				setState(201);
				match(T__18);
				setState(202);
				((PropertyContext)_localctx).STR = match(STR);
				((PropertyContext)_localctx).result =  addSource(_localctx, new FontStyleProperty(new StringLiteral(unQuote((((PropertyContext)_localctx).STR!=null?((PropertyContext)_localctx).STR.getText():null)))));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Literal result;
		public Token INT;
		public Token STR;
		public Token BOOL;
		public TerminalNode INT() { return getToken(QLSParser.INT, 0); }
		public TerminalNode STR() { return getToken(QLSParser.STR, 0); }
		public TerminalNode BOOL() { return getToken(QLSParser.BOOL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		try {
			setState(212);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(206);
				((LiteralContext)_localctx).INT = match(INT);
				 ((LiteralContext)_localctx).result =  addSource(_localctx, new IntegerLiteral(Integer.valueOf((((LiteralContext)_localctx).INT!=null?((LiteralContext)_localctx).INT.getText():null)))); 
				}
				break;
			case STR:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				((LiteralContext)_localctx).STR = match(STR);
				 ((LiteralContext)_localctx).result =  addSource(_localctx, new StringLiteral((((LiteralContext)_localctx).STR!=null?((LiteralContext)_localctx).STR.getText():null))); 
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 3);
				{
				setState(210);
				((LiteralContext)_localctx).BOOL = match(BOOL);
				 ((LiteralContext)_localctx).result =  addSource(_localctx, new BooleanLiteral(Boolean.valueOf((((LiteralContext)_localctx).BOOL!=null?((LiteralContext)_localctx).BOOL.getText():null)))); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3$\u00d9\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\6\2\"\n\2\r\2\16\2#\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3/\n\3\r\3\16\3\60\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\6\4<\n\4\r\4\16\4=\3\4\3\4\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\5\5H\n\5\3\6\6\6K\n\6\r\6\16\6L\3\6\6\6P\n\6\r\6\16\6Q\3\6\3\6\3"+
		"\6\3\6\5\6X\n\6\3\6\3\6\3\6\3\6\3\6\5\6_\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\5\7\u008d\n\7\3\b\3\b\3\b\3\b\3\b\7\b\u0094\n\b\f\b\16\b\u0097"+
		"\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a1\n\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\5\n\u00ad\n\n\3\13\3\13\3\13\7\13\u00b2\n\13\f"+
		"\13\16\13\u00b5\13\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00cf\n\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u00d7\n\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\2\2\u00e5\2\32\3\2\2\2\4\'\3\2\2\2\6\64\3\2\2\2\bG\3\2\2\2\nJ\3\2\2\2"+
		"\f\u008c\3\2\2\2\16\u008e\3\2\2\2\20\u0098\3\2\2\2\22\u00ac\3\2\2\2\24"+
		"\u00b3\3\2\2\2\26\u00ce\3\2\2\2\30\u00d6\3\2\2\2\32\33\7\3\2\2\33\34\7"+
		"$\2\2\34\35\b\2\1\2\35!\7\4\2\2\36\37\5\4\3\2\37 \b\2\1\2 \"\3\2\2\2!"+
		"\36\3\2\2\2\"#\3\2\2\2#!\3\2\2\2#$\3\2\2\2$%\3\2\2\2%&\7\5\2\2&\3\3\2"+
		"\2\2\'(\7\6\2\2()\7#\2\2)*\b\3\1\2*.\7\4\2\2+,\5\6\4\2,-\b\3\1\2-/\3\2"+
		"\2\2.+\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\62\3\2\2\2\62"+
		"\63\7\5\2\2\63\5\3\2\2\2\64\65\7\7\2\2\65\66\7#\2\2\66\67\b\4\1\2\67;"+
		"\7\4\2\289\5\b\5\29:\b\4\1\2:<\3\2\2\2;8\3\2\2\2<=\3\2\2\2=;\3\2\2\2="+
		">\3\2\2\2>?\3\2\2\2?@\7\5\2\2@\7\3\2\2\2AB\5\n\6\2BC\b\5\1\2CH\3\2\2\2"+
		"DE\5\20\t\2EF\b\5\1\2FH\3\2\2\2GA\3\2\2\2GD\3\2\2\2H\t\3\2\2\2IK\7\b\2"+
		"\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NP\7$\2\2ON\3\2\2"+
		"\2PQ\3\2\2\2QO\3\2\2\2QR\3\2\2\2RW\3\2\2\2ST\7\t\2\2TU\5\f\7\2UV\b\6\1"+
		"\2VX\3\2\2\2WS\3\2\2\2WX\3\2\2\2X^\3\2\2\2YZ\7\4\2\2Z[\5\24\13\2[\\\b"+
		"\6\1\2\\]\7\5\2\2]_\3\2\2\2^Y\3\2\2\2^_\3\2\2\2_`\3\2\2\2`a\b\6\1\2a\13"+
		"\3\2\2\2bc\7\n\2\2cd\7\13\2\2de\5\16\b\2ef\7\f\2\2fg\7\r\2\2gh\7#\2\2"+
		"hi\b\7\1\2i\u008d\3\2\2\2jk\7\16\2\2kl\7\13\2\2lm\5\16\b\2mn\7\f\2\2n"+
		"o\7\r\2\2op\7#\2\2pq\b\7\1\2q\u008d\3\2\2\2rs\7\17\2\2s\u008d\b\7\1\2"+
		"tu\7\20\2\2uv\7\13\2\2vw\5\16\b\2wx\7\f\2\2xy\7\r\2\2yz\7#\2\2z{\b\7\1"+
		"\2{\u008d\3\2\2\2|}\7\21\2\2}~\7\13\2\2~\177\5\16\b\2\177\u0080\7\f\2"+
		"\2\u0080\u0081\7\r\2\2\u0081\u0082\7#\2\2\u0082\u0083\b\7\1\2\u0083\u008d"+
		"\3\2\2\2\u0084\u0085\7\22\2\2\u0085\u0086\7\13\2\2\u0086\u0087\5\16\b"+
		"\2\u0087\u0088\7\f\2\2\u0088\u0089\7\r\2\2\u0089\u008a\7#\2\2\u008a\u008b"+
		"\b\7\1\2\u008b\u008d\3\2\2\2\u008cb\3\2\2\2\u008cj\3\2\2\2\u008cr\3\2"+
		"\2\2\u008ct\3\2\2\2\u008c|\3\2\2\2\u008c\u0084\3\2\2\2\u008d\r\3\2\2\2"+
		"\u008e\u008f\7#\2\2\u008f\u0095\b\b\1\2\u0090\u0091\7\23\2\2\u0091\u0092"+
		"\7#\2\2\u0092\u0094\b\b\1\2\u0093\u0090\3\2\2\2\u0094\u0097\3\2\2\2\u0095"+
		"\u0093\3\2\2\2\u0095\u0096\3\2\2\2\u0096\17\3\2\2\2\u0097\u0095\3\2\2"+
		"\2\u0098\u0099\7\r\2\2\u0099\u009a\5\22\n\2\u009a\u009b\7\4\2\2\u009b"+
		"\u00a0\5\24\13\2\u009c\u009d\7\t\2\2\u009d\u009e\5\f\7\2\u009e\u009f\b"+
		"\t\1\2\u009f\u00a1\3\2\2\2\u00a0\u009c\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1"+
		"\u00a2\3\2\2\2\u00a2\u00a3\5\24\13\2\u00a3\u00a4\7\5\2\2\u00a4\u00a5\b"+
		"\t\1\2\u00a5\21\3\2\2\2\u00a6\u00a7\7\36\2\2\u00a7\u00ad\b\n\1\2\u00a8"+
		"\u00a9\7 \2\2\u00a9\u00ad\b\n\1\2\u00aa\u00ab\7\37\2\2\u00ab\u00ad\b\n"+
		"\1\2\u00ac\u00a6\3\2\2\2\u00ac\u00a8\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad"+
		"\23\3\2\2\2\u00ae\u00af\5\26\f\2\u00af\u00b0\b\13\1\2\u00b0\u00b2\3\2"+
		"\2\2\u00b1\u00ae\3\2\2\2\u00b2\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3"+
		"\u00b4\3\2\2\2\u00b4\25\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b6\u00b7\7\24\2"+
		"\2\u00b7\u00b8\7\25\2\2\u00b8\u00b9\7#\2\2\u00b9\u00cf\b\f\1\2\u00ba\u00bb"+
		"\7\26\2\2\u00bb\u00bc\7\25\2\2\u00bc\u00bd\7\"\2\2\u00bd\u00cf\b\f\1\2"+
		"\u00be\u00bf\7\27\2\2\u00bf\u00c0\7\25\2\2\u00c0\u00c1\7\"\2\2\u00c1\u00cf"+
		"\b\f\1\2\u00c2\u00c3\7\30\2\2\u00c3\u00c4\7\25\2\2\u00c4\u00c5\7\"\2\2"+
		"\u00c5\u00cf\b\f\1\2\u00c6\u00c7\7\31\2\2\u00c7\u00c8\7\25\2\2\u00c8\u00c9"+
		"\7#\2\2\u00c9\u00cf\b\f\1\2\u00ca\u00cb\7\32\2\2\u00cb\u00cc\7\25\2\2"+
		"\u00cc\u00cd\7#\2\2\u00cd\u00cf\b\f\1\2\u00ce\u00b6\3\2\2\2\u00ce\u00ba"+
		"\3\2\2\2\u00ce\u00be\3\2\2\2\u00ce\u00c2\3\2\2\2\u00ce\u00c6\3\2\2\2\u00ce"+
		"\u00ca\3\2\2\2\u00cf\27\3\2\2\2\u00d0\u00d1\7\"\2\2\u00d1\u00d7\b\r\1"+
		"\2\u00d2\u00d3\7#\2\2\u00d3\u00d7\b\r\1\2\u00d4\u00d5\7!\2\2\u00d5\u00d7"+
		"\b\r\1\2\u00d6\u00d0\3\2\2\2\u00d6\u00d2\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\31\3\2\2\2\21#\60=GLQW^\u008c\u0095\u00a0\u00ac\u00b3\u00ce\u00d6";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/qls\QLS.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var QLSListener = require('./QLSListener').QLSListener;
var QLSVisitor = require('./QLSVisitor').QLSVisitor;

var grammarFileName = "QLS.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u001dn\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004",
    "\r\t\r\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003",
    "\u0003\u0007\u0003!\n\u0003\f\u0003\u000e\u0003$\u000b\u0003\u0003\u0003",
    "\u0003\u0003\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0005\u0004",
    ",\n\u0004\u0003\u0005\u0003\u0005\u0007\u00050\n\u0005\f\u0005\u000e",
    "\u00053\u000b\u0005\u0003\u0005\u0003\u0005\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006",
    ">\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\b\u0003",
    "\b\u0003\b\u0003\b\u0007\bH\n\b\f\b\u000e\bK\u000b\b\u0003\b\u0003\b",
    "\u0003\t\u0003\t\u0003\t\u0003\t\u0003\n\u0003\n\u0003\u000b\u0003\u000b",
    "\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b",
    "\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b\u0003\u000b",
    "\u0005\u000bc\n\u000b\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0005",
    "\fj\n\f\u0003\r\u0003\r\u0003\r\u0002\u0002\u000e\u0002\u0004\u0006",
    "\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0002\u0003\u0003\u0002\r",
    "\u0011m\u0002\u001a\u0003\u0002\u0002\u0002\u0004\u001e\u0003\u0002",
    "\u0002\u0002\u0006+\u0003\u0002\u0002\u0002\b-\u0003\u0002\u0002\u0002",
    "\n=\u0003\u0002\u0002\u0002\f?\u0003\u0002\u0002\u0002\u000eC\u0003",
    "\u0002\u0002\u0002\u0010N\u0003\u0002\u0002\u0002\u0012R\u0003\u0002",
    "\u0002\u0002\u0014b\u0003\u0002\u0002\u0002\u0016i\u0003\u0002\u0002",
    "\u0002\u0018k\u0003\u0002\u0002\u0002\u001a\u001b\u0007\u0003\u0002",
    "\u0002\u001b\u001c\u0007\u001c\u0002\u0002\u001c\u001d\u0005\u0004\u0003",
    "\u0002\u001d\u0003\u0003\u0002\u0002\u0002\u001e\"\u0007\u0004\u0002",
    "\u0002\u001f!\u0005\u0006\u0004\u0002 \u001f\u0003\u0002\u0002\u0002",
    "!$\u0003\u0002\u0002\u0002\" \u0003\u0002\u0002\u0002\"#\u0003\u0002",
    "\u0002\u0002#%\u0003\u0002\u0002\u0002$\"\u0003\u0002\u0002\u0002%&",
    "\u0007\u0005\u0002\u0002&\u0005\u0003\u0002\u0002\u0002\'(\u0007\u0006",
    "\u0002\u0002()\u0007\u001c\u0002\u0002),\u0005\b\u0005\u0002*,\u0005",
    "\f\u0007\u0002+\'\u0003\u0002\u0002\u0002+*\u0003\u0002\u0002\u0002",
    ",\u0007\u0003\u0002\u0002\u0002-1\u0007\u0004\u0002\u0002.0\u0005\n",
    "\u0006\u0002/.\u0003\u0002\u0002\u000203\u0003\u0002\u0002\u00021/\u0003",
    "\u0002\u0002\u000212\u0003\u0002\u0002\u000224\u0003\u0002\u0002\u0002",
    "31\u0003\u0002\u0002\u000245\u0007\u0005\u0002\u00025\t\u0003\u0002",
    "\u0002\u000267\u0007\u0007\u0002\u000278\u0007\u001b\u0002\u00028>\u0005",
    "\b\u0005\u00029:\u0007\b\u0002\u0002:;\u0007\u001c\u0002\u0002;>\u0005",
    "\u000e\b\u0002<>\u0005\f\u0007\u0002=6\u0003\u0002\u0002\u0002=9\u0003",
    "\u0002\u0002\u0002=<\u0003\u0002\u0002\u0002>\u000b\u0003\u0002\u0002",
    "\u0002?@\u0007\t\u0002\u0002@A\u0005\u0012\n\u0002AB\u0005\u000e\b\u0002",
    "B\r\u0003\u0002\u0002\u0002CI\u0007\u0004\u0002\u0002DE\u0005\u0010",
    "\t\u0002EF\u0007\n\u0002\u0002FH\u0003\u0002\u0002\u0002GD\u0003\u0002",
    "\u0002\u0002HK\u0003\u0002\u0002\u0002IG\u0003\u0002\u0002\u0002IJ\u0003",
    "\u0002\u0002\u0002JL\u0003\u0002\u0002\u0002KI\u0003\u0002\u0002\u0002",
    "LM\u0007\u0005\u0002\u0002M\u000f\u0003\u0002\u0002\u0002NO\u0007\u000b",
    "\u0002\u0002OP\u0007\f\u0002\u0002PQ\u0005\u0014\u000b\u0002Q\u0011",
    "\u0003\u0002\u0002\u0002RS\t\u0002\u0002\u0002S\u0013\u0003\u0002\u0002",
    "\u0002Tc\u0007\u0012\u0002\u0002Uc\u0007\u0013\u0002\u0002Vc\u0007\u0014",
    "\u0002\u0002WX\u0007\u0015\u0002\u0002XY\u0007\u0016\u0002\u0002YZ\u0005",
    "\u0016\f\u0002Z[\u0007\u0017\u0002\u0002[c\u0003\u0002\u0002\u0002\\",
    "c\u0007\u0018\u0002\u0002]^\u0007\u0019\u0002\u0002^_\u0007\u0016\u0002",
    "\u0002_`\u0005\u0016\f\u0002`a\u0007\u0017\u0002\u0002ac\u0003\u0002",
    "\u0002\u0002bT\u0003\u0002\u0002\u0002bU\u0003\u0002\u0002\u0002bV\u0003",
    "\u0002\u0002\u0002bW\u0003\u0002\u0002\u0002b\\\u0003\u0002\u0002\u0002",
    "b]\u0003\u0002\u0002\u0002c\u0015\u0003\u0002\u0002\u0002de\u0005\u0018",
    "\r\u0002ef\u0007\u001a\u0002\u0002fg\u0005\u0016\f\u0002gj\u0003\u0002",
    "\u0002\u0002hj\u0005\u0018\r\u0002id\u0003\u0002\u0002\u0002ih\u0003",
    "\u0002\u0002\u0002j\u0017\u0003\u0002\u0002\u0002kl\u0007\u001b\u0002",
    "\u0002l\u0019\u0003\u0002\u0002\u0002\t\"+1=Ibi"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ 'null', "'stylesheet'", "'{'", "'}'", "'page'", "'section'", 
                     "'question'", "'default'", "';'", "'widget'", "':'", 
                     "'boolean'", "'string'", "'integer'", "'float'", "'money'", 
                     "'slider'", "'spinbox'", "'text'", "'radio'", "'('", 
                     "')'", "'checkbox'", "'dropdown'", "','" ];

var symbolicNames = [ 'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', 'null', 'null', "STRING_LITERAL", 
                      "IDENTIFIER", "WHITESPACE" ];

var ruleNames =  [ "stylesheet", "stylesheetBlock", "stylesheetStatement", 
                   "pageBlock", "pageStatement", "defaultWidgetStatement", 
                   "widgetBlock", "widgetStatement", "type", "widgetType", 
                   "valueOptions", "valueOption" ];

function QLSParser (input) {
	antlr4.Parser.call(this, input);
    this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
    this.ruleNames = ruleNames;
    this.literalNames = literalNames;
    this.symbolicNames = symbolicNames;
    return this;
}

QLSParser.prototype = Object.create(antlr4.Parser.prototype);
QLSParser.prototype.constructor = QLSParser;

Object.defineProperty(QLSParser.prototype, "atn", {
	get : function() {
		return atn;
	}
});

QLSParser.EOF = antlr4.Token.EOF;
QLSParser.T__0 = 1;
QLSParser.T__1 = 2;
QLSParser.T__2 = 3;
QLSParser.T__3 = 4;
QLSParser.T__4 = 5;
QLSParser.T__5 = 6;
QLSParser.T__6 = 7;
QLSParser.T__7 = 8;
QLSParser.T__8 = 9;
QLSParser.T__9 = 10;
QLSParser.T__10 = 11;
QLSParser.T__11 = 12;
QLSParser.T__12 = 13;
QLSParser.T__13 = 14;
QLSParser.T__14 = 15;
QLSParser.T__15 = 16;
QLSParser.T__16 = 17;
QLSParser.T__17 = 18;
QLSParser.T__18 = 19;
QLSParser.T__19 = 20;
QLSParser.T__20 = 21;
QLSParser.T__21 = 22;
QLSParser.T__22 = 23;
QLSParser.T__23 = 24;
QLSParser.STRING_LITERAL = 25;
QLSParser.IDENTIFIER = 26;
QLSParser.WHITESPACE = 27;

QLSParser.RULE_stylesheet = 0;
QLSParser.RULE_stylesheetBlock = 1;
QLSParser.RULE_stylesheetStatement = 2;
QLSParser.RULE_pageBlock = 3;
QLSParser.RULE_pageStatement = 4;
QLSParser.RULE_defaultWidgetStatement = 5;
QLSParser.RULE_widgetBlock = 6;
QLSParser.RULE_widgetStatement = 7;
QLSParser.RULE_type = 8;
QLSParser.RULE_widgetType = 9;
QLSParser.RULE_valueOptions = 10;
QLSParser.RULE_valueOption = 11;

function StylesheetContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_stylesheet;
    return this;
}

StylesheetContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StylesheetContext.prototype.constructor = StylesheetContext;

StylesheetContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLSParser.IDENTIFIER, 0);
};

StylesheetContext.prototype.stylesheetBlock = function() {
    return this.getTypedRuleContext(StylesheetBlockContext,0);
};

StylesheetContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterStylesheet(this);
	}
};

StylesheetContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitStylesheet(this);
	}
};

StylesheetContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitStylesheet(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.StylesheetContext = StylesheetContext;

QLSParser.prototype.stylesheet = function() {

    var localctx = new StylesheetContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, QLSParser.RULE_stylesheet);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 24;
        this.match(QLSParser.T__0);
        this.state = 25;
        this.match(QLSParser.IDENTIFIER);
        this.state = 26;
        this.stylesheetBlock();
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function StylesheetBlockContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_stylesheetBlock;
    return this;
}

StylesheetBlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StylesheetBlockContext.prototype.constructor = StylesheetBlockContext;

StylesheetBlockContext.prototype.stylesheetStatement = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(StylesheetStatementContext);
    } else {
        return this.getTypedRuleContext(StylesheetStatementContext,i);
    }
};

StylesheetBlockContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterStylesheetBlock(this);
	}
};

StylesheetBlockContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitStylesheetBlock(this);
	}
};

StylesheetBlockContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitStylesheetBlock(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.StylesheetBlockContext = StylesheetBlockContext;

QLSParser.prototype.stylesheetBlock = function() {

    var localctx = new StylesheetBlockContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, QLSParser.RULE_stylesheetBlock);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 28;
        this.match(QLSParser.T__1);
        this.state = 32;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLSParser.T__3 || _la===QLSParser.T__6) {
            this.state = 29;
            this.stylesheetStatement();
            this.state = 34;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 35;
        this.match(QLSParser.T__2);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function StylesheetStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_stylesheetStatement;
    return this;
}

StylesheetStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StylesheetStatementContext.prototype.constructor = StylesheetStatementContext;

StylesheetStatementContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLSParser.IDENTIFIER, 0);
};

StylesheetStatementContext.prototype.pageBlock = function() {
    return this.getTypedRuleContext(PageBlockContext,0);
};

StylesheetStatementContext.prototype.defaultWidgetStatement = function() {
    return this.getTypedRuleContext(DefaultWidgetStatementContext,0);
};

StylesheetStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterStylesheetStatement(this);
	}
};

StylesheetStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitStylesheetStatement(this);
	}
};

StylesheetStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitStylesheetStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.StylesheetStatementContext = StylesheetStatementContext;

QLSParser.prototype.stylesheetStatement = function() {

    var localctx = new StylesheetStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, QLSParser.RULE_stylesheetStatement);
    try {
        this.state = 41;
        switch(this._input.LA(1)) {
        case QLSParser.T__3:
            this.enterOuterAlt(localctx, 1);
            this.state = 37;
            this.match(QLSParser.T__3);
            this.state = 38;
            this.match(QLSParser.IDENTIFIER);
            this.state = 39;
            this.pageBlock();
            break;
        case QLSParser.T__6:
            this.enterOuterAlt(localctx, 2);
            this.state = 40;
            this.defaultWidgetStatement();
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function PageBlockContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_pageBlock;
    return this;
}

PageBlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
PageBlockContext.prototype.constructor = PageBlockContext;

PageBlockContext.prototype.pageStatement = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(PageStatementContext);
    } else {
        return this.getTypedRuleContext(PageStatementContext,i);
    }
};

PageBlockContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterPageBlock(this);
	}
};

PageBlockContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitPageBlock(this);
	}
};

PageBlockContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitPageBlock(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.PageBlockContext = PageBlockContext;

QLSParser.prototype.pageBlock = function() {

    var localctx = new PageBlockContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, QLSParser.RULE_pageBlock);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 43;
        this.match(QLSParser.T__1);
        this.state = 47;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLSParser.T__4) | (1 << QLSParser.T__5) | (1 << QLSParser.T__6))) !== 0)) {
            this.state = 44;
            this.pageStatement();
            this.state = 49;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 50;
        this.match(QLSParser.T__2);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function PageStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_pageStatement;
    return this;
}

PageStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
PageStatementContext.prototype.constructor = PageStatementContext;

PageStatementContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLSParser.STRING_LITERAL, 0);
};

PageStatementContext.prototype.pageBlock = function() {
    return this.getTypedRuleContext(PageBlockContext,0);
};

PageStatementContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLSParser.IDENTIFIER, 0);
};

PageStatementContext.prototype.widgetBlock = function() {
    return this.getTypedRuleContext(WidgetBlockContext,0);
};

PageStatementContext.prototype.defaultWidgetStatement = function() {
    return this.getTypedRuleContext(DefaultWidgetStatementContext,0);
};

PageStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterPageStatement(this);
	}
};

PageStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitPageStatement(this);
	}
};

PageStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitPageStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.PageStatementContext = PageStatementContext;

QLSParser.prototype.pageStatement = function() {

    var localctx = new PageStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, QLSParser.RULE_pageStatement);
    try {
        this.state = 59;
        switch(this._input.LA(1)) {
        case QLSParser.T__4:
            this.enterOuterAlt(localctx, 1);
            this.state = 52;
            this.match(QLSParser.T__4);
            this.state = 53;
            this.match(QLSParser.STRING_LITERAL);
            this.state = 54;
            this.pageBlock();
            break;
        case QLSParser.T__5:
            this.enterOuterAlt(localctx, 2);
            this.state = 55;
            this.match(QLSParser.T__5);
            this.state = 56;
            this.match(QLSParser.IDENTIFIER);
            this.state = 57;
            this.widgetBlock();
            break;
        case QLSParser.T__6:
            this.enterOuterAlt(localctx, 3);
            this.state = 58;
            this.defaultWidgetStatement();
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function DefaultWidgetStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_defaultWidgetStatement;
    return this;
}

DefaultWidgetStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
DefaultWidgetStatementContext.prototype.constructor = DefaultWidgetStatementContext;

DefaultWidgetStatementContext.prototype.type = function() {
    return this.getTypedRuleContext(TypeContext,0);
};

DefaultWidgetStatementContext.prototype.widgetBlock = function() {
    return this.getTypedRuleContext(WidgetBlockContext,0);
};

DefaultWidgetStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterDefaultWidgetStatement(this);
	}
};

DefaultWidgetStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitDefaultWidgetStatement(this);
	}
};

DefaultWidgetStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitDefaultWidgetStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.DefaultWidgetStatementContext = DefaultWidgetStatementContext;

QLSParser.prototype.defaultWidgetStatement = function() {

    var localctx = new DefaultWidgetStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, QLSParser.RULE_defaultWidgetStatement);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 61;
        this.match(QLSParser.T__6);
        this.state = 62;
        this.type();
        this.state = 63;
        this.widgetBlock();
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function WidgetBlockContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_widgetBlock;
    return this;
}

WidgetBlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
WidgetBlockContext.prototype.constructor = WidgetBlockContext;

WidgetBlockContext.prototype.widgetStatement = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(WidgetStatementContext);
    } else {
        return this.getTypedRuleContext(WidgetStatementContext,i);
    }
};

WidgetBlockContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterWidgetBlock(this);
	}
};

WidgetBlockContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitWidgetBlock(this);
	}
};

WidgetBlockContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitWidgetBlock(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.WidgetBlockContext = WidgetBlockContext;

QLSParser.prototype.widgetBlock = function() {

    var localctx = new WidgetBlockContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, QLSParser.RULE_widgetBlock);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 65;
        this.match(QLSParser.T__1);
        this.state = 71;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLSParser.T__8) {
            this.state = 66;
            this.widgetStatement();
            this.state = 67;
            this.match(QLSParser.T__7);
            this.state = 73;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 74;
        this.match(QLSParser.T__2);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function WidgetStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_widgetStatement;
    return this;
}

WidgetStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
WidgetStatementContext.prototype.constructor = WidgetStatementContext;

WidgetStatementContext.prototype.widgetType = function() {
    return this.getTypedRuleContext(WidgetTypeContext,0);
};

WidgetStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterWidgetStatement(this);
	}
};

WidgetStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitWidgetStatement(this);
	}
};

WidgetStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitWidgetStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.WidgetStatementContext = WidgetStatementContext;

QLSParser.prototype.widgetStatement = function() {

    var localctx = new WidgetStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, QLSParser.RULE_widgetStatement);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 76;
        this.match(QLSParser.T__8);
        this.state = 77;
        this.match(QLSParser.T__9);
        this.state = 78;
        this.widgetType();
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function TypeContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_type;
    return this;
}

TypeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
TypeContext.prototype.constructor = TypeContext;


TypeContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterType(this);
	}
};

TypeContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitType(this);
	}
};

TypeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitType(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.TypeContext = TypeContext;

QLSParser.prototype.type = function() {

    var localctx = new TypeContext(this, this._ctx, this.state);
    this.enterRule(localctx, 16, QLSParser.RULE_type);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 80;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLSParser.T__10) | (1 << QLSParser.T__11) | (1 << QLSParser.T__12) | (1 << QLSParser.T__13) | (1 << QLSParser.T__14))) !== 0))) {
        this._errHandler.recoverInline(this);
        }
        else {
            this.consume();
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function WidgetTypeContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_widgetType;
    return this;
}

WidgetTypeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
WidgetTypeContext.prototype.constructor = WidgetTypeContext;

WidgetTypeContext.prototype.valueOptions = function() {
    return this.getTypedRuleContext(ValueOptionsContext,0);
};

WidgetTypeContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterWidgetType(this);
	}
};

WidgetTypeContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitWidgetType(this);
	}
};

WidgetTypeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitWidgetType(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.WidgetTypeContext = WidgetTypeContext;

QLSParser.prototype.widgetType = function() {

    var localctx = new WidgetTypeContext(this, this._ctx, this.state);
    this.enterRule(localctx, 18, QLSParser.RULE_widgetType);
    try {
        this.state = 96;
        switch(this._input.LA(1)) {
        case QLSParser.T__15:
            this.enterOuterAlt(localctx, 1);
            this.state = 82;
            this.match(QLSParser.T__15);
            break;
        case QLSParser.T__16:
            this.enterOuterAlt(localctx, 2);
            this.state = 83;
            this.match(QLSParser.T__16);
            break;
        case QLSParser.T__17:
            this.enterOuterAlt(localctx, 3);
            this.state = 84;
            this.match(QLSParser.T__17);
            break;
        case QLSParser.T__18:
            this.enterOuterAlt(localctx, 4);
            this.state = 85;
            this.match(QLSParser.T__18);
            this.state = 86;
            this.match(QLSParser.T__19);
            this.state = 87;
            this.valueOptions();
            this.state = 88;
            this.match(QLSParser.T__20);
            break;
        case QLSParser.T__21:
            this.enterOuterAlt(localctx, 5);
            this.state = 90;
            this.match(QLSParser.T__21);
            break;
        case QLSParser.T__22:
            this.enterOuterAlt(localctx, 6);
            this.state = 91;
            this.match(QLSParser.T__22);
            this.state = 92;
            this.match(QLSParser.T__19);
            this.state = 93;
            this.valueOptions();
            this.state = 94;
            this.match(QLSParser.T__20);
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ValueOptionsContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_valueOptions;
    return this;
}

ValueOptionsContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ValueOptionsContext.prototype.constructor = ValueOptionsContext;

ValueOptionsContext.prototype.valueOption = function() {
    return this.getTypedRuleContext(ValueOptionContext,0);
};

ValueOptionsContext.prototype.valueOptions = function() {
    return this.getTypedRuleContext(ValueOptionsContext,0);
};

ValueOptionsContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterValueOptions(this);
	}
};

ValueOptionsContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitValueOptions(this);
	}
};

ValueOptionsContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitValueOptions(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.ValueOptionsContext = ValueOptionsContext;

QLSParser.prototype.valueOptions = function() {

    var localctx = new ValueOptionsContext(this, this._ctx, this.state);
    this.enterRule(localctx, 20, QLSParser.RULE_valueOptions);
    try {
        this.state = 103;
        var la_ = this._interp.adaptivePredict(this._input,6,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 98;
            this.valueOption();
            this.state = 99;
            this.match(QLSParser.T__23);
            this.state = 100;
            this.valueOptions();
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 102;
            this.valueOption();
            break;

        }
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};

function ValueOptionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_valueOption;
    return this;
}

ValueOptionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ValueOptionContext.prototype.constructor = ValueOptionContext;

ValueOptionContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLSParser.STRING_LITERAL, 0);
};

ValueOptionContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterValueOption(this);
	}
};

ValueOptionContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitValueOption(this);
	}
};

ValueOptionContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitValueOption(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.ValueOptionContext = ValueOptionContext;

QLSParser.prototype.valueOption = function() {

    var localctx = new ValueOptionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 22, QLSParser.RULE_valueOption);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 105;
        this.match(QLSParser.STRING_LITERAL);
    } catch (re) {
    	if(re instanceof antlr4.error.RecognitionException) {
	        localctx.exception = re;
	        this._errHandler.reportError(this, re);
	        this._errHandler.recover(this, re);
	    } else {
	    	throw re;
	    }
    } finally {
        this.exitRule();
    }
    return localctx;
};


exports.QLSParser = QLSParser;

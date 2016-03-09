// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/qls\QLS.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var QLSListener = require('./QLSListener').QLSListener;
var QLSVisitor = require('./QLSVisitor').QLSVisitor;

var grammarFileName = "QLS.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u001fq\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004",
    "\r\t\r\u0004\u000e\t\u000e\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0002\u0003\u0003\u0003\u0003\u0007\u0003#\n\u0003\f\u0003\u000e\u0003",
    "&\u000b\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0003",
    "\u0004\u0003\u0004\u0005\u0004.\n\u0004\u0003\u0005\u0003\u0005\u0007",
    "\u00052\n\u0005\f\u0005\u000e\u00055\u000b\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0005\u0006@\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\b\u0003\b\u0003\b\u0003\b\u0007\bJ\n\b\f\b\u000e",
    "\bM\u000b\b\u0003\b\u0003\b\u0003\t\u0003\t\u0003\t\u0003\t\u0003\t",
    "\u0005\tV\n\t\u0003\n\u0003\n\u0003\n\u0003\n\u0005\n\\\n\n\u0003\u000b",
    "\u0003\u000b\u0003\u000b\u0003\u000b\u0003\f\u0003\f\u0003\f\u0003\f",
    "\u0005\ff\n\f\u0003\r\u0003\r\u0003\r\u0003\r\u0003\r\u0005\rm\n\r\u0003",
    "\u000e\u0003\u000e\u0003\u000e\u0002\u0002\u000f\u0002\u0004\u0006\b",
    "\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u0002\u0003\u0003\u0002",
    "\u0013\u0017q\u0002\u001c\u0003\u0002\u0002\u0002\u0004 \u0003\u0002",
    "\u0002\u0002\u0006-\u0003\u0002\u0002\u0002\b/\u0003\u0002\u0002\u0002",
    "\n?\u0003\u0002\u0002\u0002\fA\u0003\u0002\u0002\u0002\u000eE\u0003",
    "\u0002\u0002\u0002\u0010U\u0003\u0002\u0002\u0002\u0012[\u0003\u0002",
    "\u0002\u0002\u0014]\u0003\u0002\u0002\u0002\u0016e\u0003\u0002\u0002",
    "\u0002\u0018l\u0003\u0002\u0002\u0002\u001an\u0003\u0002\u0002\u0002",
    "\u001c\u001d\u0007\u0003\u0002\u0002\u001d\u001e\u0007\u001e\u0002\u0002",
    "\u001e\u001f\u0005\u0004\u0003\u0002\u001f\u0003\u0003\u0002\u0002\u0002",
    " $\u0007\u0004\u0002\u0002!#\u0005\u0006\u0004\u0002\"!\u0003\u0002",
    "\u0002\u0002#&\u0003\u0002\u0002\u0002$\"\u0003\u0002\u0002\u0002$%",
    "\u0003\u0002\u0002\u0002%\'\u0003\u0002\u0002\u0002&$\u0003\u0002\u0002",
    "\u0002\'(\u0007\u0005\u0002\u0002(\u0005\u0003\u0002\u0002\u0002)*\u0007",
    "\u0006\u0002\u0002*+\u0007\u001e\u0002\u0002+.\u0005\b\u0005\u0002,",
    ".\u0005\f\u0007\u0002-)\u0003\u0002\u0002\u0002-,\u0003\u0002\u0002",
    "\u0002.\u0007\u0003\u0002\u0002\u0002/3\u0007\u0004\u0002\u000202\u0005",
    "\n\u0006\u000210\u0003\u0002\u0002\u000225\u0003\u0002\u0002\u00023",
    "1\u0003\u0002\u0002\u000234\u0003\u0002\u0002\u000246\u0003\u0002\u0002",
    "\u000253\u0003\u0002\u0002\u000267\u0007\u0005\u0002\u00027\t\u0003",
    "\u0002\u0002\u000289\u0007\u0007\u0002\u00029:\u0007\u0018\u0002\u0002",
    ":@\u0005\b\u0005\u0002;<\u0007\b\u0002\u0002<=\u0007\u001e\u0002\u0002",
    "=@\u0005\u000e\b\u0002>@\u0005\f\u0007\u0002?8\u0003\u0002\u0002\u0002",
    "?;\u0003\u0002\u0002\u0002?>\u0003\u0002\u0002\u0002@\u000b\u0003\u0002",
    "\u0002\u0002AB\u0007\t\u0002\u0002BC\u0005\u001a\u000e\u0002CD\u0005",
    "\u000e\b\u0002D\r\u0003\u0002\u0002\u0002EK\u0007\u0004\u0002\u0002",
    "FG\u0005\u0010\t\u0002GH\u0007\n\u0002\u0002HJ\u0003\u0002\u0002\u0002",
    "IF\u0003\u0002\u0002\u0002JM\u0003\u0002\u0002\u0002KI\u0003\u0002\u0002",
    "\u0002KL\u0003\u0002\u0002\u0002LN\u0003\u0002\u0002\u0002MK\u0003\u0002",
    "\u0002\u0002NO\u0007\u0005\u0002\u0002O\u000f\u0003\u0002\u0002\u0002",
    "PQ\u0007\u000b\u0002\u0002QV\u0005\u0012\n\u0002RS\u0007\u001e\u0002",
    "\u0002ST\u0007\f\u0002\u0002TV\u0005\u0018\r\u0002UP\u0003\u0002\u0002",
    "\u0002UR\u0003\u0002\u0002\u0002V\u0011\u0003\u0002\u0002\u0002W\\\u0007",
    "\r\u0002\u0002X\\\u0007\u000e\u0002\u0002YZ\u0007\u000f\u0002\u0002",
    "Z\\\u0005\u0014\u000b\u0002[W\u0003\u0002\u0002\u0002[X\u0003\u0002",
    "\u0002\u0002[Y\u0003\u0002\u0002\u0002\\\u0013\u0003\u0002\u0002\u0002",
    "]^\u0007\u0010\u0002\u0002^_\u0005\u0016\f\u0002_`\u0007\u0011\u0002",
    "\u0002`\u0015\u0003\u0002\u0002\u0002ab\u0007\u0018\u0002\u0002bc\u0007",
    "\u0012\u0002\u0002cf\u0005\u0016\f\u0002df\u0007\u0018\u0002\u0002e",
    "a\u0003\u0002\u0002\u0002ed\u0003\u0002\u0002\u0002f\u0017\u0003\u0002",
    "\u0002\u0002gm\u0007\u001d\u0002\u0002hm\u0007\u0018\u0002\u0002im\u0007",
    "\u0019\u0002\u0002jm\u0007\u001a\u0002\u0002km\u0007\u001b\u0002\u0002",
    "lg\u0003\u0002\u0002\u0002lh\u0003\u0002\u0002\u0002li\u0003\u0002\u0002",
    "\u0002lj\u0003\u0002\u0002\u0002lk\u0003\u0002\u0002\u0002m\u0019\u0003",
    "\u0002\u0002\u0002no\t\u0002\u0002\u0002o\u001b\u0003\u0002\u0002\u0002",
    "\u000b$-3?KU[el"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ 'null', "'stylesheet'", "'{'", "'}'", "'page'", "'section'", 
                     "'question'", "'default'", "';'", "'widget'", "':'", 
                     "'slider'", "'text'", "'radio'", "'('", "')'", "','", 
                     "'boolean'", "'string'", "'integer'", "'float'", "'money'" ];

var symbolicNames = [ 'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', "STRING_LITERAL", "INTEGER_LITERAL", "FLOAT_LITERAL", 
                      "MONEY_LITERAL", "MONEY_LITERAL_CENTS", "BOOLEAN_LITERAL", 
                      "IDENTIFIER", "WHITESPACE" ];

var ruleNames =  [ "stylesheet", "stylesheetBlock", "stylesheetStatement", 
                   "pageBlock", "pageStatement", "typeDefaultStatement", 
                   "styleBlock", "styleStatement", "widgetType", "valueOptionList", 
                   "valueOptions", "literal", "type" ];

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
QLSParser.STRING_LITERAL = 22;
QLSParser.INTEGER_LITERAL = 23;
QLSParser.FLOAT_LITERAL = 24;
QLSParser.MONEY_LITERAL = 25;
QLSParser.MONEY_LITERAL_CENTS = 26;
QLSParser.BOOLEAN_LITERAL = 27;
QLSParser.IDENTIFIER = 28;
QLSParser.WHITESPACE = 29;

QLSParser.RULE_stylesheet = 0;
QLSParser.RULE_stylesheetBlock = 1;
QLSParser.RULE_stylesheetStatement = 2;
QLSParser.RULE_pageBlock = 3;
QLSParser.RULE_pageStatement = 4;
QLSParser.RULE_typeDefaultStatement = 5;
QLSParser.RULE_styleBlock = 6;
QLSParser.RULE_styleStatement = 7;
QLSParser.RULE_widgetType = 8;
QLSParser.RULE_valueOptionList = 9;
QLSParser.RULE_valueOptions = 10;
QLSParser.RULE_literal = 11;
QLSParser.RULE_type = 12;

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
        this.state = 26;
        this.match(QLSParser.T__0);
        this.state = 27;
        this.match(QLSParser.IDENTIFIER);
        this.state = 28;
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
        this.state = 30;
        this.match(QLSParser.T__1);
        this.state = 34;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLSParser.T__3 || _la===QLSParser.T__6) {
            this.state = 31;
            this.stylesheetStatement();
            this.state = 36;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 37;
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


 
StylesheetStatementContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function TypeDefaultStylesheetStatementContext(parser, ctx) {
	StylesheetStatementContext.call(this, parser);
    StylesheetStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

TypeDefaultStylesheetStatementContext.prototype = Object.create(StylesheetStatementContext.prototype);
TypeDefaultStylesheetStatementContext.prototype.constructor = TypeDefaultStylesheetStatementContext;

QLSParser.TypeDefaultStylesheetStatementContext = TypeDefaultStylesheetStatementContext;

TypeDefaultStylesheetStatementContext.prototype.typeDefaultStatement = function() {
    return this.getTypedRuleContext(TypeDefaultStatementContext,0);
};
TypeDefaultStylesheetStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterTypeDefaultStylesheetStatement(this);
	}
};

TypeDefaultStylesheetStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitTypeDefaultStylesheetStatement(this);
	}
};

TypeDefaultStylesheetStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitTypeDefaultStylesheetStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function PageStylesheetStatementContext(parser, ctx) {
	StylesheetStatementContext.call(this, parser);
    StylesheetStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

PageStylesheetStatementContext.prototype = Object.create(StylesheetStatementContext.prototype);
PageStylesheetStatementContext.prototype.constructor = PageStylesheetStatementContext;

QLSParser.PageStylesheetStatementContext = PageStylesheetStatementContext;

PageStylesheetStatementContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLSParser.IDENTIFIER, 0);
};

PageStylesheetStatementContext.prototype.pageBlock = function() {
    return this.getTypedRuleContext(PageBlockContext,0);
};
PageStylesheetStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterPageStylesheetStatement(this);
	}
};

PageStylesheetStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitPageStylesheetStatement(this);
	}
};

PageStylesheetStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitPageStylesheetStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLSParser.StylesheetStatementContext = StylesheetStatementContext;

QLSParser.prototype.stylesheetStatement = function() {

    var localctx = new StylesheetStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, QLSParser.RULE_stylesheetStatement);
    try {
        this.state = 43;
        switch(this._input.LA(1)) {
        case QLSParser.T__3:
            localctx = new PageStylesheetStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 39;
            this.match(QLSParser.T__3);
            this.state = 40;
            this.match(QLSParser.IDENTIFIER);
            this.state = 41;
            this.pageBlock();
            break;
        case QLSParser.T__6:
            localctx = new TypeDefaultStylesheetStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 42;
            this.typeDefaultStatement();
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
        this.state = 45;
        this.match(QLSParser.T__1);
        this.state = 49;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLSParser.T__4) | (1 << QLSParser.T__5) | (1 << QLSParser.T__6))) !== 0)) {
            this.state = 46;
            this.pageStatement();
            this.state = 51;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 52;
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


 
PageStatementContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function SectionPageStatementContext(parser, ctx) {
	PageStatementContext.call(this, parser);
    PageStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

SectionPageStatementContext.prototype = Object.create(PageStatementContext.prototype);
SectionPageStatementContext.prototype.constructor = SectionPageStatementContext;

QLSParser.SectionPageStatementContext = SectionPageStatementContext;

SectionPageStatementContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLSParser.STRING_LITERAL, 0);
};

SectionPageStatementContext.prototype.pageBlock = function() {
    return this.getTypedRuleContext(PageBlockContext,0);
};
SectionPageStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterSectionPageStatement(this);
	}
};

SectionPageStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitSectionPageStatement(this);
	}
};

SectionPageStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitSectionPageStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function QuestionPageStatementContext(parser, ctx) {
	PageStatementContext.call(this, parser);
    PageStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

QuestionPageStatementContext.prototype = Object.create(PageStatementContext.prototype);
QuestionPageStatementContext.prototype.constructor = QuestionPageStatementContext;

QLSParser.QuestionPageStatementContext = QuestionPageStatementContext;

QuestionPageStatementContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLSParser.IDENTIFIER, 0);
};

QuestionPageStatementContext.prototype.styleBlock = function() {
    return this.getTypedRuleContext(StyleBlockContext,0);
};
QuestionPageStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterQuestionPageStatement(this);
	}
};

QuestionPageStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitQuestionPageStatement(this);
	}
};

QuestionPageStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitQuestionPageStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function TypeDefaultPageStatementContext(parser, ctx) {
	PageStatementContext.call(this, parser);
    PageStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

TypeDefaultPageStatementContext.prototype = Object.create(PageStatementContext.prototype);
TypeDefaultPageStatementContext.prototype.constructor = TypeDefaultPageStatementContext;

QLSParser.TypeDefaultPageStatementContext = TypeDefaultPageStatementContext;

TypeDefaultPageStatementContext.prototype.typeDefaultStatement = function() {
    return this.getTypedRuleContext(TypeDefaultStatementContext,0);
};
TypeDefaultPageStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterTypeDefaultPageStatement(this);
	}
};

TypeDefaultPageStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitTypeDefaultPageStatement(this);
	}
};

TypeDefaultPageStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitTypeDefaultPageStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLSParser.PageStatementContext = PageStatementContext;

QLSParser.prototype.pageStatement = function() {

    var localctx = new PageStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, QLSParser.RULE_pageStatement);
    try {
        this.state = 61;
        switch(this._input.LA(1)) {
        case QLSParser.T__4:
            localctx = new SectionPageStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 54;
            this.match(QLSParser.T__4);
            this.state = 55;
            this.match(QLSParser.STRING_LITERAL);
            this.state = 56;
            this.pageBlock();
            break;
        case QLSParser.T__5:
            localctx = new QuestionPageStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 57;
            this.match(QLSParser.T__5);
            this.state = 58;
            this.match(QLSParser.IDENTIFIER);
            this.state = 59;
            this.styleBlock();
            break;
        case QLSParser.T__6:
            localctx = new TypeDefaultPageStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 60;
            this.typeDefaultStatement();
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

function TypeDefaultStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_typeDefaultStatement;
    return this;
}

TypeDefaultStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
TypeDefaultStatementContext.prototype.constructor = TypeDefaultStatementContext;

TypeDefaultStatementContext.prototype.type = function() {
    return this.getTypedRuleContext(TypeContext,0);
};

TypeDefaultStatementContext.prototype.styleBlock = function() {
    return this.getTypedRuleContext(StyleBlockContext,0);
};

TypeDefaultStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterTypeDefaultStatement(this);
	}
};

TypeDefaultStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitTypeDefaultStatement(this);
	}
};

TypeDefaultStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitTypeDefaultStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.TypeDefaultStatementContext = TypeDefaultStatementContext;

QLSParser.prototype.typeDefaultStatement = function() {

    var localctx = new TypeDefaultStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, QLSParser.RULE_typeDefaultStatement);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 63;
        this.match(QLSParser.T__6);
        this.state = 64;
        this.type();
        this.state = 65;
        this.styleBlock();
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

function StyleBlockContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_styleBlock;
    return this;
}

StyleBlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StyleBlockContext.prototype.constructor = StyleBlockContext;

StyleBlockContext.prototype.styleStatement = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(StyleStatementContext);
    } else {
        return this.getTypedRuleContext(StyleStatementContext,i);
    }
};

StyleBlockContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterStyleBlock(this);
	}
};

StyleBlockContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitStyleBlock(this);
	}
};

StyleBlockContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitStyleBlock(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.StyleBlockContext = StyleBlockContext;

QLSParser.prototype.styleBlock = function() {

    var localctx = new StyleBlockContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, QLSParser.RULE_styleBlock);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 67;
        this.match(QLSParser.T__1);
        this.state = 73;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLSParser.T__8 || _la===QLSParser.IDENTIFIER) {
            this.state = 68;
            this.styleStatement();
            this.state = 69;
            this.match(QLSParser.T__7);
            this.state = 75;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 76;
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

function StyleStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_styleStatement;
    return this;
}

StyleStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StyleStatementContext.prototype.constructor = StyleStatementContext;


 
StyleStatementContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function ArgStyleStatementContext(parser, ctx) {
	StyleStatementContext.call(this, parser);
    StyleStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

ArgStyleStatementContext.prototype = Object.create(StyleStatementContext.prototype);
ArgStyleStatementContext.prototype.constructor = ArgStyleStatementContext;

QLSParser.ArgStyleStatementContext = ArgStyleStatementContext;

ArgStyleStatementContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLSParser.IDENTIFIER, 0);
};

ArgStyleStatementContext.prototype.literal = function() {
    return this.getTypedRuleContext(LiteralContext,0);
};
ArgStyleStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterArgStyleStatement(this);
	}
};

ArgStyleStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitArgStyleStatement(this);
	}
};

ArgStyleStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitArgStyleStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function WidgetStyleStatementContext(parser, ctx) {
	StyleStatementContext.call(this, parser);
    StyleStatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

WidgetStyleStatementContext.prototype = Object.create(StyleStatementContext.prototype);
WidgetStyleStatementContext.prototype.constructor = WidgetStyleStatementContext;

QLSParser.WidgetStyleStatementContext = WidgetStyleStatementContext;

WidgetStyleStatementContext.prototype.widgetType = function() {
    return this.getTypedRuleContext(WidgetTypeContext,0);
};
WidgetStyleStatementContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterWidgetStyleStatement(this);
	}
};

WidgetStyleStatementContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitWidgetStyleStatement(this);
	}
};

WidgetStyleStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitWidgetStyleStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLSParser.StyleStatementContext = StyleStatementContext;

QLSParser.prototype.styleStatement = function() {

    var localctx = new StyleStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, QLSParser.RULE_styleStatement);
    try {
        this.state = 83;
        switch(this._input.LA(1)) {
        case QLSParser.T__8:
            localctx = new WidgetStyleStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 78;
            this.match(QLSParser.T__8);
            this.state = 79;
            this.widgetType();
            break;
        case QLSParser.IDENTIFIER:
            localctx = new ArgStyleStatementContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 80;
            this.match(QLSParser.IDENTIFIER);
            this.state = 81;
            this.match(QLSParser.T__9);
            this.state = 82;
            this.literal();
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


 
WidgetTypeContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function SliderWidgetTypeContext(parser, ctx) {
	WidgetTypeContext.call(this, parser);
    WidgetTypeContext.prototype.copyFrom.call(this, ctx);
    return this;
}

SliderWidgetTypeContext.prototype = Object.create(WidgetTypeContext.prototype);
SliderWidgetTypeContext.prototype.constructor = SliderWidgetTypeContext;

QLSParser.SliderWidgetTypeContext = SliderWidgetTypeContext;

SliderWidgetTypeContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterSliderWidgetType(this);
	}
};

SliderWidgetTypeContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitSliderWidgetType(this);
	}
};

SliderWidgetTypeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitSliderWidgetType(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function TextWidgetTypeContext(parser, ctx) {
	WidgetTypeContext.call(this, parser);
    WidgetTypeContext.prototype.copyFrom.call(this, ctx);
    return this;
}

TextWidgetTypeContext.prototype = Object.create(WidgetTypeContext.prototype);
TextWidgetTypeContext.prototype.constructor = TextWidgetTypeContext;

QLSParser.TextWidgetTypeContext = TextWidgetTypeContext;

TextWidgetTypeContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterTextWidgetType(this);
	}
};

TextWidgetTypeContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitTextWidgetType(this);
	}
};

TextWidgetTypeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitTextWidgetType(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function RadioWidgetTypeContext(parser, ctx) {
	WidgetTypeContext.call(this, parser);
    WidgetTypeContext.prototype.copyFrom.call(this, ctx);
    return this;
}

RadioWidgetTypeContext.prototype = Object.create(WidgetTypeContext.prototype);
RadioWidgetTypeContext.prototype.constructor = RadioWidgetTypeContext;

QLSParser.RadioWidgetTypeContext = RadioWidgetTypeContext;

RadioWidgetTypeContext.prototype.valueOptionList = function() {
    return this.getTypedRuleContext(ValueOptionListContext,0);
};
RadioWidgetTypeContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterRadioWidgetType(this);
	}
};

RadioWidgetTypeContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitRadioWidgetType(this);
	}
};

RadioWidgetTypeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitRadioWidgetType(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLSParser.WidgetTypeContext = WidgetTypeContext;

QLSParser.prototype.widgetType = function() {

    var localctx = new WidgetTypeContext(this, this._ctx, this.state);
    this.enterRule(localctx, 16, QLSParser.RULE_widgetType);
    try {
        this.state = 89;
        switch(this._input.LA(1)) {
        case QLSParser.T__10:
            localctx = new SliderWidgetTypeContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 85;
            this.match(QLSParser.T__10);
            break;
        case QLSParser.T__11:
            localctx = new TextWidgetTypeContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 86;
            this.match(QLSParser.T__11);
            break;
        case QLSParser.T__12:
            localctx = new RadioWidgetTypeContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 87;
            this.match(QLSParser.T__12);
            this.state = 88;
            this.valueOptionList();
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

function ValueOptionListContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_valueOptionList;
    return this;
}

ValueOptionListContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ValueOptionListContext.prototype.constructor = ValueOptionListContext;

ValueOptionListContext.prototype.valueOptions = function() {
    return this.getTypedRuleContext(ValueOptionsContext,0);
};

ValueOptionListContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterValueOptionList(this);
	}
};

ValueOptionListContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitValueOptionList(this);
	}
};

ValueOptionListContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitValueOptionList(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLSParser.ValueOptionListContext = ValueOptionListContext;

QLSParser.prototype.valueOptionList = function() {

    var localctx = new ValueOptionListContext(this, this._ctx, this.state);
    this.enterRule(localctx, 18, QLSParser.RULE_valueOptionList);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 91;
        this.match(QLSParser.T__13);
        this.state = 92;
        this.valueOptions();
        this.state = 93;
        this.match(QLSParser.T__14);
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


 
ValueOptionsContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function MoreValueOptionsContext(parser, ctx) {
	ValueOptionsContext.call(this, parser);
    ValueOptionsContext.prototype.copyFrom.call(this, ctx);
    return this;
}

MoreValueOptionsContext.prototype = Object.create(ValueOptionsContext.prototype);
MoreValueOptionsContext.prototype.constructor = MoreValueOptionsContext;

QLSParser.MoreValueOptionsContext = MoreValueOptionsContext;

MoreValueOptionsContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLSParser.STRING_LITERAL, 0);
};

MoreValueOptionsContext.prototype.valueOptions = function() {
    return this.getTypedRuleContext(ValueOptionsContext,0);
};
MoreValueOptionsContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterMoreValueOptions(this);
	}
};

MoreValueOptionsContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitMoreValueOptions(this);
	}
};

MoreValueOptionsContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitMoreValueOptions(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function LastValueOptionContext(parser, ctx) {
	ValueOptionsContext.call(this, parser);
    ValueOptionsContext.prototype.copyFrom.call(this, ctx);
    return this;
}

LastValueOptionContext.prototype = Object.create(ValueOptionsContext.prototype);
LastValueOptionContext.prototype.constructor = LastValueOptionContext;

QLSParser.LastValueOptionContext = LastValueOptionContext;

LastValueOptionContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLSParser.STRING_LITERAL, 0);
};
LastValueOptionContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterLastValueOption(this);
	}
};

LastValueOptionContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitLastValueOption(this);
	}
};

LastValueOptionContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitLastValueOption(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLSParser.ValueOptionsContext = ValueOptionsContext;

QLSParser.prototype.valueOptions = function() {

    var localctx = new ValueOptionsContext(this, this._ctx, this.state);
    this.enterRule(localctx, 20, QLSParser.RULE_valueOptions);
    try {
        this.state = 99;
        var la_ = this._interp.adaptivePredict(this._input,7,this._ctx);
        switch(la_) {
        case 1:
            localctx = new MoreValueOptionsContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 95;
            this.match(QLSParser.STRING_LITERAL);
            this.state = 96;
            this.match(QLSParser.T__15);
            this.state = 97;
            this.valueOptions();
            break;

        case 2:
            localctx = new LastValueOptionContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 98;
            this.match(QLSParser.STRING_LITERAL);
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

function LiteralContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLSParser.RULE_literal;
    return this;
}

LiteralContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
LiteralContext.prototype.constructor = LiteralContext;


 
LiteralContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function StringLiteralContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

StringLiteralContext.prototype = Object.create(LiteralContext.prototype);
StringLiteralContext.prototype.constructor = StringLiteralContext;

QLSParser.StringLiteralContext = StringLiteralContext;

StringLiteralContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLSParser.STRING_LITERAL, 0);
};
StringLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterStringLiteral(this);
	}
};

StringLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitStringLiteral(this);
	}
};

StringLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitStringLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function IntegerLiteralContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

IntegerLiteralContext.prototype = Object.create(LiteralContext.prototype);
IntegerLiteralContext.prototype.constructor = IntegerLiteralContext;

QLSParser.IntegerLiteralContext = IntegerLiteralContext;

IntegerLiteralContext.prototype.INTEGER_LITERAL = function() {
    return this.getToken(QLSParser.INTEGER_LITERAL, 0);
};
IntegerLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterIntegerLiteral(this);
	}
};

IntegerLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitIntegerLiteral(this);
	}
};

IntegerLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitIntegerLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function FloatLiteralContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

FloatLiteralContext.prototype = Object.create(LiteralContext.prototype);
FloatLiteralContext.prototype.constructor = FloatLiteralContext;

QLSParser.FloatLiteralContext = FloatLiteralContext;

FloatLiteralContext.prototype.FLOAT_LITERAL = function() {
    return this.getToken(QLSParser.FLOAT_LITERAL, 0);
};
FloatLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterFloatLiteral(this);
	}
};

FloatLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitFloatLiteral(this);
	}
};

FloatLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitFloatLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function BooleanLiteralContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

BooleanLiteralContext.prototype = Object.create(LiteralContext.prototype);
BooleanLiteralContext.prototype.constructor = BooleanLiteralContext;

QLSParser.BooleanLiteralContext = BooleanLiteralContext;

BooleanLiteralContext.prototype.BOOLEAN_LITERAL = function() {
    return this.getToken(QLSParser.BOOLEAN_LITERAL, 0);
};
BooleanLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterBooleanLiteral(this);
	}
};

BooleanLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitBooleanLiteral(this);
	}
};

BooleanLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitBooleanLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function MoneyLiteralContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

MoneyLiteralContext.prototype = Object.create(LiteralContext.prototype);
MoneyLiteralContext.prototype.constructor = MoneyLiteralContext;

QLSParser.MoneyLiteralContext = MoneyLiteralContext;

MoneyLiteralContext.prototype.MONEY_LITERAL = function() {
    return this.getToken(QLSParser.MONEY_LITERAL, 0);
};
MoneyLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.enterMoneyLiteral(this);
	}
};

MoneyLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLSListener ) {
        listener.exitMoneyLiteral(this);
	}
};

MoneyLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLSVisitor ) {
        return visitor.visitMoneyLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLSParser.LiteralContext = LiteralContext;

QLSParser.prototype.literal = function() {

    var localctx = new LiteralContext(this, this._ctx, this.state);
    this.enterRule(localctx, 22, QLSParser.RULE_literal);
    try {
        this.state = 106;
        switch(this._input.LA(1)) {
        case QLSParser.BOOLEAN_LITERAL:
            localctx = new BooleanLiteralContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 101;
            this.match(QLSParser.BOOLEAN_LITERAL);
            break;
        case QLSParser.STRING_LITERAL:
            localctx = new StringLiteralContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 102;
            this.match(QLSParser.STRING_LITERAL);
            break;
        case QLSParser.INTEGER_LITERAL:
            localctx = new IntegerLiteralContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 103;
            this.match(QLSParser.INTEGER_LITERAL);
            break;
        case QLSParser.FLOAT_LITERAL:
            localctx = new FloatLiteralContext(this, localctx);
            this.enterOuterAlt(localctx, 4);
            this.state = 104;
            this.match(QLSParser.FLOAT_LITERAL);
            break;
        case QLSParser.MONEY_LITERAL:
            localctx = new MoneyLiteralContext(this, localctx);
            this.enterOuterAlt(localctx, 5);
            this.state = 105;
            this.match(QLSParser.MONEY_LITERAL);
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
    this.enterRule(localctx, 24, QLSParser.RULE_type);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 108;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLSParser.T__16) | (1 << QLSParser.T__17) | (1 << QLSParser.T__18) | (1 << QLSParser.T__19) | (1 << QLSParser.T__20))) !== 0))) {
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


exports.QLSParser = QLSParser;

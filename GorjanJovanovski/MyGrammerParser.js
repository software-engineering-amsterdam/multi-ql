// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');
var MyGrammerListener = require('./MyGrammerListener').MyGrammerListener;
var MyGrammerVisitor = require('./MyGrammerVisitor').MyGrammerVisitor;

var grammarFileName = "MyGrammer.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u001ew\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0004",
    "\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0003\u0002\u0003\u0002",
    "\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0007\u0003%\n\u0003",
    "\f\u0003\u000e\u0003(\u000b\u0003\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0003\u0004\u0003\u0004\u0003\u0005\u0003\u0005\u0003\u0006\u0003\u0006",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u00077\n\u0007",
    "\u0003\b\u0003\b\u0003\b\u0003\b\u0005\b=\n\b\u0003\t\u0003\t\u0003",
    "\t\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003",
    "\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0005\nR",
    "\n\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0007\nY\n\n\f\n\u000e",
    "\n\\\u000b\n\u0003\u000b\u0003\u000b\u0003\u000b\u0005\u000ba\n\u000b",
    "\u0003\f\u0003\f\u0003\f\u0003\f\u0005\fg\n\f\u0003\r\u0003\r\u0005",
    "\rk\n\r\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003\u000f",
    "\u0003\u000f\u0003\u000f\u0003\u000f\u0005\u000fu\n\u000f\u0003\u000f",
    "\u0002\u0003\u0012\u0010\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014",
    "\u0016\u0018\u001a\u001c\u0002\u0002|\u0002\u001e\u0003\u0002\u0002",
    "\u0002\u0004&\u0003\u0002\u0002\u0002\u0006)\u0003\u0002\u0002\u0002",
    "\b.\u0003\u0002\u0002\u0002\n0\u0003\u0002\u0002\u0002\f6\u0003\u0002",
    "\u0002\u0002\u000e8\u0003\u0002\u0002\u0002\u0010>\u0003\u0002\u0002",
    "\u0002\u0012Q\u0003\u0002\u0002\u0002\u0014`\u0003\u0002\u0002\u0002",
    "\u0016f\u0003\u0002\u0002\u0002\u0018j\u0003\u0002\u0002\u0002\u001a",
    "l\u0003\u0002\u0002\u0002\u001ct\u0003\u0002\u0002\u0002\u001e\u001f",
    "\u0007\u0003\u0002\u0002\u001f \u0007\u0019\u0002\u0002 !\u0005\u0004",
    "\u0003\u0002!\u0003\u0003\u0002\u0002\u0002\"%\u0005\u0006\u0004\u0002",
    "#%\u0005\u000e\b\u0002$\"\u0003\u0002\u0002\u0002$#\u0003\u0002\u0002",
    "\u0002%(\u0003\u0002\u0002\u0002&$\u0003\u0002\u0002\u0002&\'\u0003",
    "\u0002\u0002\u0002\'\u0005\u0003\u0002\u0002\u0002(&\u0003\u0002\u0002",
    "\u0002)*\u0005\b\u0005\u0002*+\u0005\n\u0006\u0002+,\u0007\u0018\u0002",
    "\u0002,-\u0005\f\u0007\u0002-\u0007\u0003\u0002\u0002\u0002./\u0007",
    "\u001e\u0002\u0002/\t\u0003\u0002\u0002\u000201\u0007\u0019\u0002\u0002",
    "1\u000b\u0003\u0002\u0002\u000227\u0007\u0017\u0002\u000234\u0007\u0017",
    "\u0002\u000245\u0007\u0004\u0002\u000257\u0005\u0012\n\u000262\u0003",
    "\u0002\u0002\u000263\u0003\u0002\u0002\u00027\r\u0003\u0002\u0002\u0002",
    "89\u0007\u0005\u0002\u00029:\u0005\u0012\n\u0002:<\u0005\u0004\u0003",
    "\u0002;=\u0005\u0010\t\u0002<;\u0003\u0002\u0002\u0002<=\u0003\u0002",
    "\u0002\u0002=\u000f\u0003\u0002\u0002\u0002>?\u0007\u0006\u0002\u0002",
    "?@\u0005\u0004\u0003\u0002@\u0011\u0003\u0002\u0002\u0002AB\b\n\u0001",
    "\u0002BC\u0005\u001a\u000e\u0002CD\u0005\u0012\n\u0003DE\b\n\u0001\u0002",
    "ER\u0003\u0002\u0002\u0002FG\u0007\u0016\u0002\u0002GR\b\n\u0001\u0002",
    "HI\u0007\u0019\u0002\u0002IR\b\n\u0001\u0002JK\u0007\u001b\u0002\u0002",
    "KR\b\n\u0001\u0002LM\u0007\u0007\u0002\u0002MN\u0005\u0012\n\u0002N",
    "O\u0007\b\u0002\u0002OP\b\n\u0001\u0002PR\u0003\u0002\u0002\u0002QA",
    "\u0003\u0002\u0002\u0002QF\u0003\u0002\u0002\u0002QH\u0003\u0002\u0002",
    "\u0002QJ\u0003\u0002\u0002\u0002QL\u0003\u0002\u0002\u0002RZ\u0003\u0002",
    "\u0002\u0002ST\f\u0004\u0002\u0002TU\u0005\u0014\u000b\u0002UV\u0005",
    "\u0012\n\u0005VW\b\n\u0001\u0002WY\u0003\u0002\u0002\u0002XS\u0003\u0002",
    "\u0002\u0002Y\\\u0003\u0002\u0002\u0002ZX\u0003\u0002\u0002\u0002Z[",
    "\u0003\u0002\u0002\u0002[\u0013\u0003\u0002\u0002\u0002\\Z\u0003\u0002",
    "\u0002\u0002]a\u0005\u001c\u000f\u0002^a\u0005\u0018\r\u0002_a\u0005",
    "\u0016\f\u0002`]\u0003\u0002\u0002\u0002`^\u0003\u0002\u0002\u0002`",
    "_\u0003\u0002\u0002\u0002a\u0015\u0003\u0002\u0002\u0002bg\u0007\t\u0002",
    "\u0002cg\u0007\n\u0002\u0002dg\u0007\u000b\u0002\u0002eg\u0007\f\u0002",
    "\u0002fb\u0003\u0002\u0002\u0002fc\u0003\u0002\u0002\u0002fd\u0003\u0002",
    "\u0002\u0002fe\u0003\u0002\u0002\u0002g\u0017\u0003\u0002\u0002\u0002",
    "hk\u0007\r\u0002\u0002ik\u0007\u000e\u0002\u0002jh\u0003\u0002\u0002",
    "\u0002ji\u0003\u0002\u0002\u0002k\u0019\u0003\u0002\u0002\u0002lm\u0007",
    "\u000f\u0002\u0002m\u001b\u0003\u0002\u0002\u0002nu\u0007\u0010\u0002",
    "\u0002ou\u0007\u0011\u0002\u0002pu\u0007\u0012\u0002\u0002qu\u0007\u0013",
    "\u0002\u0002ru\u0007\u0014\u0002\u0002su\u0007\u0015\u0002\u0002tn\u0003",
    "\u0002\u0002\u0002to\u0003\u0002\u0002\u0002tp\u0003\u0002\u0002\u0002",
    "tq\u0003\u0002\u0002\u0002tr\u0003\u0002\u0002\u0002ts\u0003\u0002\u0002",
    "\u0002u\u001d\u0003\u0002\u0002\u0002\f$&6<QZ`fjt"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ null, "'form'", "'='", "'if'", "'else'", "'('", "')'", 
                     "'+'", "'-'", "'*'", "'/'", "'&&'", "'||'", "'!'", 
                     "'<'", "'<='", "'>'", "'>='", "'!='", "'=='", null, 
                     null, "':'" ];

var symbolicNames = [ null, null, null, null, null, null, null, null, null, 
                      null, null, null, null, null, null, null, null, null, 
                      null, null, "BOOLSTMT", "TYPE", "DELIMITER", "LABEL", 
                      "NEWLINE", "NUMBER", "WHITESPACE", "BRACKETS", "STRING" ];

var ruleNames =  [ "form", "queries", "question", "questionText", "questionLabel", 
                   "questionValue", "ifstmt", "elsestmt", "expr", "op", 
                   "mathop", "boolop", "nooperator", "compop" ];

function MyGrammerParser (input) {
	antlr4.Parser.call(this, input);
    this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
    this.ruleNames = ruleNames;
    this.literalNames = literalNames;
    this.symbolicNames = symbolicNames;
    return this;
}

MyGrammerParser.prototype = Object.create(antlr4.Parser.prototype);
MyGrammerParser.prototype.constructor = MyGrammerParser;

Object.defineProperty(MyGrammerParser.prototype, "atn", {
	get : function() {
		return atn;
	}
});

MyGrammerParser.EOF = antlr4.Token.EOF;
MyGrammerParser.T__0 = 1;
MyGrammerParser.T__1 = 2;
MyGrammerParser.T__2 = 3;
MyGrammerParser.T__3 = 4;
MyGrammerParser.T__4 = 5;
MyGrammerParser.T__5 = 6;
MyGrammerParser.T__6 = 7;
MyGrammerParser.T__7 = 8;
MyGrammerParser.T__8 = 9;
MyGrammerParser.T__9 = 10;
MyGrammerParser.T__10 = 11;
MyGrammerParser.T__11 = 12;
MyGrammerParser.T__12 = 13;
MyGrammerParser.T__13 = 14;
MyGrammerParser.T__14 = 15;
MyGrammerParser.T__15 = 16;
MyGrammerParser.T__16 = 17;
MyGrammerParser.T__17 = 18;
MyGrammerParser.T__18 = 19;
MyGrammerParser.BOOLSTMT = 20;
MyGrammerParser.TYPE = 21;
MyGrammerParser.DELIMITER = 22;
MyGrammerParser.LABEL = 23;
MyGrammerParser.NEWLINE = 24;
MyGrammerParser.NUMBER = 25;
MyGrammerParser.WHITESPACE = 26;
MyGrammerParser.BRACKETS = 27;
MyGrammerParser.STRING = 28;

MyGrammerParser.RULE_form = 0;
MyGrammerParser.RULE_queries = 1;
MyGrammerParser.RULE_question = 2;
MyGrammerParser.RULE_questionText = 3;
MyGrammerParser.RULE_questionLabel = 4;
MyGrammerParser.RULE_questionValue = 5;
MyGrammerParser.RULE_ifstmt = 6;
MyGrammerParser.RULE_elsestmt = 7;
MyGrammerParser.RULE_expr = 8;
MyGrammerParser.RULE_op = 9;
MyGrammerParser.RULE_mathop = 10;
MyGrammerParser.RULE_boolop = 11;
MyGrammerParser.RULE_nooperator = 12;
MyGrammerParser.RULE_compop = 13;

function FormContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_form;
    this.que = null; // QueriesContext
    return this;
}

FormContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
FormContext.prototype.constructor = FormContext;

FormContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
};

FormContext.prototype.queries = function() {
    return this.getTypedRuleContext(QueriesContext,0);
};

FormContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterForm(this);
	}
};

FormContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitForm(this);
	}
};

FormContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitForm(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.FormContext = FormContext;

MyGrammerParser.prototype.form = function() {

    var localctx = new FormContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, MyGrammerParser.RULE_form);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 28;
        this.match(MyGrammerParser.T__0);
        this.state = 29;
        this.match(MyGrammerParser.LABEL);
        this.state = 30;
        localctx.que = this.queries();
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

function QueriesContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_queries;
    this.que = null; // QuestionContext
    return this;
}

QueriesContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QueriesContext.prototype.constructor = QueriesContext;

QueriesContext.prototype.question = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(QuestionContext);
    } else {
        return this.getTypedRuleContext(QuestionContext,i);
    }
};

QueriesContext.prototype.ifstmt = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(IfstmtContext);
    } else {
        return this.getTypedRuleContext(IfstmtContext,i);
    }
};

QueriesContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterQueries(this);
	}
};

QueriesContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitQueries(this);
	}
};

QueriesContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitQueries(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.QueriesContext = QueriesContext;

MyGrammerParser.prototype.queries = function() {

    var localctx = new QueriesContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, MyGrammerParser.RULE_queries);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 36;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,1,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                this.state = 34;
                switch(this._input.LA(1)) {
                case MyGrammerParser.STRING:
                    this.state = 32;
                    localctx.que = this.question();
                    break;
                case MyGrammerParser.T__2:
                    this.state = 33;
                    localctx.que = this.ifstmt();
                    break;
                default:
                    throw new antlr4.error.NoViableAltException(this);
                } 
            }
            this.state = 38;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,1,this._ctx);
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

function QuestionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_question;
    return this;
}

QuestionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionContext.prototype.constructor = QuestionContext;

QuestionContext.prototype.questionText = function() {
    return this.getTypedRuleContext(QuestionTextContext,0);
};

QuestionContext.prototype.questionLabel = function() {
    return this.getTypedRuleContext(QuestionLabelContext,0);
};

QuestionContext.prototype.DELIMITER = function() {
    return this.getToken(MyGrammerParser.DELIMITER, 0);
};

QuestionContext.prototype.questionValue = function() {
    return this.getTypedRuleContext(QuestionValueContext,0);
};

QuestionContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterQuestion(this);
	}
};

QuestionContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitQuestion(this);
	}
};

QuestionContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitQuestion(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.QuestionContext = QuestionContext;

MyGrammerParser.prototype.question = function() {

    var localctx = new QuestionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, MyGrammerParser.RULE_question);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 39;
        this.questionText();
        this.state = 40;
        this.questionLabel();
        this.state = 41;
        this.match(MyGrammerParser.DELIMITER);
        this.state = 42;
        this.questionValue();
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

function QuestionTextContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_questionText;
    return this;
}

QuestionTextContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionTextContext.prototype.constructor = QuestionTextContext;

QuestionTextContext.prototype.STRING = function() {
    return this.getToken(MyGrammerParser.STRING, 0);
};

QuestionTextContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterQuestionText(this);
	}
};

QuestionTextContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitQuestionText(this);
	}
};

QuestionTextContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitQuestionText(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.QuestionTextContext = QuestionTextContext;

MyGrammerParser.prototype.questionText = function() {

    var localctx = new QuestionTextContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, MyGrammerParser.RULE_questionText);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 44;
        this.match(MyGrammerParser.STRING);
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

function QuestionLabelContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_questionLabel;
    return this;
}

QuestionLabelContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionLabelContext.prototype.constructor = QuestionLabelContext;

QuestionLabelContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
};

QuestionLabelContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterQuestionLabel(this);
	}
};

QuestionLabelContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitQuestionLabel(this);
	}
};

QuestionLabelContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitQuestionLabel(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.QuestionLabelContext = QuestionLabelContext;

MyGrammerParser.prototype.questionLabel = function() {

    var localctx = new QuestionLabelContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, MyGrammerParser.RULE_questionLabel);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 46;
        this.match(MyGrammerParser.LABEL);
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

function QuestionValueContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_questionValue;
    return this;
}

QuestionValueContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionValueContext.prototype.constructor = QuestionValueContext;

QuestionValueContext.prototype.TYPE = function() {
    return this.getToken(MyGrammerParser.TYPE, 0);
};

QuestionValueContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

QuestionValueContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterQuestionValue(this);
	}
};

QuestionValueContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitQuestionValue(this);
	}
};

QuestionValueContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitQuestionValue(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.QuestionValueContext = QuestionValueContext;

MyGrammerParser.prototype.questionValue = function() {

    var localctx = new QuestionValueContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, MyGrammerParser.RULE_questionValue);
    try {
        this.state = 52;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,2,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 48;
            this.match(MyGrammerParser.TYPE);
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 49;
            this.match(MyGrammerParser.TYPE);
            this.state = 50;
            this.match(MyGrammerParser.T__1);
            this.state = 51;
            this.expr(0);
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

function IfstmtContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_ifstmt;
    this.que = null; // QueriesContext
    this.els = null; // ElsestmtContext
    return this;
}

IfstmtContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
IfstmtContext.prototype.constructor = IfstmtContext;

IfstmtContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

IfstmtContext.prototype.queries = function() {
    return this.getTypedRuleContext(QueriesContext,0);
};

IfstmtContext.prototype.elsestmt = function() {
    return this.getTypedRuleContext(ElsestmtContext,0);
};

IfstmtContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterIfstmt(this);
	}
};

IfstmtContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitIfstmt(this);
	}
};

IfstmtContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitIfstmt(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.IfstmtContext = IfstmtContext;

MyGrammerParser.prototype.ifstmt = function() {

    var localctx = new IfstmtContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, MyGrammerParser.RULE_ifstmt);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 54;
        this.match(MyGrammerParser.T__2);
        this.state = 55;
        this.expr(0);
        this.state = 56;
        localctx.que = this.queries();
        this.state = 58;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,3,this._ctx);
        if(la_===1) {
            this.state = 57;
            localctx.els = this.elsestmt();

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

function ElsestmtContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_elsestmt;
    this.que = null; // QueriesContext
    return this;
}

ElsestmtContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ElsestmtContext.prototype.constructor = ElsestmtContext;

ElsestmtContext.prototype.queries = function() {
    return this.getTypedRuleContext(QueriesContext,0);
};

ElsestmtContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterElsestmt(this);
	}
};

ElsestmtContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitElsestmt(this);
	}
};

ElsestmtContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitElsestmt(this);
    } else {
        return visitor.visitChildren(this);
    }
};




MyGrammerParser.ElsestmtContext = ElsestmtContext;

MyGrammerParser.prototype.elsestmt = function() {

    var localctx = new ElsestmtContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, MyGrammerParser.RULE_elsestmt);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 60;
        this.match(MyGrammerParser.T__3);
        this.state = 61;
        localctx.que = this.queries();
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

function ExprContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_expr;
    this.result = null
    return this;
}

ExprContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExprContext.prototype.constructor = ExprContext;


 
ExprContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
    this.result = ctx.result;
};

function NegationExprContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.a8 = null; // ExprContext;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

NegationExprContext.prototype = Object.create(ExprContext.prototype);
NegationExprContext.prototype.constructor = NegationExprContext;

MyGrammerParser.NegationExprContext = NegationExprContext;

NegationExprContext.prototype.nooperator = function() {
    return this.getTypedRuleContext(NooperatorContext,0);
};

NegationExprContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};
NegationExprContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterNegationExpr(this);
	}
};

NegationExprContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitNegationExpr(this);
	}
};

NegationExprContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitNegationExpr(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function OpExprContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.a5 = null; // ExprContext;
    this.a6 = null; // OpContext;
    this.a7 = null; // ExprContext;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

OpExprContext.prototype = Object.create(ExprContext.prototype);
OpExprContext.prototype.constructor = OpExprContext;

MyGrammerParser.OpExprContext = OpExprContext;

OpExprContext.prototype.expr = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ExprContext);
    } else {
        return this.getTypedRuleContext(ExprContext,i);
    }
};

OpExprContext.prototype.op = function() {
    return this.getTypedRuleContext(OpContext,0);
};
OpExprContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterOpExpr(this);
	}
};

OpExprContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitOpExpr(this);
	}
};

OpExprContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitOpExpr(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function LabelLiteralContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.a2 = null; // Token;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

LabelLiteralContext.prototype = Object.create(ExprContext.prototype);
LabelLiteralContext.prototype.constructor = LabelLiteralContext;

MyGrammerParser.LabelLiteralContext = LabelLiteralContext;

LabelLiteralContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
};
LabelLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterLabelLiteral(this);
	}
};

LabelLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitLabelLiteral(this);
	}
};

LabelLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitLabelLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function BooleanLiteralContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.a1 = null; // Token;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

BooleanLiteralContext.prototype = Object.create(ExprContext.prototype);
BooleanLiteralContext.prototype.constructor = BooleanLiteralContext;

MyGrammerParser.BooleanLiteralContext = BooleanLiteralContext;

BooleanLiteralContext.prototype.BOOLSTMT = function() {
    return this.getToken(MyGrammerParser.BOOLSTMT, 0);
};
BooleanLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterBooleanLiteral(this);
	}
};

BooleanLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitBooleanLiteral(this);
	}
};

BooleanLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitBooleanLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function ParenthesisExprContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.a4 = null; // ExprContext;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

ParenthesisExprContext.prototype = Object.create(ExprContext.prototype);
ParenthesisExprContext.prototype.constructor = ParenthesisExprContext;

MyGrammerParser.ParenthesisExprContext = ParenthesisExprContext;

ParenthesisExprContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};
ParenthesisExprContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterParenthesisExpr(this);
	}
};

ParenthesisExprContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitParenthesisExpr(this);
	}
};

ParenthesisExprContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitParenthesisExpr(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function NumberLiteralContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.a3 = null; // Token;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

NumberLiteralContext.prototype = Object.create(ExprContext.prototype);
NumberLiteralContext.prototype.constructor = NumberLiteralContext;

MyGrammerParser.NumberLiteralContext = NumberLiteralContext;

NumberLiteralContext.prototype.NUMBER = function() {
    return this.getToken(MyGrammerParser.NUMBER, 0);
};
NumberLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterNumberLiteral(this);
	}
};

NumberLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitNumberLiteral(this);
	}
};

NumberLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitNumberLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};



MyGrammerParser.prototype.expr = function(_p) {
	if(_p===undefined) {
	    _p = 0;
	}
    var _parentctx = this._ctx;
    var _parentState = this.state;
    var localctx = new ExprContext(this, this._ctx, _parentState);
    var _prevctx = localctx;
    var _startState = 16;
    this.enterRecursionRule(localctx, 16, MyGrammerParser.RULE_expr, _p);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 79;
        switch(this._input.LA(1)) {
        case MyGrammerParser.T__12:
            localctx = new NegationExprContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;

            this.state = 64;
            this.nooperator();
            this.state = 65;
            localctx.a8 = this.expr(1);
            localctx.result = new JSNoOpExpr(localctx.a8.result)
            break;
        case MyGrammerParser.BOOLSTMT:
            localctx = new BooleanLiteralContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 68;
            localctx.a1 = this.match(MyGrammerParser.BOOLSTMT);
            localctx.result = 'true' == (localctx.a1===null ? null : localctx.a1.text)
            break;
        case MyGrammerParser.LABEL:
            localctx = new LabelLiteralContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 70;
            localctx.a2 = this.match(MyGrammerParser.LABEL);
            localctx.result = (localctx.a2===null ? null : localctx.a2.text)
            break;
        case MyGrammerParser.NUMBER:
            localctx = new NumberLiteralContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 72;
            localctx.a3 = this.match(MyGrammerParser.NUMBER);
            localctx.result = parseInt((localctx.a3===null ? null : localctx.a3.text))
            break;
        case MyGrammerParser.T__4:
            localctx = new ParenthesisExprContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 74;
            this.match(MyGrammerParser.T__4);
            this.state = 75;
            localctx.a4 = this.expr(0);
            this.state = 76;
            this.match(MyGrammerParser.T__5);
            localctx.result = new JSExpr(localctx.a4.result)
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 88;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,5,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                localctx = new OpExprContext(this, new ExprContext(this, _parentctx, _parentState));
                localctx.a5 = _prevctx;
                this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                this.state = 81;
                if (!( this.precpred(this._ctx, 2))) {
                    throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                }
                this.state = 82;
                localctx.a6 = this.op();
                this.state = 83;
                localctx.a7 = this.expr(3);
                localctx.result = new JSOpExpr(localctx.a5.result, (localctx.a6===null ? null : this._input.getText(new antlr4.Interval(localctx.a6.start,localctx.a6.stop))), localctx.a7.result) 
            }
            this.state = 90;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,5,this._ctx);
        }

    } catch( error) {
        if(error instanceof antlr4.error.RecognitionException) {
	        localctx.exception = error;
	        this._errHandler.reportError(this, error);
	        this._errHandler.recover(this, error);
	    } else {
	    	throw error;
	    }
    } finally {
        this.unrollRecursionContexts(_parentctx)
    }
    return localctx;
};

function OpContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_op;
    return this;
}

OpContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
OpContext.prototype.constructor = OpContext;


 
OpContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function BoolOpContext(parser, ctx) {
	OpContext.call(this, parser);
    OpContext.prototype.copyFrom.call(this, ctx);
    return this;
}

BoolOpContext.prototype = Object.create(OpContext.prototype);
BoolOpContext.prototype.constructor = BoolOpContext;

MyGrammerParser.BoolOpContext = BoolOpContext;

BoolOpContext.prototype.boolop = function() {
    return this.getTypedRuleContext(BoolopContext,0);
};
BoolOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterBoolOp(this);
	}
};

BoolOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitBoolOp(this);
	}
};

BoolOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitBoolOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function MathOpContext(parser, ctx) {
	OpContext.call(this, parser);
    OpContext.prototype.copyFrom.call(this, ctx);
    return this;
}

MathOpContext.prototype = Object.create(OpContext.prototype);
MathOpContext.prototype.constructor = MathOpContext;

MyGrammerParser.MathOpContext = MathOpContext;

MathOpContext.prototype.mathop = function() {
    return this.getTypedRuleContext(MathopContext,0);
};
MathOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterMathOp(this);
	}
};

MathOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitMathOp(this);
	}
};

MathOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitMathOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function CompareOpContext(parser, ctx) {
	OpContext.call(this, parser);
    OpContext.prototype.copyFrom.call(this, ctx);
    return this;
}

CompareOpContext.prototype = Object.create(OpContext.prototype);
CompareOpContext.prototype.constructor = CompareOpContext;

MyGrammerParser.CompareOpContext = CompareOpContext;

CompareOpContext.prototype.compop = function() {
    return this.getTypedRuleContext(CompopContext,0);
};
CompareOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterCompareOp(this);
	}
};

CompareOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitCompareOp(this);
	}
};

CompareOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitCompareOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};



MyGrammerParser.OpContext = OpContext;

MyGrammerParser.prototype.op = function() {

    var localctx = new OpContext(this, this._ctx, this.state);
    this.enterRule(localctx, 18, MyGrammerParser.RULE_op);
    try {
        this.state = 94;
        switch(this._input.LA(1)) {
        case MyGrammerParser.T__13:
        case MyGrammerParser.T__14:
        case MyGrammerParser.T__15:
        case MyGrammerParser.T__16:
        case MyGrammerParser.T__17:
        case MyGrammerParser.T__18:
            localctx = new CompareOpContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 91;
            this.compop();
            break;
        case MyGrammerParser.T__10:
        case MyGrammerParser.T__11:
            localctx = new BoolOpContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 92;
            this.boolop();
            break;
        case MyGrammerParser.T__6:
        case MyGrammerParser.T__7:
        case MyGrammerParser.T__8:
        case MyGrammerParser.T__9:
            localctx = new MathOpContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 93;
            this.mathop();
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

function MathopContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_mathop;
    return this;
}

MathopContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
MathopContext.prototype.constructor = MathopContext;


 
MathopContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function SubstractionOpContext(parser, ctx) {
	MathopContext.call(this, parser);
    MathopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

SubstractionOpContext.prototype = Object.create(MathopContext.prototype);
SubstractionOpContext.prototype.constructor = SubstractionOpContext;

MyGrammerParser.SubstractionOpContext = SubstractionOpContext;

SubstractionOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterSubstractionOp(this);
	}
};

SubstractionOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitSubstractionOp(this);
	}
};

SubstractionOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitSubstractionOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function AdditionOpContext(parser, ctx) {
	MathopContext.call(this, parser);
    MathopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

AdditionOpContext.prototype = Object.create(MathopContext.prototype);
AdditionOpContext.prototype.constructor = AdditionOpContext;

MyGrammerParser.AdditionOpContext = AdditionOpContext;

AdditionOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterAdditionOp(this);
	}
};

AdditionOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitAdditionOp(this);
	}
};

AdditionOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitAdditionOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function MultiplicationOpContext(parser, ctx) {
	MathopContext.call(this, parser);
    MathopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

MultiplicationOpContext.prototype = Object.create(MathopContext.prototype);
MultiplicationOpContext.prototype.constructor = MultiplicationOpContext;

MyGrammerParser.MultiplicationOpContext = MultiplicationOpContext;

MultiplicationOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterMultiplicationOp(this);
	}
};

MultiplicationOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitMultiplicationOp(this);
	}
};

MultiplicationOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitMultiplicationOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function DivisionOpContext(parser, ctx) {
	MathopContext.call(this, parser);
    MathopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

DivisionOpContext.prototype = Object.create(MathopContext.prototype);
DivisionOpContext.prototype.constructor = DivisionOpContext;

MyGrammerParser.DivisionOpContext = DivisionOpContext;

DivisionOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterDivisionOp(this);
	}
};

DivisionOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitDivisionOp(this);
	}
};

DivisionOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitDivisionOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};



MyGrammerParser.MathopContext = MathopContext;

MyGrammerParser.prototype.mathop = function() {

    var localctx = new MathopContext(this, this._ctx, this.state);
    this.enterRule(localctx, 20, MyGrammerParser.RULE_mathop);
    try {
        this.state = 100;
        switch(this._input.LA(1)) {
        case MyGrammerParser.T__6:
            localctx = new AdditionOpContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 96;
            this.match(MyGrammerParser.T__6);
            break;
        case MyGrammerParser.T__7:
            localctx = new SubstractionOpContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 97;
            this.match(MyGrammerParser.T__7);
            break;
        case MyGrammerParser.T__8:
            localctx = new MultiplicationOpContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 98;
            this.match(MyGrammerParser.T__8);
            break;
        case MyGrammerParser.T__9:
            localctx = new DivisionOpContext(this, localctx);
            this.enterOuterAlt(localctx, 4);
            this.state = 99;
            this.match(MyGrammerParser.T__9);
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

function BoolopContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_boolop;
    return this;
}

BoolopContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BoolopContext.prototype.constructor = BoolopContext;


 
BoolopContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function OrOpContext(parser, ctx) {
	BoolopContext.call(this, parser);
    BoolopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

OrOpContext.prototype = Object.create(BoolopContext.prototype);
OrOpContext.prototype.constructor = OrOpContext;

MyGrammerParser.OrOpContext = OrOpContext;

OrOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterOrOp(this);
	}
};

OrOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitOrOp(this);
	}
};

OrOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitOrOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function AndOpContext(parser, ctx) {
	BoolopContext.call(this, parser);
    BoolopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

AndOpContext.prototype = Object.create(BoolopContext.prototype);
AndOpContext.prototype.constructor = AndOpContext;

MyGrammerParser.AndOpContext = AndOpContext;

AndOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterAndOp(this);
	}
};

AndOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitAndOp(this);
	}
};

AndOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitAndOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};



MyGrammerParser.BoolopContext = BoolopContext;

MyGrammerParser.prototype.boolop = function() {

    var localctx = new BoolopContext(this, this._ctx, this.state);
    this.enterRule(localctx, 22, MyGrammerParser.RULE_boolop);
    try {
        this.state = 104;
        switch(this._input.LA(1)) {
        case MyGrammerParser.T__10:
            localctx = new AndOpContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 102;
            this.match(MyGrammerParser.T__10);
            break;
        case MyGrammerParser.T__11:
            localctx = new OrOpContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 103;
            this.match(MyGrammerParser.T__11);
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

function NooperatorContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_nooperator;
    return this;
}

NooperatorContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
NooperatorContext.prototype.constructor = NooperatorContext;


 
NooperatorContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function NoOpContext(parser, ctx) {
	NooperatorContext.call(this, parser);
    NooperatorContext.prototype.copyFrom.call(this, ctx);
    return this;
}

NoOpContext.prototype = Object.create(NooperatorContext.prototype);
NoOpContext.prototype.constructor = NoOpContext;

MyGrammerParser.NoOpContext = NoOpContext;

NoOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterNoOp(this);
	}
};

NoOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitNoOp(this);
	}
};

NoOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitNoOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};



MyGrammerParser.NooperatorContext = NooperatorContext;

MyGrammerParser.prototype.nooperator = function() {

    var localctx = new NooperatorContext(this, this._ctx, this.state);
    this.enterRule(localctx, 24, MyGrammerParser.RULE_nooperator);
    try {
        localctx = new NoOpContext(this, localctx);
        this.enterOuterAlt(localctx, 1);
        this.state = 106;
        this.match(MyGrammerParser.T__12);
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

function CompopContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = MyGrammerParser.RULE_compop;
    return this;
}

CompopContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
CompopContext.prototype.constructor = CompopContext;


 
CompopContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function EqOpContext(parser, ctx) {
	CompopContext.call(this, parser);
    CompopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

EqOpContext.prototype = Object.create(CompopContext.prototype);
EqOpContext.prototype.constructor = EqOpContext;

MyGrammerParser.EqOpContext = EqOpContext;

EqOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterEqOp(this);
	}
};

EqOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitEqOp(this);
	}
};

EqOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitEqOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function NEqOpContext(parser, ctx) {
	CompopContext.call(this, parser);
    CompopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

NEqOpContext.prototype = Object.create(CompopContext.prototype);
NEqOpContext.prototype.constructor = NEqOpContext;

MyGrammerParser.NEqOpContext = NEqOpContext;

NEqOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterNEqOp(this);
	}
};

NEqOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitNEqOp(this);
	}
};

NEqOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitNEqOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function LtEqOpContext(parser, ctx) {
	CompopContext.call(this, parser);
    CompopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

LtEqOpContext.prototype = Object.create(CompopContext.prototype);
LtEqOpContext.prototype.constructor = LtEqOpContext;

MyGrammerParser.LtEqOpContext = LtEqOpContext;

LtEqOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterLtEqOp(this);
	}
};

LtEqOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitLtEqOp(this);
	}
};

LtEqOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitLtEqOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function LtOpContext(parser, ctx) {
	CompopContext.call(this, parser);
    CompopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

LtOpContext.prototype = Object.create(CompopContext.prototype);
LtOpContext.prototype.constructor = LtOpContext;

MyGrammerParser.LtOpContext = LtOpContext;

LtOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterLtOp(this);
	}
};

LtOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitLtOp(this);
	}
};

LtOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitLtOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function GtEqOpContext(parser, ctx) {
	CompopContext.call(this, parser);
    CompopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

GtEqOpContext.prototype = Object.create(CompopContext.prototype);
GtEqOpContext.prototype.constructor = GtEqOpContext;

MyGrammerParser.GtEqOpContext = GtEqOpContext;

GtEqOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterGtEqOp(this);
	}
};

GtEqOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitGtEqOp(this);
	}
};

GtEqOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitGtEqOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function GtOpContext(parser, ctx) {
	CompopContext.call(this, parser);
    CompopContext.prototype.copyFrom.call(this, ctx);
    return this;
}

GtOpContext.prototype = Object.create(CompopContext.prototype);
GtOpContext.prototype.constructor = GtOpContext;

MyGrammerParser.GtOpContext = GtOpContext;

GtOpContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterGtOp(this);
	}
};

GtOpContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitGtOp(this);
	}
};

GtOpContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitGtOp(this);
    } else {
        return visitor.visitChildren(this);
    }
};



MyGrammerParser.CompopContext = CompopContext;

MyGrammerParser.prototype.compop = function() {

    var localctx = new CompopContext(this, this._ctx, this.state);
    this.enterRule(localctx, 26, MyGrammerParser.RULE_compop);
    try {
        this.state = 114;
        switch(this._input.LA(1)) {
        case MyGrammerParser.T__13:
            localctx = new LtOpContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 108;
            this.match(MyGrammerParser.T__13);
            break;
        case MyGrammerParser.T__14:
            localctx = new LtEqOpContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 109;
            this.match(MyGrammerParser.T__14);
            break;
        case MyGrammerParser.T__15:
            localctx = new GtOpContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 110;
            this.match(MyGrammerParser.T__15);
            break;
        case MyGrammerParser.T__16:
            localctx = new GtEqOpContext(this, localctx);
            this.enterOuterAlt(localctx, 4);
            this.state = 111;
            this.match(MyGrammerParser.T__16);
            break;
        case MyGrammerParser.T__17:
            localctx = new NEqOpContext(this, localctx);
            this.enterOuterAlt(localctx, 5);
            this.state = 112;
            this.match(MyGrammerParser.T__17);
            break;
        case MyGrammerParser.T__18:
            localctx = new EqOpContext(this, localctx);
            this.enterOuterAlt(localctx, 6);
            this.state = 113;
            this.match(MyGrammerParser.T__18);
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


MyGrammerParser.prototype.sempred = function(localctx, ruleIndex, predIndex) {
	switch(ruleIndex) {
	case 8:
			return this.expr_sempred(localctx, predIndex);
    default:
        throw "No predicate with index:" + ruleIndex;
   }
};

MyGrammerParser.prototype.expr_sempred = function(localctx, predIndex) {
	switch(predIndex) {
		case 0:
			return this.precpred(this._ctx, 2);
		default:
			throw "No predicate with index:" + predIndex;
	}
};


exports.MyGrammerParser = MyGrammerParser;

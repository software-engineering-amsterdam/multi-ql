// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');
var MyGrammerListener = require('./MyGrammerListener').MyGrammerListener;
var grammarFileName = "MyGrammer.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u0017O\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0002\u0003\u0003\u0003\u0003\u0003\u0003\u0007\u0003\u001c\n\u0003",
    "\f\u0003\u000e\u0003\u001f\u000b\u0003\u0003\u0003\u0003\u0003\u0003",
    "\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0005\u0003",
    "\u0005\u0003\u0006\u0003\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003",
    "\u0007\u0005\u00070\n\u0007\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b",
    "\u0003\t\u0003\t\u0005\t9\n\t\u0003\n\u0003\n\u0003\n\u0003\n\u0003",
    "\n\u0003\n\u0003\n\u0003\n\u0003\n\u0003\n\u0005\nE\n\n\u0003\n\u0003",
    "\n\u0003\n\u0007\nJ\n\n\f\n\u000e\nM\u000b\n\u0003\n\u0002\u0003\u0012",
    "\u000b\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0002\u0002N\u0002",
    "\u0014\u0003\u0002\u0002\u0002\u0004\u0018\u0003\u0002\u0002\u0002\u0006",
    "\"\u0003\u0002\u0002\u0002\b\'\u0003\u0002\u0002\u0002\n)\u0003\u0002",
    "\u0002\u0002\f/\u0003\u0002\u0002\u0002\u000e1\u0003\u0002\u0002\u0002",
    "\u00108\u0003\u0002\u0002\u0002\u0012D\u0003\u0002\u0002\u0002\u0014",
    "\u0015\u0007\u0003\u0002\u0002\u0015\u0016\u0007\u0013\u0002\u0002\u0016",
    "\u0017\u0005\u0004\u0003\u0002\u0017\u0003\u0003\u0002\u0002\u0002\u0018",
    "\u001d\u0007\u0004\u0002\u0002\u0019\u001c\u0005\u0006\u0004\u0002\u001a",
    "\u001c\u0005\u000e\b\u0002\u001b\u0019\u0003\u0002\u0002\u0002\u001b",
    "\u001a\u0003\u0002\u0002\u0002\u001c\u001f\u0003\u0002\u0002\u0002\u001d",
    "\u001b\u0003\u0002\u0002\u0002\u001d\u001e\u0003\u0002\u0002\u0002\u001e",
    " \u0003\u0002\u0002\u0002\u001f\u001d\u0003\u0002\u0002\u0002 !\u0007",
    "\u0005\u0002\u0002!\u0005\u0003\u0002\u0002\u0002\"#\u0005\b\u0005\u0002",
    "#$\u0005\n\u0006\u0002$%\u0007\u0012\u0002\u0002%&\u0005\f\u0007\u0002",
    "&\u0007\u0003\u0002\u0002\u0002\'(\u0007\u0017\u0002\u0002(\t\u0003",
    "\u0002\u0002\u0002)*\u0007\u0013\u0002\u0002*\u000b\u0003\u0002\u0002",
    "\u0002+0\u0007\f\u0002\u0002,-\u0007\f\u0002\u0002-.\u0007\u0006\u0002",
    "\u0002.0\u0005\u0012\n\u0002/+\u0003\u0002\u0002\u0002/,\u0003\u0002",
    "\u0002\u00020\r\u0003\u0002\u0002\u000212\u0007\u0007\u0002\u000223",
    "\u0005\u0012\n\u000234\u0005\u0004\u0003\u000245\u0005\u0010\t\u0002",
    "5\u000f\u0003\u0002\u0002\u000267\u0007\b\u0002\u000279\u0005\u0004",
    "\u0003\u000286\u0003\u0002\u0002\u000289\u0003\u0002\u0002\u00029\u0011",
    "\u0003\u0002\u0002\u0002:;\b\n\u0001\u0002;<\u0007\u0010\u0002\u0002",
    "<E\u0005\u0012\n\u0003=E\u0007\u000b\u0002\u0002>E\u0007\u0013\u0002",
    "\u0002?E\u0007\u0015\u0002\u0002@A\u0007\t\u0002\u0002AB\u0005\u0012",
    "\n\u0002BC\u0007\n\u0002\u0002CE\u0003\u0002\u0002\u0002D:\u0003\u0002",
    "\u0002\u0002D=\u0003\u0002\u0002\u0002D>\u0003\u0002\u0002\u0002D?\u0003",
    "\u0002\u0002\u0002D@\u0003\u0002\u0002\u0002EK\u0003\u0002\u0002\u0002",
    "FG\f\u0004\u0002\u0002GH\u0007\r\u0002\u0002HJ\u0005\u0012\n\u0005I",
    "F\u0003\u0002\u0002\u0002JM\u0003\u0002\u0002\u0002KI\u0003\u0002\u0002",
    "\u0002KL\u0003\u0002\u0002\u0002L\u0013\u0003\u0002\u0002\u0002MK\u0003",
    "\u0002\u0002\u0002\b\u001b\u001d/8DK"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ null, "'form'", "'{'", "'}'", "'='", "'if'", "'else'", 
                     "'('", "')'", null, null, null, null, null, "'!'", 
                     null, "':'" ];

var symbolicNames = [ null, null, null, null, null, null, null, null, null, 
                      "BOOLSTMT", "TYPE", "OP", "MATHOP", "BOOLOP", "NOOP", 
                      "COMPOP", "DELIMITER", "LABEL", "NEWLINE", "INT", 
                      "WHITESPACE", "STRING" ];

var ruleNames =  [ "form", "queries", "question", "questionText", "questionLabel", 
                   "questionValue", "ifstmt", "elsestmt", "expr" ];

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
MyGrammerParser.BOOLSTMT = 9;
MyGrammerParser.TYPE = 10;
MyGrammerParser.OP = 11;
MyGrammerParser.MATHOP = 12;
MyGrammerParser.BOOLOP = 13;
MyGrammerParser.NOOP = 14;
MyGrammerParser.COMPOP = 15;
MyGrammerParser.DELIMITER = 16;
MyGrammerParser.LABEL = 17;
MyGrammerParser.NEWLINE = 18;
MyGrammerParser.INT = 19;
MyGrammerParser.WHITESPACE = 20;
MyGrammerParser.STRING = 21;

MyGrammerParser.RULE_form = 0;
MyGrammerParser.RULE_queries = 1;
MyGrammerParser.RULE_question = 2;
MyGrammerParser.RULE_questionText = 3;
MyGrammerParser.RULE_questionLabel = 4;
MyGrammerParser.RULE_questionValue = 5;
MyGrammerParser.RULE_ifstmt = 6;
MyGrammerParser.RULE_elsestmt = 7;
MyGrammerParser.RULE_expr = 8;

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




MyGrammerParser.FormContext = FormContext;

MyGrammerParser.prototype.form = function() {

    var localctx = new FormContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, MyGrammerParser.RULE_form);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 18;
        this.match(MyGrammerParser.T__0);
        this.state = 19;
        this.match(MyGrammerParser.LABEL);
        this.state = 20;
        this.queries();
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




MyGrammerParser.QueriesContext = QueriesContext;

MyGrammerParser.prototype.queries = function() {

    var localctx = new QueriesContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, MyGrammerParser.RULE_queries);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 22;
        this.match(MyGrammerParser.T__1);
        this.state = 27;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===MyGrammerParser.T__4 || _la===MyGrammerParser.STRING) {
            this.state = 25;
            switch(this._input.LA(1)) {
            case MyGrammerParser.STRING:
                this.state = 23;
                this.question();
                break;
            case MyGrammerParser.T__4:
                this.state = 24;
                this.ifstmt();
                break;
            default:
                throw new antlr4.error.NoViableAltException(this);
            }
            this.state = 29;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 30;
        this.match(MyGrammerParser.T__2);
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




MyGrammerParser.QuestionContext = QuestionContext;

MyGrammerParser.prototype.question = function() {

    var localctx = new QuestionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, MyGrammerParser.RULE_question);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 32;
        this.questionText();
        this.state = 33;
        this.questionLabel();
        this.state = 34;
        this.match(MyGrammerParser.DELIMITER);
        this.state = 35;
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




MyGrammerParser.QuestionTextContext = QuestionTextContext;

MyGrammerParser.prototype.questionText = function() {

    var localctx = new QuestionTextContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, MyGrammerParser.RULE_questionText);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 37;
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




MyGrammerParser.QuestionLabelContext = QuestionLabelContext;

MyGrammerParser.prototype.questionLabel = function() {

    var localctx = new QuestionLabelContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, MyGrammerParser.RULE_questionLabel);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 39;
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




MyGrammerParser.QuestionValueContext = QuestionValueContext;

MyGrammerParser.prototype.questionValue = function() {

    var localctx = new QuestionValueContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, MyGrammerParser.RULE_questionValue);
    try {
        this.state = 45;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,2,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 41;
            this.match(MyGrammerParser.TYPE);
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 42;
            this.match(MyGrammerParser.TYPE);
            this.state = 43;
            this.match(MyGrammerParser.T__3);
            this.state = 44;
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




MyGrammerParser.IfstmtContext = IfstmtContext;

MyGrammerParser.prototype.ifstmt = function() {

    var localctx = new IfstmtContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, MyGrammerParser.RULE_ifstmt);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 47;
        this.match(MyGrammerParser.T__4);
        this.state = 48;
        this.expr(0);
        this.state = 49;
        this.queries();
        this.state = 50;
        this.elsestmt();
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




MyGrammerParser.ElsestmtContext = ElsestmtContext;

MyGrammerParser.prototype.elsestmt = function() {

    var localctx = new ElsestmtContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, MyGrammerParser.RULE_elsestmt);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 54;
        _la = this._input.LA(1);
        if(_la===MyGrammerParser.T__5) {
            this.state = 52;
            this.match(MyGrammerParser.T__5);
            this.state = 53;
            this.queries();
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
    return this;
}

ExprContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExprContext.prototype.constructor = ExprContext;

ExprContext.prototype.NOOP = function() {
    return this.getToken(MyGrammerParser.NOOP, 0);
};

ExprContext.prototype.expr = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ExprContext);
    } else {
        return this.getTypedRuleContext(ExprContext,i);
    }
};

ExprContext.prototype.BOOLSTMT = function() {
    return this.getToken(MyGrammerParser.BOOLSTMT, 0);
};

ExprContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
};

ExprContext.prototype.INT = function() {
    return this.getToken(MyGrammerParser.INT, 0);
};

ExprContext.prototype.OP = function() {
    return this.getToken(MyGrammerParser.OP, 0);
};

ExprContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterExpr(this);
	}
};

ExprContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitExpr(this);
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
        this.state = 66;
        switch(this._input.LA(1)) {
        case MyGrammerParser.NOOP:
            this.state = 57;
            this.match(MyGrammerParser.NOOP);
            this.state = 58;
            this.expr(1);
            break;
        case MyGrammerParser.BOOLSTMT:
            this.state = 59;
            this.match(MyGrammerParser.BOOLSTMT);
            break;
        case MyGrammerParser.LABEL:
            this.state = 60;
            this.match(MyGrammerParser.LABEL);
            break;
        case MyGrammerParser.INT:
            this.state = 61;
            this.match(MyGrammerParser.INT);
            break;
        case MyGrammerParser.T__6:
            this.state = 62;
            this.match(MyGrammerParser.T__6);
            this.state = 63;
            this.expr(0);
            this.state = 64;
            this.match(MyGrammerParser.T__7);
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 73;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,5,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                localctx = new ExprContext(this, _parentctx, _parentState);
                this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                this.state = 68;
                if (!( this.precpred(this._ctx, 2))) {
                    throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                }
                this.state = 69;
                this.match(MyGrammerParser.OP);
                this.state = 70;
                this.expr(3); 
            }
            this.state = 75;
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

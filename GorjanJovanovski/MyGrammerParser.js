// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');
var MyGrammerListener = require('./MyGrammerListener').MyGrammerListener;
var grammarFileName = "MyGrammer.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u0016@\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0003",
    "\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003",
    "\u0003\u0007\u0003\u0016\n\u0003\f\u0003\u000e\u0003\u0019\u000b\u0003",
    "\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0003\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005",
    "&\n\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u00076\n\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0007\u0007;\n\u0007\f\u0007\u000e\u0007>\u000b",
    "\u0007\u0003\u0007\u0002\u0003\f\b\u0002\u0004\u0006\b\n\f\u0002\u0002",
    "A\u0002\u000e\u0003\u0002\u0002\u0002\u0004\u0012\u0003\u0002\u0002",
    "\u0002\u0006\u001c\u0003\u0002\u0002\u0002\b%\u0003\u0002\u0002\u0002",
    "\n\'\u0003\u0002\u0002\u0002\f5\u0003\u0002\u0002\u0002\u000e\u000f",
    "\u0007\u0003\u0002\u0002\u000f\u0010\u0007\u0012\u0002\u0002\u0010\u0011",
    "\u0005\u0004\u0003\u0002\u0011\u0003\u0003\u0002\u0002\u0002\u0012\u0017",
    "\u0007\u0004\u0002\u0002\u0013\u0016\u0005\u0006\u0004\u0002\u0014\u0016",
    "\u0005\n\u0006\u0002\u0015\u0013\u0003\u0002\u0002\u0002\u0015\u0014",
    "\u0003\u0002\u0002\u0002\u0016\u0019\u0003\u0002\u0002\u0002\u0017\u0015",
    "\u0003\u0002\u0002\u0002\u0017\u0018\u0003\u0002\u0002\u0002\u0018\u001a",
    "\u0003\u0002\u0002\u0002\u0019\u0017\u0003\u0002\u0002\u0002\u001a\u001b",
    "\u0007\u0005\u0002\u0002\u001b\u0005\u0003\u0002\u0002\u0002\u001c\u001d",
    "\u0007\u0016\u0002\u0002\u001d\u001e\u0007\u0012\u0002\u0002\u001e\u001f",
    "\u0007\u0011\u0002\u0002\u001f \u0005\b\u0005\u0002 \u0007\u0003\u0002",
    "\u0002\u0002!&\u0007\u000b\u0002\u0002\"#\u0007\u000b\u0002\u0002#$",
    "\u0007\u0006\u0002\u0002$&\u0005\f\u0007\u0002%!\u0003\u0002\u0002\u0002",
    "%\"\u0003\u0002\u0002\u0002&\t\u0003\u0002\u0002\u0002\'(\u0007\u0007",
    "\u0002\u0002()\u0005\f\u0007\u0002)*\u0005\u0004\u0003\u0002*\u000b",
    "\u0003\u0002\u0002\u0002+,\b\u0007\u0001\u0002,-\u0007\u000f\u0002\u0002",
    "-6\u0005\f\u0007\u0003.6\u0007\n\u0002\u0002/6\u0007\u0012\u0002\u0002",
    "06\u0007\u0014\u0002\u000212\u0007\b\u0002\u000223\u0005\f\u0007\u0002",
    "34\u0007\t\u0002\u000246\u0003\u0002\u0002\u00025+\u0003\u0002\u0002",
    "\u00025.\u0003\u0002\u0002\u00025/\u0003\u0002\u0002\u000250\u0003\u0002",
    "\u0002\u000251\u0003\u0002\u0002\u00026<\u0003\u0002\u0002\u000278\f",
    "\u0004\u0002\u000289\u0007\f\u0002\u00029;\u0005\f\u0007\u0005:7\u0003",
    "\u0002\u0002\u0002;>\u0003\u0002\u0002\u0002<:\u0003\u0002\u0002\u0002",
    "<=\u0003\u0002\u0002\u0002=\r\u0003\u0002\u0002\u0002><\u0003\u0002",
    "\u0002\u0002\u0007\u0015\u0017%5<"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ null, "'form'", "'{'", "'}'", "'='", "'if'", "'('", 
                     "')'", null, null, null, null, null, "'!'", null, "':'" ];

var symbolicNames = [ null, null, null, null, null, null, null, null, "BOOLSTMT", 
                      "TYPE", "OP", "MATHOP", "BOOLOP", "NOOP", "COMPOP", 
                      "DELIMITER", "LABEL", "NEWLINE", "INT", "WHITESPACE", 
                      "STRING" ];

var ruleNames =  [ "form", "queries", "question", "questionValue", "ifstmt", 
                   "expr" ];

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
MyGrammerParser.BOOLSTMT = 8;
MyGrammerParser.TYPE = 9;
MyGrammerParser.OP = 10;
MyGrammerParser.MATHOP = 11;
MyGrammerParser.BOOLOP = 12;
MyGrammerParser.NOOP = 13;
MyGrammerParser.COMPOP = 14;
MyGrammerParser.DELIMITER = 15;
MyGrammerParser.LABEL = 16;
MyGrammerParser.NEWLINE = 17;
MyGrammerParser.INT = 18;
MyGrammerParser.WHITESPACE = 19;
MyGrammerParser.STRING = 20;

MyGrammerParser.RULE_form = 0;
MyGrammerParser.RULE_queries = 1;
MyGrammerParser.RULE_question = 2;
MyGrammerParser.RULE_questionValue = 3;
MyGrammerParser.RULE_ifstmt = 4;
MyGrammerParser.RULE_expr = 5;

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
        this.state = 12;
        this.match(MyGrammerParser.T__0);
        this.state = 13;
        this.match(MyGrammerParser.LABEL);
        this.state = 14;
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
        this.state = 16;
        this.match(MyGrammerParser.T__1);
        this.state = 21;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===MyGrammerParser.T__4 || _la===MyGrammerParser.STRING) {
            this.state = 19;
            switch(this._input.LA(1)) {
            case MyGrammerParser.STRING:
                this.state = 17;
                this.question();
                break;
            case MyGrammerParser.T__4:
                this.state = 18;
                this.ifstmt();
                break;
            default:
                throw new antlr4.error.NoViableAltException(this);
            }
            this.state = 23;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 24;
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

QuestionContext.prototype.STRING = function() {
    return this.getToken(MyGrammerParser.STRING, 0);
};

QuestionContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
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
        this.state = 26;
        this.match(MyGrammerParser.STRING);
        this.state = 27;
        this.match(MyGrammerParser.LABEL);
        this.state = 28;
        this.match(MyGrammerParser.DELIMITER);
        this.state = 29;
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
    this.enterRule(localctx, 6, MyGrammerParser.RULE_questionValue);
    try {
        this.state = 35;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,2,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 31;
            this.match(MyGrammerParser.TYPE);
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 32;
            this.match(MyGrammerParser.TYPE);
            this.state = 33;
            this.match(MyGrammerParser.T__3);
            this.state = 34;
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
    this.enterRule(localctx, 8, MyGrammerParser.RULE_ifstmt);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 37;
        this.match(MyGrammerParser.T__4);
        this.state = 38;
        this.expr(0);
        this.state = 39;
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
    var _startState = 10;
    this.enterRecursionRule(localctx, 10, MyGrammerParser.RULE_expr, _p);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 51;
        switch(this._input.LA(1)) {
        case MyGrammerParser.NOOP:
            this.state = 42;
            this.match(MyGrammerParser.NOOP);
            this.state = 43;
            this.expr(1);
            break;
        case MyGrammerParser.BOOLSTMT:
            this.state = 44;
            this.match(MyGrammerParser.BOOLSTMT);
            break;
        case MyGrammerParser.LABEL:
            this.state = 45;
            this.match(MyGrammerParser.LABEL);
            break;
        case MyGrammerParser.INT:
            this.state = 46;
            this.match(MyGrammerParser.INT);
            break;
        case MyGrammerParser.T__5:
            this.state = 47;
            this.match(MyGrammerParser.T__5);
            this.state = 48;
            this.expr(0);
            this.state = 49;
            this.match(MyGrammerParser.T__6);
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 58;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,4,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                localctx = new ExprContext(this, _parentctx, _parentState);
                this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                this.state = 53;
                if (!( this.precpred(this._ctx, 2))) {
                    throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                }
                this.state = 54;
                this.match(MyGrammerParser.OP);
                this.state = 55;
                this.expr(3); 
            }
            this.state = 60;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,4,this._ctx);
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
	case 5:
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

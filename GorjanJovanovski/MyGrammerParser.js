// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');
var MyGrammerListener = require('./MyGrammerListener').MyGrammerListener;
var MyGrammerVisitor = require('./MyGrammerVisitor').MyGrammerVisitor;

var grammarFileName = "MyGrammer.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u0019j\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0003\u0002\u0003\u0002",
    "\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0003",
    "\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0007\u0003\u0019\n",
    "\u0003\f\u0003\u000e\u0003\u001c\u000b\u0003\u0003\u0003\u0003\u0003",
    "\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0003\u0004\u0005\u0004-\n\u0004\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005;\n\u0005\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0005\u0006",
    "O\n\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0007\u0006e\n\u0006\f\u0006\u000e",
    "\u0006h\u000b\u0006\u0003\u0006\u0002\u0003\n\u0007\u0002\u0004\u0006",
    "\b\n\u0002\u0002q\u0002\f\u0003\u0002\u0002\u0002\u0004\u0011\u0003",
    "\u0002\u0002\u0002\u0006,\u0003\u0002\u0002\u0002\b:\u0003\u0002\u0002",
    "\u0002\nN\u0003\u0002\u0002\u0002\f\r\u0007\u0003\u0002\u0002\r\u000e",
    "\u0007\u0013\u0002\u0002\u000e\u000f\u0005\u0004\u0003\u0002\u000f\u0010",
    "\b\u0002\u0001\u0002\u0010\u0003\u0003\u0002\u0002\u0002\u0011\u001a",
    "\u0007\u0004\u0002\u0002\u0012\u0013\u0005\u0006\u0004\u0002\u0013\u0014",
    "\b\u0003\u0001\u0002\u0014\u0019\u0003\u0002\u0002\u0002\u0015\u0016",
    "\u0005\b\u0005\u0002\u0016\u0017\b\u0003\u0001\u0002\u0017\u0019\u0003",
    "\u0002\u0002\u0002\u0018\u0012\u0003\u0002\u0002\u0002\u0018\u0015\u0003",
    "\u0002\u0002\u0002\u0019\u001c\u0003\u0002\u0002\u0002\u001a\u0018\u0003",
    "\u0002\u0002\u0002\u001a\u001b\u0003\u0002\u0002\u0002\u001b\u001d\u0003",
    "\u0002\u0002\u0002\u001c\u001a\u0003\u0002\u0002\u0002\u001d\u001e\u0007",
    "\u0005\u0002\u0002\u001e\u0005\u0003\u0002\u0002\u0002\u001f \u0007",
    "\u0019\u0002\u0002 !\u0007\u0013\u0002\u0002!\"\u0007\u0012\u0002\u0002",
    "\"#\u0007\u0010\u0002\u0002#-\b\u0004\u0001\u0002$%\u0007\u0019\u0002",
    "\u0002%&\u0007\u0013\u0002\u0002&\'\u0007\u0012\u0002\u0002\'(\u0007",
    "\u0010\u0002\u0002()\u0007\u0006\u0002\u0002)*\u0005\n\u0006\u0002*",
    "+\b\u0004\u0001\u0002+-\u0003\u0002\u0002\u0002,\u001f\u0003\u0002\u0002",
    "\u0002,$\u0003\u0002\u0002\u0002-\u0007\u0003\u0002\u0002\u0002./\u0007",
    "\u0007\u0002\u0002/0\u0005\n\u0006\u000201\u0005\u0004\u0003\u00021",
    "2\b\u0005\u0001\u00022;\u0003\u0002\u0002\u000234\u0007\u0007\u0002",
    "\u000245\u0005\n\u0006\u000256\u0005\u0004\u0003\u000267\u0007\b\u0002",
    "\u000278\u0005\u0004\u0003\u000289\b\u0005\u0001\u00029;\u0003\u0002",
    "\u0002\u0002:.\u0003\u0002\u0002\u0002:3\u0003\u0002\u0002\u0002;\t",
    "\u0003\u0002\u0002\u0002<=\b\u0006\u0001\u0002=>\u0007\u000e\u0002\u0002",
    ">?\u0005\n\u0006\u0003?@\b\u0006\u0001\u0002@O\u0003\u0002\u0002\u0002",
    "AB\u0007\u0011\u0002\u0002BO\b\u0006\u0001\u0002CD\u0007\u0013\u0002",
    "\u0002DO\b\u0006\u0001\u0002EF\u0007\u0015\u0002\u0002FO\b\u0006\u0001",
    "\u0002GH\u0007\u0016\u0002\u0002HO\b\u0006\u0001\u0002IJ\u0007\t\u0002",
    "\u0002JK\u0005\n\u0006\u0002KL\u0007\n\u0002\u0002LM\b\u0006\u0001\u0002",
    "MO\u0003\u0002\u0002\u0002N<\u0003\u0002\u0002\u0002NA\u0003\u0002\u0002",
    "\u0002NC\u0003\u0002\u0002\u0002NE\u0003\u0002\u0002\u0002NG\u0003\u0002",
    "\u0002\u0002NI\u0003\u0002\u0002\u0002Of\u0003\u0002\u0002\u0002PQ\f",
    "\u0007\u0002\u0002QR\u0007\u000b\u0002\u0002RS\u0005\n\u0006\bST\b\u0006",
    "\u0001\u0002Te\u0003\u0002\u0002\u0002UV\f\u0006\u0002\u0002VW\u0007",
    "\f\u0002\u0002WX\u0005\n\u0006\u0007XY\b\u0006\u0001\u0002Ye\u0003\u0002",
    "\u0002\u0002Z[\f\u0005\u0002\u0002[\\\u0007\u000f\u0002\u0002\\]\u0005",
    "\n\u0006\u0006]^\b\u0006\u0001\u0002^e\u0003\u0002\u0002\u0002_`\f\u0004",
    "\u0002\u0002`a\u0007\r\u0002\u0002ab\u0005\n\u0006\u0005bc\b\u0006\u0001",
    "\u0002ce\u0003\u0002\u0002\u0002dP\u0003\u0002\u0002\u0002dU\u0003\u0002",
    "\u0002\u0002dZ\u0003\u0002\u0002\u0002d_\u0003\u0002\u0002\u0002eh\u0003",
    "\u0002\u0002\u0002fd\u0003\u0002\u0002\u0002fg\u0003\u0002\u0002\u0002",
    "g\u000b\u0003\u0002\u0002\u0002hf\u0003\u0002\u0002\u0002\t\u0018\u001a",
    ",:Ndf"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ null, "'form'", "'{'", "'}'", "'='", "'if'", "'else'", 
                     "'('", "')'", null, null, null, "'!'", null, null, 
                     null, "':'" ];

var symbolicNames = [ null, null, null, null, null, null, null, null, null, 
                      "MULTDIVOPERATOR", "ADDSUBOPERATOR", "BOOLOPERATOR", 
                      "NOTOPERATOR", "COMPAREOPERATOR", "TYPE", "BOOLEAN", 
                      "DELIMITER", "LABEL", "NEWLINE", "NUMBER", "DECIMAL", 
                      "WHITESPACE", "BRACKETS", "STRING" ];

var ruleNames =  [ "form", "queries", "question", "ifstmt", "expr" ];

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
MyGrammerParser.MULTDIVOPERATOR = 9;
MyGrammerParser.ADDSUBOPERATOR = 10;
MyGrammerParser.BOOLOPERATOR = 11;
MyGrammerParser.NOTOPERATOR = 12;
MyGrammerParser.COMPAREOPERATOR = 13;
MyGrammerParser.TYPE = 14;
MyGrammerParser.BOOLEAN = 15;
MyGrammerParser.DELIMITER = 16;
MyGrammerParser.LABEL = 17;
MyGrammerParser.NEWLINE = 18;
MyGrammerParser.NUMBER = 19;
MyGrammerParser.DECIMAL = 20;
MyGrammerParser.WHITESPACE = 21;
MyGrammerParser.BRACKETS = 22;
MyGrammerParser.STRING = 23;

MyGrammerParser.RULE_form = 0;
MyGrammerParser.RULE_queries = 1;
MyGrammerParser.RULE_question = 2;
MyGrammerParser.RULE_ifstmt = 3;
MyGrammerParser.RULE_expr = 4;

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
    this.FormNode = null
    this.lbl = null; // Token
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
        this.state = 10;
        this.match(MyGrammerParser.T__0);
        this.state = 11;
        localctx.lbl = this.match(MyGrammerParser.LABEL);
        this.state = 12;
        localctx.que = this.queries();
        localctx.FormNode = new FormNode((localctx.lbl===null ? null : localctx.lbl.text), localctx.que.QueriesNode)
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
    this.QueriesNode = null
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
     localctx.QueriesNode =  new Array() 
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 15;
        this.match(MyGrammerParser.T__1);
        this.state = 24;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===MyGrammerParser.T__4 || _la===MyGrammerParser.STRING) {
            this.state = 22;
            switch(this._input.LA(1)) {
            case MyGrammerParser.STRING:
                this.state = 16;
                localctx.que = this.question();
                localctx.QueriesNode.push(localctx.que.ResultNode)
                break;
            case MyGrammerParser.T__4:
                this.state = 19;
                localctx.que = this.ifstmt();
                localctx.QueriesNode.push(localctx.que.ResultNode)
                break;
            default:
                throw new antlr4.error.NoViableAltException(this);
            }
            this.state = 26;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 27;
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
    this.ResultNode = null
    this.txt = null; // Token
    this.lbl = null; // Token
    this.type = null; // Token
    this.exp = null; // ExprContext
    return this;
}

QuestionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionContext.prototype.constructor = QuestionContext;

QuestionContext.prototype.DELIMITER = function() {
    return this.getToken(MyGrammerParser.DELIMITER, 0);
};

QuestionContext.prototype.STRING = function() {
    return this.getToken(MyGrammerParser.STRING, 0);
};

QuestionContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
};

QuestionContext.prototype.TYPE = function() {
    return this.getToken(MyGrammerParser.TYPE, 0);
};

QuestionContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
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
        this.state = 42;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,2,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 29;
            localctx.txt = this.match(MyGrammerParser.STRING);
            this.state = 30;
            localctx.lbl = this.match(MyGrammerParser.LABEL);
            this.state = 31;
            this.match(MyGrammerParser.DELIMITER);
            this.state = 32;
            localctx.type = this.match(MyGrammerParser.TYPE);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), (localctx.type===null ? null : localctx.type.text), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 34;
            localctx.txt = this.match(MyGrammerParser.STRING);
            this.state = 35;
            localctx.lbl = this.match(MyGrammerParser.LABEL);
            this.state = 36;
            this.match(MyGrammerParser.DELIMITER);
            this.state = 37;
            localctx.type = this.match(MyGrammerParser.TYPE);
            this.state = 38;
            this.match(MyGrammerParser.T__3);
            this.state = 39;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), (localctx.type===null ? null : localctx.type.text), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
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
    this.ResultNode = null
    this.exp = null; // ExprContext
    this.que = null; // QueriesContext
    this.elseque = null; // QueriesContext
    return this;
}

IfstmtContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
IfstmtContext.prototype.constructor = IfstmtContext;

IfstmtContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

IfstmtContext.prototype.queries = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(QueriesContext);
    } else {
        return this.getTypedRuleContext(QueriesContext,i);
    }
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
    this.enterRule(localctx, 6, MyGrammerParser.RULE_ifstmt);
    try {
        this.state = 56;
        this._errHandler.sync(this);
        var la_ = this._interp.adaptivePredict(this._input,3,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 44;
            this.match(MyGrammerParser.T__4);
            this.state = 45;
            localctx.exp = this.expr(0);
            this.state = 46;
            localctx.que = this.queries();
            localctx.ResultNode = new ConditionNode(localctx.exp.ExprNode, localctx.que.QueriesNode, (localctx.exp===null ? null : localctx.exp.start).line)
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 49;
            this.match(MyGrammerParser.T__4);
            this.state = 50;
            localctx.exp = this.expr(0);
            this.state = 51;
            localctx.que = this.queries();
            this.state = 52;
            this.match(MyGrammerParser.T__5);
            this.state = 53;
            localctx.elseque = this.queries();
            localctx.ResultNode = new ConditionNode(localctx.exp.ExprNode, localctx.que.QueriesNode, (localctx.exp===null ? null : localctx.exp.start).line, localctx.elseque.QueriesNode)
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
    this.ExprNode = null
    return this;
}

ExprContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExprContext.prototype.constructor = ExprContext;


 
ExprContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
    this.ExprNode = ctx.ExprNode;
};

function DecimalLiteralContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.dec = null; // Token;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

DecimalLiteralContext.prototype = Object.create(ExprContext.prototype);
DecimalLiteralContext.prototype.constructor = DecimalLiteralContext;

MyGrammerParser.DecimalLiteralContext = DecimalLiteralContext;

DecimalLiteralContext.prototype.DECIMAL = function() {
    return this.getToken(MyGrammerParser.DECIMAL, 0);
};
DecimalLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterDecimalLiteral(this);
	}
};

DecimalLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitDecimalLiteral(this);
	}
};

DecimalLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitDecimalLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function LabelNodeContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.lbl = null; // Token;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

LabelNodeContext.prototype = Object.create(ExprContext.prototype);
LabelNodeContext.prototype.constructor = LabelNodeContext;

MyGrammerParser.LabelNodeContext = LabelNodeContext;

LabelNodeContext.prototype.LABEL = function() {
    return this.getToken(MyGrammerParser.LABEL, 0);
};
LabelNodeContext.prototype.enterRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.enterLabelNode(this);
	}
};

LabelNodeContext.prototype.exitRule = function(listener) {
    if(listener instanceof MyGrammerListener ) {
        listener.exitLabelNode(this);
	}
};

LabelNodeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof MyGrammerVisitor ) {
        return visitor.visitLabelNode(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function NegationExprContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.exp = null; // ExprContext;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

NegationExprContext.prototype = Object.create(ExprContext.prototype);
NegationExprContext.prototype.constructor = NegationExprContext;

MyGrammerParser.NegationExprContext = NegationExprContext;

NegationExprContext.prototype.NOTOPERATOR = function() {
    return this.getToken(MyGrammerParser.NOTOPERATOR, 0);
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
    this.left = null; // ExprContext;
    this.op = null; // Token;
    this.right = null; // ExprContext;
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

OpExprContext.prototype.MULTDIVOPERATOR = function() {
    return this.getToken(MyGrammerParser.MULTDIVOPERATOR, 0);
};

OpExprContext.prototype.ADDSUBOPERATOR = function() {
    return this.getToken(MyGrammerParser.ADDSUBOPERATOR, 0);
};

OpExprContext.prototype.COMPAREOPERATOR = function() {
    return this.getToken(MyGrammerParser.COMPAREOPERATOR, 0);
};

OpExprContext.prototype.BOOLOPERATOR = function() {
    return this.getToken(MyGrammerParser.BOOLOPERATOR, 0);
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


function BooleanLiteralContext(parser, ctx) {
	ExprContext.call(this, parser);
    this.bool = null; // Token;
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

BooleanLiteralContext.prototype = Object.create(ExprContext.prototype);
BooleanLiteralContext.prototype.constructor = BooleanLiteralContext;

MyGrammerParser.BooleanLiteralContext = BooleanLiteralContext;

BooleanLiteralContext.prototype.BOOLEAN = function() {
    return this.getToken(MyGrammerParser.BOOLEAN, 0);
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
    this.exp = null; // ExprContext;
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
    this.num = null; // Token;
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
    var _startState = 8;
    this.enterRecursionRule(localctx, 8, MyGrammerParser.RULE_expr, _p);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 76;
        switch(this._input.LA(1)) {
        case MyGrammerParser.NOTOPERATOR:
            localctx = new NegationExprContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;

            this.state = 59;
            this.match(MyGrammerParser.NOTOPERATOR);
            this.state = 60;
            localctx.exp = this.expr(1);
            localctx.ExprNode = new NotExpression(localctx.exp.ExprNode, (localctx.exp===null ? null : localctx.exp.start).line)
            break;
        case MyGrammerParser.BOOLEAN:
            localctx = new BooleanLiteralContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 63;
            localctx.bool = this.match(MyGrammerParser.BOOLEAN);
            localctx.ExprNode = 'true' == (localctx.bool===null ? null : localctx.bool.text)
            break;
        case MyGrammerParser.LABEL:
            localctx = new LabelNodeContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 65;
            localctx.lbl = this.match(MyGrammerParser.LABEL);
            localctx.ExprNode = new LabelNode((localctx.lbl===null ? null : localctx.lbl.text), (localctx.lbl === null ? 0 : localctx.lbl.line))
            break;
        case MyGrammerParser.NUMBER:
            localctx = new NumberLiteralContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 67;
            localctx.num = this.match(MyGrammerParser.NUMBER);
            localctx.ExprNode = parseInt((localctx.num===null ? null : localctx.num.text))
            break;
        case MyGrammerParser.DECIMAL:
            localctx = new DecimalLiteralContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 69;
            localctx.dec = this.match(MyGrammerParser.DECIMAL);
            localctx.ExprNode = parseFloat((localctx.dec===null ? null : localctx.dec.text))
            break;
        case MyGrammerParser.T__6:
            localctx = new ParenthesisExprContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 71;
            this.match(MyGrammerParser.T__6);
            this.state = 72;
            localctx.exp = this.expr(0);
            this.state = 73;
            this.match(MyGrammerParser.T__7);
            localctx.ExprNode = new ExpressionNode(localctx.exp.ExprNode, (localctx.exp===null ? null : localctx.exp.start).line)
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 100;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,6,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                this.state = 98;
                this._errHandler.sync(this);
                var la_ = this._interp.adaptivePredict(this._input,5,this._ctx);
                switch(la_) {
                case 1:
                    localctx = new OpExprContext(this, new ExprContext(this, _parentctx, _parentState));
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                    this.state = 78;
                    if (!( this.precpred(this._ctx, 5))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 5)");
                    }
                    this.state = 79;
                    localctx.op = this.match(MyGrammerParser.MULTDIVOPERATOR);
                    this.state = 80;
                    localctx.right = this.expr(6);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, (localctx.op===null ? null : localctx.op.text), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 2:
                    localctx = new OpExprContext(this, new ExprContext(this, _parentctx, _parentState));
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                    this.state = 83;
                    if (!( this.precpred(this._ctx, 4))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 4)");
                    }
                    this.state = 84;
                    localctx.op = this.match(MyGrammerParser.ADDSUBOPERATOR);
                    this.state = 85;
                    localctx.right = this.expr(5);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, (localctx.op===null ? null : localctx.op.text), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 3:
                    localctx = new OpExprContext(this, new ExprContext(this, _parentctx, _parentState));
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                    this.state = 88;
                    if (!( this.precpred(this._ctx, 3))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 3)");
                    }
                    this.state = 89;
                    localctx.op = this.match(MyGrammerParser.COMPAREOPERATOR);
                    this.state = 90;
                    localctx.right = this.expr(4);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, (localctx.op===null ? null : localctx.op.text), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 4:
                    localctx = new OpExprContext(this, new ExprContext(this, _parentctx, _parentState));
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, MyGrammerParser.RULE_expr);
                    this.state = 93;
                    if (!( this.precpred(this._ctx, 2))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                    }
                    this.state = 94;
                    localctx.op = this.match(MyGrammerParser.BOOLOPERATOR);
                    this.state = 95;
                    localctx.right = this.expr(3);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, (localctx.op===null ? null : localctx.op.text), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                } 
            }
            this.state = 102;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,6,this._ctx);
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
	case 4:
			return this.expr_sempred(localctx, predIndex);
    default:
        throw "No predicate with index:" + ruleIndex;
   }
};

MyGrammerParser.prototype.expr_sempred = function(localctx, predIndex) {
	switch(predIndex) {
		case 0:
			return this.precpred(this._ctx, 5);
		case 1:
			return this.precpred(this._ctx, 4);
		case 2:
			return this.precpred(this._ctx, 3);
		case 3:
			return this.precpred(this._ctx, 2);
		default:
			throw "No predicate with index:" + predIndex;
	}
};


exports.MyGrammerParser = MyGrammerParser;

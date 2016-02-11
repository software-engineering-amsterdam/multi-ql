// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/generated_parser\QL.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var QLListener = require('./QLListener').QLListener;
var QLVisitor = require('./QLVisitor').QLVisitor;

var grammarFileName = "QL.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003%b\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004",
    "\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b",
    "\t\b\u0004\t\t\t\u0004\n\t\n\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0003\u0003\u0003\u0007\u0003\u001a\n\u0003\f\u0003\u000e\u0003\u001d",
    "\u000b\u0003\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0003\u0004\u0005\u0004%\n\u0004\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005.\n\u0005",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0005\u00066\n\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007",
    "A\n\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0007\u0007R\n\u0007",
    "\f\u0007\u000e\u0007U\u000b\u0007\u0003\b\u0003\b\u0003\b\u0003\b\u0003",
    "\b\u0005\b\\\n\b\u0003\t\u0003\t\u0003\n\u0003\n\u0003\n\u0002\u0003",
    "\f\u000b\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0002\b\u0004\u0002",
    "\u0017\u0017\u001e\u001e\u0003\u0002\u0018\u0019\u0003\u0002\u0016\u0017",
    "\u0003\u0002\u0010\u0015\u0003\u0002\u001a\u001b\u0003\u0002\u0006\n",
    "h\u0002\u0014\u0003\u0002\u0002\u0002\u0004\u0017\u0003\u0002\u0002",
    "\u0002\u0006$\u0003\u0002\u0002\u0002\b&\u0003\u0002\u0002\u0002\n5",
    "\u0003\u0002\u0002\u0002\f@\u0003\u0002\u0002\u0002\u000e[\u0003\u0002",
    "\u0002\u0002\u0010]\u0003\u0002\u0002\u0002\u0012_\u0003\u0002\u0002",
    "\u0002\u0014\u0015\u0007\u0003\u0002\u0002\u0015\u0016\u0005\u0004\u0003",
    "\u0002\u0016\u0003\u0003\u0002\u0002\u0002\u0017\u001b\u0007\u000b\u0002",
    "\u0002\u0018\u001a\u0005\u0006\u0004\u0002\u0019\u0018\u0003\u0002\u0002",
    "\u0002\u001a\u001d\u0003\u0002\u0002\u0002\u001b\u0019\u0003\u0002\u0002",
    "\u0002\u001b\u001c\u0003\u0002\u0002\u0002\u001c\u001e\u0003\u0002\u0002",
    "\u0002\u001d\u001b\u0003\u0002\u0002\u0002\u001e\u001f\u0007\f\u0002",
    "\u0002\u001f\u0005\u0003\u0002\u0002\u0002 %\u0005\b\u0005\u0002!\"",
    "\u0005\n\u0006\u0002\"#\u0007\u000f\u0002\u0002#%\u0003\u0002\u0002",
    "\u0002$ \u0003\u0002\u0002\u0002$!\u0003\u0002\u0002\u0002%\u0007\u0003",
    "\u0002\u0002\u0002&\'\u0007\u0004\u0002\u0002\'(\u0007\r\u0002\u0002",
    "()\u0005\f\u0007\u0002)*\u0007\u000e\u0002\u0002*-\u0005\u0004\u0003",
    "\u0002+,\u0007\u0005\u0002\u0002,.\u0005\u0004\u0003\u0002-+\u0003\u0002",
    "\u0002\u0002-.\u0003\u0002\u0002\u0002.\t\u0003\u0002\u0002\u0002/0",
    "\u0007\u001f\u0002\u000201\u0007$\u0002\u000216\u0005\u0012\n\u0002",
    "23\u0007\u001f\u0002\u000234\u0007$\u0002\u000246\u0005\f\u0007\u0002",
    "5/\u0003\u0002\u0002\u000252\u0003\u0002\u0002\u00026\u000b\u0003\u0002",
    "\u0002\u000278\b\u0007\u0001\u000289\t\u0002\u0002\u00029A\u0005\f\u0007",
    "\b:;\u0007\r\u0002\u0002;<\u0005\f\u0007\u0002<=\u0007\u000e\u0002\u0002",
    "=A\u0003\u0002\u0002\u0002>A\u0005\u000e\b\u0002?A\u0007$\u0002\u0002",
    "@7\u0003\u0002\u0002\u0002@:\u0003\u0002\u0002\u0002@>\u0003\u0002\u0002",
    "\u0002@?\u0003\u0002\u0002\u0002AS\u0003\u0002\u0002\u0002BC\f\u0007",
    "\u0002\u0002CD\t\u0003\u0002\u0002DR\u0005\f\u0007\bEF\f\u0006\u0002",
    "\u0002FG\t\u0004\u0002\u0002GR\u0005\f\u0007\u0007HI\f\u0005\u0002\u0002",
    "IJ\t\u0005\u0002\u0002JR\u0005\f\u0007\u0006KL\f\u0004\u0002\u0002L",
    "M\u0007\u001c\u0002\u0002MR\u0005\f\u0007\u0005NO\f\u0003\u0002\u0002",
    "OP\u0007\u001d\u0002\u0002PR\u0005\f\u0007\u0004QB\u0003\u0002\u0002",
    "\u0002QE\u0003\u0002\u0002\u0002QH\u0003\u0002\u0002\u0002QK\u0003\u0002",
    "\u0002\u0002QN\u0003\u0002\u0002\u0002RU\u0003\u0002\u0002\u0002SQ\u0003",
    "\u0002\u0002\u0002ST\u0003\u0002\u0002\u0002T\r\u0003\u0002\u0002\u0002",
    "US\u0003\u0002\u0002\u0002V\\\u0005\u0010\t\u0002W\\\u0007\u001f\u0002",
    "\u0002X\\\u0007 \u0002\u0002Y\\\u0007!\u0002\u0002Z\\\u0007\"\u0002",
    "\u0002[V\u0003\u0002\u0002\u0002[W\u0003\u0002\u0002\u0002[X\u0003\u0002",
    "\u0002\u0002[Y\u0003\u0002\u0002\u0002[Z\u0003\u0002\u0002\u0002\\\u000f",
    "\u0003\u0002\u0002\u0002]^\t\u0006\u0002\u0002^\u0011\u0003\u0002\u0002",
    "\u0002_`\t\u0007\u0002\u0002`\u0013\u0003\u0002\u0002\u0002\n\u001b",
    "$-5@QS["].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ 'null', "'Form'", "'if'", "'else'", "'boolean'", "'string'", 
                     "'integer'", "'float'", "'money'", "'{'", "'}'", "'('", 
                     "')'", "';'", "'=='", "'!='", "'>'", "'>='", "'<'", 
                     "'<='", "'+'", "'-'", "'*'", "'/'", "'true'", "'false'", 
                     "'&&'", "'||'", "'!'" ];

var symbolicNames = [ 'null', "FORM", "IF", "ELSE", "TYPE_BOOLEAN", "TYPE_STRING", 
                      "TYPE_INTEGER", "TYPE_FLOAT", "TYPE_MONEY", "LEFT_BRACE", 
                      "RIGHT_BRACE", "LEFT_PAREN", "RIGHT_PAREN", "SEMICOL", 
                      "EQ", "NOT_EQ", "GT", "GT_EQ", "LT", "LT_EQ", "PLUS", 
                      "MINUS", "MUL", "DIV", "BOOLEAN_TRUE", "BOOLEAN_FALSE", 
                      "AND", "OR", "NOT", "STRING_LITERAL", "INTEGER_LITERAL", 
                      "FLOAT_LITERAL", "MONEY_LITERAL", "MONEY_LITERAL_CENTS", 
                      "IDENTIFIER", "WHITESPACE" ];

var ruleNames =  [ "form", "block", "statement", "if_", "question", "expr", 
                   "literal", "booleanLiteral", "type" ];

function QLParser (input) {
	antlr4.Parser.call(this, input);
    this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
    this.ruleNames = ruleNames;
    this.literalNames = literalNames;
    this.symbolicNames = symbolicNames;
    return this;
}

QLParser.prototype = Object.create(antlr4.Parser.prototype);
QLParser.prototype.constructor = QLParser;

Object.defineProperty(QLParser.prototype, "atn", {
	get : function() {
		return atn;
	}
});

QLParser.EOF = antlr4.Token.EOF;
QLParser.FORM = 1;
QLParser.IF = 2;
QLParser.ELSE = 3;
QLParser.TYPE_BOOLEAN = 4;
QLParser.TYPE_STRING = 5;
QLParser.TYPE_INTEGER = 6;
QLParser.TYPE_FLOAT = 7;
QLParser.TYPE_MONEY = 8;
QLParser.LEFT_BRACE = 9;
QLParser.RIGHT_BRACE = 10;
QLParser.LEFT_PAREN = 11;
QLParser.RIGHT_PAREN = 12;
QLParser.SEMICOL = 13;
QLParser.EQ = 14;
QLParser.NOT_EQ = 15;
QLParser.GT = 16;
QLParser.GT_EQ = 17;
QLParser.LT = 18;
QLParser.LT_EQ = 19;
QLParser.PLUS = 20;
QLParser.MINUS = 21;
QLParser.MUL = 22;
QLParser.DIV = 23;
QLParser.BOOLEAN_TRUE = 24;
QLParser.BOOLEAN_FALSE = 25;
QLParser.AND = 26;
QLParser.OR = 27;
QLParser.NOT = 28;
QLParser.STRING_LITERAL = 29;
QLParser.INTEGER_LITERAL = 30;
QLParser.FLOAT_LITERAL = 31;
QLParser.MONEY_LITERAL = 32;
QLParser.MONEY_LITERAL_CENTS = 33;
QLParser.IDENTIFIER = 34;
QLParser.WHITESPACE = 35;

QLParser.RULE_form = 0;
QLParser.RULE_block = 1;
QLParser.RULE_statement = 2;
QLParser.RULE_if_ = 3;
QLParser.RULE_question = 4;
QLParser.RULE_expr = 5;
QLParser.RULE_literal = 6;
QLParser.RULE_booleanLiteral = 7;
QLParser.RULE_type = 8;

function FormContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_form;
    return this;
}

FormContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
FormContext.prototype.constructor = FormContext;

FormContext.prototype.FORM = function() {
    return this.getToken(QLParser.FORM, 0);
};

FormContext.prototype.block = function() {
    return this.getTypedRuleContext(BlockContext,0);
};

FormContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterForm(this);
	}
};

FormContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitForm(this);
	}
};

FormContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitForm(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.FormContext = FormContext;

QLParser.prototype.form = function() {

    var localctx = new FormContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, QLParser.RULE_form);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 18;
        this.match(QLParser.FORM);
        this.state = 19;
        this.block();
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

function BlockContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_block;
    return this;
}

BlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BlockContext.prototype.constructor = BlockContext;

BlockContext.prototype.LEFT_BRACE = function() {
    return this.getToken(QLParser.LEFT_BRACE, 0);
};

BlockContext.prototype.RIGHT_BRACE = function() {
    return this.getToken(QLParser.RIGHT_BRACE, 0);
};

BlockContext.prototype.statement = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(StatementContext);
    } else {
        return this.getTypedRuleContext(StatementContext,i);
    }
};

BlockContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterBlock(this);
	}
};

BlockContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitBlock(this);
	}
};

BlockContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitBlock(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.BlockContext = BlockContext;

QLParser.prototype.block = function() {

    var localctx = new BlockContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, QLParser.RULE_block);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 21;
        this.match(QLParser.LEFT_BRACE);
        this.state = 25;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLParser.IF || _la===QLParser.STRING_LITERAL) {
            this.state = 22;
            this.statement();
            this.state = 27;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 28;
        this.match(QLParser.RIGHT_BRACE);
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

function StatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_statement;
    return this;
}

StatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StatementContext.prototype.constructor = StatementContext;


 
StatementContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function IfStatementCaseContext(parser, ctx) {
	StatementContext.call(this, parser);
    StatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

IfStatementCaseContext.prototype = Object.create(StatementContext.prototype);
IfStatementCaseContext.prototype.constructor = IfStatementCaseContext;

QLParser.IfStatementCaseContext = IfStatementCaseContext;

IfStatementCaseContext.prototype.if_ = function() {
    return this.getTypedRuleContext(If_Context,0);
};
IfStatementCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterIfStatementCase(this);
	}
};

IfStatementCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitIfStatementCase(this);
	}
};

IfStatementCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitIfStatementCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function QuestionStatementCaseContext(parser, ctx) {
	StatementContext.call(this, parser);
    StatementContext.prototype.copyFrom.call(this, ctx);
    return this;
}

QuestionStatementCaseContext.prototype = Object.create(StatementContext.prototype);
QuestionStatementCaseContext.prototype.constructor = QuestionStatementCaseContext;

QLParser.QuestionStatementCaseContext = QuestionStatementCaseContext;

QuestionStatementCaseContext.prototype.question = function() {
    return this.getTypedRuleContext(QuestionContext,0);
};

QuestionStatementCaseContext.prototype.SEMICOL = function() {
    return this.getToken(QLParser.SEMICOL, 0);
};
QuestionStatementCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterQuestionStatementCase(this);
	}
};

QuestionStatementCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitQuestionStatementCase(this);
	}
};

QuestionStatementCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitQuestionStatementCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLParser.StatementContext = StatementContext;

QLParser.prototype.statement = function() {

    var localctx = new StatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, QLParser.RULE_statement);
    try {
        this.state = 34;
        switch(this._input.LA(1)) {
        case QLParser.IF:
            localctx = new IfStatementCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 30;
            this.if_();
            break;
        case QLParser.STRING_LITERAL:
            localctx = new QuestionStatementCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 31;
            this.question();
            this.state = 32;
            this.match(QLParser.SEMICOL);
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

function If_Context(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_if_;
    return this;
}

If_Context.prototype = Object.create(antlr4.ParserRuleContext.prototype);
If_Context.prototype.constructor = If_Context;

If_Context.prototype.IF = function() {
    return this.getToken(QLParser.IF, 0);
};

If_Context.prototype.LEFT_PAREN = function() {
    return this.getToken(QLParser.LEFT_PAREN, 0);
};

If_Context.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

If_Context.prototype.RIGHT_PAREN = function() {
    return this.getToken(QLParser.RIGHT_PAREN, 0);
};

If_Context.prototype.block = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(BlockContext);
    } else {
        return this.getTypedRuleContext(BlockContext,i);
    }
};

If_Context.prototype.ELSE = function() {
    return this.getToken(QLParser.ELSE, 0);
};

If_Context.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterIf_(this);
	}
};

If_Context.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitIf_(this);
	}
};

If_Context.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitIf_(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.If_Context = If_Context;

QLParser.prototype.if_ = function() {

    var localctx = new If_Context(this, this._ctx, this.state);
    this.enterRule(localctx, 6, QLParser.RULE_if_);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 36;
        this.match(QLParser.IF);
        this.state = 37;
        this.match(QLParser.LEFT_PAREN);
        this.state = 38;
        this.expr(0);
        this.state = 39;
        this.match(QLParser.RIGHT_PAREN);
        this.state = 40;
        this.block();
        this.state = 43;
        _la = this._input.LA(1);
        if(_la===QLParser.ELSE) {
            this.state = 41;
            this.match(QLParser.ELSE);
            this.state = 42;
            this.block();
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
    this.ruleIndex = QLParser.RULE_question;
    return this;
}

QuestionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionContext.prototype.constructor = QuestionContext;


 
QuestionContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function ExprQuestionCaseContext(parser, ctx) {
	QuestionContext.call(this, parser);
    QuestionContext.prototype.copyFrom.call(this, ctx);
    return this;
}

ExprQuestionCaseContext.prototype = Object.create(QuestionContext.prototype);
ExprQuestionCaseContext.prototype.constructor = ExprQuestionCaseContext;

QLParser.ExprQuestionCaseContext = ExprQuestionCaseContext;

ExprQuestionCaseContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLParser.STRING_LITERAL, 0);
};

ExprQuestionCaseContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};

ExprQuestionCaseContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};
ExprQuestionCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterExprQuestionCase(this);
	}
};

ExprQuestionCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitExprQuestionCase(this);
	}
};

ExprQuestionCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitExprQuestionCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function InputQuestionCaseContext(parser, ctx) {
	QuestionContext.call(this, parser);
    QuestionContext.prototype.copyFrom.call(this, ctx);
    return this;
}

InputQuestionCaseContext.prototype = Object.create(QuestionContext.prototype);
InputQuestionCaseContext.prototype.constructor = InputQuestionCaseContext;

QLParser.InputQuestionCaseContext = InputQuestionCaseContext;

InputQuestionCaseContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLParser.STRING_LITERAL, 0);
};

InputQuestionCaseContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};

InputQuestionCaseContext.prototype.type = function() {
    return this.getTypedRuleContext(TypeContext,0);
};
InputQuestionCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterInputQuestionCase(this);
	}
};

InputQuestionCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitInputQuestionCase(this);
	}
};

InputQuestionCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitInputQuestionCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLParser.QuestionContext = QuestionContext;

QLParser.prototype.question = function() {

    var localctx = new QuestionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, QLParser.RULE_question);
    try {
        this.state = 51;
        var la_ = this._interp.adaptivePredict(this._input,3,this._ctx);
        switch(la_) {
        case 1:
            localctx = new InputQuestionCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 45;
            this.match(QLParser.STRING_LITERAL);
            this.state = 46;
            this.match(QLParser.IDENTIFIER);
            this.state = 47;
            this.type();
            break;

        case 2:
            localctx = new ExprQuestionCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 48;
            this.match(QLParser.STRING_LITERAL);
            this.state = 49;
            this.match(QLParser.IDENTIFIER);
            this.state = 50;
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

function ExprContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_expr;
    return this;
}

ExprContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExprContext.prototype.constructor = ExprContext;


 
ExprContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};

function UnaryPrefixExprCaseContext(parser, ctx) {
	ExprContext.call(this, parser);
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

UnaryPrefixExprCaseContext.prototype = Object.create(ExprContext.prototype);
UnaryPrefixExprCaseContext.prototype.constructor = UnaryPrefixExprCaseContext;

QLParser.UnaryPrefixExprCaseContext = UnaryPrefixExprCaseContext;

UnaryPrefixExprCaseContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

UnaryPrefixExprCaseContext.prototype.NOT = function() {
    return this.getToken(QLParser.NOT, 0);
};

UnaryPrefixExprCaseContext.prototype.MINUS = function() {
    return this.getToken(QLParser.MINUS, 0);
};
UnaryPrefixExprCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterUnaryPrefixExprCase(this);
	}
};

UnaryPrefixExprCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitUnaryPrefixExprCase(this);
	}
};

UnaryPrefixExprCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitUnaryPrefixExprCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function ParenExprCaseContext(parser, ctx) {
	ExprContext.call(this, parser);
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

ParenExprCaseContext.prototype = Object.create(ExprContext.prototype);
ParenExprCaseContext.prototype.constructor = ParenExprCaseContext;

QLParser.ParenExprCaseContext = ParenExprCaseContext;

ParenExprCaseContext.prototype.LEFT_PAREN = function() {
    return this.getToken(QLParser.LEFT_PAREN, 0);
};

ParenExprCaseContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

ParenExprCaseContext.prototype.RIGHT_PAREN = function() {
    return this.getToken(QLParser.RIGHT_PAREN, 0);
};
ParenExprCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterParenExprCase(this);
	}
};

ParenExprCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitParenExprCase(this);
	}
};

ParenExprCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitParenExprCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function InfixExprCaseContext(parser, ctx) {
	ExprContext.call(this, parser);
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

InfixExprCaseContext.prototype = Object.create(ExprContext.prototype);
InfixExprCaseContext.prototype.constructor = InfixExprCaseContext;

QLParser.InfixExprCaseContext = InfixExprCaseContext;

InfixExprCaseContext.prototype.expr = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ExprContext);
    } else {
        return this.getTypedRuleContext(ExprContext,i);
    }
};

InfixExprCaseContext.prototype.MUL = function() {
    return this.getToken(QLParser.MUL, 0);
};

InfixExprCaseContext.prototype.DIV = function() {
    return this.getToken(QLParser.DIV, 0);
};

InfixExprCaseContext.prototype.PLUS = function() {
    return this.getToken(QLParser.PLUS, 0);
};

InfixExprCaseContext.prototype.MINUS = function() {
    return this.getToken(QLParser.MINUS, 0);
};

InfixExprCaseContext.prototype.EQ = function() {
    return this.getToken(QLParser.EQ, 0);
};

InfixExprCaseContext.prototype.NOT_EQ = function() {
    return this.getToken(QLParser.NOT_EQ, 0);
};

InfixExprCaseContext.prototype.GT = function() {
    return this.getToken(QLParser.GT, 0);
};

InfixExprCaseContext.prototype.GT_EQ = function() {
    return this.getToken(QLParser.GT_EQ, 0);
};

InfixExprCaseContext.prototype.LT = function() {
    return this.getToken(QLParser.LT, 0);
};

InfixExprCaseContext.prototype.LT_EQ = function() {
    return this.getToken(QLParser.LT_EQ, 0);
};

InfixExprCaseContext.prototype.AND = function() {
    return this.getToken(QLParser.AND, 0);
};

InfixExprCaseContext.prototype.OR = function() {
    return this.getToken(QLParser.OR, 0);
};
InfixExprCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterInfixExprCase(this);
	}
};

InfixExprCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitInfixExprCase(this);
	}
};

InfixExprCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitInfixExprCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function IdentifierExprCaseContext(parser, ctx) {
	ExprContext.call(this, parser);
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

IdentifierExprCaseContext.prototype = Object.create(ExprContext.prototype);
IdentifierExprCaseContext.prototype.constructor = IdentifierExprCaseContext;

QLParser.IdentifierExprCaseContext = IdentifierExprCaseContext;

IdentifierExprCaseContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};
IdentifierExprCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterIdentifierExprCase(this);
	}
};

IdentifierExprCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitIdentifierExprCase(this);
	}
};

IdentifierExprCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitIdentifierExprCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function LiteralExprCaseContext(parser, ctx) {
	ExprContext.call(this, parser);
    ExprContext.prototype.copyFrom.call(this, ctx);
    return this;
}

LiteralExprCaseContext.prototype = Object.create(ExprContext.prototype);
LiteralExprCaseContext.prototype.constructor = LiteralExprCaseContext;

QLParser.LiteralExprCaseContext = LiteralExprCaseContext;

LiteralExprCaseContext.prototype.literal = function() {
    return this.getTypedRuleContext(LiteralContext,0);
};
LiteralExprCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterLiteralExprCase(this);
	}
};

LiteralExprCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitLiteralExprCase(this);
	}
};

LiteralExprCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitLiteralExprCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLParser.prototype.expr = function(_p) {
	if(_p===undefined) {
	    _p = 0;
	}
    var _parentctx = this._ctx;
    var _parentState = this.state;
    var localctx = new ExprContext(this, this._ctx, _parentState);
    var _prevctx = localctx;
    var _startState = 10;
    this.enterRecursionRule(localctx, 10, QLParser.RULE_expr, _p);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 62;
        switch(this._input.LA(1)) {
        case QLParser.MINUS:
        case QLParser.NOT:
            localctx = new UnaryPrefixExprCaseContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;

            this.state = 54;
            _la = this._input.LA(1);
            if(!(_la===QLParser.MINUS || _la===QLParser.NOT)) {
            this._errHandler.recoverInline(this);
            }
            else {
                this.consume();
            }
            this.state = 55;
            this.expr(6);
            break;
        case QLParser.LEFT_PAREN:
            localctx = new ParenExprCaseContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 56;
            this.match(QLParser.LEFT_PAREN);
            this.state = 57;
            this.expr(0);
            this.state = 58;
            this.match(QLParser.RIGHT_PAREN);
            break;
        case QLParser.BOOLEAN_TRUE:
        case QLParser.BOOLEAN_FALSE:
        case QLParser.STRING_LITERAL:
        case QLParser.INTEGER_LITERAL:
        case QLParser.FLOAT_LITERAL:
        case QLParser.MONEY_LITERAL:
            localctx = new LiteralExprCaseContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 60;
            this.literal();
            break;
        case QLParser.IDENTIFIER:
            localctx = new IdentifierExprCaseContext(this, localctx);
            this._ctx = localctx;
            _prevctx = localctx;
            this.state = 61;
            this.match(QLParser.IDENTIFIER);
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 81;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,6,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                this.state = 79;
                var la_ = this._interp.adaptivePredict(this._input,5,this._ctx);
                switch(la_) {
                case 1:
                    localctx = new InfixExprCaseContext(this, new ExprContext(this, _parentctx, _parentState));
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 64;
                    if (!( this.precpred(this._ctx, 5))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 5)");
                    }
                    this.state = 65;
                    _la = this._input.LA(1);
                    if(!(_la===QLParser.MUL || _la===QLParser.DIV)) {
                    this._errHandler.recoverInline(this);
                    }
                    else {
                        this.consume();
                    }
                    this.state = 66;
                    this.expr(6);
                    break;

                case 2:
                    localctx = new InfixExprCaseContext(this, new ExprContext(this, _parentctx, _parentState));
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 67;
                    if (!( this.precpred(this._ctx, 4))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 4)");
                    }
                    this.state = 68;
                    _la = this._input.LA(1);
                    if(!(_la===QLParser.PLUS || _la===QLParser.MINUS)) {
                    this._errHandler.recoverInline(this);
                    }
                    else {
                        this.consume();
                    }
                    this.state = 69;
                    this.expr(5);
                    break;

                case 3:
                    localctx = new InfixExprCaseContext(this, new ExprContext(this, _parentctx, _parentState));
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 70;
                    if (!( this.precpred(this._ctx, 3))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 3)");
                    }
                    this.state = 71;
                    _la = this._input.LA(1);
                    if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLParser.EQ) | (1 << QLParser.NOT_EQ) | (1 << QLParser.GT) | (1 << QLParser.GT_EQ) | (1 << QLParser.LT) | (1 << QLParser.LT_EQ))) !== 0))) {
                    this._errHandler.recoverInline(this);
                    }
                    else {
                        this.consume();
                    }
                    this.state = 72;
                    this.expr(4);
                    break;

                case 4:
                    localctx = new InfixExprCaseContext(this, new ExprContext(this, _parentctx, _parentState));
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 73;
                    if (!( this.precpred(this._ctx, 2))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                    }
                    this.state = 74;
                    this.match(QLParser.AND);
                    this.state = 75;
                    this.expr(3);
                    break;

                case 5:
                    localctx = new InfixExprCaseContext(this, new ExprContext(this, _parentctx, _parentState));
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 76;
                    if (!( this.precpred(this._ctx, 1))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 1)");
                    }
                    this.state = 77;
                    this.match(QLParser.OR);
                    this.state = 78;
                    this.expr(2);
                    break;

                } 
            }
            this.state = 83;
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

function LiteralContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_literal;
    return this;
}

LiteralContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
LiteralContext.prototype.constructor = LiteralContext;


 
LiteralContext.prototype.copyFrom = function(ctx) {
    antlr4.ParserRuleContext.prototype.copyFrom.call(this, ctx);
};


function IntegerLiteralCaseContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

IntegerLiteralCaseContext.prototype = Object.create(LiteralContext.prototype);
IntegerLiteralCaseContext.prototype.constructor = IntegerLiteralCaseContext;

QLParser.IntegerLiteralCaseContext = IntegerLiteralCaseContext;

IntegerLiteralCaseContext.prototype.INTEGER_LITERAL = function() {
    return this.getToken(QLParser.INTEGER_LITERAL, 0);
};
IntegerLiteralCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterIntegerLiteralCase(this);
	}
};

IntegerLiteralCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitIntegerLiteralCase(this);
	}
};

IntegerLiteralCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitIntegerLiteralCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function MoneyLiteralCaseContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

MoneyLiteralCaseContext.prototype = Object.create(LiteralContext.prototype);
MoneyLiteralCaseContext.prototype.constructor = MoneyLiteralCaseContext;

QLParser.MoneyLiteralCaseContext = MoneyLiteralCaseContext;

MoneyLiteralCaseContext.prototype.MONEY_LITERAL = function() {
    return this.getToken(QLParser.MONEY_LITERAL, 0);
};
MoneyLiteralCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterMoneyLiteralCase(this);
	}
};

MoneyLiteralCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitMoneyLiteralCase(this);
	}
};

MoneyLiteralCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitMoneyLiteralCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function BooleanLiteralCaseContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

BooleanLiteralCaseContext.prototype = Object.create(LiteralContext.prototype);
BooleanLiteralCaseContext.prototype.constructor = BooleanLiteralCaseContext;

QLParser.BooleanLiteralCaseContext = BooleanLiteralCaseContext;

BooleanLiteralCaseContext.prototype.booleanLiteral = function() {
    return this.getTypedRuleContext(BooleanLiteralContext,0);
};
BooleanLiteralCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterBooleanLiteralCase(this);
	}
};

BooleanLiteralCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitBooleanLiteralCase(this);
	}
};

BooleanLiteralCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitBooleanLiteralCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function StringLiteralCaseContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

StringLiteralCaseContext.prototype = Object.create(LiteralContext.prototype);
StringLiteralCaseContext.prototype.constructor = StringLiteralCaseContext;

QLParser.StringLiteralCaseContext = StringLiteralCaseContext;

StringLiteralCaseContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLParser.STRING_LITERAL, 0);
};
StringLiteralCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterStringLiteralCase(this);
	}
};

StringLiteralCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitStringLiteralCase(this);
	}
};

StringLiteralCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitStringLiteralCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};


function FloatLiteralCaseContext(parser, ctx) {
	LiteralContext.call(this, parser);
    LiteralContext.prototype.copyFrom.call(this, ctx);
    return this;
}

FloatLiteralCaseContext.prototype = Object.create(LiteralContext.prototype);
FloatLiteralCaseContext.prototype.constructor = FloatLiteralCaseContext;

QLParser.FloatLiteralCaseContext = FloatLiteralCaseContext;

FloatLiteralCaseContext.prototype.FLOAT_LITERAL = function() {
    return this.getToken(QLParser.FLOAT_LITERAL, 0);
};
FloatLiteralCaseContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterFloatLiteralCase(this);
	}
};

FloatLiteralCaseContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitFloatLiteralCase(this);
	}
};

FloatLiteralCaseContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitFloatLiteralCase(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLParser.LiteralContext = LiteralContext;

QLParser.prototype.literal = function() {

    var localctx = new LiteralContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, QLParser.RULE_literal);
    try {
        this.state = 89;
        switch(this._input.LA(1)) {
        case QLParser.BOOLEAN_TRUE:
        case QLParser.BOOLEAN_FALSE:
            localctx = new BooleanLiteralCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 1);
            this.state = 84;
            this.booleanLiteral();
            break;
        case QLParser.STRING_LITERAL:
            localctx = new StringLiteralCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 2);
            this.state = 85;
            this.match(QLParser.STRING_LITERAL);
            break;
        case QLParser.INTEGER_LITERAL:
            localctx = new IntegerLiteralCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 3);
            this.state = 86;
            this.match(QLParser.INTEGER_LITERAL);
            break;
        case QLParser.FLOAT_LITERAL:
            localctx = new FloatLiteralCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 4);
            this.state = 87;
            this.match(QLParser.FLOAT_LITERAL);
            break;
        case QLParser.MONEY_LITERAL:
            localctx = new MoneyLiteralCaseContext(this, localctx);
            this.enterOuterAlt(localctx, 5);
            this.state = 88;
            this.match(QLParser.MONEY_LITERAL);
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

function BooleanLiteralContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_booleanLiteral;
    return this;
}

BooleanLiteralContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BooleanLiteralContext.prototype.constructor = BooleanLiteralContext;

BooleanLiteralContext.prototype.BOOLEAN_TRUE = function() {
    return this.getToken(QLParser.BOOLEAN_TRUE, 0);
};

BooleanLiteralContext.prototype.BOOLEAN_FALSE = function() {
    return this.getToken(QLParser.BOOLEAN_FALSE, 0);
};

BooleanLiteralContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterBooleanLiteral(this);
	}
};

BooleanLiteralContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitBooleanLiteral(this);
	}
};

BooleanLiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitBooleanLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.BooleanLiteralContext = BooleanLiteralContext;

QLParser.prototype.booleanLiteral = function() {

    var localctx = new BooleanLiteralContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, QLParser.RULE_booleanLiteral);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 91;
        _la = this._input.LA(1);
        if(!(_la===QLParser.BOOLEAN_TRUE || _la===QLParser.BOOLEAN_FALSE)) {
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

function TypeContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_type;
    return this;
}

TypeContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
TypeContext.prototype.constructor = TypeContext;

TypeContext.prototype.TYPE_BOOLEAN = function() {
    return this.getToken(QLParser.TYPE_BOOLEAN, 0);
};

TypeContext.prototype.TYPE_STRING = function() {
    return this.getToken(QLParser.TYPE_STRING, 0);
};

TypeContext.prototype.TYPE_INTEGER = function() {
    return this.getToken(QLParser.TYPE_INTEGER, 0);
};

TypeContext.prototype.TYPE_FLOAT = function() {
    return this.getToken(QLParser.TYPE_FLOAT, 0);
};

TypeContext.prototype.TYPE_MONEY = function() {
    return this.getToken(QLParser.TYPE_MONEY, 0);
};

TypeContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.enterType(this);
	}
};

TypeContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLListener ) {
        listener.exitType(this);
	}
};

TypeContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitType(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.TypeContext = TypeContext;

QLParser.prototype.type = function() {

    var localctx = new TypeContext(this, this._ctx, this.state);
    this.enterRule(localctx, 16, QLParser.RULE_type);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 93;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLParser.TYPE_BOOLEAN) | (1 << QLParser.TYPE_STRING) | (1 << QLParser.TYPE_INTEGER) | (1 << QLParser.TYPE_FLOAT) | (1 << QLParser.TYPE_MONEY))) !== 0))) {
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


QLParser.prototype.sempred = function(localctx, ruleIndex, predIndex) {
	switch(ruleIndex) {
	case 5:
			return this.expr_sempred(localctx, predIndex);
    default:
        throw "No predicate with index:" + ruleIndex;
   }
};

QLParser.prototype.expr_sempred = function(localctx, predIndex) {
	switch(predIndex) {
		case 0:
			return this.precpred(this._ctx, 5);
		case 1:
			return this.precpred(this._ctx, 4);
		case 2:
			return this.precpred(this._ctx, 3);
		case 3:
			return this.precpred(this._ctx, 2);
		case 4:
			return this.precpred(this._ctx, 1);
		default:
			throw "No predicate with index:" + predIndex;
	}
};


exports.QLParser = QLParser;

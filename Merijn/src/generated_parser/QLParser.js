// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/generated_parser\QL.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var QLVisitor = require('./QLVisitor').QLVisitor;

var grammarFileName = "QL.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003!O\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t\u0004",
    "\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004\b",
    "\t\b\u0004\t\t\t\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003",
    "\u0003\u0007\u0003\u0018\n\u0003\f\u0003\u000e\u0003\u001b\u000b\u0003",
    "\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0005\u0004#\n\u0004\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005,\n\u0005\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0005\u0007;\n\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0007\u0007",
    "F\n\u0007\f\u0007\u000e\u0007I\u000b\u0007\u0003\b\u0003\b\u0003\t\u0003",
    "\t\u0003\t\u0002\u0003\f\n\u0002\u0004\u0006\b\n\f\u000e\u0010\u0002",
    "\u0007\u0003\u0002\u0016\u0017\u0003\u0002\u0018\u0019\u0003\u0002\u0010",
    "\u0015\u0003\u0002\u001a\u001e\u0003\u0002\u0006\nO\u0002\u0012\u0003",
    "\u0002\u0002\u0002\u0004\u0015\u0003\u0002\u0002\u0002\u0006\"\u0003",
    "\u0002\u0002\u0002\b$\u0003\u0002\u0002\u0002\n-\u0003\u0002\u0002\u0002",
    "\f:\u0003\u0002\u0002\u0002\u000eJ\u0003\u0002\u0002\u0002\u0010L\u0003",
    "\u0002\u0002\u0002\u0012\u0013\u0007\u0003\u0002\u0002\u0013\u0014\u0005",
    "\u0004\u0003\u0002\u0014\u0003\u0003\u0002\u0002\u0002\u0015\u0019\u0007",
    "\u000b\u0002\u0002\u0016\u0018\u0005\u0006\u0004\u0002\u0017\u0016\u0003",
    "\u0002\u0002\u0002\u0018\u001b\u0003\u0002\u0002\u0002\u0019\u0017\u0003",
    "\u0002\u0002\u0002\u0019\u001a\u0003\u0002\u0002\u0002\u001a\u001c\u0003",
    "\u0002\u0002\u0002\u001b\u0019\u0003\u0002\u0002\u0002\u001c\u001d\u0007",
    "\f\u0002\u0002\u001d\u0005\u0003\u0002\u0002\u0002\u001e#\u0005\b\u0005",
    "\u0002\u001f \u0005\n\u0006\u0002 !\u0007\u000f\u0002\u0002!#\u0003",
    "\u0002\u0002\u0002\"\u001e\u0003\u0002\u0002\u0002\"\u001f\u0003\u0002",
    "\u0002\u0002#\u0007\u0003\u0002\u0002\u0002$%\u0007\u0004\u0002\u0002",
    "%&\u0007\r\u0002\u0002&\'\u0005\f\u0007\u0002\'(\u0007\u000e\u0002\u0002",
    "(+\u0005\u0004\u0003\u0002)*\u0007\u0005\u0002\u0002*,\u0005\u0004\u0003",
    "\u0002+)\u0003\u0002\u0002\u0002+,\u0003\u0002\u0002\u0002,\t\u0003",
    "\u0002\u0002\u0002-.\u0007\u001b\u0002\u0002./\u0007 \u0002\u0002/0",
    "\u0005\u0010\t\u00020\u000b\u0003\u0002\u0002\u000212\b\u0007\u0001",
    "\u000223\t\u0002\u0002\u00023;\u0005\f\u0007\u000645\u0007\r\u0002\u0002",
    "56\u0005\f\u0007\u000267\u0007\u000e\u0002\u00027;\u0003\u0002\u0002",
    "\u00028;\u0005\u000e\b\u00029;\u0007 \u0002\u0002:1\u0003\u0002\u0002",
    "\u0002:4\u0003\u0002\u0002\u0002:8\u0003\u0002\u0002\u0002:9\u0003\u0002",
    "\u0002\u0002;G\u0003\u0002\u0002\u0002<=\f\u0005\u0002\u0002=>\t\u0003",
    "\u0002\u0002>F\u0005\f\u0007\u0006?@\f\u0004\u0002\u0002@A\t\u0002\u0002",
    "\u0002AF\u0005\f\u0007\u0005BC\f\u0003\u0002\u0002CD\t\u0004\u0002\u0002",
    "DF\u0005\f\u0007\u0004E<\u0003\u0002\u0002\u0002E?\u0003\u0002\u0002",
    "\u0002EB\u0003\u0002\u0002\u0002FI\u0003\u0002\u0002\u0002GE\u0003\u0002",
    "\u0002\u0002GH\u0003\u0002\u0002\u0002H\r\u0003\u0002\u0002\u0002IG",
    "\u0003\u0002\u0002\u0002JK\t\u0005\u0002\u0002K\u000f\u0003\u0002\u0002",
    "\u0002LM\t\u0006\u0002\u0002M\u0011\u0003\u0002\u0002\u0002\b\u0019",
    "\"+:EG"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ 'null', "'Form'", "'if'", "'else'", "'boolean'", "'string'", 
                     "'integer'", "'float'", "'money'", "'{'", "'}'", "'('", 
                     "')'", "';'", "'=='", "'!='", "'>'", "'>='", "'<'", 
                     "'<='", "'+'", "'-'", "'*'", "'/'" ];

var symbolicNames = [ 'null', "FORM", "IF", "ELSE", "TYPE_BOOLEAN", "TYPE_STRING", 
                      "TYPE_INTEGER", "TYPE_FLOAT", "TYPE_MONEY", "LEFT_BRACE", 
                      "RIGHT_BRACE", "LEFT_PAREN", "RIGHT_PAREN", "SEMICOL", 
                      "EQ", "NOT_EQ", "GT", "GT_EQ", "LT", "LT_EQ", "PLUS", 
                      "MINUS", "STAR", "DIV", "BOOLEAN_LITERAL", "STRING_LITERAL", 
                      "INTEGER_LITERAL", "FLOAT_LITERAL", "MONEY_LITERAL", 
                      "MONEY_LITERAL_CENTS", "IDENTIFIER", "WHITESPACE" ];

var ruleNames =  [ "form", "block", "statement", "ifStatement", "question", 
                   "expr", "literal", "type" ];

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
QLParser.STAR = 22;
QLParser.DIV = 23;
QLParser.BOOLEAN_LITERAL = 24;
QLParser.STRING_LITERAL = 25;
QLParser.INTEGER_LITERAL = 26;
QLParser.FLOAT_LITERAL = 27;
QLParser.MONEY_LITERAL = 28;
QLParser.MONEY_LITERAL_CENTS = 29;
QLParser.IDENTIFIER = 30;
QLParser.WHITESPACE = 31;

QLParser.RULE_form = 0;
QLParser.RULE_block = 1;
QLParser.RULE_statement = 2;
QLParser.RULE_ifStatement = 3;
QLParser.RULE_question = 4;
QLParser.RULE_expr = 5;
QLParser.RULE_literal = 6;
QLParser.RULE_type = 7;

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
        this.state = 16;
        this.match(QLParser.FORM);
        this.state = 17;
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
        this.state = 19;
        this.match(QLParser.LEFT_BRACE);
        this.state = 23;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLParser.IF || _la===QLParser.STRING_LITERAL) {
            this.state = 20;
            this.statement();
            this.state = 25;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 26;
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

StatementContext.prototype.ifStatement = function() {
    return this.getTypedRuleContext(IfStatementContext,0);
};

StatementContext.prototype.question = function() {
    return this.getTypedRuleContext(QuestionContext,0);
};

StatementContext.prototype.SEMICOL = function() {
    return this.getToken(QLParser.SEMICOL, 0);
};

StatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.StatementContext = StatementContext;

QLParser.prototype.statement = function() {

    var localctx = new StatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, QLParser.RULE_statement);
    try {
        this.state = 32;
        switch(this._input.LA(1)) {
        case QLParser.IF:
            this.enterOuterAlt(localctx, 1);
            this.state = 28;
            this.ifStatement();
            break;
        case QLParser.STRING_LITERAL:
            this.enterOuterAlt(localctx, 2);
            this.state = 29;
            this.question();
            this.state = 30;
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

function IfStatementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_ifStatement;
    return this;
}

IfStatementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
IfStatementContext.prototype.constructor = IfStatementContext;

IfStatementContext.prototype.IF = function() {
    return this.getToken(QLParser.IF, 0);
};

IfStatementContext.prototype.LEFT_PAREN = function() {
    return this.getToken(QLParser.LEFT_PAREN, 0);
};

IfStatementContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

IfStatementContext.prototype.RIGHT_PAREN = function() {
    return this.getToken(QLParser.RIGHT_PAREN, 0);
};

IfStatementContext.prototype.block = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(BlockContext);
    } else {
        return this.getTypedRuleContext(BlockContext,i);
    }
};

IfStatementContext.prototype.ELSE = function() {
    return this.getToken(QLParser.ELSE, 0);
};

IfStatementContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitIfStatement(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.IfStatementContext = IfStatementContext;

QLParser.prototype.ifStatement = function() {

    var localctx = new IfStatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, QLParser.RULE_ifStatement);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 34;
        this.match(QLParser.IF);
        this.state = 35;
        this.match(QLParser.LEFT_PAREN);
        this.state = 36;
        this.expr(0);
        this.state = 37;
        this.match(QLParser.RIGHT_PAREN);
        this.state = 38;
        this.block();
        this.state = 41;
        _la = this._input.LA(1);
        if(_la===QLParser.ELSE) {
            this.state = 39;
            this.match(QLParser.ELSE);
            this.state = 40;
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

QuestionContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLParser.STRING_LITERAL, 0);
};

QuestionContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};

QuestionContext.prototype.type = function() {
    return this.getTypedRuleContext(TypeContext,0);
};

QuestionContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitQuestion(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.QuestionContext = QuestionContext;

QLParser.prototype.question = function() {

    var localctx = new QuestionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, QLParser.RULE_question);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 43;
        this.match(QLParser.STRING_LITERAL);
        this.state = 44;
        this.match(QLParser.IDENTIFIER);
        this.state = 45;
        this.type();
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

ExprContext.prototype.PLUS = function() {
    return this.getToken(QLParser.PLUS, 0);
};

ExprContext.prototype.MINUS = function() {
    return this.getToken(QLParser.MINUS, 0);
};

ExprContext.prototype.LEFT_PAREN = function() {
    return this.getToken(QLParser.LEFT_PAREN, 0);
};

ExprContext.prototype.RIGHT_PAREN = function() {
    return this.getToken(QLParser.RIGHT_PAREN, 0);
};

ExprContext.prototype.literal = function() {
    return this.getTypedRuleContext(LiteralContext,0);
};

ExprContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};

ExprContext.prototype.STAR = function() {
    return this.getToken(QLParser.STAR, 0);
};

ExprContext.prototype.DIV = function() {
    return this.getToken(QLParser.DIV, 0);
};

ExprContext.prototype.EQ = function() {
    return this.getToken(QLParser.EQ, 0);
};

ExprContext.prototype.NOT_EQ = function() {
    return this.getToken(QLParser.NOT_EQ, 0);
};

ExprContext.prototype.GT = function() {
    return this.getToken(QLParser.GT, 0);
};

ExprContext.prototype.GT_EQ = function() {
    return this.getToken(QLParser.GT_EQ, 0);
};

ExprContext.prototype.LT = function() {
    return this.getToken(QLParser.LT, 0);
};

ExprContext.prototype.LT_EQ = function() {
    return this.getToken(QLParser.LT_EQ, 0);
};

ExprContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitExpr(this);
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
        this.state = 56;
        switch(this._input.LA(1)) {
        case QLParser.PLUS:
        case QLParser.MINUS:
            this.state = 48;
            _la = this._input.LA(1);
            if(!(_la===QLParser.PLUS || _la===QLParser.MINUS)) {
            this._errHandler.recoverInline(this);
            }
            else {
                this.consume();
            }
            this.state = 49;
            this.expr(4);
            break;
        case QLParser.LEFT_PAREN:
            this.state = 50;
            this.match(QLParser.LEFT_PAREN);
            this.state = 51;
            this.expr(0);
            this.state = 52;
            this.match(QLParser.RIGHT_PAREN);
            break;
        case QLParser.BOOLEAN_LITERAL:
        case QLParser.STRING_LITERAL:
        case QLParser.INTEGER_LITERAL:
        case QLParser.FLOAT_LITERAL:
        case QLParser.MONEY_LITERAL:
            this.state = 54;
            this.literal();
            break;
        case QLParser.IDENTIFIER:
            this.state = 55;
            this.match(QLParser.IDENTIFIER);
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 69;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,5,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                this.state = 67;
                var la_ = this._interp.adaptivePredict(this._input,4,this._ctx);
                switch(la_) {
                case 1:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 58;
                    if (!( this.precpred(this._ctx, 3))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 3)");
                    }
                    this.state = 59;
                    _la = this._input.LA(1);
                    if(!(_la===QLParser.STAR || _la===QLParser.DIV)) {
                    this._errHandler.recoverInline(this);
                    }
                    else {
                        this.consume();
                    }
                    this.state = 60;
                    this.expr(4);
                    break;

                case 2:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 61;
                    if (!( this.precpred(this._ctx, 2))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                    }
                    this.state = 62;
                    _la = this._input.LA(1);
                    if(!(_la===QLParser.PLUS || _la===QLParser.MINUS)) {
                    this._errHandler.recoverInline(this);
                    }
                    else {
                        this.consume();
                    }
                    this.state = 63;
                    this.expr(3);
                    break;

                case 3:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    this.pushNewRecursionContext(localctx, _startState, QLParser.RULE_expr);
                    this.state = 64;
                    if (!( this.precpred(this._ctx, 1))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 1)");
                    }
                    this.state = 65;
                    _la = this._input.LA(1);
                    if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLParser.EQ) | (1 << QLParser.NOT_EQ) | (1 << QLParser.GT) | (1 << QLParser.GT_EQ) | (1 << QLParser.LT) | (1 << QLParser.LT_EQ))) !== 0))) {
                    this._errHandler.recoverInline(this);
                    }
                    else {
                        this.consume();
                    }
                    this.state = 66;
                    this.expr(2);
                    break;

                } 
            }
            this.state = 71;
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

LiteralContext.prototype.BOOLEAN_LITERAL = function() {
    return this.getToken(QLParser.BOOLEAN_LITERAL, 0);
};

LiteralContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLParser.STRING_LITERAL, 0);
};

LiteralContext.prototype.INTEGER_LITERAL = function() {
    return this.getToken(QLParser.INTEGER_LITERAL, 0);
};

LiteralContext.prototype.FLOAT_LITERAL = function() {
    return this.getToken(QLParser.FLOAT_LITERAL, 0);
};

LiteralContext.prototype.MONEY_LITERAL = function() {
    return this.getToken(QLParser.MONEY_LITERAL, 0);
};

LiteralContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLVisitor ) {
        return visitor.visitLiteral(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLParser.LiteralContext = LiteralContext;

QLParser.prototype.literal = function() {

    var localctx = new LiteralContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, QLParser.RULE_literal);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 72;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLParser.BOOLEAN_LITERAL) | (1 << QLParser.STRING_LITERAL) | (1 << QLParser.INTEGER_LITERAL) | (1 << QLParser.FLOAT_LITERAL) | (1 << QLParser.MONEY_LITERAL))) !== 0))) {
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
    this.enterRule(localctx, 14, QLParser.RULE_type);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 74;
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
			return this.precpred(this._ctx, 3);
		case 1:
			return this.precpred(this._ctx, 2);
		case 2:
			return this.precpred(this._ctx, 1);
		default:
			throw "No predicate with index:" + predIndex;
	}
};


exports.QLParser = QLParser;

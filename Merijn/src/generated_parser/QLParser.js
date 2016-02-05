// Generated from C:/Users/mwijngaard/Documents/Projects/software-construction/2015-2016/Merijn/src/generated_parser\QL.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var grammarFileName = "QL.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003\u001dP\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004\f\t\f\u0003",
    "\u0002\u0003\u0002\u0003\u0002\u0005\u0002\u001c\n\u0002\u0003\u0002",
    "\u0003\u0002\u0003\u0003\u0006\u0003!\n\u0003\r\u0003\u000e\u0003\"",
    "\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0005\u0004)\n\u0004",
    "\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0005\u00051\n\u0005\u0003\u0005\u0003\u0005\u0005\u00055\n\u0005\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0005\u0006:\n\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\b\u0003",
    "\b\u0005\bD\n\b\u0003\t\u0003\t\u0003\n\u0003\n\u0003\u000b\u0003\u000b",
    "\u0003\u000b\u0003\u000b\u0003\f\u0003\f\u0003\f\u0002\u0002\r\u0002",
    "\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0002\u0005\u0003\u0002",
    "\u0016\u001a\u0003\u0002\u0010\u0015\u0003\u0002\u0006\nK\u0002\u0018",
    "\u0003\u0002\u0002\u0002\u0004 \u0003\u0002\u0002\u0002\u0006(\u0003",
    "\u0002\u0002\u0002\b*\u0003\u0002\u0002\u0002\n6\u0003\u0002\u0002\u0002",
    "\f=\u0003\u0002\u0002\u0002\u000eC\u0003\u0002\u0002\u0002\u0010E\u0003",
    "\u0002\u0002\u0002\u0012G\u0003\u0002\u0002\u0002\u0014I\u0003\u0002",
    "\u0002\u0002\u0016M\u0003\u0002\u0002\u0002\u0018\u0019\u0007\u0003",
    "\u0002\u0002\u0019\u001b\u0007\u000b\u0002\u0002\u001a\u001c\u0005\u0004",
    "\u0003\u0002\u001b\u001a\u0003\u0002\u0002\u0002\u001b\u001c\u0003\u0002",
    "\u0002\u0002\u001c\u001d\u0003\u0002\u0002\u0002\u001d\u001e\u0007\f",
    "\u0002\u0002\u001e\u0003\u0003\u0002\u0002\u0002\u001f!\u0005\u0006",
    "\u0004\u0002 \u001f\u0003\u0002\u0002\u0002!\"\u0003\u0002\u0002\u0002",
    "\" \u0003\u0002\u0002\u0002\"#\u0003\u0002\u0002\u0002#\u0005\u0003",
    "\u0002\u0002\u0002$)\u0005\b\u0005\u0002%&\u0005\u0014\u000b\u0002&",
    "\'\u0007\u000f\u0002\u0002\')\u0003\u0002\u0002\u0002($\u0003\u0002",
    "\u0002\u0002(%\u0003\u0002\u0002\u0002)\u0007\u0003\u0002\u0002\u0002",
    "*+\u0007\u0004\u0002\u0002+,\u0007\r\u0002\u0002,-\u0005\f\u0007\u0002",
    "-.\u0007\u000e\u0002\u0002.0\u0007\u000b\u0002\u0002/1\u0005\u0004\u0003",
    "\u00020/\u0003\u0002\u0002\u000201\u0003\u0002\u0002\u000212\u0003\u0002",
    "\u0002\u000224\u0007\f\u0002\u000235\u0005\n\u0006\u000243\u0003\u0002",
    "\u0002\u000245\u0003\u0002\u0002\u00025\t\u0003\u0002\u0002\u000267",
    "\u0007\u0005\u0002\u000279\u0007\u000b\u0002\u00028:\u0005\u0004\u0003",
    "\u000298\u0003\u0002\u0002\u00029:\u0003\u0002\u0002\u0002:;\u0003\u0002",
    "\u0002\u0002;<\u0007\f\u0002\u0002<\u000b\u0003\u0002\u0002\u0002=>",
    "\u0005\u000e\b\u0002>?\u0005\u0012\n\u0002?@\u0005\u000e\b\u0002@\r",
    "\u0003\u0002\u0002\u0002AD\u0007\u001c\u0002\u0002BD\u0005\u0010\t\u0002",
    "CA\u0003\u0002\u0002\u0002CB\u0003\u0002\u0002\u0002D\u000f\u0003\u0002",
    "\u0002\u0002EF\t\u0002\u0002\u0002F\u0011\u0003\u0002\u0002\u0002GH",
    "\t\u0003\u0002\u0002H\u0013\u0003\u0002\u0002\u0002IJ\u0007\u0017\u0002",
    "\u0002JK\u0007\u001c\u0002\u0002KL\u0005\u0016\f\u0002L\u0015\u0003",
    "\u0002\u0002\u0002MN\t\u0004\u0002\u0002N\u0017\u0003\u0002\u0002\u0002",
    "\t\u001b\"(049C"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ 'null', "'Form'", "'if'", "'else'", "'boolean'", "'string'", 
                     "'integer'", "'float'", "'money'", "'{'", "'}'", "'('", 
                     "')'", "';'", "'=='", "'!='", "'>'", "'>='", "'<'", 
                     "'<='" ];

var symbolicNames = [ 'null', "FORM", "IF", "ELSE", "TYPE_BOOLEAN", "TYPE_STRING", 
                      "TYPE_INTEGER", "TYPE_FLOAT", "TYPE_MONEY", "LEFT_BRACE", 
                      "RIGHT_BRACE", "LEFT_PAREN", "RIGHT_PAREN", "SEMICOL", 
                      "EQ", "NOT_EQ", "GT", "GT_EQ", "LT", "LT_EQ", "BOOLEAN_LITERAL", 
                      "STRING_LITERAL", "INTEGER_LITERAL", "FLOAT_LITERAL", 
                      "MONEY_LITERAL", "MONEY_LITERAL_CENTS", "IDENTIFIER", 
                      "WHITESPACE" ];

var ruleNames =  [ "form", "statements", "statement", "if_statement", "else_statement", 
                   "condition", "expr", "literal", "comp", "question_statement", 
                   "type" ];

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
QLParser.BOOLEAN_LITERAL = 20;
QLParser.STRING_LITERAL = 21;
QLParser.INTEGER_LITERAL = 22;
QLParser.FLOAT_LITERAL = 23;
QLParser.MONEY_LITERAL = 24;
QLParser.MONEY_LITERAL_CENTS = 25;
QLParser.IDENTIFIER = 26;
QLParser.WHITESPACE = 27;

QLParser.RULE_form = 0;
QLParser.RULE_statements = 1;
QLParser.RULE_statement = 2;
QLParser.RULE_if_statement = 3;
QLParser.RULE_else_statement = 4;
QLParser.RULE_condition = 5;
QLParser.RULE_expr = 6;
QLParser.RULE_literal = 7;
QLParser.RULE_comp = 8;
QLParser.RULE_question_statement = 9;
QLParser.RULE_type = 10;

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

FormContext.prototype.LEFT_BRACE = function() {
    return this.getToken(QLParser.LEFT_BRACE, 0);
};

FormContext.prototype.RIGHT_BRACE = function() {
    return this.getToken(QLParser.RIGHT_BRACE, 0);
};

FormContext.prototype.statements = function() {
    return this.getTypedRuleContext(StatementsContext,0);
};




QLParser.FormContext = FormContext;

QLParser.prototype.form = function() {

    var localctx = new FormContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, QLParser.RULE_form);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 22;
        this.match(QLParser.FORM);
        this.state = 23;
        this.match(QLParser.LEFT_BRACE);
        this.state = 25;
        _la = this._input.LA(1);
        if(_la===QLParser.IF || _la===QLParser.STRING_LITERAL) {
            this.state = 24;
            this.statements();
        }

        this.state = 27;
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

function StatementsContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_statements;
    return this;
}

StatementsContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
StatementsContext.prototype.constructor = StatementsContext;

StatementsContext.prototype.statement = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(StatementContext);
    } else {
        return this.getTypedRuleContext(StatementContext,i);
    }
};




QLParser.StatementsContext = StatementsContext;

QLParser.prototype.statements = function() {

    var localctx = new StatementsContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, QLParser.RULE_statements);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 30; 
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        do {
            this.state = 29;
            this.statement();
            this.state = 32; 
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        } while(_la===QLParser.IF || _la===QLParser.STRING_LITERAL);
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

StatementContext.prototype.if_statement = function() {
    return this.getTypedRuleContext(If_statementContext,0);
};

StatementContext.prototype.question_statement = function() {
    return this.getTypedRuleContext(Question_statementContext,0);
};

StatementContext.prototype.SEMICOL = function() {
    return this.getToken(QLParser.SEMICOL, 0);
};




QLParser.StatementContext = StatementContext;

QLParser.prototype.statement = function() {

    var localctx = new StatementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, QLParser.RULE_statement);
    try {
        this.state = 38;
        switch(this._input.LA(1)) {
        case QLParser.IF:
            this.enterOuterAlt(localctx, 1);
            this.state = 34;
            this.if_statement();
            break;
        case QLParser.STRING_LITERAL:
            this.enterOuterAlt(localctx, 2);
            this.state = 35;
            this.question_statement();
            this.state = 36;
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

function If_statementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_if_statement;
    return this;
}

If_statementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
If_statementContext.prototype.constructor = If_statementContext;

If_statementContext.prototype.IF = function() {
    return this.getToken(QLParser.IF, 0);
};

If_statementContext.prototype.LEFT_PAREN = function() {
    return this.getToken(QLParser.LEFT_PAREN, 0);
};

If_statementContext.prototype.condition = function() {
    return this.getTypedRuleContext(ConditionContext,0);
};

If_statementContext.prototype.RIGHT_PAREN = function() {
    return this.getToken(QLParser.RIGHT_PAREN, 0);
};

If_statementContext.prototype.LEFT_BRACE = function() {
    return this.getToken(QLParser.LEFT_BRACE, 0);
};

If_statementContext.prototype.RIGHT_BRACE = function() {
    return this.getToken(QLParser.RIGHT_BRACE, 0);
};

If_statementContext.prototype.statements = function() {
    return this.getTypedRuleContext(StatementsContext,0);
};

If_statementContext.prototype.else_statement = function() {
    return this.getTypedRuleContext(Else_statementContext,0);
};




QLParser.If_statementContext = If_statementContext;

QLParser.prototype.if_statement = function() {

    var localctx = new If_statementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, QLParser.RULE_if_statement);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 40;
        this.match(QLParser.IF);
        this.state = 41;
        this.match(QLParser.LEFT_PAREN);
        this.state = 42;
        this.condition();
        this.state = 43;
        this.match(QLParser.RIGHT_PAREN);
        this.state = 44;
        this.match(QLParser.LEFT_BRACE);
        this.state = 46;
        _la = this._input.LA(1);
        if(_la===QLParser.IF || _la===QLParser.STRING_LITERAL) {
            this.state = 45;
            this.statements();
        }

        this.state = 48;
        this.match(QLParser.RIGHT_BRACE);
        this.state = 50;
        _la = this._input.LA(1);
        if(_la===QLParser.ELSE) {
            this.state = 49;
            this.else_statement();
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

function Else_statementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_else_statement;
    return this;
}

Else_statementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
Else_statementContext.prototype.constructor = Else_statementContext;

Else_statementContext.prototype.ELSE = function() {
    return this.getToken(QLParser.ELSE, 0);
};

Else_statementContext.prototype.LEFT_BRACE = function() {
    return this.getToken(QLParser.LEFT_BRACE, 0);
};

Else_statementContext.prototype.RIGHT_BRACE = function() {
    return this.getToken(QLParser.RIGHT_BRACE, 0);
};

Else_statementContext.prototype.statements = function() {
    return this.getTypedRuleContext(StatementsContext,0);
};




QLParser.Else_statementContext = Else_statementContext;

QLParser.prototype.else_statement = function() {

    var localctx = new Else_statementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, QLParser.RULE_else_statement);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 52;
        this.match(QLParser.ELSE);
        this.state = 53;
        this.match(QLParser.LEFT_BRACE);
        this.state = 55;
        _la = this._input.LA(1);
        if(_la===QLParser.IF || _la===QLParser.STRING_LITERAL) {
            this.state = 54;
            this.statements();
        }

        this.state = 57;
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

function ConditionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_condition;
    return this;
}

ConditionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ConditionContext.prototype.constructor = ConditionContext;

ConditionContext.prototype.expr = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(ExprContext);
    } else {
        return this.getTypedRuleContext(ExprContext,i);
    }
};

ConditionContext.prototype.comp = function() {
    return this.getTypedRuleContext(CompContext,0);
};




QLParser.ConditionContext = ConditionContext;

QLParser.prototype.condition = function() {

    var localctx = new ConditionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, QLParser.RULE_condition);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 59;
        this.expr();
        this.state = 60;
        this.comp();
        this.state = 61;
        this.expr();
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

ExprContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};

ExprContext.prototype.literal = function() {
    return this.getTypedRuleContext(LiteralContext,0);
};




QLParser.ExprContext = ExprContext;

QLParser.prototype.expr = function() {

    var localctx = new ExprContext(this, this._ctx, this.state);
    this.enterRule(localctx, 12, QLParser.RULE_expr);
    try {
        this.state = 65;
        switch(this._input.LA(1)) {
        case QLParser.IDENTIFIER:
            this.enterOuterAlt(localctx, 1);
            this.state = 63;
            this.match(QLParser.IDENTIFIER);
            break;
        case QLParser.BOOLEAN_LITERAL:
        case QLParser.STRING_LITERAL:
        case QLParser.INTEGER_LITERAL:
        case QLParser.FLOAT_LITERAL:
        case QLParser.MONEY_LITERAL:
            this.enterOuterAlt(localctx, 2);
            this.state = 64;
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




QLParser.LiteralContext = LiteralContext;

QLParser.prototype.literal = function() {

    var localctx = new LiteralContext(this, this._ctx, this.state);
    this.enterRule(localctx, 14, QLParser.RULE_literal);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 67;
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

function CompContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_comp;
    return this;
}

CompContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
CompContext.prototype.constructor = CompContext;

CompContext.prototype.EQ = function() {
    return this.getToken(QLParser.EQ, 0);
};

CompContext.prototype.NOT_EQ = function() {
    return this.getToken(QLParser.NOT_EQ, 0);
};

CompContext.prototype.GT = function() {
    return this.getToken(QLParser.GT, 0);
};

CompContext.prototype.GT_EQ = function() {
    return this.getToken(QLParser.GT_EQ, 0);
};

CompContext.prototype.LT = function() {
    return this.getToken(QLParser.LT, 0);
};

CompContext.prototype.LT_EQ = function() {
    return this.getToken(QLParser.LT_EQ, 0);
};




QLParser.CompContext = CompContext;

QLParser.prototype.comp = function() {

    var localctx = new CompContext(this, this._ctx, this.state);
    this.enterRule(localctx, 16, QLParser.RULE_comp);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 69;
        _la = this._input.LA(1);
        if(!((((_la) & ~0x1f) == 0 && ((1 << _la) & ((1 << QLParser.EQ) | (1 << QLParser.NOT_EQ) | (1 << QLParser.GT) | (1 << QLParser.GT_EQ) | (1 << QLParser.LT) | (1 << QLParser.LT_EQ))) !== 0))) {
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

function Question_statementContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLParser.RULE_question_statement;
    return this;
}

Question_statementContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
Question_statementContext.prototype.constructor = Question_statementContext;

Question_statementContext.prototype.STRING_LITERAL = function() {
    return this.getToken(QLParser.STRING_LITERAL, 0);
};

Question_statementContext.prototype.IDENTIFIER = function() {
    return this.getToken(QLParser.IDENTIFIER, 0);
};

Question_statementContext.prototype.type = function() {
    return this.getTypedRuleContext(TypeContext,0);
};




QLParser.Question_statementContext = Question_statementContext;

QLParser.prototype.question_statement = function() {

    var localctx = new Question_statementContext(this, this._ctx, this.state);
    this.enterRule(localctx, 18, QLParser.RULE_question_statement);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 71;
        this.match(QLParser.STRING_LITERAL);
        this.state = 72;
        this.match(QLParser.IDENTIFIER);
        this.state = 73;
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




QLParser.TypeContext = TypeContext;

QLParser.prototype.type = function() {

    var localctx = new TypeContext(this, this._ctx, this.state);
    this.enterRule(localctx, 20, QLParser.RULE_type);
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 75;
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


exports.QLParser = QLParser;

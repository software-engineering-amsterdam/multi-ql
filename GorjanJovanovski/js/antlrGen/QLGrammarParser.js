// Generated from C:/xampp/htdocs/Software Construction/multi-ql/GorjanJovanovski\QLGrammar.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');
var QLGrammarListener = require('./QLGrammarListener').QLGrammarListener;
var QLGrammarVisitor = require('./QLGrammarVisitor').QLGrammarVisitor;

var grammarFileName = "QLGrammar.g4";

var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0003!\u00cb\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004\u0004\t",
    "\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t\u0007\u0004",
    "\b\t\b\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0003\u0003\u0003\u0003\u0003\u0003\u0003\u0007\u0003\u001a\n\u0003",
    "\f\u0003\u000e\u0003\u001d\u000b\u0003\u0003\u0003\u0003\u0003\u0003",
    "\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003",
    "\u0004\u0003\u0004\u0003\u0004\u0005\u0004*\n\u0004\u0003\u0005\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0005\u0005O\n\u0005\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0005\u0006\u0089\n\u0006\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0005\u0007\u0097\n",
    "\u0007\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003",
    "\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003",
    "\b\u0003\b\u0005\b\u00ab\n\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b",
    "\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003",
    "\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003",
    "\b\u0003\b\u0003\b\u0007\b\u00c6\n\b\f\b\u000e\b\u00c9\u000b\b\u0003",
    "\b\u0002\u0003\u000e\t\u0002\u0004\u0006\b\n\f\u000e\u0002\u0002\u00dd",
    "\u0002\u0010\u0003\u0002\u0002\u0002\u0004\u0015\u0003\u0002\u0002\u0002",
    "\u0006)\u0003\u0002\u0002\u0002\bN\u0003\u0002\u0002\u0002\n\u0088\u0003",
    "\u0002\u0002\u0002\f\u0096\u0003\u0002\u0002\u0002\u000e\u00aa\u0003",
    "\u0002\u0002\u0002\u0010\u0011\u0007\u0003\u0002\u0002\u0011\u0012\u0007",
    "\u001b\u0002\u0002\u0012\u0013\u0005\u0004\u0003\u0002\u0013\u0014\b",
    "\u0002\u0001\u0002\u0014\u0003\u0003\u0002\u0002\u0002\u0015\u001b\u0007",
    "\u0004\u0002\u0002\u0016\u0017\u0005\u0006\u0004\u0002\u0017\u0018\b",
    "\u0003\u0001\u0002\u0018\u001a\u0003\u0002\u0002\u0002\u0019\u0016\u0003",
    "\u0002\u0002\u0002\u001a\u001d\u0003\u0002\u0002\u0002\u001b\u0019\u0003",
    "\u0002\u0002\u0002\u001b\u001c\u0003\u0002\u0002\u0002\u001c\u001e\u0003",
    "\u0002\u0002\u0002\u001d\u001b\u0003\u0002\u0002\u0002\u001e\u001f\u0007",
    "\u0005\u0002\u0002\u001f\u0005\u0003\u0002\u0002\u0002 !\u0005\n\u0006",
    "\u0002!\"\b\u0004\u0001\u0002\"*\u0003\u0002\u0002\u0002#$\u0005\b\u0005",
    "\u0002$%\b\u0004\u0001\u0002%*\u0003\u0002\u0002\u0002&\'\u0005\f\u0007",
    "\u0002\'(\b\u0004\u0001\u0002(*\u0003\u0002\u0002\u0002) \u0003\u0002",
    "\u0002\u0002)#\u0003\u0002\u0002\u0002)&\u0003\u0002\u0002\u0002*\u0007",
    "\u0003\u0002\u0002\u0002+,\u0007!\u0002\u0002,-\u0007\u001b\u0002\u0002",
    "-.\u0007\u001a\u0002\u0002./\u0007\u0006\u0002\u0002/O\b\u0005\u0001",
    "\u000201\u0007!\u0002\u000212\u0007\u001b\u0002\u000223\u0007\u001a",
    "\u0002\u000234\u0007\u0007\u0002\u00024O\b\u0005\u0001\u000256\u0007",
    "!\u0002\u000267\u0007\u001b\u0002\u000278\u0007\u001a\u0002\u000289",
    "\u0007\b\u0002\u00029O\b\u0005\u0001\u0002:;\u0007!\u0002\u0002;<\u0007",
    "\u001b\u0002\u0002<=\u0007\u001a\u0002\u0002=>\u0007\t\u0002\u0002>",
    "O\b\u0005\u0001\u0002?@\u0007!\u0002\u0002@A\u0007\u001b\u0002\u0002",
    "AB\u0007\u001a\u0002\u0002BC\u0007\n\u0002\u0002CO\b\u0005\u0001\u0002",
    "DE\u0007!\u0002\u0002EF\u0007\u001b\u0002\u0002FG\u0007\u001a\u0002",
    "\u0002GH\u0007\u000b\u0002\u0002HO\b\u0005\u0001\u0002IJ\u0007!\u0002",
    "\u0002JK\u0007\u001b\u0002\u0002KL\u0007\u001a\u0002\u0002LM\u0007\f",
    "\u0002\u0002MO\b\u0005\u0001\u0002N+\u0003\u0002\u0002\u0002N0\u0003",
    "\u0002\u0002\u0002N5\u0003\u0002\u0002\u0002N:\u0003\u0002\u0002\u0002",
    "N?\u0003\u0002\u0002\u0002ND\u0003\u0002\u0002\u0002NI\u0003\u0002\u0002",
    "\u0002O\t\u0003\u0002\u0002\u0002PQ\u0007!\u0002\u0002QR\u0007\u001b",
    "\u0002\u0002RS\u0007\u001a\u0002\u0002ST\u0007\u0006\u0002\u0002TU\u0007",
    "\r\u0002\u0002UV\u0005\u000e\b\u0002VW\b\u0006\u0001\u0002W\u0089\u0003",
    "\u0002\u0002\u0002XY\u0007!\u0002\u0002YZ\u0007\u001b\u0002\u0002Z[",
    "\u0007\u001a\u0002\u0002[\\\u0007\u0007\u0002\u0002\\]\u0007\r\u0002",
    "\u0002]^\u0005\u000e\b\u0002^_\b\u0006\u0001\u0002_\u0089\u0003\u0002",
    "\u0002\u0002`a\u0007!\u0002\u0002ab\u0007\u001b\u0002\u0002bc\u0007",
    "\u001a\u0002\u0002cd\u0007\b\u0002\u0002de\u0007\r\u0002\u0002ef\u0005",
    "\u000e\b\u0002fg\b\u0006\u0001\u0002g\u0089\u0003\u0002\u0002\u0002",
    "hi\u0007!\u0002\u0002ij\u0007\u001b\u0002\u0002jk\u0007\u001a\u0002",
    "\u0002kl\u0007\t\u0002\u0002lm\u0007\r\u0002\u0002mn\u0005\u000e\b\u0002",
    "no\b\u0006\u0001\u0002o\u0089\u0003\u0002\u0002\u0002pq\u0007!\u0002",
    "\u0002qr\u0007\u001b\u0002\u0002rs\u0007\u001a\u0002\u0002st\u0007\n",
    "\u0002\u0002tu\u0007\r\u0002\u0002uv\u0005\u000e\b\u0002vw\b\u0006\u0001",
    "\u0002w\u0089\u0003\u0002\u0002\u0002xy\u0007!\u0002\u0002yz\u0007\u001b",
    "\u0002\u0002z{\u0007\u001a\u0002\u0002{|\u0007\u000b\u0002\u0002|}\u0007",
    "\r\u0002\u0002}~\u0005\u000e\b\u0002~\u007f\b\u0006\u0001\u0002\u007f",
    "\u0089\u0003\u0002\u0002\u0002\u0080\u0081\u0007!\u0002\u0002\u0081",
    "\u0082\u0007\u001b\u0002\u0002\u0082\u0083\u0007\u001a\u0002\u0002\u0083",
    "\u0084\u0007\f\u0002\u0002\u0084\u0085\u0007\r\u0002\u0002\u0085\u0086",
    "\u0005\u000e\b\u0002\u0086\u0087\b\u0006\u0001\u0002\u0087\u0089\u0003",
    "\u0002\u0002\u0002\u0088P\u0003\u0002\u0002\u0002\u0088X\u0003\u0002",
    "\u0002\u0002\u0088`\u0003\u0002\u0002\u0002\u0088h\u0003\u0002\u0002",
    "\u0002\u0088p\u0003\u0002\u0002\u0002\u0088x\u0003\u0002\u0002\u0002",
    "\u0088\u0080\u0003\u0002\u0002\u0002\u0089\u000b\u0003\u0002\u0002\u0002",
    "\u008a\u008b\u0007\u000e\u0002\u0002\u008b\u008c\u0005\u000e\b\u0002",
    "\u008c\u008d\u0005\u0004\u0003\u0002\u008d\u008e\b\u0007\u0001\u0002",
    "\u008e\u0097\u0003\u0002\u0002\u0002\u008f\u0090\u0007\u000e\u0002\u0002",
    "\u0090\u0091\u0005\u000e\b\u0002\u0091\u0092\u0005\u0004\u0003\u0002",
    "\u0092\u0093\u0007\u000f\u0002\u0002\u0093\u0094\u0005\u0004\u0003\u0002",
    "\u0094\u0095\b\u0007\u0001\u0002\u0095\u0097\u0003\u0002\u0002\u0002",
    "\u0096\u008a\u0003\u0002\u0002\u0002\u0096\u008f\u0003\u0002\u0002\u0002",
    "\u0097\r\u0003\u0002\u0002\u0002\u0098\u0099\b\b\u0001\u0002\u0099\u009a",
    "\u0007\u0015\u0002\u0002\u009a\u009b\u0005\u000e\b\u0003\u009b\u009c",
    "\b\b\u0001\u0002\u009c\u00ab\u0003\u0002\u0002\u0002\u009d\u009e\u0007",
    "\u0019\u0002\u0002\u009e\u00ab\b\b\u0001\u0002\u009f\u00a0\u0007\u001d",
    "\u0002\u0002\u00a0\u00ab\b\b\u0001\u0002\u00a1\u00a2\u0007\u001e\u0002",
    "\u0002\u00a2\u00ab\b\b\u0001\u0002\u00a3\u00a4\u0007\u001b\u0002\u0002",
    "\u00a4\u00ab\b\b\u0001\u0002\u00a5\u00a6\u0007\u0010\u0002\u0002\u00a6",
    "\u00a7\u0005\u000e\b\u0002\u00a7\u00a8\u0007\u0011\u0002\u0002\u00a8",
    "\u00a9\b\b\u0001\u0002\u00a9\u00ab\u0003\u0002\u0002\u0002\u00aa\u0098",
    "\u0003\u0002\u0002\u0002\u00aa\u009d\u0003\u0002\u0002\u0002\u00aa\u009f",
    "\u0003\u0002\u0002\u0002\u00aa\u00a1\u0003\u0002\u0002\u0002\u00aa\u00a3",
    "\u0003\u0002\u0002\u0002\u00aa\u00a5\u0003\u0002\u0002\u0002\u00ab\u00c7",
    "\u0003\u0002\u0002\u0002\u00ac\u00ad\f\b\u0002\u0002\u00ad\u00ae\u0007",
    "\u0012\u0002\u0002\u00ae\u00af\u0005\u000e\b\t\u00af\u00b0\b\b\u0001",
    "\u0002\u00b0\u00c6\u0003\u0002\u0002\u0002\u00b1\u00b2\f\u0007\u0002",
    "\u0002\u00b2\u00b3\u0007\u0013\u0002\u0002\u00b3\u00b4\u0005\u000e\b",
    "\b\u00b4\u00b5\b\b\u0001\u0002\u00b5\u00c6\u0003\u0002\u0002\u0002\u00b6",
    "\u00b7\f\u0006\u0002\u0002\u00b7\u00b8\u0007\u0016\u0002\u0002\u00b8",
    "\u00b9\u0005\u000e\b\u0007\u00b9\u00ba\b\b\u0001\u0002\u00ba\u00c6\u0003",
    "\u0002\u0002\u0002\u00bb\u00bc\f\u0005\u0002\u0002\u00bc\u00bd\u0007",
    "\u0014\u0002\u0002\u00bd\u00be\u0005\u000e\b\u0006\u00be\u00bf\b\b\u0001",
    "\u0002\u00bf\u00c6\u0003\u0002\u0002\u0002\u00c0\u00c1\f\u0004\u0002",
    "\u0002\u00c1\u00c2\u0007\u0017\u0002\u0002\u00c2\u00c3\u0005\u000e\b",
    "\u0005\u00c3\u00c4\b\b\u0001\u0002\u00c4\u00c6\u0003\u0002\u0002\u0002",
    "\u00c5\u00ac\u0003\u0002\u0002\u0002\u00c5\u00b1\u0003\u0002\u0002\u0002",
    "\u00c5\u00b6\u0003\u0002\u0002\u0002\u00c5\u00bb\u0003\u0002\u0002\u0002",
    "\u00c5\u00c0\u0003\u0002\u0002\u0002\u00c6\u00c9\u0003\u0002\u0002\u0002",
    "\u00c7\u00c5\u0003\u0002\u0002\u0002\u00c7\u00c8\u0003\u0002\u0002\u0002",
    "\u00c8\u000f\u0003\u0002\u0002\u0002\u00c9\u00c7\u0003\u0002\u0002\u0002",
    "\n\u001b)N\u0088\u0096\u00aa\u00c5\u00c7"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

var sharedContextCache = new antlr4.PredictionContextCache();

var literalNames = [ 'null', "'form'", "'{'", "'}'", "'decimal'", "'integer'", 
                     "'boolean'", "'string'", "'money'", "'currency'", "'date'", 
                     "'='", "'if'", "'else'", "'('", "')'", 'null', 'null', 
                     'null', "'!'", 'null', 'null', 'null', 'null', "':'" ];

var symbolicNames = [ 'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', 'null', 'null', 'null', 'null', 'null', 
                      'null', 'null', "MULTDIVOP", "ADDSUBOP", "BOOLOP", 
                      "NOTOP", "COMPOP", "BOOLCOMOP", "TYPE", "BOOLEAN", 
                      "DELIMITER", "LABEL", "NEWLINE", "NUMBER", "DECIMAL", 
                      "WHITESPACE", "BRACKETS", "STRING" ];

var ruleNames =  [ "form", "block", "blockstmt", "question", "computedquestion", 
                   "ifstmt", "expr" ];

function QLGrammarParser (input) {
	antlr4.Parser.call(this, input);
    this._interp = new antlr4.atn.ParserATNSimulator(this, atn, decisionsToDFA, sharedContextCache);
    this.ruleNames = ruleNames;
    this.literalNames = literalNames;
    this.symbolicNames = symbolicNames;
    return this;
}

QLGrammarParser.prototype = Object.create(antlr4.Parser.prototype);
QLGrammarParser.prototype.constructor = QLGrammarParser;

Object.defineProperty(QLGrammarParser.prototype, "atn", {
	get : function() {
		return atn;
	}
});

QLGrammarParser.EOF = antlr4.Token.EOF;
QLGrammarParser.T__0 = 1;
QLGrammarParser.T__1 = 2;
QLGrammarParser.T__2 = 3;
QLGrammarParser.T__3 = 4;
QLGrammarParser.T__4 = 5;
QLGrammarParser.T__5 = 6;
QLGrammarParser.T__6 = 7;
QLGrammarParser.T__7 = 8;
QLGrammarParser.T__8 = 9;
QLGrammarParser.T__9 = 10;
QLGrammarParser.T__10 = 11;
QLGrammarParser.T__11 = 12;
QLGrammarParser.T__12 = 13;
QLGrammarParser.T__13 = 14;
QLGrammarParser.T__14 = 15;
QLGrammarParser.MULTDIVOP = 16;
QLGrammarParser.ADDSUBOP = 17;
QLGrammarParser.BOOLOP = 18;
QLGrammarParser.NOTOP = 19;
QLGrammarParser.COMPOP = 20;
QLGrammarParser.BOOLCOMOP = 21;
QLGrammarParser.TYPE = 22;
QLGrammarParser.BOOLEAN = 23;
QLGrammarParser.DELIMITER = 24;
QLGrammarParser.LABEL = 25;
QLGrammarParser.NEWLINE = 26;
QLGrammarParser.NUMBER = 27;
QLGrammarParser.DECIMAL = 28;
QLGrammarParser.WHITESPACE = 29;
QLGrammarParser.BRACKETS = 30;
QLGrammarParser.STRING = 31;

QLGrammarParser.RULE_form = 0;
QLGrammarParser.RULE_block = 1;
QLGrammarParser.RULE_blockstmt = 2;
QLGrammarParser.RULE_question = 3;
QLGrammarParser.RULE_computedquestion = 4;
QLGrammarParser.RULE_ifstmt = 5;
QLGrammarParser.RULE_expr = 6;

function FormContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLGrammarParser.RULE_form;
    this.FormNode = null
    this.lbl = null; // Token
    this.blk = null; // BlockContext
    return this;
}

FormContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
FormContext.prototype.constructor = FormContext;

FormContext.prototype.LABEL = function() {
    return this.getToken(QLGrammarParser.LABEL, 0);
};

FormContext.prototype.block = function() {
    return this.getTypedRuleContext(BlockContext,0);
};

FormContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterForm(this);
	}
};

FormContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitForm(this);
	}
};

FormContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitForm(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLGrammarParser.FormContext = FormContext;

QLGrammarParser.prototype.form = function() {

    var localctx = new FormContext(this, this._ctx, this.state);
    this.enterRule(localctx, 0, QLGrammarParser.RULE_form);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 14;
        this.match(QLGrammarParser.T__0);
        this.state = 15;
        localctx.lbl = this.match(QLGrammarParser.LABEL);
        this.state = 16;
        localctx.blk = this.block();
        localctx.FormNode = new FormNode((localctx.lbl===null ? null : localctx.lbl.text), localctx.blk.BlockArray)
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
    this.ruleIndex = QLGrammarParser.RULE_block;
    this.BlockArray = null
    this.blk = null; // BlockstmtContext
    return this;
}

BlockContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BlockContext.prototype.constructor = BlockContext;

BlockContext.prototype.blockstmt = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(BlockstmtContext);
    } else {
        return this.getTypedRuleContext(BlockstmtContext,i);
    }
};

BlockContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterBlock(this);
	}
};

BlockContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitBlock(this);
	}
};

BlockContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitBlock(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLGrammarParser.BlockContext = BlockContext;

QLGrammarParser.prototype.block = function() {

    var localctx = new BlockContext(this, this._ctx, this.state);
    this.enterRule(localctx, 2, QLGrammarParser.RULE_block);
     localctx.BlockArray =  new Array() 
    var _la = 0; // Token type
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 19;
        this.match(QLGrammarParser.T__1);
        this.state = 25;
        this._errHandler.sync(this);
        _la = this._input.LA(1);
        while(_la===QLGrammarParser.T__11 || _la===QLGrammarParser.STRING) {
            this.state = 20;
            localctx.blk = this.blockstmt();
            localctx.BlockArray.push(localctx.blk.ResultNode)
            this.state = 27;
            this._errHandler.sync(this);
            _la = this._input.LA(1);
        }
        this.state = 28;
        this.match(QLGrammarParser.T__2);
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

function BlockstmtContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLGrammarParser.RULE_blockstmt;
    this.ResultNode = null
    this.cq = null; // ComputedquestionContext
    this.q = null; // QuestionContext
    this.i = null; // IfstmtContext
    return this;
}

BlockstmtContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
BlockstmtContext.prototype.constructor = BlockstmtContext;

BlockstmtContext.prototype.computedquestion = function() {
    return this.getTypedRuleContext(ComputedquestionContext,0);
};

BlockstmtContext.prototype.question = function() {
    return this.getTypedRuleContext(QuestionContext,0);
};

BlockstmtContext.prototype.ifstmt = function() {
    return this.getTypedRuleContext(IfstmtContext,0);
};

BlockstmtContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterBlockstmt(this);
	}
};

BlockstmtContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitBlockstmt(this);
	}
};

BlockstmtContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitBlockstmt(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLGrammarParser.BlockstmtContext = BlockstmtContext;

QLGrammarParser.prototype.blockstmt = function() {

    var localctx = new BlockstmtContext(this, this._ctx, this.state);
    this.enterRule(localctx, 4, QLGrammarParser.RULE_blockstmt);
    try {
        this.state = 39;
        var la_ = this._interp.adaptivePredict(this._input,1,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 30;
            localctx.cq = this.computedquestion();
            localctx.ResultNode = localctx.cq.ResultNode
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 33;
            localctx.q = this.question();
            localctx.ResultNode = localctx.q.ResultNode
            break;

        case 3:
            this.enterOuterAlt(localctx, 3);
            this.state = 36;
            localctx.i = this.ifstmt();
            localctx.ResultNode = localctx.i.ResultNode
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

function QuestionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLGrammarParser.RULE_question;
    this.ResultNode = null
    this.txt = null; // Token
    this.lbl = null; // Token
    return this;
}

QuestionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
QuestionContext.prototype.constructor = QuestionContext;

QuestionContext.prototype.DELIMITER = function() {
    return this.getToken(QLGrammarParser.DELIMITER, 0);
};

QuestionContext.prototype.STRING = function() {
    return this.getToken(QLGrammarParser.STRING, 0);
};

QuestionContext.prototype.LABEL = function() {
    return this.getToken(QLGrammarParser.LABEL, 0);
};

QuestionContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterQuestion(this);
	}
};

QuestionContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitQuestion(this);
	}
};

QuestionContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitQuestion(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLGrammarParser.QuestionContext = QuestionContext;

QLGrammarParser.prototype.question = function() {

    var localctx = new QuestionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 6, QLGrammarParser.RULE_question);
    try {
        this.state = 76;
        var la_ = this._interp.adaptivePredict(this._input,2,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 41;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 42;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 43;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 44;
            this.match(QLGrammarParser.T__3);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new DecimalType(), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 46;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 47;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 48;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 49;
            this.match(QLGrammarParser.T__4);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new NumberType(), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 3:
            this.enterOuterAlt(localctx, 3);
            this.state = 51;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 52;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 53;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 54;
            this.match(QLGrammarParser.T__5);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new BooleanType(), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 4:
            this.enterOuterAlt(localctx, 4);
            this.state = 56;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 57;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 58;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 59;
            this.match(QLGrammarParser.T__6);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new StringType(), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 5:
            this.enterOuterAlt(localctx, 5);
            this.state = 61;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 62;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 63;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 64;
            this.match(QLGrammarParser.T__7);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new DecimalType(), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 6:
            this.enterOuterAlt(localctx, 6);
            this.state = 66;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 67;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 68;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 69;
            this.match(QLGrammarParser.T__8);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new DecimalType(), (localctx.txt === null ? 0 : localctx.txt.line))
            break;

        case 7:
            this.enterOuterAlt(localctx, 7);
            this.state = 71;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 72;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 73;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 74;
            this.match(QLGrammarParser.T__9);
            localctx.ResultNode = new QuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new DateType(), (localctx.txt === null ? 0 : localctx.txt.line))
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

function ComputedquestionContext(parser, parent, invokingState) {
	if(parent===undefined) {
	    parent = null;
	}
	if(invokingState===undefined || invokingState===null) {
		invokingState = -1;
	}
	antlr4.ParserRuleContext.call(this, parent, invokingState);
    this.parser = parser;
    this.ruleIndex = QLGrammarParser.RULE_computedquestion;
    this.ResultNode = null
    this.txt = null; // Token
    this.lbl = null; // Token
    this.exp = null; // ExprContext
    return this;
}

ComputedquestionContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ComputedquestionContext.prototype.constructor = ComputedquestionContext;

ComputedquestionContext.prototype.DELIMITER = function() {
    return this.getToken(QLGrammarParser.DELIMITER, 0);
};

ComputedquestionContext.prototype.STRING = function() {
    return this.getToken(QLGrammarParser.STRING, 0);
};

ComputedquestionContext.prototype.LABEL = function() {
    return this.getToken(QLGrammarParser.LABEL, 0);
};

ComputedquestionContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

ComputedquestionContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterComputedquestion(this);
	}
};

ComputedquestionContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitComputedquestion(this);
	}
};

ComputedquestionContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitComputedquestion(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLGrammarParser.ComputedquestionContext = ComputedquestionContext;

QLGrammarParser.prototype.computedquestion = function() {

    var localctx = new ComputedquestionContext(this, this._ctx, this.state);
    this.enterRule(localctx, 8, QLGrammarParser.RULE_computedquestion);
    try {
        this.state = 134;
        var la_ = this._interp.adaptivePredict(this._input,3,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 78;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 79;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 80;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 81;
            this.match(QLGrammarParser.T__3);
            this.state = 82;
            this.match(QLGrammarParser.T__10);
            this.state = 83;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new FloatType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 86;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 87;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 88;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 89;
            this.match(QLGrammarParser.T__4);
            this.state = 90;
            this.match(QLGrammarParser.T__10);
            this.state = 91;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new NumberType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
            break;

        case 3:
            this.enterOuterAlt(localctx, 3);
            this.state = 94;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 95;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 96;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 97;
            this.match(QLGrammarParser.T__5);
            this.state = 98;
            this.match(QLGrammarParser.T__10);
            this.state = 99;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new BooleanType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
            break;

        case 4:
            this.enterOuterAlt(localctx, 4);
            this.state = 102;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 103;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 104;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 105;
            this.match(QLGrammarParser.T__6);
            this.state = 106;
            this.match(QLGrammarParser.T__10);
            this.state = 107;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new StringType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
            break;

        case 5:
            this.enterOuterAlt(localctx, 5);
            this.state = 110;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 111;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 112;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 113;
            this.match(QLGrammarParser.T__7);
            this.state = 114;
            this.match(QLGrammarParser.T__10);
            this.state = 115;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new MoneyType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
            break;

        case 6:
            this.enterOuterAlt(localctx, 6);
            this.state = 118;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 119;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 120;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 121;
            this.match(QLGrammarParser.T__8);
            this.state = 122;
            this.match(QLGrammarParser.T__10);
            this.state = 123;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new CurrencyType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
            break;

        case 7:
            this.enterOuterAlt(localctx, 7);
            this.state = 126;
            localctx.txt = this.match(QLGrammarParser.STRING);
            this.state = 127;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            this.state = 128;
            this.match(QLGrammarParser.DELIMITER);
            this.state = 129;
            this.match(QLGrammarParser.T__9);
            this.state = 130;
            this.match(QLGrammarParser.T__10);
            this.state = 131;
            localctx.exp = this.expr(0);
            localctx.ResultNode = new ComputedQuestionNode((localctx.txt===null ? null : localctx.txt.text), (localctx.lbl===null ? null : localctx.lbl.text), new DateType(), (localctx.txt === null ? 0 : localctx.txt.line), localctx.exp.ExprNode)
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
    this.ruleIndex = QLGrammarParser.RULE_ifstmt;
    this.ResultNode = null
    this.exp = null; // ExprContext
    this.blk = null; // BlockContext
    this.elseque = null; // BlockContext
    return this;
}

IfstmtContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
IfstmtContext.prototype.constructor = IfstmtContext;

IfstmtContext.prototype.expr = function() {
    return this.getTypedRuleContext(ExprContext,0);
};

IfstmtContext.prototype.block = function(i) {
    if(i===undefined) {
        i = null;
    }
    if(i===null) {
        return this.getTypedRuleContexts(BlockContext);
    } else {
        return this.getTypedRuleContext(BlockContext,i);
    }
};

IfstmtContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterIfstmt(this);
	}
};

IfstmtContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitIfstmt(this);
	}
};

IfstmtContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitIfstmt(this);
    } else {
        return visitor.visitChildren(this);
    }
};




QLGrammarParser.IfstmtContext = IfstmtContext;

QLGrammarParser.prototype.ifstmt = function() {

    var localctx = new IfstmtContext(this, this._ctx, this.state);
    this.enterRule(localctx, 10, QLGrammarParser.RULE_ifstmt);
    try {
        this.state = 148;
        var la_ = this._interp.adaptivePredict(this._input,4,this._ctx);
        switch(la_) {
        case 1:
            this.enterOuterAlt(localctx, 1);
            this.state = 136;
            this.match(QLGrammarParser.T__11);
            this.state = 137;
            localctx.exp = this.expr(0);
            this.state = 138;
            localctx.blk = this.block();
            localctx.ResultNode = new ConditionNode(localctx.exp.ExprNode, localctx.blk.BlockArray, (localctx.exp===null ? null : localctx.exp.start).line)
            break;

        case 2:
            this.enterOuterAlt(localctx, 2);
            this.state = 141;
            this.match(QLGrammarParser.T__11);
            this.state = 142;
            localctx.exp = this.expr(0);
            this.state = 143;
            localctx.blk = this.block();
            this.state = 144;
            this.match(QLGrammarParser.T__12);
            this.state = 145;
            localctx.elseque = this.block();
            localctx.ResultNode = new ConditionNode(localctx.exp.ExprNode, localctx.blk.BlockArray, (localctx.exp===null ? null : localctx.exp.start).line, localctx.elseque.BlockArray)
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
    this.ruleIndex = QLGrammarParser.RULE_expr;
    this.ExprNode = null
    this.left = null; // ExprContext
    this.exp = null; // ExprContext
    this.bool = null; // Token
    this.num = null; // Token
    this.dec = null; // Token
    this.lbl = null; // Token
    this.op = null; // Token
    this.right = null; // ExprContext
    return this;
}

ExprContext.prototype = Object.create(antlr4.ParserRuleContext.prototype);
ExprContext.prototype.constructor = ExprContext;

ExprContext.prototype.NOTOP = function() {
    return this.getToken(QLGrammarParser.NOTOP, 0);
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

ExprContext.prototype.BOOLEAN = function() {
    return this.getToken(QLGrammarParser.BOOLEAN, 0);
};

ExprContext.prototype.NUMBER = function() {
    return this.getToken(QLGrammarParser.NUMBER, 0);
};

ExprContext.prototype.DECIMAL = function() {
    return this.getToken(QLGrammarParser.DECIMAL, 0);
};

ExprContext.prototype.LABEL = function() {
    return this.getToken(QLGrammarParser.LABEL, 0);
};

ExprContext.prototype.MULTDIVOP = function() {
    return this.getToken(QLGrammarParser.MULTDIVOP, 0);
};

ExprContext.prototype.ADDSUBOP = function() {
    return this.getToken(QLGrammarParser.ADDSUBOP, 0);
};

ExprContext.prototype.COMPOP = function() {
    return this.getToken(QLGrammarParser.COMPOP, 0);
};

ExprContext.prototype.BOOLOP = function() {
    return this.getToken(QLGrammarParser.BOOLOP, 0);
};

ExprContext.prototype.BOOLCOMOP = function() {
    return this.getToken(QLGrammarParser.BOOLCOMOP, 0);
};

ExprContext.prototype.enterRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.enterExpr(this);
	}
};

ExprContext.prototype.exitRule = function(listener) {
    if(listener instanceof QLGrammarListener ) {
        listener.exitExpr(this);
	}
};

ExprContext.prototype.accept = function(visitor) {
    if ( visitor instanceof QLGrammarVisitor ) {
        return visitor.visitExpr(this);
    } else {
        return visitor.visitChildren(this);
    }
};



QLGrammarParser.prototype.expr = function(_p) {
	if(_p===undefined) {
	    _p = 0;
	}
    var _parentctx = this._ctx;
    var _parentState = this.state;
    var localctx = new ExprContext(this, this._ctx, _parentState);
    var _prevctx = localctx;
    var _startState = 12;
    this.enterRecursionRule(localctx, 12, QLGrammarParser.RULE_expr, _p);
    try {
        this.enterOuterAlt(localctx, 1);
        this.state = 168;
        switch(this._input.LA(1)) {
        case QLGrammarParser.NOTOP:
            this.state = 151;
            this.match(QLGrammarParser.NOTOP);
            this.state = 152;
            localctx.exp = this.expr(1);
            localctx.ExprNode = new NotExpression(localctx.exp.ExprNode, (localctx.exp===null ? null : localctx.exp.start).line)
            break;
        case QLGrammarParser.BOOLEAN:
            this.state = 155;
            localctx.bool = this.match(QLGrammarParser.BOOLEAN);
            localctx.ExprNode = new LiteralNode('true' === (localctx.bool===null ? null : localctx.bool.text), (localctx.bool === null ? 0 : localctx.bool.line))
            break;
        case QLGrammarParser.NUMBER:
            this.state = 157;
            localctx.num = this.match(QLGrammarParser.NUMBER);
            localctx.ExprNode = new LiteralNode(parseInt((localctx.num===null ? null : localctx.num.text)), (localctx.num === null ? 0 : localctx.num.line))
            break;
        case QLGrammarParser.DECIMAL:
            this.state = 159;
            localctx.dec = this.match(QLGrammarParser.DECIMAL);
            localctx.ExprNode = new LiteralNode(parseFloat((localctx.dec===null ? null : localctx.dec.text)), (localctx.dec === null ? 0 : localctx.dec.line))
            break;
        case QLGrammarParser.LABEL:
            this.state = 161;
            localctx.lbl = this.match(QLGrammarParser.LABEL);
            localctx.ExprNode = new LabelNode((localctx.lbl===null ? null : localctx.lbl.text), (localctx.lbl === null ? 0 : localctx.lbl.line))
            break;
        case QLGrammarParser.T__13:
            this.state = 163;
            this.match(QLGrammarParser.T__13);
            this.state = 164;
            localctx.exp = this.expr(0);
            this.state = 165;
            this.match(QLGrammarParser.T__14);
            localctx.ExprNode = localctx.exp.ExprNode
            break;
        default:
            throw new antlr4.error.NoViableAltException(this);
        }
        this._ctx.stop = this._input.LT(-1);
        this.state = 197;
        this._errHandler.sync(this);
        var _alt = this._interp.adaptivePredict(this._input,7,this._ctx)
        while(_alt!=2 && _alt!=antlr4.atn.ATN.INVALID_ALT_NUMBER) {
            if(_alt===1) {
                if(this._parseListeners!==null) {
                    this.triggerExitRuleEvent();
                }
                _prevctx = localctx;
                this.state = 195;
                var la_ = this._interp.adaptivePredict(this._input,6,this._ctx);
                switch(la_) {
                case 1:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, QLGrammarParser.RULE_expr);
                    this.state = 170;
                    if (!( this.precpred(this._ctx, 6))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 6)");
                    }
                    this.state = 171;
                    localctx.op = this.match(QLGrammarParser.MULTDIVOP);
                    this.state = 172;
                    localctx.right = this.expr(7);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, new NumOperatorNode((localctx.op===null ? null : localctx.op.text), (localctx.left===null ? null : localctx.left.start).line), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 2:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, QLGrammarParser.RULE_expr);
                    this.state = 175;
                    if (!( this.precpred(this._ctx, 5))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 5)");
                    }
                    this.state = 176;
                    localctx.op = this.match(QLGrammarParser.ADDSUBOP);
                    this.state = 177;
                    localctx.right = this.expr(6);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, new NumOperatorNode((localctx.op===null ? null : localctx.op.text), (localctx.left===null ? null : localctx.left.start).line), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 3:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, QLGrammarParser.RULE_expr);
                    this.state = 180;
                    if (!( this.precpred(this._ctx, 4))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 4)");
                    }
                    this.state = 181;
                    localctx.op = this.match(QLGrammarParser.COMPOP);
                    this.state = 182;
                    localctx.right = this.expr(5);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, new NumOperatorNode((localctx.op===null ? null : localctx.op.text), (localctx.left===null ? null : localctx.left.start).line), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 4:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, QLGrammarParser.RULE_expr);
                    this.state = 185;
                    if (!( this.precpred(this._ctx, 3))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 3)");
                    }
                    this.state = 186;
                    localctx.op = this.match(QLGrammarParser.BOOLOP);
                    this.state = 187;
                    localctx.right = this.expr(4);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, new BoolOperatorNode((localctx.op===null ? null : localctx.op.text), (localctx.left===null ? null : localctx.left.start).line), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                case 5:
                    localctx = new ExprContext(this, _parentctx, _parentState);
                    localctx.left = _prevctx;
                    this.pushNewRecursionContext(localctx, _startState, QLGrammarParser.RULE_expr);
                    this.state = 190;
                    if (!( this.precpred(this._ctx, 2))) {
                        throw new antlr4.error.FailedPredicateException(this, "this.precpred(this._ctx, 2)");
                    }
                    this.state = 191;
                    localctx.op = this.match(QLGrammarParser.BOOLCOMOP);
                    this.state = 192;
                    localctx.right = this.expr(3);
                    localctx.ExprNode = new OperatorExpressionNode(localctx.left.ExprNode, new NumOrBoolOperatorNode((localctx.op===null ? null : localctx.op.text), (localctx.left===null ? null : localctx.left.start).line), localctx.right.ExprNode, (localctx.left===null ? null : localctx.left.start).line)
                    break;

                } 
            }
            this.state = 199;
            this._errHandler.sync(this);
            _alt = this._interp.adaptivePredict(this._input,7,this._ctx);
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


QLGrammarParser.prototype.sempred = function(localctx, ruleIndex, predIndex) {
	switch(ruleIndex) {
	case 6:
			return this.expr_sempred(localctx, predIndex);
    default:
        throw "No predicate with index:" + ruleIndex;
   }
};

QLGrammarParser.prototype.expr_sempred = function(localctx, predIndex) {
	switch(predIndex) {
		case 0:
			return this.precpred(this._ctx, 6);
		case 1:
			return this.precpred(this._ctx, 5);
		case 2:
			return this.precpred(this._ctx, 4);
		case 3:
			return this.precpred(this._ctx, 3);
		case 4:
			return this.precpred(this._ctx, 2);
		default:
			throw "No predicate with index:" + predIndex;
	}
};


exports.QLGrammarParser = QLGrammarParser;

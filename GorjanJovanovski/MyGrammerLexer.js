// Generated from MyGrammer.g4 by ANTLR 4.5.2
// jshint ignore: start
var antlr4 = require('antlr4/index');


var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0002\u001e\u00de\b\u0001\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004",
    "\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t",
    "\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004",
    "\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010",
    "\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013",
    "\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017",
    "\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a",
    "\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0003\u0002",
    "\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003",
    "\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0005\u0003\u0005\u0003\u0006\u0003\u0006\u0003\u0007\u0003\u0007",
    "\u0003\b\u0003\b\u0003\t\u0003\t\u0003\n\u0003\n\u0003\u000b\u0003\u000b",
    "\u0003\f\u0003\f\u0003\f\u0003\r\u0003\r\u0003\r\u0003\u000e\u0003\u000e",
    "\u0003\u000f\u0003\u000f\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0011",
    "\u0003\u0011\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0013\u0003\u0013",
    "\u0003\u0013\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0015\u0003\u0015",
    "\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015",
    "\u0003\u0015\u0005\u0015x\n\u0015\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0005\u0016",
    "\u00a6\n\u0016\u0003\u0017\u0003\u0017\u0003\u0018\u0003\u0018\u0007",
    "\u0018\u00ac\n\u0018\f\u0018\u000e\u0018\u00af\u000b\u0018\u0003\u0019",
    "\u0006\u0019\u00b2\n\u0019\r\u0019\u000e\u0019\u00b3\u0003\u0019\u0003",
    "\u0019\u0003\u001a\u0006\u001a\u00b9\n\u001a\r\u001a\u000e\u001a\u00ba",
    "\u0003\u001a\u0005\u001a\u00be\n\u001a\u0003\u001a\u0005\u001a\u00c1",
    "\n\u001a\u0003\u001a\u0007\u001a\u00c4\n\u001a\f\u001a\u000e\u001a\u00c7",
    "\u000b\u001a\u0003\u001b\u0006\u001b\u00ca\n\u001b\r\u001b\u000e\u001b",
    "\u00cb\u0003\u001b\u0003\u001b\u0003\u001c\u0003\u001c\u0003\u001c\u0003",
    "\u001c\u0003\u001d\u0003\u001d\u0003\u001d\u0003\u001d\u0007\u001d\u00d8",
    "\n\u001d\f\u001d\u000e\u001d\u00db\u000b\u001d\u0003\u001d\u0003\u001d",
    "\u0002\u0002\u001e\u0003\u0003\u0005\u0004\u0007\u0005\t\u0006\u000b",
    "\u0007\r\b\u000f\t\u0011\n\u0013\u000b\u0015\f\u0017\r\u0019\u000e\u001b",
    "\u000f\u001d\u0010\u001f\u0011!\u0012#\u0013%\u0014\'\u0015)\u0016+",
    "\u0017-\u0018/\u00191\u001a3\u001b5\u001c7\u001d9\u001e\u0003\u0002",
    "\n\u0004\u0002C\\c|\u0005\u00022;C\\c|\u0004\u0002\f\f\u000f\u000f\u0003",
    "\u00022;\u0004\u0002\u000b\u000b\"\"\u0004\u0002}}\u007f\u007f\u0006",
    "\u0002\f\f\u000f\u000f$$^^\u0004\u0002$$^^\u00ed\u0002\u0003\u0003\u0002",
    "\u0002\u0002\u0002\u0005\u0003\u0002\u0002\u0002\u0002\u0007\u0003\u0002",
    "\u0002\u0002\u0002\t\u0003\u0002\u0002\u0002\u0002\u000b\u0003\u0002",
    "\u0002\u0002\u0002\r\u0003\u0002\u0002\u0002\u0002\u000f\u0003\u0002",
    "\u0002\u0002\u0002\u0011\u0003\u0002\u0002\u0002\u0002\u0013\u0003\u0002",
    "\u0002\u0002\u0002\u0015\u0003\u0002\u0002\u0002\u0002\u0017\u0003\u0002",
    "\u0002\u0002\u0002\u0019\u0003\u0002\u0002\u0002\u0002\u001b\u0003\u0002",
    "\u0002\u0002\u0002\u001d\u0003\u0002\u0002\u0002\u0002\u001f\u0003\u0002",
    "\u0002\u0002\u0002!\u0003\u0002\u0002\u0002\u0002#\u0003\u0002\u0002",
    "\u0002\u0002%\u0003\u0002\u0002\u0002\u0002\'\u0003\u0002\u0002\u0002",
    "\u0002)\u0003\u0002\u0002\u0002\u0002+\u0003\u0002\u0002\u0002\u0002",
    "-\u0003\u0002\u0002\u0002\u0002/\u0003\u0002\u0002\u0002\u00021\u0003",
    "\u0002\u0002\u0002\u00023\u0003\u0002\u0002\u0002\u00025\u0003\u0002",
    "\u0002\u0002\u00027\u0003\u0002\u0002\u0002\u00029\u0003\u0002\u0002",
    "\u0002\u0003;\u0003\u0002\u0002\u0002\u0005@\u0003\u0002\u0002\u0002",
    "\u0007B\u0003\u0002\u0002\u0002\tE\u0003\u0002\u0002\u0002\u000bJ\u0003",
    "\u0002\u0002\u0002\rL\u0003\u0002\u0002\u0002\u000fN\u0003\u0002\u0002",
    "\u0002\u0011P\u0003\u0002\u0002\u0002\u0013R\u0003\u0002\u0002\u0002",
    "\u0015T\u0003\u0002\u0002\u0002\u0017V\u0003\u0002\u0002\u0002\u0019",
    "Y\u0003\u0002\u0002\u0002\u001b\\\u0003\u0002\u0002\u0002\u001d^\u0003",
    "\u0002\u0002\u0002\u001f`\u0003\u0002\u0002\u0002!c\u0003\u0002\u0002",
    "\u0002#e\u0003\u0002\u0002\u0002%h\u0003\u0002\u0002\u0002\'k\u0003",
    "\u0002\u0002\u0002)w\u0003\u0002\u0002\u0002+\u00a5\u0003\u0002\u0002",
    "\u0002-\u00a7\u0003\u0002\u0002\u0002/\u00a9\u0003\u0002\u0002\u0002",
    "1\u00b1\u0003\u0002\u0002\u00023\u00b8\u0003\u0002\u0002\u00025\u00c9",
    "\u0003\u0002\u0002\u00027\u00cf\u0003\u0002\u0002\u00029\u00d3\u0003",
    "\u0002\u0002\u0002;<\u0007h\u0002\u0002<=\u0007q\u0002\u0002=>\u0007",
    "t\u0002\u0002>?\u0007o\u0002\u0002?\u0004\u0003\u0002\u0002\u0002@A",
    "\u0007?\u0002\u0002A\u0006\u0003\u0002\u0002\u0002BC\u0007k\u0002\u0002",
    "CD\u0007h\u0002\u0002D\b\u0003\u0002\u0002\u0002EF\u0007g\u0002\u0002",
    "FG\u0007n\u0002\u0002GH\u0007u\u0002\u0002HI\u0007g\u0002\u0002I\n\u0003",
    "\u0002\u0002\u0002JK\u0007*\u0002\u0002K\f\u0003\u0002\u0002\u0002L",
    "M\u0007+\u0002\u0002M\u000e\u0003\u0002\u0002\u0002NO\u0007-\u0002\u0002",
    "O\u0010\u0003\u0002\u0002\u0002PQ\u0007/\u0002\u0002Q\u0012\u0003\u0002",
    "\u0002\u0002RS\u0007,\u0002\u0002S\u0014\u0003\u0002\u0002\u0002TU\u0007",
    "1\u0002\u0002U\u0016\u0003\u0002\u0002\u0002VW\u0007(\u0002\u0002WX",
    "\u0007(\u0002\u0002X\u0018\u0003\u0002\u0002\u0002YZ\u0007~\u0002\u0002",
    "Z[\u0007~\u0002\u0002[\u001a\u0003\u0002\u0002\u0002\\]\u0007#\u0002",
    "\u0002]\u001c\u0003\u0002\u0002\u0002^_\u0007>\u0002\u0002_\u001e\u0003",
    "\u0002\u0002\u0002`a\u0007>\u0002\u0002ab\u0007?\u0002\u0002b \u0003",
    "\u0002\u0002\u0002cd\u0007@\u0002\u0002d\"\u0003\u0002\u0002\u0002e",
    "f\u0007@\u0002\u0002fg\u0007?\u0002\u0002g$\u0003\u0002\u0002\u0002",
    "hi\u0007#\u0002\u0002ij\u0007?\u0002\u0002j&\u0003\u0002\u0002\u0002",
    "kl\u0007?\u0002\u0002lm\u0007?\u0002\u0002m(\u0003\u0002\u0002\u0002",
    "no\u0007v\u0002\u0002op\u0007t\u0002\u0002pq\u0007w\u0002\u0002qx\u0007",
    "g\u0002\u0002rs\u0007h\u0002\u0002st\u0007c\u0002\u0002tu\u0007n\u0002",
    "\u0002uv\u0007u\u0002\u0002vx\u0007g\u0002\u0002wn\u0003\u0002\u0002",
    "\u0002wr\u0003\u0002\u0002\u0002x*\u0003\u0002\u0002\u0002yz\u0007f",
    "\u0002\u0002z{\u0007g\u0002\u0002{|\u0007e\u0002\u0002|}\u0007k\u0002",
    "\u0002}~\u0007o\u0002\u0002~\u007f\u0007c\u0002\u0002\u007f\u00a6\u0007",
    "n\u0002\u0002\u0080\u0081\u0007k\u0002\u0002\u0081\u0082\u0007p\u0002",
    "\u0002\u0082\u0083\u0007v\u0002\u0002\u0083\u0084\u0007g\u0002\u0002",
    "\u0084\u0085\u0007i\u0002\u0002\u0085\u0086\u0007g\u0002\u0002\u0086",
    "\u00a6\u0007t\u0002\u0002\u0087\u0088\u0007d\u0002\u0002\u0088\u0089",
    "\u0007q\u0002\u0002\u0089\u008a\u0007q\u0002\u0002\u008a\u008b\u0007",
    "n\u0002\u0002\u008b\u008c\u0007g\u0002\u0002\u008c\u008d\u0007c\u0002",
    "\u0002\u008d\u00a6\u0007p\u0002\u0002\u008e\u008f\u0007u\u0002\u0002",
    "\u008f\u0090\u0007v\u0002\u0002\u0090\u0091\u0007t\u0002\u0002\u0091",
    "\u0092\u0007k\u0002\u0002\u0092\u0093\u0007p\u0002\u0002\u0093\u00a6",
    "\u0007i\u0002\u0002\u0094\u0095\u0007o\u0002\u0002\u0095\u0096\u0007",
    "q\u0002\u0002\u0096\u0097\u0007p\u0002\u0002\u0097\u0098\u0007g\u0002",
    "\u0002\u0098\u00a6\u0007{\u0002\u0002\u0099\u009a\u0007e\u0002\u0002",
    "\u009a\u009b\u0007w\u0002\u0002\u009b\u009c\u0007t\u0002\u0002\u009c",
    "\u009d\u0007t\u0002\u0002\u009d\u009e\u0007g\u0002\u0002\u009e\u009f",
    "\u0007p\u0002\u0002\u009f\u00a0\u0007e\u0002\u0002\u00a0\u00a6\u0007",
    "{\u0002\u0002\u00a1\u00a2\u0007f\u0002\u0002\u00a2\u00a3\u0007c\u0002",
    "\u0002\u00a3\u00a4\u0007v\u0002\u0002\u00a4\u00a6\u0007g\u0002\u0002",
    "\u00a5y\u0003\u0002\u0002\u0002\u00a5\u0080\u0003\u0002\u0002\u0002",
    "\u00a5\u0087\u0003\u0002\u0002\u0002\u00a5\u008e\u0003\u0002\u0002\u0002",
    "\u00a5\u0094\u0003\u0002\u0002\u0002\u00a5\u0099\u0003\u0002\u0002\u0002",
    "\u00a5\u00a1\u0003\u0002\u0002\u0002\u00a6,\u0003\u0002\u0002\u0002",
    "\u00a7\u00a8\u0007<\u0002\u0002\u00a8.\u0003\u0002\u0002\u0002\u00a9",
    "\u00ad\t\u0002\u0002\u0002\u00aa\u00ac\t\u0003\u0002\u0002\u00ab\u00aa",
    "\u0003\u0002\u0002\u0002\u00ac\u00af\u0003\u0002\u0002\u0002\u00ad\u00ab",
    "\u0003\u0002\u0002\u0002\u00ad\u00ae\u0003\u0002\u0002\u0002\u00ae0",
    "\u0003\u0002\u0002\u0002\u00af\u00ad\u0003\u0002\u0002\u0002\u00b0\u00b2",
    "\t\u0004\u0002\u0002\u00b1\u00b0\u0003\u0002\u0002\u0002\u00b2\u00b3",
    "\u0003\u0002\u0002\u0002\u00b3\u00b1\u0003\u0002\u0002\u0002\u00b3\u00b4",
    "\u0003\u0002\u0002\u0002\u00b4\u00b5\u0003\u0002\u0002\u0002\u00b5\u00b6",
    "\b\u0019\u0002\u0002\u00b62\u0003\u0002\u0002\u0002\u00b7\u00b9\t\u0005",
    "\u0002\u0002\u00b8\u00b7\u0003\u0002\u0002\u0002\u00b9\u00ba\u0003\u0002",
    "\u0002\u0002\u00ba\u00b8\u0003\u0002\u0002\u0002\u00ba\u00bb\u0003\u0002",
    "\u0002\u0002\u00bb\u00bd\u0003\u0002\u0002\u0002\u00bc\u00be\u00070",
    "\u0002\u0002\u00bd\u00bc\u0003\u0002\u0002\u0002\u00bd\u00be\u0003\u0002",
    "\u0002\u0002\u00be\u00c0\u0003\u0002\u0002\u0002\u00bf\u00c1\u0007.",
    "\u0002\u0002\u00c0\u00bf\u0003\u0002\u0002\u0002\u00c0\u00c1\u0003\u0002",
    "\u0002\u0002\u00c1\u00c5\u0003\u0002\u0002\u0002\u00c2\u00c4\t\u0005",
    "\u0002\u0002\u00c3\u00c2\u0003\u0002\u0002\u0002\u00c4\u00c7\u0003\u0002",
    "\u0002\u0002\u00c5\u00c3\u0003\u0002\u0002\u0002\u00c5\u00c6\u0003\u0002",
    "\u0002\u0002\u00c64\u0003\u0002\u0002\u0002\u00c7\u00c5\u0003\u0002",
    "\u0002\u0002\u00c8\u00ca\t\u0006\u0002\u0002\u00c9\u00c8\u0003\u0002",
    "\u0002\u0002\u00ca\u00cb\u0003\u0002\u0002\u0002\u00cb\u00c9\u0003\u0002",
    "\u0002\u0002\u00cb\u00cc\u0003\u0002\u0002\u0002\u00cc\u00cd\u0003\u0002",
    "\u0002\u0002\u00cd\u00ce\b\u001b\u0002\u0002\u00ce6\u0003\u0002\u0002",
    "\u0002\u00cf\u00d0\t\u0007\u0002\u0002\u00d0\u00d1\u0003\u0002\u0002",
    "\u0002\u00d1\u00d2\b\u001c\u0002\u0002\u00d28\u0003\u0002\u0002\u0002",
    "\u00d3\u00d9\u0007$\u0002\u0002\u00d4\u00d8\n\b\u0002\u0002\u00d5\u00d6",
    "\u0007^\u0002\u0002\u00d6\u00d8\t\t\u0002\u0002\u00d7\u00d4\u0003\u0002",
    "\u0002\u0002\u00d7\u00d5\u0003\u0002\u0002\u0002\u00d8\u00db\u0003\u0002",
    "\u0002\u0002\u00d9\u00d7\u0003\u0002\u0002\u0002\u00d9\u00da\u0003\u0002",
    "\u0002\u0002\u00da\u00dc\u0003\u0002\u0002\u0002\u00db\u00d9\u0003\u0002",
    "\u0002\u0002\u00dc\u00dd\u0007$\u0002\u0002\u00dd:\u0003\u0002\u0002",
    "\u0002\u000e\u0002w\u00a5\u00ad\u00b3\u00ba\u00bd\u00c0\u00c5\u00cb",
    "\u00d7\u00d9\u0003\b\u0002\u0002"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

function MyGrammerLexer(input) {
	antlr4.Lexer.call(this, input);
    this._interp = new antlr4.atn.LexerATNSimulator(this, atn, decisionsToDFA, new antlr4.PredictionContextCache());
    return this;
}

MyGrammerLexer.prototype = Object.create(antlr4.Lexer.prototype);
MyGrammerLexer.prototype.constructor = MyGrammerLexer;

MyGrammerLexer.EOF = antlr4.Token.EOF;
MyGrammerLexer.T__0 = 1;
MyGrammerLexer.T__1 = 2;
MyGrammerLexer.T__2 = 3;
MyGrammerLexer.T__3 = 4;
MyGrammerLexer.T__4 = 5;
MyGrammerLexer.T__5 = 6;
MyGrammerLexer.T__6 = 7;
MyGrammerLexer.T__7 = 8;
MyGrammerLexer.T__8 = 9;
MyGrammerLexer.T__9 = 10;
MyGrammerLexer.T__10 = 11;
MyGrammerLexer.T__11 = 12;
MyGrammerLexer.T__12 = 13;
MyGrammerLexer.T__13 = 14;
MyGrammerLexer.T__14 = 15;
MyGrammerLexer.T__15 = 16;
MyGrammerLexer.T__16 = 17;
MyGrammerLexer.T__17 = 18;
MyGrammerLexer.T__18 = 19;
MyGrammerLexer.BOOLSTMT = 20;
MyGrammerLexer.TYPE = 21;
MyGrammerLexer.DELIMITER = 22;
MyGrammerLexer.LABEL = 23;
MyGrammerLexer.NEWLINE = 24;
MyGrammerLexer.NUMBER = 25;
MyGrammerLexer.WHITESPACE = 26;
MyGrammerLexer.BRACKETS = 27;
MyGrammerLexer.STRING = 28;


MyGrammerLexer.modeNames = [ "DEFAULT_MODE" ];

MyGrammerLexer.literalNames = [ null, "'form'", "'='", "'if'", "'else'", 
                                "'('", "')'", "'+'", "'-'", "'*'", "'/'", 
                                "'&&'", "'||'", "'!'", "'<'", "'<='", "'>'", 
                                "'>='", "'!='", "'=='", null, null, "':'" ];

MyGrammerLexer.symbolicNames = [ null, null, null, null, null, null, null, 
                                 null, null, null, null, null, null, null, 
                                 null, null, null, null, null, null, "BOOLSTMT", 
                                 "TYPE", "DELIMITER", "LABEL", "NEWLINE", 
                                 "NUMBER", "WHITESPACE", "BRACKETS", "STRING" ];

MyGrammerLexer.ruleNames = [ "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", 
                             "T__6", "T__7", "T__8", "T__9", "T__10", "T__11", 
                             "T__12", "T__13", "T__14", "T__15", "T__16", 
                             "T__17", "T__18", "BOOLSTMT", "TYPE", "DELIMITER", 
                             "LABEL", "NEWLINE", "NUMBER", "WHITESPACE", 
                             "BRACKETS", "STRING" ];

MyGrammerLexer.grammarFileName = "MyGrammer.g4";



exports.MyGrammerLexer = MyGrammerLexer;


// Generated from C:/Users/mwijngaard/Documents/Projects/multi-ql/Merijn/src/qls\QLS.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');


var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0002\u001f\u00e7\b\u0001\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004",
    "\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t",
    "\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004",
    "\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010",
    "\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013",
    "\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017",
    "\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a",
    "\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0004\u001e",
    "\t\u001e\u0004\u001f\t\u001f\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003",
    "\u0002\u0003\u0002\u0003\u0003\u0003\u0003\u0003\u0004\u0003\u0004\u0003",
    "\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0006\u0003",
    "\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006\u0003",
    "\u0006\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003",
    "\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\b\u0003\b\u0003\b\u0003",
    "\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0003\t\u0003\t\u0003",
    "\t\u0003\t\u0003\t\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\f\u0003",
    "\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\f\u0003\r\u0003\r\u0003\r\u0003",
    "\r\u0003\r\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e\u0003\u000e",
    "\u0003\u000e\u0003\u000f\u0003\u000f\u0003\u0010\u0003\u0010\u0003\u0011",
    "\u0003\u0011\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0012",
    "\u0003\u0012\u0003\u0012\u0003\u0012\u0003\u0013\u0003\u0013\u0003\u0013",
    "\u0003\u0013\u0003\u0013\u0003\u0013\u0003\u0013\u0003\u0014\u0003\u0014",
    "\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014\u0003\u0014",
    "\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015",
    "\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016\u0003\u0016",
    "\u0003\u0017\u0003\u0017\u0007\u0017\u00b5\n\u0017\f\u0017\u000e\u0017",
    "\u00b8\u000b\u0017\u0003\u0017\u0003\u0017\u0003\u0018\u0006\u0018\u00bd",
    "\n\u0018\r\u0018\u000e\u0018\u00be\u0003\u0019\u0003\u0019\u0003\u0019",
    "\u0003\u0019\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0003\u001b",
    "\u0003\u001b\u0003\u001b\u0003\u001b\u0005\u001b\u00cd\n\u001b\u0003",
    "\u001c\u0003\u001c\u0003\u001c\u0003\u001c\u0003\u001c\u0003\u001c\u0003",
    "\u001c\u0003\u001c\u0003\u001c\u0005\u001c\u00d8\n\u001c\u0003\u001d",
    "\u0006\u001d\u00db\n\u001d\r\u001d\u000e\u001d\u00dc\u0003\u001e\u0006",
    "\u001e\u00e0\n\u001e\r\u001e\u000e\u001e\u00e1\u0003\u001e\u0003\u001e",
    "\u0003\u001f\u0003\u001f\u0002\u0002 \u0003\u0003\u0005\u0004\u0007",
    "\u0005\t\u0006\u000b\u0007\r\b\u000f\t\u0011\n\u0013\u000b\u0015\f\u0017",
    "\r\u0019\u000e\u001b\u000f\u001d\u0010\u001f\u0011!\u0012#\u0013%\u0014",
    "\'\u0015)\u0016+\u0017-\u0018/\u00191\u001a3\u001b5\u001c7\u001d9\u001e",
    ";\u001f=\u0002\u0003\u0002\u0006\u0003\u0002$$\u0005\u0002C\\aac|\u0005",
    "\u0002\u000b\f\u000f\u000f\"\"\u0003\u00022;\u00eb\u0002\u0003\u0003",
    "\u0002\u0002\u0002\u0002\u0005\u0003\u0002\u0002\u0002\u0002\u0007\u0003",
    "\u0002\u0002\u0002\u0002\t\u0003\u0002\u0002\u0002\u0002\u000b\u0003",
    "\u0002\u0002\u0002\u0002\r\u0003\u0002\u0002\u0002\u0002\u000f\u0003",
    "\u0002\u0002\u0002\u0002\u0011\u0003\u0002\u0002\u0002\u0002\u0013\u0003",
    "\u0002\u0002\u0002\u0002\u0015\u0003\u0002\u0002\u0002\u0002\u0017\u0003",
    "\u0002\u0002\u0002\u0002\u0019\u0003\u0002\u0002\u0002\u0002\u001b\u0003",
    "\u0002\u0002\u0002\u0002\u001d\u0003\u0002\u0002\u0002\u0002\u001f\u0003",
    "\u0002\u0002\u0002\u0002!\u0003\u0002\u0002\u0002\u0002#\u0003\u0002",
    "\u0002\u0002\u0002%\u0003\u0002\u0002\u0002\u0002\'\u0003\u0002\u0002",
    "\u0002\u0002)\u0003\u0002\u0002\u0002\u0002+\u0003\u0002\u0002\u0002",
    "\u0002-\u0003\u0002\u0002\u0002\u0002/\u0003\u0002\u0002\u0002\u0002",
    "1\u0003\u0002\u0002\u0002\u00023\u0003\u0002\u0002\u0002\u00025\u0003",
    "\u0002\u0002\u0002\u00027\u0003\u0002\u0002\u0002\u00029\u0003\u0002",
    "\u0002\u0002\u0002;\u0003\u0002\u0002\u0002\u0003?\u0003\u0002\u0002",
    "\u0002\u0005J\u0003\u0002\u0002\u0002\u0007L\u0003\u0002\u0002\u0002",
    "\tN\u0003\u0002\u0002\u0002\u000bS\u0003\u0002\u0002\u0002\r[\u0003",
    "\u0002\u0002\u0002\u000fd\u0003\u0002\u0002\u0002\u0011l\u0003\u0002",
    "\u0002\u0002\u0013s\u0003\u0002\u0002\u0002\u0015u\u0003\u0002\u0002",
    "\u0002\u0017w\u0003\u0002\u0002\u0002\u0019~\u0003\u0002\u0002\u0002",
    "\u001b\u0083\u0003\u0002\u0002\u0002\u001d\u0089\u0003\u0002\u0002\u0002",
    "\u001f\u008b\u0003\u0002\u0002\u0002!\u008d\u0003\u0002\u0002\u0002",
    "#\u008f\u0003\u0002\u0002\u0002%\u0097\u0003\u0002\u0002\u0002\'\u009e",
    "\u0003\u0002\u0002\u0002)\u00a6\u0003\u0002\u0002\u0002+\u00ac\u0003",
    "\u0002\u0002\u0002-\u00b2\u0003\u0002\u0002\u0002/\u00bc\u0003\u0002",
    "\u0002\u00021\u00c0\u0003\u0002\u0002\u00023\u00c4\u0003\u0002\u0002",
    "\u00025\u00cc\u0003\u0002\u0002\u00027\u00d7\u0003\u0002\u0002\u0002",
    "9\u00da\u0003\u0002\u0002\u0002;\u00df\u0003\u0002\u0002\u0002=\u00e5",
    "\u0003\u0002\u0002\u0002?@\u0007u\u0002\u0002@A\u0007v\u0002\u0002A",
    "B\u0007{\u0002\u0002BC\u0007n\u0002\u0002CD\u0007g\u0002\u0002DE\u0007",
    "u\u0002\u0002EF\u0007j\u0002\u0002FG\u0007g\u0002\u0002GH\u0007g\u0002",
    "\u0002HI\u0007v\u0002\u0002I\u0004\u0003\u0002\u0002\u0002JK\u0007}",
    "\u0002\u0002K\u0006\u0003\u0002\u0002\u0002LM\u0007\u007f\u0002\u0002",
    "M\b\u0003\u0002\u0002\u0002NO\u0007r\u0002\u0002OP\u0007c\u0002\u0002",
    "PQ\u0007i\u0002\u0002QR\u0007g\u0002\u0002R\n\u0003\u0002\u0002\u0002",
    "ST\u0007u\u0002\u0002TU\u0007g\u0002\u0002UV\u0007e\u0002\u0002VW\u0007",
    "v\u0002\u0002WX\u0007k\u0002\u0002XY\u0007q\u0002\u0002YZ\u0007p\u0002",
    "\u0002Z\f\u0003\u0002\u0002\u0002[\\\u0007s\u0002\u0002\\]\u0007w\u0002",
    "\u0002]^\u0007g\u0002\u0002^_\u0007u\u0002\u0002_`\u0007v\u0002\u0002",
    "`a\u0007k\u0002\u0002ab\u0007q\u0002\u0002bc\u0007p\u0002\u0002c\u000e",
    "\u0003\u0002\u0002\u0002de\u0007f\u0002\u0002ef\u0007g\u0002\u0002f",
    "g\u0007h\u0002\u0002gh\u0007c\u0002\u0002hi\u0007w\u0002\u0002ij\u0007",
    "n\u0002\u0002jk\u0007v\u0002\u0002k\u0010\u0003\u0002\u0002\u0002lm",
    "\u0007y\u0002\u0002mn\u0007k\u0002\u0002no\u0007f\u0002\u0002op\u0007",
    "i\u0002\u0002pq\u0007g\u0002\u0002qr\u0007v\u0002\u0002r\u0012\u0003",
    "\u0002\u0002\u0002st\u0007=\u0002\u0002t\u0014\u0003\u0002\u0002\u0002",
    "uv\u0007<\u0002\u0002v\u0016\u0003\u0002\u0002\u0002wx\u0007u\u0002",
    "\u0002xy\u0007n\u0002\u0002yz\u0007k\u0002\u0002z{\u0007f\u0002\u0002",
    "{|\u0007g\u0002\u0002|}\u0007t\u0002\u0002}\u0018\u0003\u0002\u0002",
    "\u0002~\u007f\u0007v\u0002\u0002\u007f\u0080\u0007g\u0002\u0002\u0080",
    "\u0081\u0007z\u0002\u0002\u0081\u0082\u0007v\u0002\u0002\u0082\u001a",
    "\u0003\u0002\u0002\u0002\u0083\u0084\u0007t\u0002\u0002\u0084\u0085",
    "\u0007c\u0002\u0002\u0085\u0086\u0007f\u0002\u0002\u0086\u0087\u0007",
    "k\u0002\u0002\u0087\u0088\u0007q\u0002\u0002\u0088\u001c\u0003\u0002",
    "\u0002\u0002\u0089\u008a\u0007*\u0002\u0002\u008a\u001e\u0003\u0002",
    "\u0002\u0002\u008b\u008c\u0007+\u0002\u0002\u008c \u0003\u0002\u0002",
    "\u0002\u008d\u008e\u0007.\u0002\u0002\u008e\"\u0003\u0002\u0002\u0002",
    "\u008f\u0090\u0007d\u0002\u0002\u0090\u0091\u0007q\u0002\u0002\u0091",
    "\u0092\u0007q\u0002\u0002\u0092\u0093\u0007n\u0002\u0002\u0093\u0094",
    "\u0007g\u0002\u0002\u0094\u0095\u0007c\u0002\u0002\u0095\u0096\u0007",
    "p\u0002\u0002\u0096$\u0003\u0002\u0002\u0002\u0097\u0098\u0007u\u0002",
    "\u0002\u0098\u0099\u0007v\u0002\u0002\u0099\u009a\u0007t\u0002\u0002",
    "\u009a\u009b\u0007k\u0002\u0002\u009b\u009c\u0007p\u0002\u0002\u009c",
    "\u009d\u0007i\u0002\u0002\u009d&\u0003\u0002\u0002\u0002\u009e\u009f",
    "\u0007k\u0002\u0002\u009f\u00a0\u0007p\u0002\u0002\u00a0\u00a1\u0007",
    "v\u0002\u0002\u00a1\u00a2\u0007g\u0002\u0002\u00a2\u00a3\u0007i\u0002",
    "\u0002\u00a3\u00a4\u0007g\u0002\u0002\u00a4\u00a5\u0007t\u0002\u0002",
    "\u00a5(\u0003\u0002\u0002\u0002\u00a6\u00a7\u0007h\u0002\u0002\u00a7",
    "\u00a8\u0007n\u0002\u0002\u00a8\u00a9\u0007q\u0002\u0002\u00a9\u00aa",
    "\u0007c\u0002\u0002\u00aa\u00ab\u0007v\u0002\u0002\u00ab*\u0003\u0002",
    "\u0002\u0002\u00ac\u00ad\u0007o\u0002\u0002\u00ad\u00ae\u0007q\u0002",
    "\u0002\u00ae\u00af\u0007p\u0002\u0002\u00af\u00b0\u0007g\u0002\u0002",
    "\u00b0\u00b1\u0007{\u0002\u0002\u00b1,\u0003\u0002\u0002\u0002\u00b2",
    "\u00b6\u0007$\u0002\u0002\u00b3\u00b5\n\u0002\u0002\u0002\u00b4\u00b3",
    "\u0003\u0002\u0002\u0002\u00b5\u00b8\u0003\u0002\u0002\u0002\u00b6\u00b4",
    "\u0003\u0002\u0002\u0002\u00b6\u00b7\u0003\u0002\u0002\u0002\u00b7\u00b9",
    "\u0003\u0002\u0002\u0002\u00b8\u00b6\u0003\u0002\u0002\u0002\u00b9\u00ba",
    "\u0007$\u0002\u0002\u00ba.\u0003\u0002\u0002\u0002\u00bb\u00bd\u0005",
    "=\u001f\u0002\u00bc\u00bb\u0003\u0002\u0002\u0002\u00bd\u00be\u0003",
    "\u0002\u0002\u0002\u00be\u00bc\u0003\u0002\u0002\u0002\u00be\u00bf\u0003",
    "\u0002\u0002\u0002\u00bf0\u0003\u0002\u0002\u0002\u00c0\u00c1\u0005",
    "/\u0018\u0002\u00c1\u00c2\u00070\u0002\u0002\u00c2\u00c3\u0005/\u0018",
    "\u0002\u00c32\u0003\u0002\u0002\u0002\u00c4\u00c5\u0005/\u0018\u0002",
    "\u00c5\u00c6\u0007.\u0002\u0002\u00c6\u00c7\u00055\u001b\u0002\u00c7",
    "4\u0003\u0002\u0002\u0002\u00c8\u00c9\u0005=\u001f\u0002\u00c9\u00ca",
    "\u0005=\u001f\u0002\u00ca\u00cd\u0003\u0002\u0002\u0002\u00cb\u00cd",
    "\u0007/\u0002\u0002\u00cc\u00c8\u0003\u0002\u0002\u0002\u00cc\u00cb",
    "\u0003\u0002\u0002\u0002\u00cd6\u0003\u0002\u0002\u0002\u00ce\u00cf",
    "\u0007v\u0002\u0002\u00cf\u00d0\u0007t\u0002\u0002\u00d0\u00d1\u0007",
    "w\u0002\u0002\u00d1\u00d8\u0007g\u0002\u0002\u00d2\u00d3\u0007h\u0002",
    "\u0002\u00d3\u00d4\u0007c\u0002\u0002\u00d4\u00d5\u0007n\u0002\u0002",
    "\u00d5\u00d6\u0007u\u0002\u0002\u00d6\u00d8\u0007g\u0002\u0002\u00d7",
    "\u00ce\u0003\u0002\u0002\u0002\u00d7\u00d2\u0003\u0002\u0002\u0002\u00d8",
    "8\u0003\u0002\u0002\u0002\u00d9\u00db\t\u0003\u0002\u0002\u00da\u00d9",
    "\u0003\u0002\u0002\u0002\u00db\u00dc\u0003\u0002\u0002\u0002\u00dc\u00da",
    "\u0003\u0002\u0002\u0002\u00dc\u00dd\u0003\u0002\u0002\u0002\u00dd:",
    "\u0003\u0002\u0002\u0002\u00de\u00e0\t\u0004\u0002\u0002\u00df\u00de",
    "\u0003\u0002\u0002\u0002\u00e0\u00e1\u0003\u0002\u0002\u0002\u00e1\u00df",
    "\u0003\u0002\u0002\u0002\u00e1\u00e2\u0003\u0002\u0002\u0002\u00e2\u00e3",
    "\u0003\u0002\u0002\u0002\u00e3\u00e4\b\u001e\u0002\u0002\u00e4<\u0003",
    "\u0002\u0002\u0002\u00e5\u00e6\t\u0005\u0002\u0002\u00e6>\u0003\u0002",
    "\u0002\u0002\t\u0002\u00b6\u00be\u00cc\u00d7\u00dc\u00e1\u0003\b\u0002",
    "\u0002"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

function QLSLexer(input) {
	antlr4.Lexer.call(this, input);
    this._interp = new antlr4.atn.LexerATNSimulator(this, atn, decisionsToDFA, new antlr4.PredictionContextCache());
    return this;
}

QLSLexer.prototype = Object.create(antlr4.Lexer.prototype);
QLSLexer.prototype.constructor = QLSLexer;

QLSLexer.EOF = antlr4.Token.EOF;
QLSLexer.T__0 = 1;
QLSLexer.T__1 = 2;
QLSLexer.T__2 = 3;
QLSLexer.T__3 = 4;
QLSLexer.T__4 = 5;
QLSLexer.T__5 = 6;
QLSLexer.T__6 = 7;
QLSLexer.T__7 = 8;
QLSLexer.T__8 = 9;
QLSLexer.T__9 = 10;
QLSLexer.T__10 = 11;
QLSLexer.T__11 = 12;
QLSLexer.T__12 = 13;
QLSLexer.T__13 = 14;
QLSLexer.T__14 = 15;
QLSLexer.T__15 = 16;
QLSLexer.T__16 = 17;
QLSLexer.T__17 = 18;
QLSLexer.T__18 = 19;
QLSLexer.T__19 = 20;
QLSLexer.T__20 = 21;
QLSLexer.STRING_LITERAL = 22;
QLSLexer.INTEGER_LITERAL = 23;
QLSLexer.FLOAT_LITERAL = 24;
QLSLexer.MONEY_LITERAL = 25;
QLSLexer.MONEY_LITERAL_CENTS = 26;
QLSLexer.BOOLEAN_LITERAL = 27;
QLSLexer.IDENTIFIER = 28;
QLSLexer.WHITESPACE = 29;


QLSLexer.modeNames = [ "DEFAULT_MODE" ];

QLSLexer.literalNames = [ 'null', "'stylesheet'", "'{'", "'}'", "'page'", 
                          "'section'", "'question'", "'default'", "'widget'", 
                          "';'", "':'", "'slider'", "'text'", "'radio'", 
                          "'('", "')'", "','", "'boolean'", "'string'", 
                          "'integer'", "'float'", "'money'" ];

QLSLexer.symbolicNames = [ 'null', 'null', 'null', 'null', 'null', 'null', 
                           'null', 'null', 'null', 'null', 'null', 'null', 
                           'null', 'null', 'null', 'null', 'null', 'null', 
                           'null', 'null', 'null', 'null', "STRING_LITERAL", 
                           "INTEGER_LITERAL", "FLOAT_LITERAL", "MONEY_LITERAL", 
                           "MONEY_LITERAL_CENTS", "BOOLEAN_LITERAL", "IDENTIFIER", 
                           "WHITESPACE" ];

QLSLexer.ruleNames = [ "T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", 
                       "T__7", "T__8", "T__9", "T__10", "T__11", "T__12", 
                       "T__13", "T__14", "T__15", "T__16", "T__17", "T__18", 
                       "T__19", "T__20", "STRING_LITERAL", "INTEGER_LITERAL", 
                       "FLOAT_LITERAL", "MONEY_LITERAL", "MONEY_LITERAL_CENTS", 
                       "BOOLEAN_LITERAL", "IDENTIFIER", "WHITESPACE", "DIGIT" ];

QLSLexer.grammarFileName = "QLS.g4";



exports.QLSLexer = QLSLexer;


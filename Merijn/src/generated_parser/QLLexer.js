// Generated from C:/Users/mwijngaard/Documents/Projects/software-construction/2015-2016/Merijn/src/generated_parser\QL.g4 by ANTLR 4.5.1
// jshint ignore: start
var antlr4 = require('antlr4/index');


var serializedATN = ["\u0003\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd",
    "\u0002\u001d\u00ba\b\u0001\u0004\u0002\t\u0002\u0004\u0003\t\u0003\u0004",
    "\u0004\t\u0004\u0004\u0005\t\u0005\u0004\u0006\t\u0006\u0004\u0007\t",
    "\u0007\u0004\b\t\b\u0004\t\t\t\u0004\n\t\n\u0004\u000b\t\u000b\u0004",
    "\f\t\f\u0004\r\t\r\u0004\u000e\t\u000e\u0004\u000f\t\u000f\u0004\u0010",
    "\t\u0010\u0004\u0011\t\u0011\u0004\u0012\t\u0012\u0004\u0013\t\u0013",
    "\u0004\u0014\t\u0014\u0004\u0015\t\u0015\u0004\u0016\t\u0016\u0004\u0017",
    "\t\u0017\u0004\u0018\t\u0018\u0004\u0019\t\u0019\u0004\u001a\t\u001a",
    "\u0004\u001b\t\u001b\u0004\u001c\t\u001c\u0004\u001d\t\u001d\u0003\u0002",
    "\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0002\u0003\u0003\u0003\u0003",
    "\u0003\u0003\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004\u0003\u0004",
    "\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005\u0003\u0005",
    "\u0003\u0005\u0003\u0005\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0006",
    "\u0003\u0006\u0003\u0006\u0003\u0006\u0003\u0007\u0003\u0007\u0003\u0007",
    "\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\u0007\u0003\b",
    "\u0003\b\u0003\b\u0003\b\u0003\b\u0003\b\u0003\t\u0003\t\u0003\t\u0003",
    "\t\u0003\t\u0003\t\u0003\n\u0003\n\u0003\u000b\u0003\u000b\u0003\f\u0003",
    "\f\u0003\r\u0003\r\u0003\u000e\u0003\u000e\u0003\u000f\u0003\u000f\u0003",
    "\u000f\u0003\u0010\u0003\u0010\u0003\u0010\u0003\u0011\u0003\u0011\u0003",
    "\u0012\u0003\u0012\u0003\u0012\u0003\u0013\u0003\u0013\u0003\u0014\u0003",
    "\u0014\u0003\u0014\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0003",
    "\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0003\u0015\u0005\u0015\u008f",
    "\n\u0015\u0003\u0016\u0003\u0016\u0007\u0016\u0093\n\u0016\f\u0016\u000e",
    "\u0016\u0096\u000b\u0016\u0003\u0016\u0003\u0016\u0003\u0017\u0006\u0017",
    "\u009b\n\u0017\r\u0017\u000e\u0017\u009c\u0003\u0018\u0003\u0018\u0003",
    "\u0018\u0003\u0018\u0003\u0019\u0003\u0019\u0003\u0019\u0003\u0019\u0003",
    "\u001a\u0003\u001a\u0003\u001a\u0003\u001a\u0005\u001a\u00ab\n\u001a",
    "\u0003\u001b\u0006\u001b\u00ae\n\u001b\r\u001b\u000e\u001b\u00af\u0003",
    "\u001c\u0006\u001c\u00b3\n\u001c\r\u001c\u000e\u001c\u00b4\u0003\u001c",
    "\u0003\u001c\u0003\u001d\u0003\u001d\u0002\u0002\u001e\u0003\u0003\u0005",
    "\u0004\u0007\u0005\t\u0006\u000b\u0007\r\b\u000f\t\u0011\n\u0013\u000b",
    "\u0015\f\u0017\r\u0019\u000e\u001b\u000f\u001d\u0010\u001f\u0011!\u0012",
    "#\u0013%\u0014\'\u0015)\u0016+\u0017-\u0018/\u00191\u001a3\u001b5\u001c",
    "7\u001d9\u0002\u0003\u0002\u0006\u0003\u0002$$\u0005\u0002C\\aac|\u0005",
    "\u0002\u000b\f\u000f\u000f\"\"\u0003\u00022;\u00be\u0002\u0003\u0003",
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
    "\u0002\u0002\u0002\u00027\u0003\u0002\u0002\u0002\u0003;\u0003\u0002",
    "\u0002\u0002\u0005@\u0003\u0002\u0002\u0002\u0007C\u0003\u0002\u0002",
    "\u0002\tH\u0003\u0002\u0002\u0002\u000bP\u0003\u0002\u0002\u0002\rW",
    "\u0003\u0002\u0002\u0002\u000f_\u0003\u0002\u0002\u0002\u0011e\u0003",
    "\u0002\u0002\u0002\u0013k\u0003\u0002\u0002\u0002\u0015m\u0003\u0002",
    "\u0002\u0002\u0017o\u0003\u0002\u0002\u0002\u0019q\u0003\u0002\u0002",
    "\u0002\u001bs\u0003\u0002\u0002\u0002\u001du\u0003\u0002\u0002\u0002",
    "\u001fx\u0003\u0002\u0002\u0002!{\u0003\u0002\u0002\u0002#}\u0003\u0002",
    "\u0002\u0002%\u0080\u0003\u0002\u0002\u0002\'\u0082\u0003\u0002\u0002",
    "\u0002)\u008e\u0003\u0002\u0002\u0002+\u0090\u0003\u0002\u0002\u0002",
    "-\u009a\u0003\u0002\u0002\u0002/\u009e\u0003\u0002\u0002\u00021\u00a2",
    "\u0003\u0002\u0002\u00023\u00aa\u0003\u0002\u0002\u00025\u00ad\u0003",
    "\u0002\u0002\u00027\u00b2\u0003\u0002\u0002\u00029\u00b8\u0003\u0002",
    "\u0002\u0002;<\u0007H\u0002\u0002<=\u0007q\u0002\u0002=>\u0007t\u0002",
    "\u0002>?\u0007o\u0002\u0002?\u0004\u0003\u0002\u0002\u0002@A\u0007k",
    "\u0002\u0002AB\u0007h\u0002\u0002B\u0006\u0003\u0002\u0002\u0002CD\u0007",
    "g\u0002\u0002DE\u0007n\u0002\u0002EF\u0007u\u0002\u0002FG\u0007g\u0002",
    "\u0002G\b\u0003\u0002\u0002\u0002HI\u0007d\u0002\u0002IJ\u0007q\u0002",
    "\u0002JK\u0007q\u0002\u0002KL\u0007n\u0002\u0002LM\u0007g\u0002\u0002",
    "MN\u0007c\u0002\u0002NO\u0007p\u0002\u0002O\n\u0003\u0002\u0002\u0002",
    "PQ\u0007u\u0002\u0002QR\u0007v\u0002\u0002RS\u0007t\u0002\u0002ST\u0007",
    "k\u0002\u0002TU\u0007p\u0002\u0002UV\u0007i\u0002\u0002V\f\u0003\u0002",
    "\u0002\u0002WX\u0007k\u0002\u0002XY\u0007p\u0002\u0002YZ\u0007v\u0002",
    "\u0002Z[\u0007g\u0002\u0002[\\\u0007i\u0002\u0002\\]\u0007g\u0002\u0002",
    "]^\u0007t\u0002\u0002^\u000e\u0003\u0002\u0002\u0002_`\u0007h\u0002",
    "\u0002`a\u0007n\u0002\u0002ab\u0007q\u0002\u0002bc\u0007c\u0002\u0002",
    "cd\u0007v\u0002\u0002d\u0010\u0003\u0002\u0002\u0002ef\u0007o\u0002",
    "\u0002fg\u0007q\u0002\u0002gh\u0007p\u0002\u0002hi\u0007g\u0002\u0002",
    "ij\u0007{\u0002\u0002j\u0012\u0003\u0002\u0002\u0002kl\u0007}\u0002",
    "\u0002l\u0014\u0003\u0002\u0002\u0002mn\u0007\u007f\u0002\u0002n\u0016",
    "\u0003\u0002\u0002\u0002op\u0007*\u0002\u0002p\u0018\u0003\u0002\u0002",
    "\u0002qr\u0007+\u0002\u0002r\u001a\u0003\u0002\u0002\u0002st\u0007=",
    "\u0002\u0002t\u001c\u0003\u0002\u0002\u0002uv\u0007?\u0002\u0002vw\u0007",
    "?\u0002\u0002w\u001e\u0003\u0002\u0002\u0002xy\u0007#\u0002\u0002yz",
    "\u0007?\u0002\u0002z \u0003\u0002\u0002\u0002{|\u0007@\u0002\u0002|",
    "\"\u0003\u0002\u0002\u0002}~\u0007@\u0002\u0002~\u007f\u0007?\u0002",
    "\u0002\u007f$\u0003\u0002\u0002\u0002\u0080\u0081\u0007>\u0002\u0002",
    "\u0081&\u0003\u0002\u0002\u0002\u0082\u0083\u0007>\u0002\u0002\u0083",
    "\u0084\u0007?\u0002\u0002\u0084(\u0003\u0002\u0002\u0002\u0085\u0086",
    "\u0007v\u0002\u0002\u0086\u0087\u0007t\u0002\u0002\u0087\u0088\u0007",
    "w\u0002\u0002\u0088\u008f\u0007g\u0002\u0002\u0089\u008a\u0007h\u0002",
    "\u0002\u008a\u008b\u0007c\u0002\u0002\u008b\u008c\u0007n\u0002\u0002",
    "\u008c\u008d\u0007u\u0002\u0002\u008d\u008f\u0007g\u0002\u0002\u008e",
    "\u0085\u0003\u0002\u0002\u0002\u008e\u0089\u0003\u0002\u0002\u0002\u008f",
    "*\u0003\u0002\u0002\u0002\u0090\u0094\u0007$\u0002\u0002\u0091\u0093",
    "\n\u0002\u0002\u0002\u0092\u0091\u0003\u0002\u0002\u0002\u0093\u0096",
    "\u0003\u0002\u0002\u0002\u0094\u0092\u0003\u0002\u0002\u0002\u0094\u0095",
    "\u0003\u0002\u0002\u0002\u0095\u0097\u0003\u0002\u0002\u0002\u0096\u0094",
    "\u0003\u0002\u0002\u0002\u0097\u0098\u0007$\u0002\u0002\u0098,\u0003",
    "\u0002\u0002\u0002\u0099\u009b\u00059\u001d\u0002\u009a\u0099\u0003",
    "\u0002\u0002\u0002\u009b\u009c\u0003\u0002\u0002\u0002\u009c\u009a\u0003",
    "\u0002\u0002\u0002\u009c\u009d\u0003\u0002\u0002\u0002\u009d.\u0003",
    "\u0002\u0002\u0002\u009e\u009f\u0005-\u0017\u0002\u009f\u00a0\u0007",
    "0\u0002\u0002\u00a0\u00a1\u0005-\u0017\u0002\u00a10\u0003\u0002\u0002",
    "\u0002\u00a2\u00a3\u0005-\u0017\u0002\u00a3\u00a4\u0007.\u0002\u0002",
    "\u00a4\u00a5\u00053\u001a\u0002\u00a52\u0003\u0002\u0002\u0002\u00a6",
    "\u00a7\u00059\u001d\u0002\u00a7\u00a8\u00059\u001d\u0002\u00a8\u00ab",
    "\u0003\u0002\u0002\u0002\u00a9\u00ab\u0007/\u0002\u0002\u00aa\u00a6",
    "\u0003\u0002\u0002\u0002\u00aa\u00a9\u0003\u0002\u0002\u0002\u00ab4",
    "\u0003\u0002\u0002\u0002\u00ac\u00ae\t\u0003\u0002\u0002\u00ad\u00ac",
    "\u0003\u0002\u0002\u0002\u00ae\u00af\u0003\u0002\u0002\u0002\u00af\u00ad",
    "\u0003\u0002\u0002\u0002\u00af\u00b0\u0003\u0002\u0002\u0002\u00b06",
    "\u0003\u0002\u0002\u0002\u00b1\u00b3\t\u0004\u0002\u0002\u00b2\u00b1",
    "\u0003\u0002\u0002\u0002\u00b3\u00b4\u0003\u0002\u0002\u0002\u00b4\u00b2",
    "\u0003\u0002\u0002\u0002\u00b4\u00b5\u0003\u0002\u0002\u0002\u00b5\u00b6",
    "\u0003\u0002\u0002\u0002\u00b6\u00b7\b\u001c\u0002\u0002\u00b78\u0003",
    "\u0002\u0002\u0002\u00b8\u00b9\t\u0005\u0002\u0002\u00b9:\u0003\u0002",
    "\u0002\u0002\t\u0002\u008e\u0094\u009c\u00aa\u00af\u00b4\u0003\b\u0002",
    "\u0002"].join("");


var atn = new antlr4.atn.ATNDeserializer().deserialize(serializedATN);

var decisionsToDFA = atn.decisionToState.map( function(ds, index) { return new antlr4.dfa.DFA(ds, index); });

function QLLexer(input) {
	antlr4.Lexer.call(this, input);
    this._interp = new antlr4.atn.LexerATNSimulator(this, atn, decisionsToDFA, new antlr4.PredictionContextCache());
    return this;
}

QLLexer.prototype = Object.create(antlr4.Lexer.prototype);
QLLexer.prototype.constructor = QLLexer;

QLLexer.EOF = antlr4.Token.EOF;
QLLexer.FORM = 1;
QLLexer.IF = 2;
QLLexer.ELSE = 3;
QLLexer.TYPE_BOOLEAN = 4;
QLLexer.TYPE_STRING = 5;
QLLexer.TYPE_INTEGER = 6;
QLLexer.TYPE_FLOAT = 7;
QLLexer.TYPE_MONEY = 8;
QLLexer.LEFT_BRACE = 9;
QLLexer.RIGHT_BRACE = 10;
QLLexer.LEFT_PAREN = 11;
QLLexer.RIGHT_PAREN = 12;
QLLexer.SEMICOL = 13;
QLLexer.EQ = 14;
QLLexer.NOT_EQ = 15;
QLLexer.GT = 16;
QLLexer.GT_EQ = 17;
QLLexer.LT = 18;
QLLexer.LT_EQ = 19;
QLLexer.BOOLEAN_LITERAL = 20;
QLLexer.STRING_LITERAL = 21;
QLLexer.INTEGER_LITERAL = 22;
QLLexer.FLOAT_LITERAL = 23;
QLLexer.MONEY_LITERAL = 24;
QLLexer.MONEY_LITERAL_CENTS = 25;
QLLexer.IDENTIFIER = 26;
QLLexer.WHITESPACE = 27;


QLLexer.modeNames = [ "DEFAULT_MODE" ];

QLLexer.literalNames = [ 'null', "'Form'", "'if'", "'else'", "'boolean'", 
                         "'string'", "'integer'", "'float'", "'money'", 
                         "'{'", "'}'", "'('", "')'", "';'", "'=='", "'!='", 
                         "'>'", "'>='", "'<'", "'<='" ];

QLLexer.symbolicNames = [ 'null', "FORM", "IF", "ELSE", "TYPE_BOOLEAN", 
                          "TYPE_STRING", "TYPE_INTEGER", "TYPE_FLOAT", "TYPE_MONEY", 
                          "LEFT_BRACE", "RIGHT_BRACE", "LEFT_PAREN", "RIGHT_PAREN", 
                          "SEMICOL", "EQ", "NOT_EQ", "GT", "GT_EQ", "LT", 
                          "LT_EQ", "BOOLEAN_LITERAL", "STRING_LITERAL", 
                          "INTEGER_LITERAL", "FLOAT_LITERAL", "MONEY_LITERAL", 
                          "MONEY_LITERAL_CENTS", "IDENTIFIER", "WHITESPACE" ];

QLLexer.ruleNames = [ "FORM", "IF", "ELSE", "TYPE_BOOLEAN", "TYPE_STRING", 
                      "TYPE_INTEGER", "TYPE_FLOAT", "TYPE_MONEY", "LEFT_BRACE", 
                      "RIGHT_BRACE", "LEFT_PAREN", "RIGHT_PAREN", "SEMICOL", 
                      "EQ", "NOT_EQ", "GT", "GT_EQ", "LT", "LT_EQ", "BOOLEAN_LITERAL", 
                      "STRING_LITERAL", "INTEGER_LITERAL", "FLOAT_LITERAL", 
                      "MONEY_LITERAL", "MONEY_LITERAL_CENTS", "IDENTIFIER", 
                      "WHITESPACE", "DIGIT" ];

QLLexer.grammarFileName = "QL.g4";



exports.QLLexer = QLLexer;


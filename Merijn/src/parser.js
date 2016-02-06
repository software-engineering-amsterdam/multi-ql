import * as antlr4 from 'antlr4';
import { QLLexer as GeneratedLexer } from 'src/generated_parser/QLLexer';
import { QLParser as GeneratedParser } from 'src/generated_parser/QLParser';

export function parse(input) {
	var chars = new antlr4.InputStream(input);
	var lexer = new GeneratedLexer(chars);
	var tokens  = new antlr4.CommonTokenStream(lexer);
	var parser = new GeneratedParser(tokens);
	parser.buildParseTrees = true;
	var tree = parser.form();
	return tree;
}
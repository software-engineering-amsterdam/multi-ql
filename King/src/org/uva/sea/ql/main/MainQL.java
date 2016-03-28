package org.uva.sea.ql.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.ast.domain.Form;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;
import org.uva.sea.ql.semantic.TypeChecker;

public class MainQL {

	public static void main(String[] args) throws IOException {
		// Loading the DSL script into the ANTLR stream.
		ANTLRInputStream input = new ANTLRFileStream(new File("resources/questionaire.gr").getPath());

		// Passing the input to the lexer to create tokens
		QLLexer lexer = new QLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		// Passing the tokens to the parser to create the parse tree.
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();
		Form ast = fileContext.form(0).result;
		// System.out.println(ast);
		TypeChecker typeChecker = new TypeChecker(ast);

	}

	public void printTree(QLParser parser) {
		// show AST in GUI
		TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), parser.form());
		viewr.setScale(1.5);// scale a little
		viewr.open();
	}

}

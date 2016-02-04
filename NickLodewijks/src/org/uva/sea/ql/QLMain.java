package org.uva.sea.ql;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;
import org.uva.sea.ql.parser.antlr.QLParser.FileContext;

public class QLMain {
	public static void main(String[] args) throws IOException {
		FileContext fileContext;

		ANTLRInputStream input = new ANTLRFileStream(new File("resources/Questionaire.ql").getPath());
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);

		fileContext = parser.file();

		TypeChecker tc = new TypeChecker(fileContext.form(0).result);

//		TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
//		viewr.setScale(1.5);// scale a little
//
//		viewr.open();
	}
}

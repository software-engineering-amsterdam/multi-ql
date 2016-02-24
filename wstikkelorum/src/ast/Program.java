package ast;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import antlr.QLLexer;
import antlr.QLParser;
import antlr.QLParser.FileContext;
import ast.visitor.BasicVisitor;
import ast.visitor.TypeChecker;

public class Program {
	public static void main(String[] args) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream(new File(
				"resources/Questionaire.ql").getPath());
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		QLParser parser = new QLParser(tokens);

		FileContext fileContext = parser.file();
		// System.out.println(fileContext.form().toStringTree());

		// BasicVisitor visitor = new BasicVisitor();
		// visitor.visit(fileContext.form().result);

		TypeChecker typeChecker = new TypeChecker();
		typeChecker.visit(fileContext.form().result);

		// TreeViewer viewer = new
		// TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
		// viewer.open();

		System.out.println("Done");
	}
}

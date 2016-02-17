package ql;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import antlrsources.QLLexer;
import antlrsources.QLParser;
import ast.construct.QLNodeVisitor;
import ast.model.Form;



public class Main {

	public static void main(String[] args) throws IOException {
		ANTLRFileStream input = new ANTLRFileStream("src/main/resources/quesionnaire1.ql");
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokens);
		ParseTree parseTree = parser.form();
		QLNodeVisitor nodeVisitor = new QLNodeVisitor();
		Form form = (Form) parseTree.accept(nodeVisitor);
		System.out.println("done");
	}

}

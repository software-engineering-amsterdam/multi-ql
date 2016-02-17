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
import ast.visitor.TestVisitor;

public class Program {
	public static void main(String[] args) throws IOException{
		ANTLRFileStream input = new ANTLRFileStream(new File("resources/Questionaire.ql").getPath());
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		QLParser parser = new QLParser(tokens);		
		
		FileContext fileContext = parser.file();
		//System.out.println(fileContext.form().toStringTree());
		
		TestVisitor visitor = new TestVisitor();
		visitor.visit(fileContext.form().result);
		for(String s : visitor.types){
			System.out.println(s);
		}
		
		TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
		viewer.open();
		
		System.out.println("Done");
	}
}

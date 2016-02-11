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

public class Program {
	public static void main(String[] args) throws IOException{
		ANTLRFileStream input = new ANTLRFileStream(new File("resources/Questionaire.ql").getPath());
		//System.out.println(input);
		CommonTokenStream tokens = new CommonTokenStream(new QLLexer(input));
		System.out.println(tokens.getTokens());
		QLParser parser = new QLParser(tokens);		
		
		
		FileContext fileContext = parser.file();
		TreeViewer viewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), fileContext);
		viewer.setScale(0.5);
		viewer.open();
		
		System.out.println("Done");
	}
}

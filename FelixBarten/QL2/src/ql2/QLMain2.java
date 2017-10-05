package ql2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ql2.parser.generated.src.ql2.parser.Ql2Lexer;
import ql2.parser.generated.src.ql2.parser.Ql2Parser;


public class QLMain2 {
	
	public static void main(String[] args) {
		String parseTest2 = ""
    			+ "\"What was the selling price\""
    			+ "    sellingPrice: money";
        String parseTest3 = ""
        		+ "form taxExampleForm {"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money"
        		+ "}";
        String parseForm = ""
        		+ "form parseForm{"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money"
        		+ "\"Did you own a house this year?\" hasHouse: boolean"
        		+ "}";   
        String parseForm2 = "form parseForm2 { }";
        String path = "QLExamples/QLGitExample.ql";
        String path2 = "QLExamples/questionnaire.ql";
        String path3 = "QLExamples/advancedquestionnaire.ql";

        
        System.out.println("Starting parsing");

        	inspectParseTreeContent(parseTest2);
        	inspectParseTreeContent(parseTest3);

        	inspectParseTreeContent(parseForm);
        	inspectParseTreeContent(parseForm2);

       
    
        parseQuestionnaireExample();
        
        //  BaseVisitor<T> basevisit = new BaseVisitor<T>();
        //   basevisit.visit(lexer);
        
        System.out.println("Finished parsing");
        
        inspectParseTree(path);
        inspectParseTree(path2);
        inspectParseTree(path3);
        inspectParseTree("QLExamples/formexample.ql");

	}

	private static void parseQuestionnaireExample() {
		// TODO Auto-generated method stub
		
	}
	
	private static void inspectParseTreeContent(String content) {
		
		System.out.println("");
        System.out.println("Inspecting parse tree");
        System.out.println(content);

	      Ql2Lexer lexer = null;
			lexer = new Ql2Lexer( new ANTLRInputStream(content));
	        CommonTokenStream tokens = new CommonTokenStream( lexer );
	        
	        Ql2Parser parser = new Ql2Parser( tokens );
	        ParseTree tree = parser.question();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
			System.out.println("Finished inspecting parse tree");

	}
	
	private static void inspectParseTree(String path) {
		System.out.println("");
        System.out.println("Inspecting parse tree");
		Ql2Lexer lexer2 = null;
		try {
			lexer2 = new Ql2Lexer(CharStreams.fromFileName(path));
			CommonTokenStream tokens2 = new CommonTokenStream( lexer2 );
			Ql2Parser parser = new Ql2Parser( tokens2 );	
		
		    ParseTree tree = parser.question();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		System.out.println("Finished inspecting parse tree");

	}
}

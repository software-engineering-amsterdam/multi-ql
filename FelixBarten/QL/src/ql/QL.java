/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ql;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ql.parser.QLLexer;
import ql.parser.QLParser;
/**
 *
 * @author felixbarten
 */
public class QL {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
    	String path = "/Users/felixbarten/Git/multi-ql/FelixBarten/QL/src/ql/parser/QL.g4";
    	String path2 = "";
    	String parseTest = "\"is this a test?\" testQuestion: money";
    	String parseTest2 = ""
    			+ "\"What was the selling price?\""
    			+ "    sellingPrice : money";
        String parseTest3 = ""
        		+ "form taxExampleForm {"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money"
        		+ "}";
        
        System.out.println("Starting parsing");
        QLLexer lexer = null;
		lexer = new QLLexer( new ANTLRInputStream(parseTest2));
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        QLParser parser = new QLParser( tokens );
        ParseTree tree = parser.question();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk( new QLWalker(), tree );
        
        // parse test for simple forms
        QLLexer lexer2 = null;
		lexer2 = new QLLexer( new ANTLRInputStream(parseTest3));
        CommonTokenStream tokens2 = new CommonTokenStream( lexer2 );
        QLParser parser2 = new QLParser( tokens2 );
        ParseTree tree2 = parser2.form();
        ParseTreeWalker walker2 = new ParseTreeWalker();
        walker2.walk( new QLWalker(), tree2 );
    
        parseExamples();
        
        System.out.println("Finished parsing");
    }

    // some ugly testing code
    
	private static void parseExamples() {
		// TODO Auto-generated method stub
		parseQuestionExample();
		parseFormExample();
		parseIfExample();
	}

	private static void parseIfExample() {
		System.out.println("Parsing IF example");

        QLLexer lexer = null;
		try {
			lexer = new QLLexer( new ANTLRFileStream("examples/ifexample.ql"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CommonTokenStream tokens = new CommonTokenStream( lexer );
        QLParser parser = new QLParser( tokens );
        ParseTree tree = parser.form();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk( new QLWalker(), tree );
		System.out.println("Parsing If example");

	}

	private static void parseFormExample() {
		System.out.println("Parsing Form example");
        QLLexer lexer = null;
		try {
			lexer = new QLLexer( new ANTLRFileStream("examples/formexample.ql"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        CommonTokenStream tokens = new CommonTokenStream( lexer );
        QLParser parser = new QLParser( tokens );
        ParseTree tree = parser.form();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk( new QLWalker(), tree );
		System.out.println("Finished Form example");

        
	}

	private static void parseQuestionExample() {
		System.out.println("Parsing Question example");

		// TODO Auto-generated method stub
        QLLexer lexer = null;
		try {
			lexer = new QLLexer( new ANTLRFileStream("examples/questionexample.ql"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        CommonTokenStream tokens = new CommonTokenStream( lexer );
        QLParser parser = new QLParser( tokens );
        ParseTree tree = parser.question();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk( new QLWalker(), tree );
		System.out.println("Finished Question Example");

        
	}
    
    
    
}

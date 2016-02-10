package org.uva.sea.ql;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.gui.TreeViewer;
import org.uva.sea.ql.parser.QLLexer;
import org.uva.sea.ql.parser.QLParser;
import org.uva.sea.ql.parser.QLParser.FormContext;
import org.uva.sea.utils.Utils;
import org.uva.sea.ql.ast.expr.*;


import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String [] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("D:\\Master\\Software Construction\\Github\\Kevin van den Bekerom\\DSLQL\\src\\main\\resources\\SampleForm.txt"));
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
		    testGrammar(getParser(everything));
		    getAST(getParser(everything));
		} finally {
		    br.close();
		    
		}
		
		
		
	}

	public static void testGrammar(QLParser parser){
		ParseTree tree = parser.form(); // begin parsing at init rule
		
		//show AST in GUI
		TreeViewer viewr = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        viewr.setScale(1.5);// scale a little

        viewr.open();
	}
	
	public static void getAST(QLParser parser){
		FormContext fc = parser.form(); // begin parsing at init rule
		System.out.println(fc.getChildCount());
	}

	public static QLParser getParser(String in){
		// create a CharStream that reads from standard input
			ANTLRInputStream input = new ANTLRInputStream(in);
			// create a lexer that feeds off of input CharStream
			QLLexer lexer = new QLLexer(input);
			// create a buffer of tokens pulled from the lexer
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			// create a parser that feeds off the tokens buffer
			QLParser parser = new QLParser(tokens);
			
			return parser;
	}

}

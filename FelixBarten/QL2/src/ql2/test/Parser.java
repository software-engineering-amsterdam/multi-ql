package ql2.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ql2.Ql2Walker;
import ql2.parser.generated.Ql2Lexer;
import ql2.parser.generated.Ql2Parser;

public class Parser {
	
	private Ql2Parser parser;
	private Ql2Lexer lexer;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private String getParseTreeFromFile(String path) {
		
		try {
			lexer = new Ql2Lexer(CharStreams.fromFileName(path));
		    CommonTokenStream tokens = new CommonTokenStream( lexer );
	        
		    Ql2Parser parser = new Ql2Parser( tokens );
		    ParseTree tree = parser.questionnaire();
		    ParseTreeWalker walker = new ParseTreeWalker();
		    walker.walk( new Ql2Walker(), tree );
		    return tree.toStringTree(parser);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	private String getParseTreeFromString(String content) {
		
		lexer = new Ql2Lexer(new ANTLRInputStream(content));
	    CommonTokenStream tokens = new CommonTokenStream( lexer );
	        
	    Ql2Parser parser = new Ql2Parser( tokens );
	    ParseTree tree = parser.questionnaire();
	    ParseTreeWalker walker = new ParseTreeWalker();
	    walker.walk( new Ql2Walker(), tree );
		
		return tree.toStringTree(parser);
	}
	
	@Test
	public void testQuestions() {
		// Check parse trees.
		System.out.println(getParseTreeFromFile("resources/test/testQuestion.ql"));
		assertEquals("(questionnaire (forms (form form (formname survey) (block { (question (inputquestion (questiontext \"How would you rate your customer support experience?\") (questionname customerGrade) : (questiontype int))) (question (calculatedquestion (inputquestion (questiontext \"Is this percentage representation correct?\") (questionname calcGrade) : (questiontype int)) = (conditions ( (condition (orExpr (andExpr (relExpr (addExpr (mulExpr (unaryExpr ( (orExpr (andExpr (relExpr (addExpr (mulExpr (unaryExpr (value customerGrade)) / (unaryExpr (value (literal (intliteral 5))))))))) )) * (unaryExpr (value (literal (intliteral 100)))))))))) )))) }))) <EOF>)" 
			, getParseTreeFromFile("resources/test/testQuestion.ql"));
		
	}

}

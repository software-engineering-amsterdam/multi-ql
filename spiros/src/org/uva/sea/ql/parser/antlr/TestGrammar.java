package org.uva.sea.ql.parser.antlr;

import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
//import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.*;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.questionnaire.Questionnaire;
import org.uva.sea.ql.ast.visitor.MyQLVisitor;
import org.uva.sea.ql.type_checker.TypeChecker;
 

// TODO TODAY: change so that i get AST as a form, not as questionnaire...

public class TestGrammar {

	
	public static void main(String[] args) throws IOException {
		
		//CharStream charStream = new ANTLRInputStream(" form sampleForm {  } ");
		File file = new File("testQuestionnaire");
	    FileInputStream fileInputStream = new FileInputStream(file);
	    ANTLRInputStream inputStream = new ANTLRInputStream(fileInputStream);
		QLLexer lexer = new QLLexer(inputStream);
		TokenStream tokenStream = new CommonTokenStream(lexer);
		QLParser parser = new QLParser(tokenStream);
		ParseTree parseTree = parser.form();
		MyQLVisitor visitor = new MyQLVisitor();
		//Questionnaire ast = (Questionnaire) parseTree.accept(visitor);
		Form ast = (Form) parseTree.accept(visitor);
		
		TypeChecker typeChecker = new TypeChecker(ast);
		typeChecker.performTypeChecking();
		
		//show tree in simple GUI
	    JFrame frame = new JFrame("Generated Parse Tree");
	    JPanel panel = new JPanel();
	    TreeViewer treeViewer = new TreeViewer(Arrays.asList(
	               parser.getRuleNames()),parseTree);
	    treeViewer.setScale(1);
	    panel.add(treeViewer);
	    frame.add(panel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1200,600);
	    //frame.setVisible(true);
	}
}
package ql2;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.antlr.runtime.Parser;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Tree;

import com.sun.istack.internal.Nullable;

import ql2.ast.Expr;
import ql2.ast.Form;
import ql2.ast.Questionnaire;
import ql2.parser.generated.Ql2Lexer;
import ql2.parser.generated.Ql2Parser;
import ql2.ui.ErrorDialog;
import ql2.ui.QlGui;

public class QLMain {
	
	public static void main(String[] args) {
		String inputQuestionExample = ""
    			+ "\"What was the selling price?\""
    			+ "    sellingPrice: integer";
		String calculatedQuestionExample = ""
				+ "\"What is the sum of two and two?\" "
				+ " sumQuestion : integer = ( 2 + 2 )";
        String parseTest3 = ""
        		+ "form taxExampleForm {"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money"
        		+ "}";
        String parseForm = ""
        		+ "form parseForm {"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money "
        		+ "\"Did you own a house this year?\" hasHouse: boolean "
        		+ "}"; 
        String parseStatement = ""
        		+ "form parseForm {"
        		+ " \"What was your income last year?\""
        		+ "		incomeQuestion : money "
        		+ "if (incomeQuestion > 10000) {"
        		+ "\"Did you own a house this year?\" hasHouse: boolean "
        		+ "		}"
        		+ "}"; 
        String parseForm2 = "form parseForm2 { }";
        String path = "QLExamples/QLGitExample.ql";
        String path2 = "QLExamples/questionnaire.ql";
        String path3 = "QLExamples/advancedquestionnaire.ql";
        String path4 = "QLExamples/advancedquestionnaire2.ql";

        
        System.out.println("Starting parsing");

        
        	inspectParseTreeQuestion(inputQuestionExample);
        	inspectParseTreeQuestion(calculatedQuestionExample, false);
        	
        	inspectParseTreeContent(parseTest3);

        	inspectParseTreeContent(parseForm);
        	inspectParseTreeContent(parseForm2);
        	
        	inspectParseTreeContent(parseStatement);
       
    
        parseQuestionnaireExample();

        System.out.println("Finished String parsing");
        System.out.println("");
        
        System.out.println("Starting File Parsing");
       // inspectParseTree(path);
        inspectParseTree(path2);
        inspectParseTree(path3);
        inspectParseTree(path4);
        inspectParseTreeForm("QLExamples/formexample.ql", false);

        conflictScenarios();
        String formExample = "QLExamples/formexample3.ql";
        
        semanticAnalysis(formExample);
        run(formExample);

        //inspectParseTreeForm("QLExamples/formexample2.ql", true);
        System.out.println("Finished File Parsing");
	}
	
	public static void run(String path) {
		
		SemanticAnalysis sem = new SemanticAnalysis(path);
		sem.run();
		// error checking
		if (sem.getContext().noErrors()) {
			QlGui gui = new QlGui(sem.getForm(), sem.getContext());
		}  else {
			ErrorDialog dialog = new ErrorDialog(sem.getContext());
		}
	}
	
	
	public static void semanticAnalysis(String path) {
		SemanticAnalysis sem = new SemanticAnalysis(path);
		sem.run();
		
		sem.reset("QLExamples/typechecker/duplicatelabel.ql");
		sem.reset("QLExamples/typechecker/duplicateID.ql");
		sem.reset("QLExamples/typechecker/varnotdeclared.ql");
		sem.reset("QLExamples/typechecker/invalidcondition.ql");
		sem.reset("QLExamples/typechecker/cyclicdependency.ql");
		 
	}

	
	private static void conflictScenarios() {
        inspectParseTreeForm("QLExamples/typechecker/duplicatelabel.ql", false);
        inspectParseTreeForm("QLExamples/typechecker/duplicateID.ql", false);
        inspectParseTreeForm("QLExamples/typechecker/varnotdeclared.ql", false);
        inspectParseTreeForm("QLExamples/typechecker/invalidcondition.ql", false);

	}


	private static void parseQuestionnaireExample() {
		// TODO Auto-generated method stub
		
		
	}
	
	private static void inspectParseTreeContent(String content) {
		
		System.out.println("");
        System.out.println("Inspecting parse tree from String");
        System.out.println(content);

	      Ql2Lexer lexer = null;
			lexer = new Ql2Lexer( new ANTLRInputStream(content));
	        CommonTokenStream tokens = new CommonTokenStream( lexer );
	        
	        Ql2Parser parser = new Ql2Parser( tokens );
	        ParseTree tree = parser.questionnaire();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
			System.out.println("Finished inspecting parse tree");

	}
	
	private static void inspectParseTree(String path) {
		System.out.println("");
        System.out.println("Inspecting parse tree from File: " + path);
		Ql2Lexer lexer = null;
		try {
			lexer = new Ql2Lexer(CharStreams.fromFileName(path));
			CommonTokenStream tokens2 = new CommonTokenStream( lexer );
			Ql2Parser parser = new Ql2Parser( tokens2 );	
		
		    ParseTree tree = parser.questionnaire();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
	        
	        BaseVisitor<Questionnaire> visitor = new BaseVisitor<Questionnaire>();
	        Questionnaire q = visitor.visit(parser.questionnaire().result);
	        
	        //visitor.getContext()
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		System.out.println("Finished inspecting parse tree");

	}
	
	private static void inspectParseTreeForm(String path, Boolean visual) {
		System.out.println("");
        System.out.println("Inspecting parse tree from File: " + path);
		Ql2Lexer lexer = null;
		try {
			//lexer2 = new Ql2Lexer(CharStreams.fromFileName(path));
			lexer = new Ql2Lexer(new ANTLRFileStream(path));
			CommonTokenStream tokens2 = new CommonTokenStream( lexer );
			Ql2Parser parser = new Ql2Parser( tokens2 );		        
		    ParseTree tree = parser.form();
		    ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
	        
	        if (visual) {
	        		System.out.println(tree.toStringTree());
	        		showTree(tree, parser);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}       
		System.out.println("Finished inspecting parse tree");

	}
	
	private static void inspectParseTreeQuestion(String content) {
		// overloaded for omitting boolean.
		inspectParseTreeQuestion(content, false);
	}
	
	private static void inspectParseTreeQuestion(String content, @Nullable Boolean visual) {
		System.out.println("");
        System.out.println("Inspecting parse tree from String");
        System.out.println(content);

	      Ql2Lexer lexer = null;
			lexer = new Ql2Lexer( new ANTLRInputStream(content));
	        CommonTokenStream tokens = new CommonTokenStream( lexer );
	        
	        Ql2Parser parser = new Ql2Parser( tokens );
	        ParseTree tree = parser.question();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
			System.out.println("Finished inspecting parse tree");
			if (visual) {
				showTree(tree, parser);
			}
	}
	
	private static void inspectConditionsParseTree(String content) {
		inspectConditionsParseTree(content, false);
	}
	
	private static void inspectConditionsParseTree(String content, Boolean visual) {
		System.out.println("");
        System.out.println("Inspecting parse tree from String");
        System.out.println(content);

	    Ql2Lexer lexer = null;
		lexer = new Ql2Lexer( new ANTLRInputStream(content));
	        CommonTokenStream tokens = new CommonTokenStream( lexer );
	        
	        Ql2Parser parser = new Ql2Parser( tokens );
	        ParseTree tree = parser.conditions();
	        ParseTreeWalker walker = new ParseTreeWalker();
	        walker.walk( new Ql2Walker(), tree );
			if (visual) {
				System.out.println(tree.toStringTree(parser));
				showTree(tree, parser);
			}			
			System.out.println("Finished inspecting parse tree");

	}
	
	private static void showTree(Tree tree, Ql2Parser parser) {

        //show AST in GUI
        JFrame frame = new JFrame("Antlr Parse Tree");
        JPanel panel = new JPanel();
        TreeViewer viewr = new TreeViewer(Arrays.asList(
                parser.getRuleNames()),tree);
        viewr.setScale(0.9);//scale a little
        panel.add(viewr);
        JScrollPane scrollpanel = new JScrollPane(panel);
        frame.add(scrollpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setVisible(true);
	}
	
	
	private static void conditionsTesting() {
		//unary
		inspectConditionsParseTree("(!a)");
		inspectConditionsParseTree("(-4)");
		inspectConditionsParseTree("(-b)");

		//binary
    		inspectConditionsParseTree("(2 + 2)");
    		inspectConditionsParseTree("(2 * 2)");
    		inspectConditionsParseTree("(2 - 2)");
    		inspectConditionsParseTree("(2 * 2)");
    		inspectConditionsParseTree("(a > b)");
    		inspectConditionsParseTree("(a => b)"); // -> Error
    		inspectConditionsParseTree("(a >= b)");
    		inspectConditionsParseTree("(a < b)");
    		inspectConditionsParseTree("(a <= b)");
    		inspectConditionsParseTree("(a == b)", false);
    		inspectConditionsParseTree("((a) == (b))", false);
    		inspectConditionsParseTree("(a || (b && c))", false);
    		inspectConditionsParseTree("(a || ((b) && (c)))");
    		inspectConditionsParseTree("(a || ((b <= 6) && (c || (d > 5))))", false);
    		
    		inspectConditionsParseTree("(questionA == questionB)", false); // (identity eq identity)
	}
}

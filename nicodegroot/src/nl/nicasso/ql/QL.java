package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;

import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.statement.Question;
import nl.nicasso.ql.ast.structure.Form;

public class QL {
	
	public final static String DSLFILE = "exampleQuestionnaire";
	
	private QLParser parser;		
	private ParseTree tree;
	
	public static SymbolTable symbolTable;
	
	public QL() {
		// Empty?
	}
	
	public void start() {
		ANTLRInputStream input = readInputDSL();
		
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		parser = new QLParser(tokens);		
		tree = parser.form();
		
		//System.out.println(tree.toStringTree(parser));
		
		symbolTable = new SymbolTable();
        
        // VISITOR PATTERN!
        CreateASTVisitor astVisitor = new CreateASTVisitor();
        Form ast = (Form) tree.accept(astVisitor);
        
        QuestionVisitor questionVisitor = new QuestionVisitor();
        
        ast.accept(questionVisitor);
        
        //ArrayList<Question> questions = questionVisitor.getQuestions();
        //questionVisitor.checkNullPointers();
        
        displayMessages("QuestionVisitor Warnings", questionVisitor.getWarnings());
        displayMessages("QuestionVisitor Errors", questionVisitor.getErrors());
	    
        //displaySymbolTable();
        
        CyclicDependencyVisitor cyclicDependencyVisitor = new CyclicDependencyVisitor();
        
        ast.accept(cyclicDependencyVisitor);
        
        cyclicDependencyVisitor.detectCyclicDependencies();
        
        displayMessages("CyclicDependencyVisitor Warnings", cyclicDependencyVisitor.getWarnings());
        displayMessages("CyclicDependencyVisitor Errors", cyclicDependencyVisitor.getErrors());
        
        TypeCheckerVisitor typeChecker = new TypeCheckerVisitor();
        
        ast.accept(typeChecker);
        
        displayMessages("TypeChecker Warnings", typeChecker.getWarnings());
        displayMessages("TypeChecker Errors", typeChecker.getErrors());
        
        EvaluatorVisitor evaluator = new EvaluatorVisitor();
        // Get all initial values
        ast.accept(evaluator);
        
        // Use values to evaluate expressions
        ast.accept(evaluator);
        
        //displaySymbolTable();
        
        //Gui ex = new Gui();
        //ex.setVisible(true);
        
	}
	
	private void displaySymbolTable() {
		Iterator<Entry<Question, Literal>> it = QL.symbolTable.getSymbols().entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<Question, Literal> pair = it.next();
	        Question key = (Question) pair.getKey();
	        Literal value = (Literal) pair.getValue();
	        System.out.println(key.getId().getValue()+" ("+ key.getType().getType() +")"+ " = " + value.getValue());
	    }
	}
	
	private void displayMessages(String title, ArrayList<String> messages) {
		if (!messages.isEmpty()) {
        	System.out.println("-------------------------------"+title+"--------------------------------------------");
        	for (String message : messages) {
        		System.out.println(message);
        	}
        }
	}
	
	private ANTLRInputStream readInputDSL() {
		File file = new File(DSLFILE);
	    FileInputStream fis;
	    ANTLRInputStream input = null;
	    
		try {
			fis = new FileInputStream(file);
			input = new ANTLRInputStream(fis);
			fis.close();
			return input;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void displayParseTree() {
	    JFrame frame = new JFrame("Antlr Parse Tree");
	    JPanel panel = new JPanel();
	    TreeViewer viewr = new TreeViewer(Arrays.asList(
	            parser.getRuleNames()),tree);
	    viewr.setScale(1);
	    panel.add(viewr);
	    frame.add(panel);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(1200,600);
	    frame.setVisible(true);
	}
	
	public static void main( String[] args) throws Exception {
		System.out.print("LET'S GO!\n");
		
		QL ql = new QL();
		ql.start();
    }

}
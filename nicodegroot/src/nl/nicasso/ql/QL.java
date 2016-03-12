package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.gui.TreeViewer;

import nl.nicasso.ql.antlr.QLLexer;
import nl.nicasso.ql.antlr.QLParser;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.gui.Gui;
import nl.nicasso.ql.gui.MainFrame;
import nl.nicasso.ql.stateTable.StateTable;
import nl.nicasso.ql.symbolTable.SymbolTable;

public class QL {
	
	public final static String DSLFILE = "exampleQuestionnaire";
	
	private QLParser parser;		
	private ParseTree tree;
	
	public void start() {
		ANTLRInputStream input = readInputDSL();
		
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		parser = new QLParser(tokens);
		tree = parser.form();
				
		SymbolTable symbolTable = new SymbolTable();
        
        CreateAST astVisitor = new CreateAST();
        Form ast = (Form) tree.accept(astVisitor);

        CollectIdentifiers collectIdentifiers = new CollectIdentifiers();
        
        QuestionIndexer questionVisitor = new QuestionIndexer(symbolTable, collectIdentifiers);
        ast.accept(questionVisitor, null);
        
        //symbolTable.displaySymbolTable(symbolTable);
        
        displayMessages("QuestionVisitor Warnings", questionVisitor.getWarnings());
        displayMessages("QuestionVisitor Errors", questionVisitor.getErrors());
    
    	TypeChecker typeChecker = new TypeChecker(symbolTable);
    	ast.accept(typeChecker, null);
        
        displayMessages("TypeChecker Warnings", typeChecker.getWarnings());
        displayMessages("TypeChecker Errors", typeChecker.getErrors());
        
        //symbolTable.displaySymbolTable(symbolTable);
        
        StateTable stateTable = new StateTable();
        
        Evaluator evaluator = new Evaluator(stateTable);
        // Get all initial values
        ast.accept(evaluator, null);

        // Use values to evaluate expressions (NOT NEEDED ANYMORE? HUH!)
        //ast.accept(evaluator);
        
        //symbolTable.displaySymbolTable(symbolTable);

        MainFrame main = new MainFrame();
        
        Gui guiVisitor = new Gui(stateTable, main);
        ast.accept(guiVisitor, null);
        //ex.setVisible(true);
	}
	
	private void displayMessages(String title, List<String> messages) {
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
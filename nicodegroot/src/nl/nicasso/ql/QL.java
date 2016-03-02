package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.uva.sea.ql.parser.antlr.QLLexer;
import org.uva.sea.ql.parser.antlr.QLParser;

import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.ast.structure.Form;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;

public class QL {
	
	public final static String DSLFILE = "exampleQuestionnaire";
	
	private QLParser parser;		
	private ParseTree tree;
	
	public QL() {
		// Empty?
	}
	
	public void start() {
		ANTLRInputStream input = readInputDSL();
		
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		parser = new QLParser(tokens);
		tree = parser.form();
				
		SymbolTable symbolTable = new SymbolTable();
        
        CreateASTVisitor astVisitor = new CreateASTVisitor();
        Form ast = (Form) tree.accept(astVisitor);

        QuestionVisitor questionVisitor = new QuestionVisitor(symbolTable);
        ast.accept(questionVisitor);
        
        //displaySymbolTable(symbolTable);
        
        displayMessages("QuestionVisitor Warnings", questionVisitor.getWarnings());
        displayMessages("QuestionVisitor Errors", questionVisitor.getErrors());
        
        //displaySymbolTable(symbolTable);
        
        CyclicDependencyVisitor cyclicDependencyVisitor = new CyclicDependencyVisitor();
        ast.accept(cyclicDependencyVisitor);
        cyclicDependencyVisitor.detectCyclicDependencies();
        
        displayMessages("CyclicDependencyVisitor Warnings", cyclicDependencyVisitor.getWarnings());
        displayMessages("CyclicDependencyVisitor Errors", cyclicDependencyVisitor.getErrors());
        
        //displaySymbolTable(symbolTable);
    
    	TypeCheckerVisitor typeChecker = new TypeCheckerVisitor(symbolTable);
    	ast.accept(typeChecker);
        
        displayMessages("TypeChecker Warnings", typeChecker.getWarnings());
        displayMessages("TypeChecker Errors", typeChecker.getErrors());

        
        EvaluatorVisitor evaluator = new EvaluatorVisitor(symbolTable);
        // Get all initial values
        ast.accept(evaluator);
        
        // Use values to evaluate expressions
        ast.accept(evaluator);
        
        displaySymbolTable(symbolTable);
        
        //Gui ex = new Gui();
        //ex.setVisible(true);

	}
	
	public void displaySymbolTable(SymbolTable symbolTable) {
		Iterator<Entry<Identifier, SymbolTableEntry>> it = symbolTable.getSymbols().entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<Identifier, SymbolTableEntry> pair = it.next();
	    	Identifier key = (Identifier) pair.getKey();
	        SymbolTableEntry value = (SymbolTableEntry) pair.getValue();
	        
	        String realValue;
	        if (value.getValue() == null) {
	        	realValue = "undefined";
	        } else {
	        	realValue = value.getValue().getValue().toString();
	        }
	        System.out.println(key.getValue()+" ("+ value.getType().getType() +")"+ " = " + realValue);
	    }
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
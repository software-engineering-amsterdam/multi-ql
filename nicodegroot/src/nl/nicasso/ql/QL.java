package nl.nicasso.ql;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import nl.nicasso.ql.antlr.QLLexer;
import nl.nicasso.ql.antlr.QLParser;
import nl.nicasso.ql.ast.CreateAbstractSyntaxTree;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.gui.Gui;
import nl.nicasso.ql.gui.MainFrame;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.semanticAnalysis.SemanticAnalysis;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;

public class QL {
	
	public final static String DSLFILE = "exampleQuestionnaire";
	
	public void start() {
		ANTLRInputStream input = readInputDSL();
		
		QLLexer lexer = new QLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		QLParser parser = new QLParser(tokens);		
		ParseTree parseTree = parser.form();
				
		SymbolTable symbolTable = new SymbolTable();
		StateTable stateTable = new StateTable();
        
        CreateAbstractSyntaxTree astVisitor = new CreateAbstractSyntaxTree(parseTree);
        Form ast = astVisitor.getAbstractSyntaxTree();
        
        SemanticAnalysis semantics = new SemanticAnalysis(ast, symbolTable, stateTable);        

        MainFrame window = new MainFrame(stateTable, semantics.getMessages());
        
        if (!semantics.containsErrors()) {
	        @SuppressWarnings("unused")
			Gui userInterface = new Gui(ast, stateTable, window);
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
	
	public static void main( String[] args) throws Exception {
		System.out.print("LET'S GO!\n");
		
		QL ql = new QL();
		ql.start();
    }

}
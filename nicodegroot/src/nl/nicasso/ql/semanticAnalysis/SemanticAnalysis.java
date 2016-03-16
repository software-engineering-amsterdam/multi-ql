package nl.nicasso.ql.semanticAnalysis;

import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;

public class SemanticAnalysis {

	private Form ast;
	private SymbolTable symbolTable;
	private StateTable stateTable;
	private MessageHandler messages;
	
	public SemanticAnalysis(Form ast, SymbolTable symbolTable, StateTable stateTable) {
		this.ast = ast;
		this.symbolTable = symbolTable;
		this.stateTable = stateTable;
		
		messages = new MessageHandler();
	}
	
	public void initializeAnalysis() {        
        QuestionIndexer questionVisitor = new QuestionIndexer(symbolTable, stateTable, messages);
        ast.accept(questionVisitor, null);
        
        messages.displayMessages();
        
    	TypeChecker typeChecker = new TypeChecker(symbolTable, messages);
    	ast.accept(typeChecker, null);
    	
    	
	}
	
}
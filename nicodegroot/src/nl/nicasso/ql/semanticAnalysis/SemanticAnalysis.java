package nl.nicasso.ql.semanticAnalysis;

import java.util.List;

import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.semanticAnalysis.messageHandling.Message;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;

public class SemanticAnalysis {

	private MessageHandler messageHandler;
	
	public SemanticAnalysis(Form ast, SymbolTable symbolTable, StateTable stateTable) {
		messageHandler = new MessageHandler();
		
		QuestionIndexer questionVisitor = new QuestionIndexer(symbolTable, stateTable, messageHandler);
        ast.accept(questionVisitor, null);
        
    	TypeChecker typeChecker = new TypeChecker(symbolTable, messageHandler);
    	ast.accept(typeChecker, null);
	}
	
	public List<Message> getMessages() {
		return messageHandler.getAllMessages();
	}
	
	public boolean containsErrors() {
		return messageHandler.containsErrors();
	}
	
}
package nl.nicasso.ql.semanticAnalysis;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfElseStatement;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Question;
import nl.nicasso.ql.ast.nodes.statements.Statement;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTableEntry;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.DuplicateIdentifier;
import nl.nicasso.ql.semanticAnalysis.messageHandling.errors.NonExistantQuestion;
import nl.nicasso.ql.semanticAnalysis.messageHandling.warnings.DuplicateIdentifierSameType;
import nl.nicasso.ql.semanticAnalysis.messageHandling.warnings.DuplicateLabels;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class QuestionIndexer implements StructureVisitor<Identifier, Void>, StatementVisitor<Identifier, Void> {
	
	private SymbolTable symbolTable;
	private StateTable stateTable;
	private MessageHandler messageHandler;
	
	private Set<Identifier> identifiers;
	private Set<String> labels;
	private CollectIdentifiers collectIdentifiers;

	public QuestionIndexer(SymbolTable symbolTable, StateTable stateTable, MessageHandler messageHandler) {		
		this.symbolTable = symbolTable;
		this.stateTable = stateTable;
		this.messageHandler = messageHandler;
		
		this.identifiers = new HashSet<Identifier>();		
		this.labels = new HashSet<String>();		
		this.collectIdentifiers = new CollectIdentifiers();
	}

	@Override
	public Identifier visit(Form value, Void ignore) {
		value.getBlock().accept(this, null);
		
		// @TODO Move the accept method to the constructor of this class, and call this one right after.
		checkUndefinedIdentifiers();
		
		return null;
	}

	@Override
	public Identifier visit(Block value, Void ignore) {
		for (Statement currentStatement : value.getStatements()) {
			currentStatement.accept(this, ignore);
		}

		return null;
	}

	@Override
	public Identifier visit(Question value, Void context) {
		if (checkIfUniqueQuestion(value)) {
			symbolTable.addSymbol(value.getIdentifier(), new SymbolTableEntry(value.getType()));
			stateTable.addState(value.getIdentifier(), new StateTableEntry(value.getType().getDefaultValue()));
			labels.add(value.getLabel());
		}
				
		return null;
	}

	@Override
	public Identifier visit(ComputedQuestion value, Void context) {
		if (checkIfUniqueQuestion(value)) {
			symbolTable.addSymbol(value.getIdentifier(), new SymbolTableEntry(value.getType()));
			stateTable.addState(value.getIdentifier(), new StateTableEntry(value.getType().getDefaultValue()));
			labels.add(value.getLabel());
		}
		
		value.getExpr().accept(collectIdentifiers);
		identifiers.addAll(collectIdentifiers.getIdentifiers());
		return null;
	}

	@Override
	public Identifier visit(IfStatement value, Void context) {
		value.getExpr().accept(collectIdentifiers);
		identifiers.addAll(collectIdentifiers.getIdentifiers());
		
		value.getBlock_if().accept(this, null);
		return null;
	}

	@Override
	public Identifier visit(IfElseStatement value, Void context) {
		value.getExpr().accept(collectIdentifiers);
		identifiers.addAll(collectIdentifiers.getIdentifiers());
		
		value.getBlock_if().accept(this, null);
		value.getBlock_else().accept(this, null);
		return null;
	}
	
	public void checkUndefinedIdentifiers() {
		for (Identifier id : identifiers) {
			if (!checkExistanceIdentifier(id)) {
				messageHandler.addErrorMessage(new NonExistantQuestion(id));
			}
		}
	}

	public boolean checkIfUniqueQuestion(Question question) {
		checkExistanceLabel(question);
		
		if (checkExistanceIdentifier(question.getIdentifier())) {
			if (symbolTable.getEntryType(question.getIdentifier()).equals(question.getType())) {
				messageHandler.addWarningMessage(new DuplicateIdentifierSameType(question.getIdentifier()));
			} else {
				messageHandler.addErrorMessage(new DuplicateIdentifier(question.getIdentifier()));
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkExistanceIdentifier(Identifier identifier) {
		Iterator<Entry<Identifier, SymbolTableEntry>> iterator = symbolTable.getSymbols().entrySet().iterator();
	    while (iterator.hasNext()) {
	    	Entry<Identifier, SymbolTableEntry> pair = iterator.next();
	    	Identifier pairKey = (Identifier) pair.getKey();

	    	if (pairKey.getIdentifier().equals(identifier.getIdentifier())) {
				return true;
			}

	    }

		return false;
	}
	
	private boolean checkExistanceLabel(Question question) {
		for (String currentLabel : labels) {
			if (currentLabel.equals(question.getLabel())) {
				messageHandler.addWarningMessage(new DuplicateLabels(question.getIdentifier(), question.getLabel()));
				return true;
			}
		}

		return false;
	}
	
	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}
		
}
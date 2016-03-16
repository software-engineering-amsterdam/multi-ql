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
	
	private Set<Identifier> identifiers;
	private Set<String> questionLabels;
	private SymbolTable symbolTable;
	private StateTable stateTable;
	
	private CollectIdentifiers collectIdentifiers;
	private MessageHandler messages;

	public QuestionIndexer(SymbolTable symbolTable, StateTable stateTable, MessageHandler messages) {		
		this.symbolTable = symbolTable;
		this.stateTable = stateTable;
		this.messages = messages;
		
		this.identifiers = new HashSet<Identifier>();		
		this.questionLabels = new HashSet<String>();		
		this.collectIdentifiers = new CollectIdentifiers();
	}

	@Override
	public Identifier visit(Form value, Void ignore) {
		value.getBlock().accept(this, null);
		
		checkUndefinedIdentifiers();
		
		return null;
	}

	@Override
	public Identifier visit(Block value, Void ignore) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this, ignore);
		}

		return null;
	}

	@Override
	public Identifier visit(Question value, Void context) {
		if (checkIfUniqueQuestion(value)) {
			symbolTable.addSymbol(value.getId(), new SymbolTableEntry(value.getType()));
			stateTable.addState(value.getId(), new StateTableEntry(value.getType().getDefaultValue()));
			questionLabels.add(value.getLabel());
		}
				
		return null;
	}

	@Override
	public Identifier visit(ComputedQuestion value, Void context) {
		if (checkIfUniqueQuestion(value)) {
			symbolTable.addSymbol(value.getId(), new SymbolTableEntry(value.getType()));
			stateTable.addState(value.getId(), new StateTableEntry(value.getType().getDefaultValue()));
			questionLabels.add(value.getLabel());
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
				messages.addMessage(new NonExistantQuestion(id));
			}
		}
	}

	public boolean checkIfUniqueQuestion(Question q) {
		checkExistanceLabel(q);
		
		if (checkExistanceIdentifier(q.getId())) {
			if (symbolTable.getEntryType(q.getId()).equals(q.getType())) {
				messages.addMessage(new DuplicateIdentifierSameType(q.getId()));
			} else {
				messages.addMessage(new DuplicateIdentifier(q.getId()));
				return false;
			}
		}
		
		return true;
	}
	
	private boolean checkExistanceIdentifier(Identifier value) {
		Iterator<Entry<Identifier, SymbolTableEntry>> it = symbolTable.getSymbols().entrySet().iterator();
	    while (it.hasNext()) {
	    	Entry<Identifier, SymbolTableEntry> pair = it.next();
	    	Identifier key = (Identifier) pair.getKey();

	    	if (key.getValue().equals(value.getValue())) {
				return true;
			}

	    }

		return false;
	}
	
	private boolean checkExistanceLabel(Question question) {
		for (String cur : questionLabels) {
			if (cur.equals(question.getLabel())) {
				messages.addMessage(new DuplicateLabels(question.getId(), question.getLabel()));
				return true;
			}
		}

		return false;
	}
	
	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}
		
}
package nl.nicasso.ql.semanticAnalysis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class QuestionIndexer implements StructureVisitor<Identifier, Void>, StatementVisitor<Identifier, Void> {
	
	private List<String> warnings;
	private List<String> errors;
	
	private Set<Identifier> identifiers;
	private Set<String> questionLabels;
	private SymbolTable symbolTable;
	private StateTable stateTable;
	
	private CollectIdentifiers collectIdentifiers;

	public QuestionIndexer(SymbolTable symbolTable, StateTable stateTable, CollectIdentifiers collectIdentifiers) {		
		identifiers = new HashSet<Identifier>();
		
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		
		questionLabels = new HashSet<String>();
		
		this.symbolTable = symbolTable;
		this.stateTable = stateTable;
		this.collectIdentifiers = collectIdentifiers;
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
				errors.add("The identifier " + id.getValue() + " does not exist.");
			}
		}
	}

	public boolean checkIfUniqueQuestion(Question q) {
		checkExistanceLabel(q.getLabel());
		
		if (checkExistanceIdentifier(q.getId())) {
			if (symbolTable.getEntryType(q.getId()).equals(q.getType())) {
				warnings.add("Warning: The identifier " + q.getId().getValue() + " already exist. "+q.getLocation());
			} else {
				errors.add("Error: The identifier " + q.getId().getValue() + " already exist.");
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
	
	private boolean checkExistanceLabel(String value) {
		for (String cur : questionLabels) {
			if (cur.equals(value)) {
				warnings.add("Warning: The label " + value + " already exist.");
				return true;
			}
		}

		return false;
	}
	
	public List<String> getErrors() {
		return errors;
	}
	
	public List<String> getWarnings() {
		return warnings;
	}

	public Set<Identifier> getIdentifiers() {
		return identifiers;
	}
		
}
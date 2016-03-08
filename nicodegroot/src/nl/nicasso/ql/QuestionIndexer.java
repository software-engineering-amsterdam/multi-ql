package nl.nicasso.ql;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.ast.statements.ComputedQuestion;
import nl.nicasso.ql.ast.statements.IfElseStatement;
import nl.nicasso.ql.ast.statements.IfStatement;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.ast.statements.Statement;
import nl.nicasso.ql.ast.structures.Block;
import nl.nicasso.ql.ast.structures.Form;
import nl.nicasso.ql.symbolTable.SymbolTable;
import nl.nicasso.ql.symbolTable.SymbolTableEntry;
import nl.nicasso.ql.visitors.StatementVisitor;
import nl.nicasso.ql.visitors.StructureVisitor;

public class QuestionIndexer implements StructureVisitor<Identifier>, StatementVisitor<Identifier, Void> {
	
	private List<String> warnings;
	private List<String> errors;
	
	private Set<Identifier> identifiers;
	private Set<String> questionLabels;
	private SymbolTable symbolTable;
	
	private CollectIdentifiers collectIdentifiers;

	QuestionIndexer(SymbolTable symbolTable, CollectIdentifiers collectIdentifiers) {		
		identifiers = new HashSet<Identifier>();
		
		warnings = new ArrayList<String>();
		errors = new ArrayList<String>();
		
		questionLabels = new HashSet<String>();
		
		this.symbolTable = symbolTable;
		this.collectIdentifiers = collectIdentifiers;
	}

	@Override
	public Identifier visit(Form value) {
		value.getBlock().accept(this);
		
		checkUndefinedIdentifiers();
		
		return null;
	}

	@Override
	public Identifier visit(Block value) {
		for (Statement cur : value.getStatements()) {
			cur.accept(this);
		}

		return null;
	}

	@Override
	public Identifier visit(Question value, Void context) {
		if (checkIfUniqueQuestion(value)) {
			symbolTable.addSymbol(value.getId(), new SymbolTableEntry(value.getType()));
			questionLabels.add(value.getLabel());
		}
				
		return null;
	}

	@Override
	public Identifier visit(ComputedQuestion value, Void context) {
		if (checkIfUniqueQuestion(value)) {
			symbolTable.addSymbol(value.getId(), new SymbolTableEntry(value.getType()));
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
		
		value.getBlock_if().accept(this);
		return null;
	}

	@Override
	public Identifier visit(IfElseStatement value, Void context) {
		value.getExpr().accept(collectIdentifiers);
		identifiers.addAll(collectIdentifiers.getIdentifiers());
		
		value.getBlock_if().accept(this);
		value.getBlock_else().accept(this);
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
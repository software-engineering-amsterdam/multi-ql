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

public class QuestionSemantics implements StructureVisitor<Identifier, Void>, StatementVisitor<Identifier, Void> {

	private SymbolTable symbolTable;
	private StateTable stateTable;
	private MessageHandler messageHandler;

	private Set<Identifier> identifiers;
	private Set<String> labels;
	private CollectIdentifiers collectIdentifiers;

	public QuestionSemantics(Form ast, SymbolTable symbolTable, StateTable stateTable, MessageHandler messageHandler) {
		this.symbolTable = symbolTable;
		this.stateTable = stateTable;
		this.messageHandler = messageHandler;

		this.identifiers = new HashSet<Identifier>();
		this.labels = new HashSet<String>();
		this.collectIdentifiers = new CollectIdentifiers();

		ast.accept(this, null);

		checkForUndefinedIdentifiers();
	}

	@Override
	public Identifier visit(Form structure, Void ignore) {
		structure.getBlock().accept(this, null);
		return null;
	}

	@Override
	public Identifier visit(Block structure, Void ignore) {
		for (Statement currentStatement : structure.getStatements()) {
			currentStatement.accept(this, ignore);
		}
		return null;
	}

	@Override
	public Identifier visit(Question statement, Void context) {
		if (checkIfUniqueQuestion(statement)) {
			symbolTable.addSymbol(statement.getIdentifier(), new SymbolTableEntry(statement.getType()));
			stateTable.add(statement.getIdentifier(), new StateTableEntry(statement.getType().getDefaultValue()));
			labels.add(statement.getLabel());
		}
		return null;
	}

	@Override
	public Identifier visit(ComputedQuestion statement, Void context) {
		if (checkIfUniqueQuestion(statement)) {
			symbolTable.addSymbol(statement.getIdentifier(), new SymbolTableEntry(statement.getType()));
			stateTable.add(statement.getIdentifier(), new StateTableEntry(statement.getType().getDefaultValue()));
			labels.add(statement.getLabel());
		}

		statement.getExpression().accept(collectIdentifiers, null);
		identifiers.addAll(collectIdentifiers.getIdentifiers());
		return null;
	}

	@Override
	public Identifier visit(IfStatement statement, Void context) {
		statement.getExpression().accept(collectIdentifiers, null);
		identifiers.addAll(collectIdentifiers.getIdentifiers());

		statement.getBlockIf().accept(this, null);
		return null;
	}

	@Override
	public Identifier visit(IfElseStatement statement, Void context) {
		statement.getExpression().accept(collectIdentifiers, null);
		identifiers.addAll(collectIdentifiers.getIdentifiers());

		statement.getBlockIf().accept(this, null);
		statement.getBlockElse().accept(this, null);
		return null;
	}

	public void checkForUndefinedIdentifiers() {
		for (Identifier identifier : identifiers) {
			if (!checkForDuplicateIdentifier(identifier)) {
				messageHandler.addErrorMessage(new NonExistantQuestion(identifier));
			}
		}
	}

	public boolean checkIfUniqueQuestion(Question question) {
		checkForDuplicateLabel(question);

		if (checkForDuplicateIdentifier(question.getIdentifier())) {
			if (symbolTable.getEntryType(question.getIdentifier()).equals(question.getType())) {
				messageHandler.addWarningMessage(new DuplicateIdentifierSameType(question.getIdentifier()));
			} else {
				messageHandler.addErrorMessage(new DuplicateIdentifier(question.getIdentifier()));
				return false;
			}
		}

		return true;
	}

	private boolean checkForDuplicateIdentifier(Identifier identifier) {
		Iterator<Entry<Identifier, SymbolTableEntry>> iterator = symbolTable.getIterator();

		while (iterator.hasNext()) {
			Entry<Identifier, SymbolTableEntry> pair = iterator.next();
			Identifier pairKey = (Identifier) pair.getKey();

			if (pairKey.equals(identifier)) {
				return true;
			}

		}

		return false;
	}

	private boolean checkForDuplicateLabel(Question question) {
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
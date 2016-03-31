package test.java.ast.typeChecker;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.ast.nodes.literals.StringLiteral;
import nl.nicasso.ql.ast.nodes.statements.ComputedQuestion;
import nl.nicasso.ql.ast.nodes.statements.IfStatement;
import nl.nicasso.ql.ast.nodes.statements.Statement;
import nl.nicasso.ql.ast.nodes.structures.Block;
import nl.nicasso.ql.ast.nodes.structures.Form;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.gui.evaluator.stateTable.StateTable;
import nl.nicasso.ql.semanticAnalysis.QuestionSemantics;
import nl.nicasso.ql.semanticAnalysis.TypeChecker;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTableEntry;

public class Statements {

	private final CodeLocation testLocation = new CodeLocation(1, 2);

	@Test
	public void testCyclomaticDependency() {
		ComputedQuestion question1 = new ComputedQuestion(new Identifier("Q1", testLocation), "Question 1",
				new IntegerType(), new Identifier("Q2", testLocation), testLocation);
		ComputedQuestion question2 = new ComputedQuestion(new Identifier("Q2", testLocation), "Question 2",
				new IntegerType(), new Identifier("Q1", testLocation), testLocation);

		List<Statement> statements = new ArrayList<>();
		statements.add(question1);
		statements.add(question2);

		Form form = createForm(statements);
		MessageHandler messageHandler = new MessageHandler();

		SymbolTable symbolTable = new SymbolTable();
		symbolTable.addSymbol(new Identifier("Q1", testLocation), new SymbolTableEntry(new IntegerType()));
		symbolTable.addSymbol(new Identifier("Q2", testLocation), new SymbolTableEntry(new IntegerType()));

		new TypeChecker(form, symbolTable, messageHandler);

		Assert.assertEquals(messageHandler.getErrorMessages().get(0).getMessage(),
				"Cyclomatic dependency detected. Line: 1 Column: 2");
	}

	@Test
	public void testDuplicateIdentifiersSameType() {
		ComputedQuestion question1 = new ComputedQuestion(new Identifier("Q1", testLocation), "Question 1",
				new IntegerType(), new Identifier("Q1", testLocation), testLocation);
		ComputedQuestion question2 = new ComputedQuestion(new Identifier("Q1", testLocation), "Question 2",
				new IntegerType(), new Identifier("Q1", testLocation), testLocation);

		List<Statement> statements = new ArrayList<>();
		statements.add(question1);
		statements.add(question2);

		Form form = createForm(statements);

		MessageHandler messageHandler = new MessageHandler();

		new QuestionSemantics(form, new SymbolTable(), new StateTable(), messageHandler);

		Assert.assertEquals(messageHandler.getWarningMessages().get(0).getMessage(),
				"\"Q1\" already exists with the same type. Line: 1 Column: 2");
	}

	@Test
	public void testDuplicateIdentifiersDifferentType() {
		ComputedQuestion question1 = new ComputedQuestion(new Identifier("Q1", testLocation), "Question 1",
				new IntegerType(), new Identifier("Q1", testLocation), testLocation);
		ComputedQuestion question2 = new ComputedQuestion(new Identifier("Q1", testLocation), "Question 2",
				new MoneyType(), new Identifier("Q1", testLocation), testLocation);

		List<Statement> statements = new ArrayList<>();
		statements.add(question1);
		statements.add(question2);

		Form form = createForm(statements);

		MessageHandler messageHandler = new MessageHandler();

		new QuestionSemantics(form, new SymbolTable(), new StateTable(), messageHandler);

		Assert.assertEquals(messageHandler.getErrorMessages().get(0).getMessage(),
				"\"Q1\" already exists with a different type. Line: 1 Column: 2");
	}

	@Test
	public void testUndefinedIdentifier() {
		ComputedQuestion question1 = new ComputedQuestion(new Identifier("Q1", testLocation), "Question 1",
				new IntegerType(), new Identifier("Q2", testLocation), testLocation);

		List<Statement> statements = new ArrayList<>();
		statements.add(question1);

		Form form = createForm(statements);

		MessageHandler messageHandler = new MessageHandler();

		new QuestionSemantics(form, new SymbolTable(), new StateTable(), messageHandler);

		Assert.assertEquals(messageHandler.getErrorMessages().get(0).getMessage(),
				"Identifer \"Q2\" does not exist. Line: 1 Column: 2");
	}

	@Test
	public void testIfStatementExpression() {
		IfStatement ifStatement = new IfStatement(new StringLiteral("Test"),
				new Block(new ArrayList<Statement>(), testLocation), testLocation);

		List<Statement> statements = new ArrayList<>();
		statements.add(ifStatement);

		Form form = createForm(statements);

		MessageHandler messageHandler = new MessageHandler();

		new TypeChecker(form, new SymbolTable(), messageHandler);

		Assert.assertEquals(messageHandler.getErrorMessages().get(0).getMessage(),
				"The types in the expression are not compatible, a Boolean type is expected but not given. Line: 1 Column: 2");
	}

	private Form createForm(List<Statement> statements) {
		Block block = new Block(statements, testLocation);
		return new Form(new Identifier("FormId", testLocation), block, testLocation);
	}

}

package ast.typechecker.test;

import nl.uva.ql.ast.Box;
import nl.uva.ql.ast.Form;
import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.Subtraction;
import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.statement.ComputedQuestion;
import nl.uva.ql.ast.statement.IfStatement;
import nl.uva.ql.ast.statement.Question;
import nl.uva.ql.ast.statement.Statement;
import nl.uva.ql.ast.type.BooleanType;
import nl.uva.ql.ast.type.IntegerType;
import nl.uva.ql.ast.type.MoneyType;
import nl.uva.ql.typechecker.TypeChecker;
import nl.uva.ql.typechecker.errorhandler.ErrorHandler;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StatementTest {
	@Test
	public void testUndeclaredQuestionIdentifier() {
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		ComputedQuestion question = new ComputedQuestion(new Identifier("hasSoldHouse", 2), "Have you sold your house last year?", new BooleanType(), new Identifier("soldLastYear", 2), 2);
		Form form = createForm(question);
		
		typeChecker.visit(form);
		Assert.assertEquals(errorHandler.getErrors().get(0).getMessage(), "Error at line 2: Reference to undeclared question identifer 'soldLastYear'");
	}
	
	@Test
	public void testDuplicateLabelWarning() {
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		Question question1 = new Question(new Identifier("hasSoldHouse", 2), "Have you sold your house?", new BooleanType(), 2);
		Question question2 = new Question(new Identifier("hello", 3), "have you sold your house?", new BooleanType(), 3);
		Form form = createForm(question1, question2);
		
		typeChecker.visit(form);
		Assert.assertEquals(errorHandler.getWarnings().get(0).getMessage(), "Warning at line 3: duplicate question label: have you sold your house?");
	}
	
	@Test
	public void testDuplicateDeclaration() {
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		Question question1 = new Question(new Identifier("hasSoldHouse", 2), "How many houses have you sold?", new IntegerType(), 2);
		Question question2 = new Question(new Identifier("hasSoldHouse", 3), "have you sold your house?", new BooleanType(), 3);
		Form form = createForm(question1, question2);
		
		typeChecker.visit(form);
		Assert.assertEquals(errorHandler.getErrors().get(0).getMessage(), "Duplicate declaration error at line 3: Identifier 'hasSoldHouse' has already been declared, with type 'Integer'");
	}
	
	@Test
	public void testIfStatementWithNonBooleanCondition() {
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		IfStatement ifStatement = new IfStatement(new IntegerLiteral(4, 2), new Box(2, new LinkedList<Statement>()), 2);
		Form form = createForm(ifStatement);
		
		typeChecker.visit(form);
		Assert.assertEquals(errorHandler.getErrors().get(0).getMessage(), "Type missmatch error at line 2: expected type for 'If condition' is 'Boolean'"); 
	}
	
	@Test
	public void testComputedQuestionTypeMissMatch() {
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		Expression expression = new Subtraction(new IntegerLiteral(4, 6), new IntegerLiteral(2, 6), 6);
		ComputedQuestion question = new ComputedQuestion(new Identifier("hasSoldHouse", 6), "Have you sold your house?", new BooleanType(), expression, 6);
		Form form = createForm(question);
		
		typeChecker.visit(form);
		Assert.assertEquals(errorHandler.getErrors().get(0).getMessage(), "Type missmatch error at line 6: expected type for 'Computed Question Expression' is 'Boolean'");
	}
	
	@Test
	public void testCyclicDependency() {
		ErrorHandler errorHandler = new ErrorHandler();
		TypeChecker typeChecker = new TypeChecker(errorHandler);
		
		Question question1 = new Question(new Identifier("sellingPrice", 2), "price for selling the house", new MoneyType(), 2);
		
		Expression offerPriceExpr = new Subtraction(new Identifier("sellingPrice", 3), new IntegerLiteral(1000, 3), 3);
		ComputedQuestion question2 = new ComputedQuestion(new Identifier("offeredPrice", 3), "offer of the customer to buy the house", new MoneyType(), offerPriceExpr, 3);
		
		Expression sellingPriceExpr = new Addition(new Identifier("offeredPrice", 4), new IntegerLiteral(500, 4), 4);
		ComputedQuestion question3 = new ComputedQuestion(new Identifier("sellingPrice", 4), "final selling price", new MoneyType(), sellingPriceExpr, 4);
		Form form = createForm(question1, question2, question3);
		
		typeChecker.visit(form);
		Assert.assertEquals(errorHandler.getErrors().get(0).getMessage(), "There is cyclic dependency between two identifiers: 'sellingPrice' and 'offeredPrice'");
	}
	
	private Form createForm(Statement... statements) {
		List<Statement> testStatements = new LinkedList<>();
		for (Statement statement: statements) {
			testStatements.add(statement);
		}
		Box box = new Box(2, testStatements);
		return new Form(new Identifier("test form", 1), box, 1);
	}
}

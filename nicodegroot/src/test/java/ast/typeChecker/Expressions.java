package test.java.ast.typeChecker;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nl.nicasso.ql.ast.nodes.expressions.additive.Addition;
import nl.nicasso.ql.ast.nodes.expressions.additive.Subtraction;
import nl.nicasso.ql.ast.nodes.expressions.conditional.And;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Not;
import nl.nicasso.ql.ast.nodes.expressions.conditional.Or;
import nl.nicasso.ql.ast.nodes.expressions.equality.Equal;
import nl.nicasso.ql.ast.nodes.expressions.equality.NotEqual;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Division;
import nl.nicasso.ql.ast.nodes.expressions.multiplicative.Multiplication;
import nl.nicasso.ql.ast.nodes.literals.BooleanLiteral;
import nl.nicasso.ql.ast.nodes.literals.IntegerLiteral;
import nl.nicasso.ql.ast.nodes.literals.MoneyLiteral;
import nl.nicasso.ql.ast.nodes.literals.StringLiteral;
import nl.nicasso.ql.ast.nodes.types.BooleanType;
import nl.nicasso.ql.ast.nodes.types.IntegerType;
import nl.nicasso.ql.ast.nodes.types.MoneyType;
import nl.nicasso.ql.ast.nodes.types.StringType;
import nl.nicasso.ql.ast.nodes.types.Type;
import nl.nicasso.ql.semanticAnalysis.TypeChecker;
import nl.nicasso.ql.semanticAnalysis.messageHandling.MessageHandler;
import nl.nicasso.ql.semanticAnalysis.symbolTable.SymbolTable;

public class Expressions {

	private TypeChecker typeChecker;

	private final IntegerLiteral testInteger = new IntegerLiteral(1);
	private final MoneyLiteral testMoney = new MoneyLiteral(BigDecimal.valueOf(1.00));
	private final BooleanLiteral testBoolean = new BooleanLiteral(true);
	private final StringLiteral testString = new StringLiteral("a");

	@Before
	public void setup() {
		typeChecker = new TypeChecker(new SymbolTable(), new MessageHandler());
	}

	@Test
	public void testInteger() {
		IntegerLiteral integerLiteral = testInteger;
		Type type = typeChecker.visit(integerLiteral, null);
		Assert.assertEquals(type, new IntegerType());
	}

	@Test
	public void testMoney() {
		MoneyLiteral moneyLiteral = testMoney;
		Type type = typeChecker.visit(moneyLiteral, null);
		Assert.assertEquals(type, new MoneyType());
	}

	@Test
	public void testString() {
		StringLiteral stringLiteral = testString;
		Type type = typeChecker.visit(stringLiteral, null);
		Assert.assertEquals(type, new StringType());
	}

	@Test
	public void testBoolean() {
		BooleanLiteral booleanLiteral = testBoolean;
		Type type = typeChecker.visit(booleanLiteral, null);
		Assert.assertEquals(type, new BooleanType());
	}

	@Test
	public void testAdditionWithInteger() {
		Addition addition = new Addition(testInteger, testInteger, null);
		Type type = typeChecker.visit(addition, null);
		Assert.assertEquals(type, new IntegerType());
	}

	@Test
	public void testAdditionWithMoney() {
		Addition addition = new Addition(testMoney, testMoney, null);
		Type type = typeChecker.visit(addition, null);
		Assert.assertEquals(type, new MoneyType());
	}

	@Test
	public void testSubtractionWithInteger() {
		Subtraction subtraction = new Subtraction(testInteger, testInteger, null);
		Type type = typeChecker.visit(subtraction, null);
		Assert.assertEquals(type, new IntegerType());
	}

	@Test
	public void testSubtractionWithMoney() {
		Subtraction subtraction = new Subtraction(testMoney, testMoney, null);
		Type type = typeChecker.visit(subtraction, null);
		Assert.assertEquals(type, new MoneyType());
	}

	@Test
	public void testNot() {
		Not negation = new Not(testBoolean, null);
		Type type = typeChecker.visit(negation, null);
		Assert.assertEquals(type, new BooleanType());
	}

	@Test
	public void testAnd() {
		And and = new And(testBoolean, testBoolean, null);
		Type type = typeChecker.visit(and, null);
		Assert.assertEquals(type, new BooleanType());
	}

	@Test
	public void testOr() {
		Or or = new Or(testBoolean, testBoolean, null);
		Type type = typeChecker.visit(or, null);
		Assert.assertEquals(type, new BooleanType());
	}

	@Test
	public void testEqual() {
		Equal equal = new Equal(testBoolean, testBoolean, null);
		Type type = typeChecker.visit(equal, null);
		Assert.assertEquals(type, new BooleanType());
	}

	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(testBoolean, testBoolean, null);
		Type type = typeChecker.visit(notEqual, null);
		Assert.assertEquals(type, new BooleanType());
	}

	@Test
	public void testDivisionWithInteger() {
		Division division = new Division(testInteger, testInteger, null);
		Type type = typeChecker.visit(division, null);
		Assert.assertEquals(type, new IntegerType());
	}

	@Test
	public void testDivisionWithIntegerAndMoney() {
		Division division = new Division(testMoney, testInteger, null);
		Type type = typeChecker.visit(division, null);
		Assert.assertEquals(type, new MoneyType());
	}

	@Test
	public void testMultiplicationWithInteger() {
		Multiplication multiplication = new Multiplication(testInteger, testInteger, null);
		Type type = typeChecker.visit(multiplication, null);
		Assert.assertEquals(type, new IntegerType());
	}

	@Test
	public void testMultiplicationWitIntegerAndhMoney() {
		Multiplication multiplication = new Multiplication(testMoney, testInteger, null);
		Type type = typeChecker.visit(multiplication, null);
		Assert.assertEquals(type, new MoneyType());
	}
}

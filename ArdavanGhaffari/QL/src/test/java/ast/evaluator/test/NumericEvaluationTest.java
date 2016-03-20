package ast.evaluator.test;

import java.math.BigDecimal;

import nl.uva.ql.ast.expression.Expression;
import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.Division;
import nl.uva.ql.ast.expression.binaryexpression.Equal;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThan;
import nl.uva.ql.ast.expression.binaryexpression.GreaterThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.LessThan;
import nl.uva.ql.ast.expression.binaryexpression.LessThanEqual;
import nl.uva.ql.ast.expression.binaryexpression.Multiplication;
import nl.uva.ql.ast.expression.binaryexpression.NotEqual;
import nl.uva.ql.ast.expression.binaryexpression.Subtraction;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.literal.MoneyLiteral;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.IntegerValue;
import nl.uva.ql.evaluator.value.MoneyValue;
import nl.uva.ql.evaluator.value.Value;

import org.junit.Assert;
import org.junit.Test;

public class NumericEvaluationTest {
	Evaluator evaluator = new Evaluator();
	
	@Test
	public void testAdd() {
		// test for money operands
		Addition addition = new Addition(new MoneyLiteral(new BigDecimal(25.55), 3), new MoneyLiteral(new BigDecimal(125.55), 3), 3);
		Value result = evaluator.evaluate(addition);
		MoneyValue expectedValue = new MoneyValue(new BigDecimal(151.10));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());

		// test for money and integer operands
		addition = new Addition(new IntegerLiteral(100, 3), new MoneyLiteral(new BigDecimal(25.55), 3), 3);
		result = evaluator.evaluate(addition);
		expectedValue = new MoneyValue(new BigDecimal(125.55));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		addition = new Addition(new MoneyLiteral(new BigDecimal(30.7), 3), new IntegerLiteral(50, 3), 3);
		result = evaluator.evaluate(addition);
		expectedValue = new MoneyValue(new BigDecimal(80.70));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		// test for integer operands
		addition = new Addition(new IntegerLiteral(20, 3), new IntegerLiteral(50, 3), 3);
		result = evaluator.evaluate(addition);
		Assert.assertEquals(((IntegerValue) result).getValue(), (Integer) 70);
	}
	
	@Test
	public void testSubtraction() {
		// test for integer operands
		Subtraction sub = new Subtraction(new IntegerLiteral(100, 2), new IntegerLiteral(30, 2), 2);
		Value result = evaluator.evaluate(sub);
		Assert.assertEquals(((IntegerValue) result).getValue(), (Integer) 70);
		
		// test for money operands
		sub = new Subtraction(new MoneyLiteral(new BigDecimal(100.5), 2), new MoneyLiteral(new BigDecimal(80.7), 2), 2);
		result = evaluator.evaluate(sub);
		MoneyValue expectedValue = new MoneyValue(new BigDecimal(19.80));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());

		// test for integer and money together
		sub = new Subtraction(new MoneyLiteral(new BigDecimal(100.5), 2), new IntegerLiteral(30, 2), 2);
		result = evaluator.evaluate(sub);
		expectedValue = new MoneyValue(new BigDecimal(70.50));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		sub = new Subtraction(new IntegerLiteral(80, 2), new MoneyLiteral(new BigDecimal(10.99), 2), 2);
		result = evaluator.evaluate(sub);
		expectedValue = new MoneyValue(new BigDecimal(69.01));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
	}
	
	@Test
	public void testMultiplication() {
		Multiplication multiply = new Multiplication(new IntegerLiteral(5, 2), new IntegerLiteral(3, 2), 2);
		Value result = evaluator.evaluate(multiply);
		Assert.assertEquals(((IntegerValue) result).getValue(), (Integer) 15);
		
		multiply = new Multiplication(new MoneyLiteral(new BigDecimal(2.5), 2), new IntegerLiteral(2, 2), 2);
		result = evaluator.evaluate(multiply);
		MoneyValue expectedValue = new MoneyValue(new BigDecimal(5.00));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		multiply = new Multiplication(new IntegerLiteral(10, 2), new MoneyLiteral(new BigDecimal(3.55), 2), 2);
		result = evaluator.evaluate(multiply);
		expectedValue = new MoneyValue(new BigDecimal(35.50));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		multiply = new Multiplication(new MoneyLiteral(new BigDecimal(2.5), 2), new MoneyLiteral(new BigDecimal(3.5), 2), 2);
		result = evaluator.evaluate(multiply);
		expectedValue = new MoneyValue(new BigDecimal(8.75));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
	}
	
	@Test
	public void testDivision() {
		// test for integer operands
		Division divide = new Division(new IntegerLiteral(5, 2), new IntegerLiteral(2, 2), 2);
		Value result = evaluator.evaluate(divide);
		Assert.assertEquals(((IntegerValue) result).getValue(), (Integer) 2);
		
		// test for integer and money
		divide = new Division(new MoneyLiteral(new BigDecimal(7.75), 2), new IntegerLiteral(2, 2), 2);
		result = evaluator.evaluate(divide);
		MoneyValue expectedValue = new MoneyValue(new BigDecimal(3.88));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		divide = new Division(new IntegerLiteral(3, 2), new MoneyLiteral(new BigDecimal(1.5), 2), 2);
		result = evaluator.evaluate(divide);
		expectedValue = new MoneyValue(new BigDecimal(2.00));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
		
		//test for money operands
		divide = new Division(new MoneyLiteral(new BigDecimal(7.5), 2), new MoneyLiteral(new BigDecimal(2.5), 2), 2);
		result = evaluator.evaluate(divide);
		expectedValue = new MoneyValue(new BigDecimal(3.00));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new IntegerLiteral(100, 2), new IntegerLiteral(100, 2), 2);
		Value result = evaluator.evaluate(equal);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		equal = new Equal(new MoneyLiteral(new BigDecimal(100), 2), new IntegerLiteral(100, 2), 2);
		result = evaluator.evaluate(equal);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		equal = new Equal(new IntegerLiteral(90, 2), new MoneyLiteral(new BigDecimal(90), 2), 2);
		result = evaluator.evaluate(equal);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		equal = new Equal(new MoneyLiteral(new BigDecimal(90.8), 2), new MoneyLiteral(new BigDecimal(90.8), 2), 2);
		result = evaluator.evaluate(equal);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		equal = new Equal(new MoneyLiteral(new BigDecimal(99.8), 2), new MoneyLiteral(new BigDecimal(90.8), 2), 2);
		result = evaluator.evaluate(equal);
		Assert.assertFalse(((BooleanValue) result).getValue());
	}
	
	@Test
	public void testNotEqual() {
		// test integer operands
		NotEqual notEqual = new NotEqual(new IntegerLiteral(100, 2), new IntegerLiteral(890, 2), 2);
		Value result = evaluator.evaluate(notEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		notEqual = new NotEqual(new IntegerLiteral(100, 2), new IntegerLiteral(100, 2), 2);
		result = evaluator.evaluate(notEqual);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		// test integer and money together
		notEqual = new NotEqual(new IntegerLiteral(102, 2), new MoneyLiteral(new BigDecimal(10.89), 2), 2);
		result = evaluator.evaluate(notEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		notEqual = new NotEqual(new MoneyLiteral(new BigDecimal(100), 2), new IntegerLiteral(100, 2), 2);
		result = evaluator.evaluate(notEqual);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		// test money operands
		notEqual = new NotEqual(new MoneyLiteral(new BigDecimal(100.98), 2), new MoneyLiteral(new BigDecimal(190.99), 2), 2);
		result = evaluator.evaluate(notEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
	}
	
	@Test
	public void testGreaterThan() {
		GreaterThan greaterThan = new GreaterThan(new MoneyLiteral(new BigDecimal(10.09), 2), new IntegerLiteral(3, 2), 2);
		Value result = evaluator.evaluate(greaterThan);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		greaterThan = new GreaterThan(new IntegerLiteral(30, 2), new MoneyLiteral(new BigDecimal(18.99), 2), 2);
		result = evaluator.evaluate(greaterThan);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		greaterThan = new GreaterThan(new MoneyLiteral(new BigDecimal(17.99), 2), new MoneyLiteral(new BigDecimal(18.99), 2), 2);
		result = evaluator.evaluate(greaterThan);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		greaterThan = new GreaterThan(new IntegerLiteral(30, 2), new IntegerLiteral(30, 2), 2);
		result = evaluator.evaluate(greaterThan);
		Assert.assertFalse(((BooleanValue) result).getValue());
	}
	
	@Test
	public void testGreaterThanEqual() {
		GreaterThanEqual greaterThanEqual = new GreaterThanEqual(new IntegerLiteral(8, 2), new IntegerLiteral(10, 2), 2);
		Value result = evaluator.evaluate(greaterThanEqual);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		greaterThanEqual = new GreaterThanEqual(new IntegerLiteral(80, 2), new MoneyLiteral(new BigDecimal(70.99), 2), 2);
		result = evaluator.evaluate(greaterThanEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		greaterThanEqual = new GreaterThanEqual(new MoneyLiteral(new BigDecimal(90), 2), new IntegerLiteral(90, 2), 2);
		result = evaluator.evaluate(greaterThanEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		greaterThanEqual = new GreaterThanEqual(new MoneyLiteral(new BigDecimal(11.978), 2), new MoneyLiteral(new BigDecimal(10.98), 2), 2);
		result = evaluator.evaluate(greaterThanEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
	}
	
	@Test
	public void testLessThan() {
		LessThan lessThan = new LessThan(new IntegerLiteral(78, 2), new IntegerLiteral(100, 2), 2);
		Value result = evaluator.evaluate(lessThan);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		lessThan = new LessThan(new MoneyLiteral(new BigDecimal(90), 2), new IntegerLiteral(90, 2), 2);
		result = evaluator.evaluate(lessThan);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		lessThan = new LessThan(new IntegerLiteral(89, 2), new MoneyLiteral(new BigDecimal(109.9), 2), 2);
		result = evaluator.evaluate(lessThan);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		lessThan = new LessThan(new MoneyLiteral(new BigDecimal(45.99), 2), new MoneyLiteral(new BigDecimal(45.96), 2), 2);
		result = evaluator.evaluate(lessThan);
		Assert.assertFalse(((BooleanValue) result).getValue());
	}
	
	@Test
	public void testLessThanEqual() {
		LessThanEqual lessThanEqual = new LessThanEqual(new IntegerLiteral(103, 2), new IntegerLiteral(103, 2), 2);
		Value result = evaluator.evaluate(lessThanEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		lessThanEqual = new LessThanEqual(new MoneyLiteral(new BigDecimal(90), 2), new IntegerLiteral(90, 2), 2);
		result = evaluator.evaluate(lessThanEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		lessThanEqual = new LessThanEqual(new IntegerLiteral(97, 2), new MoneyLiteral(new BigDecimal(99.9), 2), 2);
		result = evaluator.evaluate(lessThanEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		lessThanEqual = new LessThanEqual(new MoneyLiteral(new BigDecimal(65.08), 2), new MoneyLiteral(new BigDecimal(45.96), 2), 2);
		result = evaluator.evaluate(lessThanEqual);
		Assert.assertFalse(((BooleanValue) result).getValue());
	}
	
	@Test
	public void testCombination() {
		Expression leftExpr = new Subtraction(new MoneyLiteral(new BigDecimal(12.5), 3), new IntegerLiteral(5, 3), 3);
		Expression rightExpr = new Multiplication(new IntegerLiteral(20, 3), new IntegerLiteral(2, 3), 3);
		
		LessThan lessThan = new LessThan(leftExpr, rightExpr, 3);
		Value result = evaluator.evaluate(lessThan);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		Equal equal = new Equal(leftExpr, rightExpr, 4);
		result = evaluator.evaluate(equal);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		NotEqual notEqual = new NotEqual(leftExpr, rightExpr, 4);
		result = evaluator.evaluate(notEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		Division division = new Division(leftExpr, rightExpr, 4);
		result = evaluator.evaluate(division);
		MoneyValue expectedValue = new MoneyValue(new BigDecimal(0.19));
		Assert.assertEquals(((MoneyValue) result).getValue(), expectedValue.getValue());
	}
}

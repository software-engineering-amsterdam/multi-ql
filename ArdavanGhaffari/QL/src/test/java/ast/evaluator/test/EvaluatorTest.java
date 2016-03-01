package ast.evaluator.test;


import org.junit.Assert;
import org.junit.Test;

import ast.evaluator.Evaluator;
import ast.model.Expression;
import ast.model.binaryexpression.Addition;
import ast.model.binaryexpression.Conjunction;
import ast.model.binaryexpression.Disjunction;
import ast.model.binaryexpression.Division;
import ast.model.binaryexpression.Equal;
import ast.model.binaryexpression.GreaterThan;
import ast.model.binaryexpression.GreaterThanEqual;
import ast.model.binaryexpression.LessThan;
import ast.model.binaryexpression.LessThanEqual;
import ast.model.binaryexpression.Multiplication;
import ast.model.binaryexpression.NotEqual;
import ast.model.binaryexpression.Subtraction;
import ast.model.literal.BooleanLiteral;
import ast.model.literal.DecimalLiteral;
import ast.model.literal.IntegerLiteral;
import ast.model.literal.StringLiteral;
import ast.model.unaryexpression.Negation;

public class EvaluatorTest {
	Evaluator evaluator = new Evaluator();
	
	@Test
	public void testNegation() {
		Negation negation = new Negation(new BooleanLiteral(false, 2), 2);
		Object result = evaluator.visit(negation);
		Assert.assertEquals((Boolean) result, true);
	}
	
	@Test
	public void testAddition() {
		// test for String operands
		Addition addition = new Addition(new StringLiteral("test ", 3), new StringLiteral("string", 3), 3);
		Object result = evaluator.visit(addition);
		Assert.assertEquals((String) result, "test string");
		
		// test for numeric operands
		addition = new Addition(new IntegerLiteral(100, 3), new DecimalLiteral(25.55, 3), 3);
		result = evaluator.visit(addition);
		Assert.assertEquals((Double) result, (Double) 125.55);
	}
	
	@Test
	public void testSubtraction() {
		// test for integer operands
		Subtraction sub = new Subtraction(new IntegerLiteral(100, 2), new IntegerLiteral(30, 2), 2);
		Object result = evaluator.visit(sub);
		Assert.assertEquals(((Integer) result).intValue(), 70);
		
		// test for integer and double together
		sub = new Subtraction(new DecimalLiteral(100.5, 2), new IntegerLiteral(30, 2), 2);
		result = evaluator.visit(sub);
		Assert.assertEquals((Double) result, (Double) 70.5);
	}
	
	@Test
	public void testConjunction() {
		Conjunction conjunction = new Conjunction(new BooleanLiteral(false, 2), new BooleanLiteral(true, 2), 2);
		Assert.assertEquals((Boolean) evaluator.visit(conjunction), false);
	}
	
	@Test
	public void testDisjunction() {
		Disjunction disjunction = new Disjunction(new BooleanLiteral(false, 2), new BooleanLiteral(true, 2), 2);
		Assert.assertEquals((Boolean) evaluator.visit(disjunction), true);
	}
	
	@Test
	public void testMultiplication() {
		Multiplication multiply = new Multiplication(new DecimalLiteral(2.5, 2), new IntegerLiteral(2, 2), 2);
		Object result = evaluator.visit(multiply);
		Assert.assertEquals((Double) result, (Double) 5.0);
		
		multiply = new Multiplication(new IntegerLiteral(10, 2), new DecimalLiteral(3.55, 2), 2);
		result = evaluator.visit(multiply);
		Assert.assertEquals((Double) result, (Double) 35.5);
		
		multiply = new Multiplication(new DecimalLiteral(2.5, 2), new DecimalLiteral(3.5, 2), 2);
		result = evaluator.visit(multiply);
		Assert.assertEquals((Double) result, (Double) 8.75);
		
		multiply = new Multiplication(new IntegerLiteral(5, 2), new IntegerLiteral(3, 2), 2);
		result = evaluator.visit(multiply);
		Assert.assertEquals((Integer) result, (Integer) 15);
	}
	
	@Test
	public void testDivision() {
		Division divide = new Division(new IntegerLiteral(5, 2), new IntegerLiteral(2, 2), 2);
		Object result = evaluator.visit(divide);
		Assert.assertEquals((Double) result, (Double) 2.5);
		
		divide = new Division(new DecimalLiteral(5.5, 2), new IntegerLiteral(2, 2), 2);
		result = evaluator.visit(divide);
		Assert.assertEquals((Double) result, (Double) 2.75);
		
		divide = new Division(new DecimalLiteral(7.5, 2), new DecimalLiteral(2.5, 2), 2);
		result = evaluator.visit(divide);
		Assert.assertEquals((Double) result, (Double) 3.0);
		
		divide = new Division(new IntegerLiteral(5, 2), new IntegerLiteral(0, 2), 2);
		result = evaluator.visit(divide);
		Assert.assertNull(result);
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new DecimalLiteral(100, 2), new IntegerLiteral(100, 2), 2);
		Object result = evaluator.visit(equal);
		Assert.assertTrue((Boolean) result);
		
		equal = new Equal(new DecimalLiteral(100.9, 2), new DecimalLiteral(100.9, 2), 2);
		result = evaluator.visit(equal);
		Assert.assertTrue((Boolean) result);
		
		equal = new Equal(new DecimalLiteral(100.9, 2), new DecimalLiteral(100, 2), 2);
		result = evaluator.visit(equal);
		Assert.assertFalse((Boolean) result);
		
		equal = new Equal(new StringLiteral("test", 2), new StringLiteral("test", 2), 2);
		result = evaluator.visit(equal);
		Assert.assertTrue((Boolean) result);
		
		equal = new Equal(new BooleanLiteral(false, 2), new BooleanLiteral(false, 2), 2);
		result = evaluator.visit(equal);
		Assert.assertTrue((Boolean) result);
	}
	
	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(new DecimalLiteral(100.3, 2), new IntegerLiteral(120, 2), 2);
		Object result = evaluator.visit(notEqual);
		Assert.assertTrue((Boolean) result);
		
		notEqual = new NotEqual(new DecimalLiteral(100.5, 2), new DecimalLiteral(100.9, 2), 2);
		result = evaluator.visit(notEqual);
		Assert.assertTrue((Boolean) result);
		
		notEqual = new NotEqual(new StringLiteral("test1", 2), new StringLiteral("test2", 2), 2);
		result = evaluator.visit(notEqual);
		Assert.assertTrue((Boolean) result);
		
		notEqual = new NotEqual(new BooleanLiteral(true, 2), new BooleanLiteral(false, 2), 2);
		result = evaluator.visit(notEqual);
		Assert.assertTrue((Boolean) result);
		
		notEqual = new NotEqual(new BooleanLiteral(false, 2), new BooleanLiteral(false, 2), 2);
		result = evaluator.visit(notEqual);
		Assert.assertFalse((Boolean) result);
	}
	
	@Test
	public void testGreaterThan() {
		GreaterThan greaterThan = new GreaterThan(new DecimalLiteral(100, 2), new IntegerLiteral(30, 2), 2);
		Object result = evaluator.visit(greaterThan);
		Assert.assertTrue((Boolean) result);
		
		greaterThan = new GreaterThan(new IntegerLiteral(100, 2), new DecimalLiteral(100.5, 2), 2);
		result = evaluator.visit(greaterThan);
		Assert.assertFalse((Boolean) result);
		
		greaterThan = new GreaterThan(new IntegerLiteral(100, 2), new IntegerLiteral(100, 2), 2);
		result = evaluator.visit(greaterThan);
		Assert.assertFalse((Boolean) result);
		
		greaterThan = new GreaterThan(new DecimalLiteral(30.44, 2), new DecimalLiteral(10.5, 2), 2);
		result = evaluator.visit(greaterThan);
		Assert.assertTrue((Boolean) result);
	}
	
	@Test
	public void testGreaterThanEqual() {
		// test the equal part
		GreaterThanEqual greaterThanEqual = new GreaterThanEqual(new DecimalLiteral(100, 2), new IntegerLiteral(100, 2), 2);
		Object result = evaluator.visit(greaterThanEqual);
		Assert.assertTrue((Boolean) result);
		
		greaterThanEqual = new GreaterThanEqual(new DecimalLiteral(101.9, 2), new DecimalLiteral(100.5, 2), 2);
		result = evaluator.visit(greaterThanEqual);
		Assert.assertTrue((Boolean) result);
		
		greaterThanEqual = new GreaterThanEqual(new DecimalLiteral(101.9, 2), new IntegerLiteral(102, 2), 2);
		result = evaluator.visit(greaterThanEqual);
		Assert.assertFalse((Boolean) result);
	}
	
	@Test
	public void testLessThan() {
		LessThan lessThan = new LessThan(new DecimalLiteral(10.5, 2), new DecimalLiteral(10, 2), 2);
		Object result = evaluator.visit(lessThan);
		Assert.assertFalse((Boolean) result);
		
		lessThan = new LessThan(new DecimalLiteral(10, 2), new IntegerLiteral(10, 2), 2);
		result = evaluator.visit(lessThan);
		Assert.assertFalse((Boolean) result);
		
		lessThan = new LessThan(new IntegerLiteral(5, 2), new DecimalLiteral(10.7, 2), 2);
		result = evaluator.visit(lessThan);
		Assert.assertTrue((Boolean) result);
	}
	
	@Test
	public void testLessThanEqual() {
		// test the equal part (even between double and integer)
		LessThanEqual lessThanEqual = new LessThanEqual(new DecimalLiteral(22, 4), new IntegerLiteral(22, 4), 4);
		Object result = evaluator.visit(lessThanEqual);
		Assert.assertTrue((Boolean) result);
		
		lessThanEqual = new LessThanEqual(new IntegerLiteral(26, 4), new IntegerLiteral(30, 4), 4);
		result = evaluator.visit(lessThanEqual);
		Assert.assertTrue((Boolean) result);
		
		lessThanEqual = new LessThanEqual(new DecimalLiteral(0.2, 4), new DecimalLiteral(0.1, 4), 4);
		result = evaluator.visit(lessThanEqual);
		Assert.assertFalse((Boolean) result);
	}
	
	@Test
	public void testCombination() {
		Expression leftExpr = new Subtraction(new DecimalLiteral(12.5, 3), new IntegerLiteral(5, 3), 3);
		Expression rightExpr = new Multiplication(new IntegerLiteral(20, 3), new IntegerLiteral(2, 3), 3);
		
		LessThan lessThan = new LessThan(leftExpr, rightExpr, 3);
		Object result = evaluator.visit(lessThan);
		Assert.assertTrue((Boolean) result);
		
		Equal equal = new Equal(leftExpr, rightExpr, 4);
		result = evaluator.visit(equal);
		Assert.assertFalse((Boolean) result);
		
		NotEqual notEqual = new NotEqual(leftExpr, rightExpr, 4);
		result = evaluator.visit(notEqual);
		Assert.assertTrue((Boolean) result);
		
		Division division = new Division(leftExpr, rightExpr, 4);
		result = evaluator.visit(division);
		Assert.assertEquals((Double) result, (Double) 0.1875);
	}
}

package ast.evaluator.test;

import java.math.BigDecimal;

import nl.uva.ql.ast.expression.binaryexpression.Conjunction;
import nl.uva.ql.ast.expression.binaryexpression.Disjunction;
import nl.uva.ql.ast.expression.binaryexpression.Equal;
import nl.uva.ql.ast.expression.binaryexpression.NotEqual;
import nl.uva.ql.ast.expression.unaryexpression.Negation;
import nl.uva.ql.ast.literal.BooleanLiteral;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.literal.MoneyLiteral;
import nl.uva.ql.ast.literal.StringLiteral;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.Value;

import org.junit.Assert;
import org.junit.Test;

public class BooleanEvaluationTest {
	Evaluator evaluator = new Evaluator();
	
	@Test
	public void testNegation() {
		Negation negation = new Negation(new BooleanLiteral(false, 2), 2);
		Value result = evaluator.evaluate(negation);
		Assert.assertEquals(((BooleanValue) result).getValue(), true);
		
		negation = new Negation(new StringLiteral("hello", 2), 2);
		result = evaluator.evaluate(negation);
		Assert.assertTrue(result.isUnknownValue());
		
		negation = new Negation(new IntegerLiteral(64, 2), 2);
		result = evaluator.evaluate(negation);
		Assert.assertTrue(result.isUnknownValue());
	}
	
	@Test
	public void testAnd() {
		Conjunction conjunction = new Conjunction(new BooleanLiteral(false, 2), new BooleanLiteral(true, 2), 2);
		Value result = evaluator.evaluate(conjunction);
		Assert.assertEquals(((BooleanValue) result).getValue(), false);
		
		conjunction = new Conjunction(new BooleanLiteral(true, 2), new BooleanLiteral(true, 2), 2);
		result = evaluator.evaluate(conjunction);
		Assert.assertEquals(((BooleanValue) result).getValue(), true);
		
		conjunction = new Conjunction(new BooleanLiteral(false, 2), new IntegerLiteral(10, 2), 2);
		result = evaluator.evaluate(conjunction);
		Assert.assertTrue(result.isUnknownValue());
	}
	
	@Test
	public void testOr() {
		Disjunction disjunction = new Disjunction(new BooleanLiteral(false, 2), new BooleanLiteral(true, 2), 2);
		Value result = evaluator.evaluate(disjunction);
		Assert.assertEquals(((BooleanValue) result).getValue(), true);
		
		disjunction = new Disjunction(new BooleanLiteral(false, 2), new BooleanLiteral(false, 2), 2);
		result = evaluator.evaluate(disjunction);
		Assert.assertEquals(((BooleanValue) result).getValue(), false);
		
		disjunction = new Disjunction(new BooleanLiteral(false, 2), new IntegerLiteral(10, 2), 2);
		result = evaluator.evaluate(disjunction);
		Assert.assertTrue(result.isUnknownValue());
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new BooleanLiteral(false, 2), new BooleanLiteral(false, 2), 2);
		Value result = evaluator.visit(equal);
		Assert.assertEquals(((BooleanValue) result).getValue(), true);
		
		equal = new Equal(new BooleanLiteral(true, 2), new BooleanLiteral(false, 2), 2);
		result = evaluator.visit(equal);
		Assert.assertEquals(((BooleanValue) result).getValue(), false);
		
		equal = new Equal(new IntegerLiteral(34, 2), new BooleanLiteral(false, 2), 2);
		result = evaluator.visit(equal);
		Assert.assertTrue(result.isUnknownValue());
	}
	
	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(new BooleanLiteral(false, 2), new BooleanLiteral(false, 2), 2);
		Value result = evaluator.visit(notEqual);
		Assert.assertEquals(((BooleanValue) result).getValue(), false);
		
		notEqual = new NotEqual(new BooleanLiteral(false, 2), new BooleanLiteral(true, 2), 2);
		result = evaluator.visit(notEqual);
		Assert.assertEquals(((BooleanValue) result).getValue(), true);
		
		notEqual = new NotEqual(new BooleanLiteral(false, 2), new MoneyLiteral(new BigDecimal(45), 2), 2);
		result = evaluator.visit(notEqual);
		Assert.assertTrue(result.isUnknownValue());
	}
}

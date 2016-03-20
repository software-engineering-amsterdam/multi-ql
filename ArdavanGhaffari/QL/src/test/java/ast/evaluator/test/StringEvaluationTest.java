package ast.evaluator.test;

import java.math.BigDecimal;

import nl.uva.ql.ast.expression.binaryexpression.Addition;
import nl.uva.ql.ast.expression.binaryexpression.Equal;
import nl.uva.ql.ast.expression.binaryexpression.NotEqual;
import nl.uva.ql.ast.literal.BooleanLiteral;
import nl.uva.ql.ast.literal.IntegerLiteral;
import nl.uva.ql.ast.literal.MoneyLiteral;
import nl.uva.ql.ast.literal.StringLiteral;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.StringValue;
import nl.uva.ql.evaluator.value.Value;

import org.junit.Assert;
import org.junit.Test;

public class StringEvaluationTest {
	Evaluator evaluator = new Evaluator();
	
	@Test
	public void testAddString() {
		Addition addition = new Addition(new StringLiteral("test ", 3), new StringLiteral("string", 3), 3);
		Value result = evaluator.evaluate(addition);
		Assert.assertEquals(((StringValue) result).getValue(), "test string");
		
		addition = new Addition(new StringLiteral("test ", 3), new IntegerLiteral(20, 3), 3);
		result = evaluator.evaluate(addition);
		Assert.assertTrue(result.isUnknownValue());
	}
	
	@Test
	public void testEqual() {
		Equal equal = new Equal(new StringLiteral("test_string", 3), new StringLiteral("test_string", 3), 3);
		Value result = evaluator.evaluate(equal);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		equal = new Equal(new StringLiteral("test_string1", 3), new StringLiteral("test_string2", 3), 3);
		result = evaluator.evaluate(equal);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		equal = new Equal(new StringLiteral("test string", 3), new IntegerLiteral(20, 3), 3);
		result = evaluator.evaluate(equal);
		Assert.assertTrue(result.isUnknownValue());
	}
	
	@Test
	public void testNotEqual() {
		NotEqual notEqual = new NotEqual(new StringLiteral("Hello", 3), new StringLiteral("World", 3), 3);
		Value result = evaluator.evaluate(notEqual);
		Assert.assertTrue(((BooleanValue) result).getValue());
		
		notEqual = new NotEqual(new StringLiteral("Hello", 3), new StringLiteral("Hello", 3), 3);
		result = evaluator.evaluate(notEqual);
		Assert.assertFalse(((BooleanValue) result).getValue());
		
		notEqual = new NotEqual(new StringLiteral("Hello", 3), new MoneyLiteral(new BigDecimal(20.89), 3), 3);
		result = evaluator.evaluate(notEqual);
		Assert.assertTrue(result.isUnknownValue());
		
		notEqual = new NotEqual(new BooleanLiteral(true, 3), new StringLiteral("World", 3), 3);
		result = evaluator.evaluate(notEqual);
		Assert.assertTrue(result.isUnknownValue());
	}
}

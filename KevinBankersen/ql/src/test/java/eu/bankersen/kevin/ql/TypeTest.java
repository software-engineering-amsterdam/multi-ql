package eu.bankersen.kevin.ql;

import eu.bankersen.kevin.ql.form.ast.types.BooleanType;
import eu.bankersen.kevin.ql.form.ast.types.MoneyType;
import eu.bankersen.kevin.ql.form.ast.types.NumberType;
import eu.bankersen.kevin.ql.form.ast.types.TextType;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.types.UndefinedType;
import junit.framework.TestCase;

public class TypeTest extends TestCase {

	public void test() {

		Type bool = new BooleanType();
		Type num = new NumberType();
		Type text = new TextType();
		Type money = new MoneyType();
		Type empty = new UndefinedType();

		// Subtract
		assertTrue(bool.subtract(bool).equals(empty));
		assertTrue(bool.subtract(num).equals(empty));
		assertTrue(bool.subtract(text).equals(empty));
		assertTrue(bool.subtract(money).equals(empty));
		assertTrue(bool.subtract(empty).equals(empty));

		assertTrue(num.subtract(bool).equals(empty));
		assertTrue(num.subtract(num).equals(num));
		assertTrue(num.subtract(text).equals(empty));
		assertTrue(num.subtract(money).equals(empty));
		assertTrue(num.subtract(empty).equals(empty));

		assertTrue(money.subtract(bool).equals(empty));
		assertTrue(money.subtract(num).equals(empty));
		assertTrue(money.subtract(text).equals(empty));
		assertTrue(money.subtract(money).equals(money));
		assertTrue(money.subtract(empty).equals(empty));

		assertTrue(text.subtract(bool).equals(empty));
		assertTrue(text.subtract(num).equals(empty));
		assertTrue(text.subtract(text).equals(empty));
		assertTrue(text.subtract(money).equals(empty));
		assertTrue(text.subtract(empty).equals(empty));

		// Add
		assertTrue(bool.add(bool).equals(empty));
		assertTrue(bool.add(num).equals(empty));
		assertTrue(bool.add(text).equals(empty));
		assertTrue(bool.add(money).equals(empty));
		assertTrue(bool.add(empty).equals(empty));

		assertTrue(num.add(bool).equals(empty));
		assertTrue(num.add(num).equals(num));
		assertTrue(num.add(text).equals(empty));
		assertTrue(num.add(money).equals(empty));
		assertTrue(num.add(empty).equals(empty));

		assertTrue(money.add(bool).equals(empty));
		assertTrue(money.add(num).equals(empty));
		assertTrue(money.add(text).equals(empty));
		assertTrue(money.add(money).equals(money));
		assertTrue(money.add(empty).equals(empty));

		assertTrue(text.add(bool).equals(empty));
		assertTrue(text.add(num).equals(text));
		assertTrue(text.add(text).equals(text));
		assertTrue(text.add(money).equals(text));
		assertTrue(text.add(empty).equals(empty));

		// Divide
		assertTrue(bool.divide(bool).equals(empty));
		assertTrue(bool.divide(num).equals(empty));
		assertTrue(bool.divide(text).equals(empty));
		assertTrue(bool.divide(money).equals(empty));
		assertTrue(bool.divide(empty).equals(empty));

		assertTrue(num.divide(bool).equals(empty));
		assertTrue(num.divide(num).equals(num));
		assertTrue(num.divide(text).equals(empty));
		assertTrue(num.divide(money).equals(empty));
		assertTrue(num.divide(empty).equals(empty));

		assertTrue(money.divide(bool).equals(empty));
		assertTrue(money.divide(num).equals(money));
		assertTrue(money.divide(text).equals(empty));
		assertTrue(money.divide(money).equals(empty));
		assertTrue(money.divide(empty).equals(empty));

		assertTrue(text.divide(bool).equals(empty));
		assertTrue(text.divide(num).equals(empty));
		assertTrue(text.divide(text).equals(empty));
		assertTrue(text.divide(money).equals(empty));
		assertTrue(text.divide(empty).equals(empty));

		// Multiply
		assertTrue(bool.multiply(bool).equals(empty));
		assertTrue(bool.multiply(num).equals(empty));
		assertTrue(bool.multiply(text).equals(empty));
		assertTrue(bool.multiply(money).equals(empty));
		assertTrue(bool.multiply(empty).equals(empty));

		assertTrue(num.multiply(bool).equals(empty));
		assertTrue(num.multiply(num).equals(num));
		assertTrue(num.multiply(text).equals(empty));
		assertTrue(num.multiply(money).equals(money));
		assertTrue(num.multiply(empty).equals(empty));

		assertTrue(money.multiply(bool).equals(empty));
		assertTrue(money.multiply(num).equals(money));
		assertTrue(money.multiply(text).equals(empty));
		assertTrue(money.multiply(money).equals(empty));
		assertTrue(money.multiply(empty).equals(empty));

		assertTrue(text.multiply(bool).equals(empty));
		assertTrue(text.multiply(num).equals(empty));
		assertTrue(text.multiply(text).equals(empty));
		assertTrue(text.multiply(money).equals(empty));
		assertTrue(text.multiply(empty).equals(empty));

		// Absolute
		assertTrue(bool.absolute().equals(bool));
		assertTrue(num.absolute().equals(num));
		assertTrue(money.absolute().equals(money));
		assertTrue(text.absolute().equals(empty));
		assertTrue(empty.absolute().equals(empty));

		// Negate
		assertTrue(bool.negate().equals(bool));
		assertTrue(num.negate().equals(num));
		assertTrue(money.negate().equals(money));
		assertTrue(text.negate().equals(empty));
		assertTrue(empty.negate().equals(empty));

		// Or
		assertTrue(bool.or(bool).equals(bool));
		assertTrue(bool.or(num).equals(empty));
		assertTrue(bool.or(text).equals(empty));
		assertTrue(bool.or(money).equals(empty));
		assertTrue(bool.or(empty).equals(empty));

		assertTrue(num.or(bool).equals(empty));
		assertTrue(num.or(num).equals(empty));
		assertTrue(num.or(text).equals(empty));
		assertTrue(num.or(money).equals(empty));
		assertTrue(num.or(empty).equals(empty));

		assertTrue(money.or(bool).equals(empty));
		assertTrue(money.or(num).equals(empty));
		assertTrue(money.or(text).equals(empty));
		assertTrue(money.or(money).equals(empty));
		assertTrue(money.or(empty).equals(empty));

		assertTrue(text.or(bool).equals(empty));
		assertTrue(text.or(num).equals(empty));
		assertTrue(text.or(text).equals(empty));
		assertTrue(text.or(money).equals(empty));
		assertTrue(text.or(empty).equals(empty));

		// And
		assertTrue(bool.and(bool).equals(bool));
		assertTrue(bool.and(num).equals(empty));
		assertTrue(bool.and(text).equals(empty));
		assertTrue(bool.and(money).equals(empty));
		assertTrue(bool.and(empty).equals(empty));

		assertTrue(num.and(bool).equals(empty));
		assertTrue(num.and(num).equals(empty));
		assertTrue(num.and(text).equals(empty));
		assertTrue(num.and(money).equals(empty));
		assertTrue(num.and(empty).equals(empty));

		assertTrue(money.and(bool).equals(empty));
		assertTrue(money.and(num).equals(empty));
		assertTrue(money.and(text).equals(empty));
		assertTrue(money.and(money).equals(empty));
		assertTrue(money.and(empty).equals(empty));

		assertTrue(text.and(bool).equals(empty));
		assertTrue(text.and(num).equals(empty));
		assertTrue(text.and(text).equals(empty));
		assertTrue(text.and(money).equals(empty));
		assertTrue(text.and(empty).equals(empty));

		// Equal
		assertTrue(bool.equal(bool).equals(bool));
		assertTrue(bool.equal(num).equals(empty));
		assertTrue(bool.equal(text).equals(empty));
		assertTrue(bool.equal(money).equals(empty));
		assertTrue(bool.equal(empty).equals(empty));

		assertTrue(num.equal(bool).equals(empty));
		assertTrue(num.equal(num).equals(bool));
		assertTrue(num.equal(text).equals(empty));
		assertTrue(num.equal(money).equals(empty));
		assertTrue(num.equal(empty).equals(empty));

		assertTrue(money.equal(bool).equals(empty));
		assertTrue(money.equal(num).equals(empty));
		assertTrue(money.equal(text).equals(empty));
		assertTrue(money.equal(money).equals(bool));
		assertTrue(money.equal(empty).equals(empty));

		assertTrue(text.equal(bool).equals(empty));
		assertTrue(text.equal(num).equals(empty));
		assertTrue(text.equal(text).equals(bool));
		assertTrue(text.equal(money).equals(empty));
		assertTrue(text.equal(empty).equals(empty));

		// NotEqual
		assertTrue(bool.notEqual(bool).equals(bool));
		assertTrue(bool.notEqual(num).equals(empty));
		assertTrue(bool.notEqual(text).equals(empty));
		assertTrue(bool.notEqual(money).equals(empty));
		assertTrue(bool.notEqual(empty).equals(empty));

		assertTrue(num.notEqual(bool).equals(empty));
		assertTrue(num.notEqual(num).equals(bool));
		assertTrue(num.notEqual(text).equals(empty));
		assertTrue(num.notEqual(money).equals(empty));
		assertTrue(num.notEqual(empty).equals(empty));

		assertTrue(money.notEqual(bool).equals(empty));
		assertTrue(money.notEqual(num).equals(empty));
		assertTrue(money.notEqual(text).equals(empty));
		assertTrue(money.notEqual(money).equals(bool));
		assertTrue(money.notEqual(empty).equals(empty));

		assertTrue(text.notEqual(bool).equals(empty));
		assertTrue(text.notEqual(num).equals(empty));
		assertTrue(text.notEqual(text).equals(bool));
		assertTrue(text.notEqual(money).equals(empty));
		assertTrue(text.notEqual(empty).equals(empty));

		// GreaterOrEqual
		assertTrue(bool.greaterOrEqual(bool).equals(empty));
		assertTrue(bool.greaterOrEqual(num).equals(empty));
		assertTrue(bool.greaterOrEqual(text).equals(empty));
		assertTrue(bool.greaterOrEqual(money).equals(empty));
		assertTrue(bool.greaterOrEqual(empty).equals(empty));

		assertTrue(num.greaterOrEqual(bool).equals(empty));
		assertTrue(num.greaterOrEqual(num).equals(bool));
		assertTrue(num.greaterOrEqual(text).equals(empty));
		assertTrue(num.greaterOrEqual(money).equals(empty));
		assertTrue(num.greaterOrEqual(empty).equals(empty));

		assertTrue(money.greaterOrEqual(bool).equals(empty));
		assertTrue(money.greaterOrEqual(num).equals(empty));
		assertTrue(money.greaterOrEqual(text).equals(empty));
		assertTrue(money.greaterOrEqual(money).equals(bool));
		assertTrue(money.greaterOrEqual(empty).equals(empty));

		assertTrue(text.greaterOrEqual(bool).equals(empty));
		assertTrue(text.greaterOrEqual(num).equals(empty));
		assertTrue(text.greaterOrEqual(text).equals(empty));
		assertTrue(text.greaterOrEqual(money).equals(empty));
		assertTrue(text.greaterOrEqual(empty).equals(empty));

		// Greater
		assertTrue(bool.greater(bool).equals(empty));
		assertTrue(bool.greater(num).equals(empty));
		assertTrue(bool.greater(text).equals(empty));
		assertTrue(bool.greater(money).equals(empty));
		assertTrue(bool.greater(empty).equals(empty));

		assertTrue(num.greater(bool).equals(empty));
		assertTrue(num.greater(num).equals(bool));
		assertTrue(num.greater(text).equals(empty));
		assertTrue(num.greater(money).equals(empty));
		assertTrue(num.greater(empty).equals(empty));

		assertTrue(money.greater(bool).equals(empty));
		assertTrue(money.greater(num).equals(empty));
		assertTrue(money.greater(text).equals(empty));
		assertTrue(money.greater(money).equals(bool));
		assertTrue(money.greater(empty).equals(empty));

		assertTrue(text.greater(bool).equals(empty));
		assertTrue(text.greater(num).equals(empty));
		assertTrue(text.greater(text).equals(empty));
		assertTrue(text.greater(money).equals(empty));
		assertTrue(text.greater(empty).equals(empty));

		// LowerOrEqual
		assertTrue(bool.lowerOrEqual(bool).equals(empty));
		assertTrue(bool.lowerOrEqual(num).equals(empty));
		assertTrue(bool.lowerOrEqual(text).equals(empty));
		assertTrue(bool.lowerOrEqual(money).equals(empty));
		assertTrue(bool.lowerOrEqual(empty).equals(empty));

		assertTrue(num.lowerOrEqual(bool).equals(empty));
		assertTrue(num.lowerOrEqual(num).equals(bool));
		assertTrue(num.lowerOrEqual(text).equals(empty));
		assertTrue(num.lowerOrEqual(money).equals(empty));
		assertTrue(num.lowerOrEqual(empty).equals(empty));

		assertTrue(money.lowerOrEqual(bool).equals(empty));
		assertTrue(money.lowerOrEqual(num).equals(empty));
		assertTrue(money.lowerOrEqual(text).equals(empty));
		assertTrue(money.lowerOrEqual(money).equals(bool));
		assertTrue(money.lowerOrEqual(empty).equals(empty));

		assertTrue(text.lowerOrEqual(bool).equals(empty));
		assertTrue(text.lowerOrEqual(num).equals(empty));
		assertTrue(text.lowerOrEqual(text).equals(empty));
		assertTrue(text.lowerOrEqual(money).equals(empty));
		assertTrue(text.lowerOrEqual(empty).equals(empty));

		// Lower
		assertTrue(bool.lower(bool).equals(empty));
		assertTrue(bool.lower(num).equals(empty));
		assertTrue(bool.lower(text).equals(empty));
		assertTrue(bool.lower(money).equals(empty));
		assertTrue(bool.lower(empty).equals(empty));

		assertTrue(num.lower(bool).equals(empty));
		assertTrue(num.lower(num).equals(bool));
		assertTrue(num.lower(text).equals(empty));
		assertTrue(num.lower(money).equals(empty));
		assertTrue(num.lower(empty).equals(empty));

		assertTrue(money.lower(bool).equals(empty));
		assertTrue(money.lower(num).equals(empty));
		assertTrue(money.lower(text).equals(empty));
		assertTrue(money.lower(money).equals(bool));
		assertTrue(money.lower(empty).equals(empty));

		assertTrue(text.lower(bool).equals(empty));
		assertTrue(text.lower(num).equals(empty));
		assertTrue(text.lower(text).equals(empty));
		assertTrue(text.lower(money).equals(empty));
		assertTrue(text.lower(empty).equals(empty));

		// Not
		assertTrue(bool.not().equals(bool));
		assertTrue(num.not().equals(empty));
		assertTrue(money.not().equals(empty));
		assertTrue(text.not().equals(empty));
		assertTrue(empty.not().equals(empty));

	}

}

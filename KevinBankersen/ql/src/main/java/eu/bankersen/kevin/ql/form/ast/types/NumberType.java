package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.NumberValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.SliderInput;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class NumberType extends Type {

	@Override
	public Widget prefered(Question question) {
		return new SliderInput(question);
	}

	@Override
	public String toString() {
		return "Integer";
	}

	@Override
	public Value value(String string) {
		try {
			return new NumberValue(string);
		} catch (NumberFormatException e) {
			return new EmptyValue();
		}
	}

	@Override
	public Type subtract(Type type) {
		return type.subtract(this);
	}

	@Override
	public Type subtract(NumberType type) {
		return new NumberType();
	}

	@Override
	public Type add(Type type) {
		return type.add(this);
	}

	@Override
	public Type add(NumberType type) {
		return new NumberType();
	}

	@Override
	public Type add(TextType type) {
		return new TextType();
	}

	@Override
	public Type divide(Type type) {
		return type.divide(this);
	}

	@Override
	public Type divide(NumberType type) {
		return new NumberType();
	}

	@Override
	public Type divide(MoneyType type) {
		return new MoneyType();
	}

	@Override
	public Type multiply(Type type) {
		return type.multiply(this);
	}

	@Override
	public Type multiply(NumberType type) {
		return new NumberType();
	}

	@Override
	public Type multiply(MoneyType type) {
		return new MoneyType();
	}

	@Override
	public Type absolute() {
		return new NumberType();
	}

	@Override
	public Type negate() {
		return new NumberType();
	}

	@Override
	public Type equal(Type type) {
		return type.equal(this);
	}

	@Override
	public Type equal(NumberType type) {
		return new BooleanType();
	}

	@Override
	public Type greaterOrEqual(Type type) {
		return type.greaterOrEqual(this);
	}

	@Override
	public Type greaterOrEqual(NumberType type) {
		return new BooleanType();
	}

	@Override
	public Type greater(Type type) {
		return type.greater(this);
	}

	@Override
	public Type greater(NumberType type) {
		return new BooleanType();
	}

	@Override
	public Type lowerOrEqual(Type type) {
		return type.lowerOrEqual(this);
	}

	@Override
	public Type lowerOrEqual(NumberType type) {
		return new BooleanType();
	}

	@Override
	public Type lower(Type type) {
		return type.lower(this);
	}

	@Override
	public Type lower(NumberType type) {
		return new BooleanType();
	}

	@Override
	public Type notEqual(Type type) {
		return type.notEqual(this);
	}

	@Override
	public Type notEqual(NumberType type) {
		return new BooleanType();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof NumberType;
	}

	@Override
	public int hashCode() {
		return 13;
	}
}

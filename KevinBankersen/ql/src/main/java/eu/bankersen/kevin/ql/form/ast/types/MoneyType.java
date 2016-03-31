package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.TextInput;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class MoneyType extends Type {

	@Override
	public Widget prefered(Question question) {
		return new TextInput(question);
	}

	@Override
	public String toString() {
		return "Money";
	}

	@Override
	public Value value(String string) {
		try {
			return new MoneyValue(string);
		} catch (NumberFormatException e) {
			return new EmptyValue();
		}
	}

	@Override
	public Type subtract(Type rhs) {
		return rhs.subtract(this);
	}

	@Override
	public Type subtract(MoneyType lhs) {
		return new MoneyType();
	}

	@Override
	public Type add(Type rhs) {
		return rhs.add(this);
	}

	@Override
	public Type add(MoneyType lhs) {
		return new MoneyType();
	}

	@Override
	public Type add(TextType lhs) {
		return new TextType();
	}

	@Override
	public Type divide(Type rhs) {
		return rhs.divide(this);
	}

	@Override
	public Type multiply(Type rhs) {
		return rhs.multiply(this);
	}

	@Override
	public Type multiply(NumberType lhs) {
		return new MoneyType();
	}

	@Override
	public Type absolute() {
		return new MoneyType();
	}

	@Override
	public Type negate() {
		return new MoneyType();
	}

	@Override
	public Type equal(Type rhs) {
		return rhs.equal(this);
	}

	@Override
	public Type equal(MoneyType lhs) {
		return new BooleanType();
	}

	@Override
	public Type greaterOrEqual(Type rhs) {
		return rhs.greaterOrEqual(this);
	}

	@Override
	public Type greaterOrEqual(MoneyType lhs) {
		return new BooleanType();
	}

	@Override
	public Type greater(Type rhs) {
		return rhs.greater(this);
	}

	@Override
	public Type greater(MoneyType lhs) {
		return new BooleanType();
	}

	@Override
	public Type lowerOrEqual(Type rhs) {
		return rhs.lowerOrEqual(this);
	}

	@Override
	public Type lowerOrEqual(MoneyType lhs) {
		return new BooleanType();
	}

	@Override
	public Type lower(Type rhs) {
		return rhs.lower(this);
	}

	@Override
	public Type lower(MoneyType type) {
		return new BooleanType();
	}

	@Override
	public Type notEqual(Type rhs) {
		return rhs.notEqual(this);
	}

	@Override
	public Type notEqual(MoneyType lhs) {
		return new BooleanType();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof MoneyType;
	}

	@Override
	public int hashCode() {
		return 13;
	}

}

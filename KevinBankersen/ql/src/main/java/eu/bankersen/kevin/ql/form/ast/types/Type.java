package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public abstract class Type {

	public abstract Widget prefered(Question question);

	public abstract Value parse(String value);

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

	public Type subtract(Type rhs) {
		return new UndefinedType();
	}

	public Type subtract(NumberType type) {
		return new UndefinedType();
	}

	public Type subtract(MoneyType type) {
		return new UndefinedType();
	}

	public Type subtract(BooleanType type) {
		return new UndefinedType();
	}

	public Type subtract(TextType type) {
		return new UndefinedType();
	}

	public Type add(Type rhs) {
		return new UndefinedType();
	}

	public Type add(NumberType type) {
		return new UndefinedType();
	}

	public Type add(MoneyType type) {
		return new UndefinedType();
	}

	public Type add(BooleanType type) {
		return new UndefinedType();
	}

	public Type add(TextType type) {
		return new UndefinedType();
	}

	public Type divide(Type rhs) {
		return new UndefinedType();
	}

	public Type divide(NumberType lhs) {
		return new UndefinedType();
	}

	public Type divide(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type divide(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type divide(TextType lhs) {
		return new UndefinedType();
	}

	public Type multiply(Type rhs) {
		return new UndefinedType();
	}

	public Type multiply(NumberType lhs) {
		return new UndefinedType();
	}

	public Type multiply(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type multiply(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type multiply(TextType lhs) {
		return new UndefinedType();
	}

	public Type absolute() {
		return new UndefinedType();
	}

	public Type negate() {
		return new UndefinedType();
	}

	public Type or(Type rhs) {
		return new UndefinedType();
	}

	public Type or(NumberType lhs) {
		return new UndefinedType();
	}

	public Type or(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type or(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type or(TextType lhs) {
		return new UndefinedType();
	}

	public Type and(Type rhs) {
		return new UndefinedType();
	}

	public Type and(NumberType lhs) {
		return new UndefinedType();
	}

	public Type and(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type and(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type and(TextType lhs) {
		return new UndefinedType();
	}

	public Type equal(Type rhs) {
		return new UndefinedType();
	}

	public Type equal(NumberType lhs) {
		return new UndefinedType();
	}

	public Type equal(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type equal(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type equal(TextType lhs) {
		return new UndefinedType();
	}

	public Type notEqual(Type rhs) {
		return new UndefinedType();
	}

	public Type notEqual(NumberType lhs) {
		return new UndefinedType();
	}

	public Type notEqual(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type notEqual(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type notEqual(TextType lhs) {
		return new UndefinedType();
	}

	public Type greaterOrEqual(Type rhs) {
		return new UndefinedType();
	}

	public Type greaterOrEqual(NumberType lhs) {
		return new UndefinedType();
	}

	public Type greaterOrEqual(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type greaterOrEqual(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type greaterOrEqual(TextType lhs) {
		return new UndefinedType();
	}

	public Type greater(Type rhs) {
		return new UndefinedType();
	}

	public Type greater(NumberType lhs) {
		return new UndefinedType();
	}

	public Type greater(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type greater(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type greater(TextType lhs) {
		return new UndefinedType();
	}

	public Type lowerOrEqual(Type rhs) {
		return new UndefinedType();
	}

	public Type lowerOrEqual(NumberType lhs) {
		return new UndefinedType();
	}

	public Type lowerOrEqual(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type lowerOrEqual(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type lowerOrEqual(TextType lhs) {
		return new UndefinedType();
	}

	public Type lower(Type rhs) {
		return new UndefinedType();
	}

	public Type lower(NumberType lhs) {
		return new UndefinedType();
	}

	public Type lower(MoneyType lhs) {
		return new UndefinedType();
	}

	public Type lower(BooleanType lhs) {
		return new UndefinedType();
	}

	public Type lower(TextType lhs) {
		return new UndefinedType();
	}

	public Type not() {
		return new UndefinedType();
	}

}

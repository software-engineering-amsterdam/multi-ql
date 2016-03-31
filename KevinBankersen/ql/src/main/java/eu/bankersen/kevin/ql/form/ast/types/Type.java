package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public abstract class Type {

	public abstract Widget prefered(Question question);

	public abstract Value value(String string);

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

	public Type subtract(Type rhs) {
		return new UndifinedType();
	}

	public Type subtract(NumberType type) {
		return new UndifinedType();
	}

	public Type subtract(MoneyType type) {
		return new UndifinedType();
	}

	public Type subtract(BooleanType type) {
		return new UndifinedType();
	}

	public Type subtract(TextType type) {
		return new UndifinedType();
	}

	public Type add(Type rhs) {
		return new UndifinedType();
	}

	public Type add(NumberType type) {
		return new UndifinedType();
	}

	public Type add(MoneyType type) {
		return new UndifinedType();
	}

	public Type add(BooleanType type) {
		return new UndifinedType();
	}

	public Type add(TextType type) {
		return new UndifinedType();
	}

	public Type divide(Type rhs) {
		return new UndifinedType();
	}

	public Type divide(NumberType lhs) {
		return new UndifinedType();
	}

	public Type divide(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type divide(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type divide(TextType lhs) {
		return new UndifinedType();
	}

	public Type multiply(Type rhs) {
		return new UndifinedType();
	}

	public Type multiply(NumberType lhs) {
		return new UndifinedType();
	}

	public Type multiply(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type multiply(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type multiply(TextType lhs) {
		return new UndifinedType();
	}

	public Type absolute() {
		return new UndifinedType();
	}

	public Type negate() {
		return new UndifinedType();
	}

	public Type or(Type rhs) {
		return new UndifinedType();
	}

	public Type or(NumberType lhs) {
		return new UndifinedType();
	}

	public Type or(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type or(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type or(TextType lhs) {
		return new UndifinedType();
	}

	public Type and(Type rhs) {
		return new UndifinedType();
	}

	public Type and(NumberType lhs) {
		return new UndifinedType();
	}

	public Type and(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type and(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type and(TextType lhs) {
		return new UndifinedType();
	}

	public Type equal(Type rhs) {
		return new UndifinedType();
	}

	public Type equal(NumberType lhs) {
		return new UndifinedType();
	}

	public Type equal(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type equal(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type equal(TextType lhs) {
		return new UndifinedType();
	}

	public Type notEqual(Type rhs) {
		return new UndifinedType();
	}

	public Type notEqual(NumberType lhs) {
		return new UndifinedType();
	}

	public Type notEqual(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type notEqual(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type notEqual(TextType lhs) {
		return new UndifinedType();
	}

	public Type greaterOrEqual(Type rhs) {
		return new UndifinedType();
	}

	public Type greaterOrEqual(NumberType lhs) {
		return new UndifinedType();
	}

	public Type greaterOrEqual(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type greaterOrEqual(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type greaterOrEqual(TextType lhs) {
		return new UndifinedType();
	}

	public Type greater(Type rhs) {
		return new UndifinedType();
	}

	public Type greater(NumberType lhs) {
		return new UndifinedType();
	}

	public Type greater(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type greater(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type greater(TextType lhs) {
		return new UndifinedType();
	}

	public Type lowerOrEqual(Type rhs) {
		return new UndifinedType();
	}

	public Type lowerOrEqual(NumberType lhs) {
		return new UndifinedType();
	}

	public Type lowerOrEqual(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type lowerOrEqual(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type lowerOrEqual(TextType lhs) {
		return new UndifinedType();
	}

	public Type lower(Type rhs) {
		return new UndifinedType();
	}

	public Type lower(NumberType lhs) {
		return new UndifinedType();
	}

	public Type lower(MoneyType lhs) {
		return new UndifinedType();
	}

	public Type lower(BooleanType lhs) {
		return new UndifinedType();
	}

	public Type lower(TextType lhs) {
		return new UndifinedType();
	}

	public Type not() {
		return new UndifinedType();
	}

}

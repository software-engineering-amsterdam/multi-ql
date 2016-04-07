package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.RadioInput;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class BooleanType extends Type {

	@Override
	public Widget prefered(Question question) {
		return new RadioInput(question);
	}

	@Override
	public String toString() {
		return "Boolean";
	}

	@Override
	public Value parse(String value) {
		if (value.matches("[Tt]rue|[Yy]es")) {
			return new BooleanValue(true);
		} else if (value.matches("[Tt]rue|[Yy]es")) {
			return new BooleanValue(false);
		} else {
			return new EmptyValue();
		}
	}

	@Override
	public Type absolute() {
		return new BooleanType();
	}

	@Override
	public Type negate() {
		return new BooleanType();
	}

	@Override
	public Type or(Type type) {
		return type.or(this);
	}

	@Override
	public Type or(BooleanType type) {
		return new BooleanType();
	}

	@Override
	public Type and(Type type) {
		return type.and(this);
	}

	@Override
	public Type and(BooleanType type) {
		return new BooleanType();
	}

	@Override
	public Type equal(Type type) {
		return type.equal(this);
	}

	@Override
	public Type equal(BooleanType type) {
		return new BooleanType();
	}

	@Override
	public Type notEqual(Type type) {
		return type.notEqual(this);
	}

	@Override
	public Type notEqual(BooleanType type) {
		return new BooleanType();
	}

	@Override
	public Type not() {
		return new BooleanType();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof BooleanType;
	}

	@Override
	public int hashCode() {
		return 13;
	}

}

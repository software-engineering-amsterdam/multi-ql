package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.TextValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.TextInput;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class TextType extends Type {

	@Override
	public Widget prefered(Question question) {
		return new TextInput(question);
	}

	@Override
	public String toString() {
		return "String";
	}

	@Override
	public Value value(String string) {
		return new TextValue(string);
	}

	@Override
	public Type add(Type rhs) {
		return rhs.add(this);
	}

	@Override
	public Type add(TextType lhs) {
		return new TextType();
	}

	@Override
	public Type equal(Type rhs) {
		return rhs.equal(this);
	}

	@Override
	public Type equal(TextType lhs) {
		return new BooleanType();
	}

	@Override
	public Type notEqual(Type rhs) {
		return rhs.notEqual(this);
	}

	@Override
	public Type notEqual(TextType lhs) {
		return new BooleanType();
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof TextType;
	}

	@Override
	public int hashCode() {
		return 13;
	}

}

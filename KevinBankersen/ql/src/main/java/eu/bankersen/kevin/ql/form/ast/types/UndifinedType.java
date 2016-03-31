package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.statements.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class UndifinedType extends Type {

	@Override
	public String toString() {
		return "Unknown Type";
	}

	@Override
	public Value value(String string) {
		return new EmptyValue();
	}

	@Override
	public Widget prefered(Question question) {
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof UndifinedType;
	}

	@Override
	public int hashCode() {
		return 13;
	}
}

package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class UserQuestion extends Question {

	public UserQuestion(String name, String text, Type type, int line) {
		super(name, text, type, line);
	}

	@Override
	public boolean isComputed() {
		return false;
	}

	@Override
	public Widget widget() {
		return type().prefered(this);
	}

	@Override
	public <T> void accept(Visitor<T> visitor, T context) {
		visitor.visit(this, context);
	}
}

package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.expressions.Expression;
import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class ComputedQuestion extends Question {

	private final Expression computation;

	public ComputedQuestion(String name, String text, Expression computation, Type type, int line) {
		super(name, text, type, line);
		this.computation = computation;
	}

	public Expression computation() {
		return computation;
	}

	@Override
	public boolean isComputed() {
		return true;
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

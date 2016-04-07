package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public abstract class Question extends Statement {

	private final String name;
	private final String question;
	private final Type type;

	public Question(String name, String question, Type type, int line) {
		super(line);
		this.name = name;
		this.question = question;
		this.type = type;
	}

	@Override
	public String toString() {
		return question;
	}

	public String name() {
		return name;
	}

	public Type type() {
		return type;
	}

	public abstract Widget widget();

	public abstract boolean isComputed();

}

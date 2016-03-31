package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.types.Type;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public abstract class Question extends Statement {

	private final String name;
	private final String text;
	private final Type type;

	public Question(String name, String text, Type type, int line) {
		super(line);
		this.name = name;
		this.text = text;
		this.type = type;
	}

	public String text() {
		return text;
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

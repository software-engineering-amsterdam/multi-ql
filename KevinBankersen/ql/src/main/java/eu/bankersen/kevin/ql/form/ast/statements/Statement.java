package eu.bankersen.kevin.ql.form.ast.statements;

import eu.bankersen.kevin.ql.form.ast.Node;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitable;

public abstract class Statement extends Node implements Visitable {

	public Statement(int line) {
		super(line);
	}

}

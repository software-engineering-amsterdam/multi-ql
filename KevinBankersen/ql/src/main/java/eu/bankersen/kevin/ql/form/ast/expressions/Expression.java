package eu.bankersen.kevin.ql.form.ast.expressions;

import eu.bankersen.kevin.ql.form.ast.Node;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.Visitable;

public abstract class Expression extends Node implements Visitable {

	public Expression(int line) {
		super(line);
	}

}
package eu.bankersen.kevin.ql.form.ast.statements;

import java.util.Iterator;
import java.util.List;

import eu.bankersen.kevin.ql.form.ast.Node;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitable;
import eu.bankersen.kevin.ql.form.ast.visitors.Visitor;

public class Body extends Node implements Visitable {

	private final List<Statement> statements;

	public Body(List<Statement> statements, int line) {
		super(line);
		this.statements = statements;
	}

	public Iterator<Statement> statements() {
		return statements.iterator();
	}

	@Override
	public <T> void accept(Visitor<T> visitor, T context) {
		visitor.visit(this, context);
	}
}

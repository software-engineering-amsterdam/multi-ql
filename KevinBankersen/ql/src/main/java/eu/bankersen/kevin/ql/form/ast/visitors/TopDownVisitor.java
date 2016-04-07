package eu.bankersen.kevin.ql.form.ast.visitors;

import eu.bankersen.kevin.ql.form.ast.Body;
import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Statement;

public abstract class TopDownVisitor<T> implements Visitor<T> {

	@Override
	public void visit(Form form, T empty) {
		form.body().accept(this, empty);
	}

	@Override
	public void visit(Body body, T empty) {
		for (Statement statement : body) {
			statement.accept(this, empty);
		}
	}

	@Override
	public void visit(IFStatement statement, T empty) {
		statement.body().accept(this, empty);
	}

	@Override
	public void visit(ElseStatement statement, T empty) {
		statement.body().accept(this, empty);
		statement.elseBody().accept(this, empty);
	}
}

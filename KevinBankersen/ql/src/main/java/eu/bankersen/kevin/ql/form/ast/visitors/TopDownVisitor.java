package eu.bankersen.kevin.ql.form.ast.visitors;

import java.util.Iterator;

import eu.bankersen.kevin.ql.form.ast.statements.Body;
import eu.bankersen.kevin.ql.form.ast.statements.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
import eu.bankersen.kevin.ql.form.ast.statements.IFStatement;
import eu.bankersen.kevin.ql.form.ast.statements.Statement;

public abstract class TopDownVisitor<T> implements Visitor<T> {

	@Override
	public void visit(Form o, T empty) {
		o.body().accept(this, empty);
	}

	@Override
	public void visit(Body o, T empty) {
		Iterator<Statement> statements = o.statements();
		while (statements.hasNext()) {
			statements.next().accept(this, empty);
		}
	}

	@Override
	public void visit(IFStatement o, T empty) {
		o.body().accept(this, empty);
	}

	@Override
	public void visit(ElseStatement o, T empty) {
		o.body().accept(this, empty);
		o.elseBody().accept(this, empty);
	}
}

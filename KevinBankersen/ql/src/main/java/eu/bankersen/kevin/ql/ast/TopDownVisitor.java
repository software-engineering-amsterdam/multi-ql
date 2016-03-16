package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;

public class TopDownVisitor<T> implements QuestionVisitor<T> {

    @Override
    public T visit(Form o, T empty) {
	o.body().accept(this, empty);
	return null;
    }

    @Override
    public T visit(Body o, T empty) {
	for (AbstractStatement s : o.statements()) {
	    s.accept(this, empty);
	}
	return null;
    }

    @Override
    public T visit(NormalQuestion o, T empty) {
	return null;
    }

    @Override
    public T visit(ComputedQuestion o, T empty) {
	return null;
    }

    @Override
    public T visit(IFStatement o, T empty) {
	o.body().accept(this, empty);
	return null;
    }

    @Override
    public T visit(ElseStatement o, T empty) {
	o.body().accept(this, empty);
	o.elseBody().accept(this, empty);
	return null;
    }
}

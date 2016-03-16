package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;

public abstract class TopDownQuestionVisitor<T> implements QuestionVisitor<T> {

    @Override
    public void visit(Form o, T empty) {
	o.body().accept(this, empty);
    }

    @Override
    public void visit(Body o, T empty) {
	for (AbstractStatement s : o.statements()) {
	    s.accept(this, empty);
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

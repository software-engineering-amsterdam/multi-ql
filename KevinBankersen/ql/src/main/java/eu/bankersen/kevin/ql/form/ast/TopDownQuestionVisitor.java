package eu.bankersen.kevin.ql.form.ast;

import eu.bankersen.kevin.ql.form.ast.form.Body;
import eu.bankersen.kevin.ql.form.ast.form.Form;
import eu.bankersen.kevin.ql.form.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.form.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.form.ast.stat.Statement;

public abstract class TopDownQuestionVisitor<T> implements QuestionVisitor<T> {

    @Override
    public void visit(Form o, T empty) {
	o.body().accept(this, empty);
    }

    @Override
    public void visit(Body o, T empty) {
	for (Statement s : o.statements()) {
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

package eu.bankersen.kevin.ql.ast;

import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.ast.stat.AbstractQuestion;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.ast.stat.ComputedQuestion;
import eu.bankersen.kevin.ql.ast.stat.ElseStatement;
import eu.bankersen.kevin.ql.ast.stat.IFStatement;
import eu.bankersen.kevin.ql.ast.stat.NormalQuestion;

public abstract class AbstractVisitor<T, U> implements BasicVisitor<T, U> {

    @Override
    public  T visit(Form o, U context) {	
	o.body().accept(this, null);
	return null;
    }

    @Override
    public void visit(Body o) {
	o.statements().forEach(s -> s.accept(this, null));
    }

    @Override
    public void visit(AbstractStatement o) {
    }

    @Override
    public void visit(ElseStatement o) {
	o.body().statements().forEach(s -> s.accept(this, null));
	o.elseBody().statements().forEach(s -> s.accept(this, null));
    }

    @Override
    public void visit(IFStatement o) {
	o.body().statements().forEach(s -> s.accept(this, null));
    }

    @Override
    public void visit(AbstractQuestion o) {
    }

    @Override
    public void visit(NormalQuestion o) {
    }

    @Override
    public void visit(ComputedQuestion o) {
    }

    @Override
    public void visit(Expr expr) {
    }

    @Override
    public void visit(Identifier o) {
    }

    @Override
    public void visit(Literal o) {
    }
}

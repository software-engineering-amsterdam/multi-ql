package eu.bankersen.kevin.ql.form.ast.stat;

import eu.bankersen.kevin.ql.form.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.form.Body;

public class IFStatement extends Statement {

    private final Expr condition;
    private final Body body;

    public IFStatement(Expr expr, Body body, int line) {
	super(line);
	this.body = body;
	this.condition = expr;
    }

    public Expr condition() {
	return condition;
    }

    public Body body() {
	return body;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

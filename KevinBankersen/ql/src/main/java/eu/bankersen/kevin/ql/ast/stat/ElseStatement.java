package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Body;

public class ElseStatement extends IFStatement {

    private final Body elseBody;

    public ElseStatement(Expr expr, Body body, Body elseBody, int line) {
	super(expr, body, line);
	this.elseBody = elseBody;
    }

    public Body elseBody() {
	return elseBody;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

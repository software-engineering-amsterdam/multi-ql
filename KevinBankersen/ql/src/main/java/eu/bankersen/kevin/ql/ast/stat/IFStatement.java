package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.form.Body;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class IFStatement extends AbstractStatement {

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

    public boolean evalCondition(Environment context) {

	QLValue result = condition.eval(context);

	if (result.equals(new UndifinedValue())) {
	    return false;
	} else {
	    return (Boolean) result.value();
	}
    }

    @Override
    public Environment evalStatement(Environment context) {

	if (context.isEnvironmentActive()) {
	    Boolean active = evalCondition(context);
	    context.setEnvironmentActive(active);
	    body.evalBody(context);
	    context.setEnvironmentActive(true);
	} else {
	    body.evalBody(context);
	}
	return context;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

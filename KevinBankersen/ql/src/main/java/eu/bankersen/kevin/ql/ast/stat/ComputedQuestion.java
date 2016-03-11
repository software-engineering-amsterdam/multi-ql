package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.BaseVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.object.type.QLType;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class ComputedQuestion extends NormalQuestion {

    private final Expr computation;

    public ComputedQuestion(String name, String text, Expr computation, QLType type, int line) {
	super(name, text, type, line);
	this.computation = computation;
    }

    public Expr computation() {
	return computation;
    }

    @Override
    public Environment evalStatement(Environment context) {
	QLValue result = computation.eval(context);
	context.updateQuestion(name(), result);
	return context;
    }

    @Override
    public <T> T accept(BaseVisitor<T> v, T context) {
	return v.visit(this, context);
    }
}

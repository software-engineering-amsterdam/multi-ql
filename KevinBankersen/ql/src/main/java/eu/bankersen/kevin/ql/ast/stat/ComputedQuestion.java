package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;
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
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

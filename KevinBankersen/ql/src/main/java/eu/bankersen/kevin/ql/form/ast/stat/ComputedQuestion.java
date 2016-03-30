package eu.bankersen.kevin.ql.form.ast.stat;

import eu.bankersen.kevin.ql.form.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.expr.Expr;
import eu.bankersen.kevin.ql.form.ast.types.Type;

public class ComputedQuestion extends Question {

    private final Expr computation;

    public ComputedQuestion(String name, String text, Expr computation, Type type, int line) {
	super(name, text, type, line);
	this.computation = computation;
    }

    @Override
    public boolean isComputed() {
	return true;
    }

    public Expr computation() {
	return computation;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

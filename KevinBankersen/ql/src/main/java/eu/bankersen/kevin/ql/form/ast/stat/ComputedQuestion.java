package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.expr.Expr;
import eu.bankersen.kevin.ql.ast.types.QLType;

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
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

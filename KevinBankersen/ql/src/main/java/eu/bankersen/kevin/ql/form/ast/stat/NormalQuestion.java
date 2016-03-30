package eu.bankersen.kevin.ql.form.ast.stat;

import eu.bankersen.kevin.ql.form.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.types.Type;

public class NormalQuestion extends Question {

    public NormalQuestion(String name, String text, Type type, int line) {
	super(name, text, type, line);
    }

    @Override
    public boolean isComputed() {
	return false;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class NormalQuestion extends AbstractStatement {
    private final String name;
    private final String text;
    private final QLType type;

    public NormalQuestion(String name, String text, QLType type, int line) {
	super(line);
	this.name = name;
	this.text = text;
	this.type = type;
    }

    public String text() {
	return text;
    }

    public String name() {
	return name;
    }

    public QLType type() {
	return type;
    }

    @Override
    public Environment evalStatement(Environment context) {
	context.updateQuestion(name());
	return context;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

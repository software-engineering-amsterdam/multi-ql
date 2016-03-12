package eu.bankersen.kevin.ql.ast.form;

import java.util.List;

import eu.bankersen.kevin.ql.ast.BaseVisitor;
import eu.bankersen.kevin.ql.ast.BaseVisitorAccept;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;
import eu.bankersen.kevin.ql.interpreter.Environment;

public class Body implements BaseVisitorAccept {

    private final List<AbstractStatement> statements;

    public Body(List<AbstractStatement> statements) {
	this.statements = statements;
    }

    public List<AbstractStatement> statements() {
	return statements;
    }

    public Environment evalBody(Environment context) {

	for (AbstractStatement s : statements) {
	    context = s.evalStatement(context);
	}
	return context;
    }

    @Override
    public <T> T accept(BaseVisitor<T> v, T context) {
	return v.visit(this, context);
    }
}

package eu.bankersen.kevin.ql.ast.form;

import java.util.List;

import eu.bankersen.kevin.ql.ast.AcceptQuestionVisitor;
import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.stat.AbstractStatement;

public class Body implements AcceptQuestionVisitor {

    private final List<AbstractStatement> statements;

    public Body(List<AbstractStatement> statements) {
	this.statements = statements;
    }

    public List<AbstractStatement> statements() {
	return statements;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

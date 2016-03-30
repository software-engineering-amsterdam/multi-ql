package eu.bankersen.kevin.ql.form.ast.form;

import java.util.List;

import eu.bankersen.kevin.ql.form.ast.AcceptQuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.form.ast.stat.Statement;

public class Body implements AcceptQuestionVisitor {

    private final List<Statement> statements;

    public Body(List<Statement> statements) {
	this.statements = statements;
    }

    public List<Statement> statements() {
	return statements;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }
}

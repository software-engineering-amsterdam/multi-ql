package eu.bankersen.kevin.ql.ast.form;

import eu.bankersen.kevin.ql.ast.AcceptQuestionVisitor;
import eu.bankersen.kevin.ql.ast.QuestionVisitor;

public class Form implements AcceptQuestionVisitor {

    private final String name;
    private final Body body;

    public Form(String name, Body body) {
	this.name = name;
	this.body = body;
    }

    public Body body() {
	return body;
    }

    public String name() {
	return name;
    }

    @Override
    public <T> void accept(QuestionVisitor<T> v, T context) {
	v.visit(this, context);
    }

}

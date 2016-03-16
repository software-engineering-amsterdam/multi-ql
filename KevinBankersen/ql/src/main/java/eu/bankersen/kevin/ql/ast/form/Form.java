package eu.bankersen.kevin.ql.ast.form;

import eu.bankersen.kevin.ql.ast.QuestionVisitor;
import eu.bankersen.kevin.ql.ast.AcceptQuestionVisitor;
import eu.bankersen.kevin.ql.interpreter.Environment;

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

    public Environment evalForm(Environment context) {
	return body.evalBody(context);
    }

    @Override
    public <T> T accept(QuestionVisitor<T> v, T context) {
	return v.visit(this, context);
    }

}

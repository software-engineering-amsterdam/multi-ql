package eu.bankersen.kevin.ql.form.ast.stat;

import eu.bankersen.kevin.ql.form.ast.AcceptQuestionVisitor;

public abstract class Statement implements AcceptQuestionVisitor {

    private final int line;

    public Statement(int line) {
	this.line = line;
    }

    public int line() {
	return line;
    }

}

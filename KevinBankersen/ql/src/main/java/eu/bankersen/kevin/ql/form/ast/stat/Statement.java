package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.AcceptQuestionVisitor;

public abstract class AbstractStatement implements AcceptQuestionVisitor {

    private final int line;

    public AbstractStatement(int line) {
	this.line = line;
    }

    public int line() {
	return line;
    }

}

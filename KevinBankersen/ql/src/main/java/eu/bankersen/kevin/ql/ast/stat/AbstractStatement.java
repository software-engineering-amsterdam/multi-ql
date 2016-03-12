package eu.bankersen.kevin.ql.ast.stat;

import eu.bankersen.kevin.ql.ast.BaseVisitorAccept;
import eu.bankersen.kevin.ql.interpreter.Environment;

public abstract class AbstractStatement implements BaseVisitorAccept {

    private final int line;

    public AbstractStatement(int line) {
	this.line = line;
    }

    public int line() {
	return line;
    }

    public abstract Environment evalStatement(Environment context);

}

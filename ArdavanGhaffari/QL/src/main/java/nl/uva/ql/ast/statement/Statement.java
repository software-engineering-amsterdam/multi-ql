package nl.uva.ql.ast.statement;

import nl.uva.ql.ast.AbstractNode;
import nl.uva.ql.visitors.StatementVisitor;

public abstract class Statement extends AbstractNode {
	public Statement(int line) {
		super(line);
	}

	public abstract <T> T accept(StatementVisitor<T> statementVisitor);
}

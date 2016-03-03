package org.uva.ql.ast;

import org.uva.ql.ast.stat.QLIFStatement;

public interface QLIFStatementVisitor<T, U> {

	public T visit(QLIFStatement form, U Context);
}

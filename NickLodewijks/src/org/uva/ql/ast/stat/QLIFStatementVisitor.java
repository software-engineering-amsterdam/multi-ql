package org.uva.ql.ast.stat;

public interface QLIFStatementVisitor<T, U> {

	public T visit(QLIFStatement form, U Context);
}

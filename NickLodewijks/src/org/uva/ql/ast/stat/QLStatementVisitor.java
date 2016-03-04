package org.uva.ql.ast.stat;

public interface QLStatementVisitor<T, U> {

	public T visit(QLQuestionInput node, U context);

	public T visit(QLQuestionComputed node, U context);

	public T visit(QLIFStatement node, U context);

}

package org.uva.ql.ast.stat;

public interface QLQuestionVisitor<T, U> {

	public T visit(QLQuestionInput node, U context);

	public T visit(QLQuestionComputed node, U context);

}

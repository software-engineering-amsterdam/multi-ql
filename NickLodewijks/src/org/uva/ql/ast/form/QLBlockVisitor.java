package org.uva.ql.ast.form;

public interface QLBlockVisitor<T, U> {

	public T visit(QLBlock node, U context);
}

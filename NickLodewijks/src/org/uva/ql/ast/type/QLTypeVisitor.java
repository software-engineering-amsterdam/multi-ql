package org.uva.ql.ast.type;

public interface QLTypeVisitor<T, U> {

	public T visit(QLBooleanType type, U context);

	public T visit(QLStringType type, U context);

	public T visit(QLIntegerType type, U context);
}

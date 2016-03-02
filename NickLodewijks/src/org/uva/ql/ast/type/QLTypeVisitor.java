package org.uva.ql.ast.type;

public interface QLTypeVisitor<T> {

	public T visit(QLBooleanType type);

	public T visit(QLStringType type);

	public T visit(QLIntegerType type);
}

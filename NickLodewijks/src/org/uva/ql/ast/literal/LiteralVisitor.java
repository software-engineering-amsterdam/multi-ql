package org.uva.ql.ast.literal;

public interface LiteralVisitor<T, U> {

	public T visit(BooleanLiteral node, U context);

	public T visit(StringLiteral node, U context);

	public T visit(IntegerLiteral node, U context);

}

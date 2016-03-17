package sc.ql.ast;

import sc.ql.ast.Literal.BooleanLiteral;
import sc.ql.ast.Literal.IntegerLiteral;
import sc.ql.ast.Literal.StringLiteral;

public interface LiteralVisitor<T, U> {

	public T visit(BooleanLiteral literal, U context);

	public T visit(StringLiteral literal, U context);

	public T visit(IntegerLiteral literal, U context);

}

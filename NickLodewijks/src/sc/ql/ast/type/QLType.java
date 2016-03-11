package sc.ql.ast.type;

import sc.ql.ast.ASTNode;

public abstract class QLType extends ASTNode {

	public static final QLType BOOLEAN = new QLBooleanType();
	public static final QLType STRING = new QLStringType();
	public static final QLType INTEGER = new QLIntegerType();

	public abstract <T, U> T accept(QLTypeVisitor<T, U> visitor, U context);
}

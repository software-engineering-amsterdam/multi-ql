package sc.ql.ast.type;

import sc.ql.ast.ASTNode;

public abstract class ValueType extends ASTNode {

	public static final ValueType BOOLEAN = new BooleanType();
	public static final ValueType STRING = new StringType();
	public static final ValueType INTEGER = new IntegerType();

	public abstract <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context);
}

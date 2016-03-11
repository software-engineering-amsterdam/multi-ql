package sc.ql.ast.type;

public interface ValueTypeVisitor<T, U> {

	public T visit(BooleanType type, U context);

	public T visit(StringType type, U context);

	public T visit(IntegerType type, U context);
}

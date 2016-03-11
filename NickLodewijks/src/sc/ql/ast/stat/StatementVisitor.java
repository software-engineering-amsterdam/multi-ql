package sc.ql.ast.stat;

public interface StatementVisitor<T, U> {

	public T visit(NormalQuestion node, U context);

	public T visit(ComputedQuestion node, U context);

	public T visit(IFStatement node, U context);

}

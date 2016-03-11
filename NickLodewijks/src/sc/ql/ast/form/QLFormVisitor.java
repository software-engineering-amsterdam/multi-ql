package sc.ql.ast.form;

public interface QLFormVisitor<T, U> {

	public T visit(QLForm form, U Context);

	public T visit(QLBlock node, U context);
}

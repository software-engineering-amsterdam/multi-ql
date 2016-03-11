package sc.ql.ast.form;

public interface FormVisitor<T, U> {

	public T visit(Form form, U Context);

	public T visit(Block node, U context);
}

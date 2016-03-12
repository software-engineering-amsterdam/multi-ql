package sc.ql.ast;

public interface FormVisitor<T, U> {

	public T visit(Form form, U Context);
}

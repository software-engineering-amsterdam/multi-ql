package org.uva.ql.ast.form;

public interface QLFormVisitor<T, U> {

	public T visit(QLForm form, U Context);
}

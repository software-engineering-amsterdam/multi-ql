package org.uva.ql.ast;

import org.uva.ql.ast.form.QLForm;

public interface QLFormVisitor<T, U> {

	public T visit(QLForm form, U Context);
}

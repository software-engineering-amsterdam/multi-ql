package org.uva.ql.ast;

import org.uva.ql.ast.form.QLBlock;

public interface QLBlockVisitor<T, U> {

	public T visit(QLBlock node, U context);
}

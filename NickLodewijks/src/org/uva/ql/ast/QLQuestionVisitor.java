package org.uva.ql.ast;

import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;

public interface QLQuestionVisitor<T, U> {

	public T visit(QLQuestionInput node, U context);

	public T visit(QLQuestionComputed node, U context);

}

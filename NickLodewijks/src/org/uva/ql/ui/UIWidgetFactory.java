package org.uva.ql.ui;

import org.uva.ql.ast.expr.Expr;

@FunctionalInterface
public interface UIWidgetFactory {

	public UIWidget create(String id, Expr expr);
}
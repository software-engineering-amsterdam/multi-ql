package org.uva.ql.ui;

import org.uva.ql.ast.stat.QLQuestion;

@FunctionalInterface
public interface UIWidgetFactory {

	public UIWidget create(QLQuestion q);
}
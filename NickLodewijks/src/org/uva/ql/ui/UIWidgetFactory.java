package org.uva.ql.ui;

import org.uva.ql.domain.Question;

@FunctionalInterface
public interface UIWidgetFactory {

	public UIWidget create(Question q);
}
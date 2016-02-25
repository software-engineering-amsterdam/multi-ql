package org.uva.ql.ui;

import org.uva.ql.domain.Question;

@FunctionalInterface
public interface UIWidgetFactory<T extends UIWidget> {

	public T create(Question question);
}
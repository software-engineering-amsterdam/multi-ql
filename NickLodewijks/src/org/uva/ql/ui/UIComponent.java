package org.uva.ql.ui;

import org.uva.ql.QLInterpreterContext;

public interface UIComponent<T> {

	public void setContext(QLInterpreterContext context);

	public T getComponent();

}
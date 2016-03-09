package org.uva.ql.ui;

import org.uva.ql.UIContext;

public interface UIQuestion {

	public void setContext(UIContext context);

	public UIWidget getLabelWidget();

	public UIWidget getValueWidget();

}

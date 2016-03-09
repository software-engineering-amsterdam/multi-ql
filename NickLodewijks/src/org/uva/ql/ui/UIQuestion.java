package org.uva.ql.ui;

import org.uva.ql.QLContext;

public interface UIQuestion {

	public void setContext(QLContext context);

	public UIWidget getLabelWidget();

	public UIWidget getValueWidget();

}

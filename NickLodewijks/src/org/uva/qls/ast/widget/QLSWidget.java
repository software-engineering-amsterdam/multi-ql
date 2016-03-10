package org.uva.qls.ast.widget;

import org.uva.qls.ast.QLSASTNode;

public class QLSWidget extends QLSASTNode {

	private final QLSWidgetType type;

	public QLSWidget(QLSWidgetType type) {
		this.type = type;
	}

	public QLSWidgetType getType() {
		return type;
	}
}

package org.uva.sea.ql.gui.panels;

import org.uva.sea.ql.ast.statement.IfElseStatement;
import org.uva.sea.ql.gui.widgets.Widget;

// needed or not?
// extends IfStatementPanel

public class IfElseStatementPanel extends Panel {

	private IfElseStatement ifStatement;
	private Widget widget;
	
	
	public IfElseStatement getIfElseStatement() {
		return ifStatement;
	}
	
	public Widget getWidget() {
		return widget;
	}
	
}

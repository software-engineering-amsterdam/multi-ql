package org.uva.sea.ql.gui.panels;

import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.gui.widgets.Widget;

// needed or not?
// extends IfQuestionPanel...

public class ComputedQuestionPanel extends Panel {

	private ComputedQuestion question;
	private Widget widget;
	
	
	public ComputedQuestion getQuestion() {
		return question;
	}
	
	
	public Widget getWidget() {
		return widget;
	}
	
}

package org.uva.sea.ql.gui.questionItems;

import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.evaluator.BoolValue;
import org.uva.sea.ql.gui.widgets.Widget;

public class BoolQuestionItem extends  QuestionItem {
	
	private BoolValue value;

	public BoolQuestionItem(Question question, Widget widget) {
		super(question, widget);
	}

	public BoolValue getValue() {
		return value;
	}

}

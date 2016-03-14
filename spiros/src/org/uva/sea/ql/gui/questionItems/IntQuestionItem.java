package org.uva.sea.ql.gui.questionItems;

import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.evaluator.IntValue;
import org.uva.sea.ql.gui.widgets.Widget;

public class IntQuestionItem extends QuestionItem {
	
	private IntValue value;

	public IntQuestionItem(Question question, Widget widget) {
		super(question, widget);
	}

	public IntValue getValue() {
		return value;
	}


}

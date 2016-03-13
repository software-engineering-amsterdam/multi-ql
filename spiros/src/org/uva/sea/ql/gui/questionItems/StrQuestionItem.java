package org.uva.sea.ql.gui.questionItems;

import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.evaluator.StrValue;
import org.uva.sea.ql.gui.widgets.Widget;

public class StrQuestionItem extends QuestionItem {

	private StrValue value;
	
	public StrQuestionItem(Question question, Widget widget) {
		super(question, widget);
	}

	public StrValue getValue() {
		return value;
	}

}

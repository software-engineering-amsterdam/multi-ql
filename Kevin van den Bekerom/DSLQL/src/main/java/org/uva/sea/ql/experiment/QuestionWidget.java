package org.uva.sea.ql.experiment;

import org.uva.sea.ql.ast.form.ValueMap;
import org.uva.sea.ql.ast.stat.ComputedQuestion;
import org.uva.sea.ql.value.Value;

public class QuestionWidget extends ValueObserver {
	ComputedQuestion compQuestion;
	String qID;
	
	public QuestionWidget(ComputedQuestion compQuestion) {
		this.compQuestion = compQuestion;
	}
	
	@Override
	public void update(ValueMap valueMap) {
		Value before = valueMap.getValueFromMap(qID);
		Value after = compQuestion.getExpr().eval(valueMap);
		System.out.println("value now:" + after.getValue());
	}

}

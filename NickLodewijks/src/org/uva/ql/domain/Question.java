package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uva.ql.QLInterpreterContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.type.QLType;

public class Question {

	private final QLQuestion qlQuestion;

	private final Expr valueComputation;

	private final List<QuestionCondition> conditions = new ArrayList<>();

	public Question(QLQuestionComputed question) {
		qlQuestion = question;
		valueComputation = question.expr();
	}

	public Question(QLQuestionInput question) {
		qlQuestion = question;
		valueComputation = null;
	}

	public String getId() {
		return qlQuestion.getId();
	}

	public String getLabel() {
		return qlQuestion.getLabel();
	}

	public boolean isComputed() {
		return valueComputation != null;
	}

	public Expr getExpr() {
		return valueComputation;
	}

	public QLType getType() {
		return qlQuestion.getType();
	}

	public boolean isEnabled(QLInterpreterContext context) {
		for (QuestionCondition condition : conditions) {
			if (!condition.evaluate(context)) {
				return false;
			}
		}

		return true;
	}

	public void addCondition(QuestionCondition condition) {
		conditions.add(condition);
	}

	public boolean isConditional() {
		return !conditions.isEmpty();
	}

	public List<QuestionCondition> getConditions() {
		return Collections.unmodifiableList(conditions);
	}
}

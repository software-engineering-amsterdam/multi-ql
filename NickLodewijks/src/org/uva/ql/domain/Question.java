package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uva.ql.QLInterpreterContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.type.QLType;

public class Question {

	private final String name;
	private final String label;
	private final QLType type;

	private final Expr valueComputation;

	private final List<QuestionCondition> conditions = new ArrayList<>();

	public Question(QLQuestionComputed question) {
		this(question.getId(), question.getLabel(), question.expr(), question.getType());
	}

	public Question(QLQuestionInput question) {
		this(question.getId(), question.getLabel(), null, question.getType());
	}

	private Question(String id, String label, Expr expr, QLType type) {
		this.name = id;
		this.label = label;
		this.valueComputation = expr;
		this.type = type;
	}

	public String getId() {
		return name;
	}

	public String getLabel() {
		return label;
	}

	public boolean isComputed() {
		return valueComputation != null;
	}

	public Expr getExpr() {
		return valueComputation;
	}

	public QLType getType() {
		return type;
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

package org.uva.ql.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.uva.ql.QLInterpreterContext;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.type.QLType;

public class Question {

	private final String id;
	private final QLType type;
	private final String label;

	private final Expr valueComputation;

	private final Set<QuestionCondition> conditions = new HashSet<>();

	public Question(QLQuestionComputed question) {
		id = question.getId();
		type = question.getType();
		label = question.getLabel();
		valueComputation = question.expr();
	}

	public Question(QLQuestionInput question) {
		id = question.getId();
		type = question.getType();
		label = question.getLabel();
		valueComputation = null;
	}

	public String getId() {
		return id;
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

	public void addConditions(QuestionCondition condition) {
		conditions.add(condition);
	}

	public void addConditions(QuestionConditions condition) {
		conditions.addAll(condition.getConditions());
	}

	public boolean isConditional() {
		return !conditions.isEmpty();
	}

	public Set<QuestionCondition> getConditions() {
		return Collections.unmodifiableSet(conditions);
	}

	@Override
	public boolean equals(Object obj) {
		Question other;

		if (!(obj instanceof Question)) {
			return false;
		}

		other = (Question) obj;

		if (!getId().equals(other.getId())) {
			return false;
		}

		if (!getLabel().equals(other.getLabel())) {
			return false;
		}

		if (!getType().equals(other.getType())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return getId().hashCode() + getLabel().hashCode() + getType().hashCode();
	}

	@Override
	public String toString() {
		return String.format("Question[%s, %s, %s]", getId(), getType(), getLabel());
	}
}

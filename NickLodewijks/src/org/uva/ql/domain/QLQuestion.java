package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uva.ql.ast.expr.Context;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.stat.ComputedQuestion;
import org.uva.ql.ast.stat.InputQuestion;
import org.uva.ql.ast.type.VariableType;

public class QLQuestion {

	private final String name;
	private final String label;
	private final VariableType type;

	private final Expr valueComputation;

	private final List<QLQuestionCondition> conditions = new ArrayList<>();

	public QLQuestion(ComputedQuestion question) {
		this(question.getId(), question.getLabel(), question.expr(), question.getType());
	}

	public QLQuestion(InputQuestion question) {
		this(question.getId(), question.getLabel(), null, question.getType());
	}

	private QLQuestion(String id, String label, Expr expr, VariableType type) {
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

	public VariableType getType() {
		return type;
	}

	public boolean isEnabled(Context context) {
		for (QLQuestionCondition condition : conditions) {
			if (!condition.evaluate(context)) {
				return false;
			}
		}

		return true;
	}

	public void addCondition(QLQuestionCondition condition) {
		conditions.add(condition);
	}

	public boolean isConditional() {
		return !conditions.isEmpty();
	}

	public List<QLQuestionCondition> getConditions() {
		return Collections.unmodifiableList(conditions);
	}
}

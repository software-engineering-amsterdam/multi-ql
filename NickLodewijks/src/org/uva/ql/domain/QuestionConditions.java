package org.uva.ql.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class QuestionConditions {

	private final Set<QuestionCondition> conditions;

	public QuestionConditions() {
		conditions = new HashSet<>();
	}

	public QuestionConditions copy() {
		QuestionConditions copy;

		copy = new QuestionConditions();
		copy.conditions.addAll(conditions);

		return copy;
	}

	public void add(QuestionCondition condition) {
		conditions.add(condition);
	}

	public Set<QuestionCondition> getConditions() {
		return Collections.unmodifiableSet(conditions);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuestionConditions)) {
			return false;
		}

		return conditions.equals(((QuestionConditions) obj).conditions);
	}

	@Override
	public int hashCode() {
		return 46;
	}
}

package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.uva.ql.ast.expr.Expr;

public class QLSection {

	private final Expr expr;
	private final List<QLQuestion> questions = new ArrayList<>();
	private final List<QLSection> subSections = new ArrayList<>();

	public QLSection(Expr expr) {
		this.expr = expr;
	}

	public Expr getExpr() {
		return expr;
	}

	public void addQuestion(QLQuestion question) {
		questions.add(question);
	}

	public void addSubSection(QLSection section) {
		subSections.add(section);
	}

	public List<QLQuestion> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<QLSection> getSections() {
		return Collections.unmodifiableList(subSections);
	}
}

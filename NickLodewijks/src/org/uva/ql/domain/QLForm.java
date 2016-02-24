package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QLForm {

	private final String name;
	private final List<QLQuestion> questions = new ArrayList<>();

	public QLForm(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addQuestion(QLQuestion question) {
		questions.add(question);
	}

	public List<QLQuestion> getQuestions() {
		return Collections.unmodifiableList(questions);
	}
}

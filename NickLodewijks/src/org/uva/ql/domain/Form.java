package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Form {

	private final String name;
	private final List<Question> questions = new ArrayList<>();

	public Form(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addQuestion(Question question) {
		questions.add(question);
	}

	public List<Question> getQuestions() {
		return Collections.unmodifiableList(questions);
	}
}

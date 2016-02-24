package org.uva.ql.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QLForm {

	private final String name;

	private final List<QLQuestion> questions = new ArrayList<QLQuestion>();
	private final List<QLSection> sections = new ArrayList<>();

	public QLForm(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addSection(QLSection section) {
		sections.add(section);
	}

	public void addQuestion(QLQuestion question) {
		questions.add(question);
	}

	public List<QLQuestion> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<QLSection> getSections() {
		return Collections.unmodifiableList(sections);
	}
}

package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.List;

import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLFormVisitor;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;

public class FormFactory implements QLFormVisitor<List<Question>, QuestionConditions> {

	public static Form create(QLForm qlForm) {
		List<Question> questions;
		Form form;

		form = new Form(qlForm.getName());

		questions = qlForm.accept(new FormFactory(), null);

		form.addAll(questions);

		return form;
	}

	private FormFactory() {

	}

	@Override
	public List<Question> visit(QLBlock node, QuestionConditions conditions) {
		List<Question> questions;

		questions = new ArrayList<>();

		for (QLQuestion qlQuestion : node.getQuestions()) {
			Question question;

			question = QuestionFactory.create(qlQuestion);

			question.addConditions(conditions);

			questions.add(question);
		}

		for (QLIFStatement ifStat : node.getIfStatements()) {
			QuestionConditions copy;

			copy = conditions.copy();
			copy.add(new QuestionCondition(ifStat.getExpr()));

			questions.addAll(ifStat.getBody().accept(this, copy));
		}

		return questions;
	}

	@Override
	public List<Question> visit(QLForm form, QuestionConditions Context) {
		List<Question> questions;

		questions = form.getBody().accept(new FormFactory(), new QuestionConditions());

		return questions;
	}

	@Override
	public List<Question> visit(QLQuestionnaire node, QuestionConditions Context) {
		return null;
	}
}
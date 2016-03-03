package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.List;

import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLBlockVisitor;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLFormVisitor;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLIFStatementVisitor;
import org.uva.ql.ast.stat.QLQuestion;

public class FormFactory implements QLFormVisitor<Form, Void>, QLIFStatementVisitor<List<Question>, QuestionConditions>,
		QLBlockVisitor<List<Question>, QuestionConditions> {

	public static Form create(QLForm form) {
		return form.accept(new FormFactory(), null);
	}

	private FormFactory() {

	}

	@Override
	public Form visit(QLForm qlForm, Void context) {
		List<Question> questions;
		Form form;

		form = new Form(qlForm.getName());

		questions = qlForm.getBody().accept(this, new QuestionConditions());

		form.addAll(questions);

		return form;
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
			questions.addAll(ifStat.accept(this, conditions));
		}

		return questions;
	}

	@Override
	public List<Question> visit(QLIFStatement ifStat, QuestionConditions conditions) {
		QuestionConditions copy;

		copy = conditions.copy();
		copy.add(new QuestionCondition(ifStat.getExpr()));

		return ifStat.getBody().accept(this, copy);
	}
}
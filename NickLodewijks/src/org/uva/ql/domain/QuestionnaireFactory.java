package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.List;

import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLFormVisitor;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.stat.QLStatementVisitor;

public class QuestionnaireFactory
		implements QLFormVisitor<List<Question>, QuestionConditions>, QLStatementVisitor<Question, Void> {

	public static Questionnaire create(QLQuestionnaire q) {
		Questionnaire questionnaire;

		questionnaire = new Questionnaire();

		q.getForms().forEach(qlForm -> {
			Form form;
			List<Question> questions;
			Form form1;

			form1 = new Form(qlForm.getName());

			questions = qlForm.accept(new QuestionnaireFactory(), null);

			form1.addAll(questions);

			form = form1;
			questionnaire.addForm(form);
		});

		return questionnaire;
	}

	private QuestionnaireFactory() {
	}

	@Override
	public List<Question> visit(QLQuestionnaire node, QuestionConditions Context) {
		return null;
	}

	@Override
	public List<Question> visit(QLForm form, QuestionConditions Context) {
		List<Question> questions;

		questions = form.getBody().accept(this, new QuestionConditions());

		return questions;
	}

	@Override
	public List<Question> visit(QLBlock node, QuestionConditions conditions) {
		List<Question> questions;

		questions = new ArrayList<>();

		for (QLQuestion qlQuestion : node.getQuestions()) {
			Question question;

			question = qlQuestion.accept(this, null);

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
	public Question visit(QLQuestionComputed node, Void context) {
		return new Question(node);
	}

	@Override
	public Question visit(QLQuestionInput node, Void context) {
		return new Question(node);
	}

	@Override
	public Question visit(QLIFStatement node, Void context) {
		return null;
	}
}

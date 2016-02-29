package org.uva.ql.domain;

import java.util.ArrayList;
import java.util.List;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.expr.Expr;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;

public class QuestionnaireFactory {

	public static Questionnaire create(QLQuestionnaire q) {
		Questionnaire questionnaire;

		questionnaire = new Questionnaire();

		for (QLForm form : q.getForms()) {
			FormBuilder builder;

			builder = new FormBuilder(form);
			form.accept(new QLFormBuilderVisitor(), builder);

			questionnaire.addForm(builder.build());
		}

		return questionnaire;
	}

	private static class FormBuilder {

		private final List<QuestionCondition> conditions;
		private final List<Question> questions;

		private final Form form;

		public FormBuilder(QLForm qlForm) {
			form = new Form(qlForm.getName());

			conditions = new ArrayList<>();
			questions = new ArrayList<>();
		}

		public FormBuilder(FormBuilder builder) {
			conditions = new ArrayList<>(builder.conditions);
			questions = new ArrayList<>(builder.questions);
			form = builder.form;
		}

		public void addCondition(Expr condition) {
			conditions.add(new QuestionCondition(condition));
		}

		private void add(Question question) {
			questions.add(question);
			conditions.stream().forEach(c -> question.addCondition(c));
			form.addQuestion(question);
		}

		public void add(QLQuestionComputed question) {
			add(new Question(question));
		}

		public void add(QLQuestionInput question) {
			add(new Question(question));
		}

		public Form build() {
			return form;
		}
	}

	private static class QLFormBuilderVisitor extends ASTNodeVisitorAdapter<Void, FormBuilder> {

		private QLFormBuilderVisitor() {

		}

		@Override
		public Void visit(QLIFStatement node, FormBuilder builder) {
			FormBuilder copyOfBuilder;

			// First make a copy, so that questions before this statement
			// do not get the condition of the statement.
			copyOfBuilder = new FormBuilder(builder);
			copyOfBuilder.addCondition(node.getExpr());

			node.getBody().accept(this, copyOfBuilder);
			return null;
		}

		@Override
		public Void visit(QLQuestionComputed node, FormBuilder builder) {
			builder.add(node);
			return null;
		}

		@Override
		public Void visit(QLQuestionInput node, FormBuilder builder) {
			builder.add(node);
			return null;
		}
	}
}

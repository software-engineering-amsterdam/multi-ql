package org.uva.ql.ui;

import java.util.Deque;
import java.util.LinkedList;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.form.QLBlock;
import org.uva.ql.ast.form.QLForm;
import org.uva.ql.ast.form.QLQuestionnaire;
import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.domain.Form;
import org.uva.ql.domain.Question;
import org.uva.ql.domain.QuestionCondition;
import org.uva.ql.domain.Questionnaire;

public class QLASTToUIVisitor {

	public static Questionnaire create(QLQuestionnaire q) {
		QLQuestionnaireBuilder builder;

		builder = new QLQuestionnaireBuilder(q);

		q.accept(new QLQuestionnaireVisitor(), builder);

		return builder.build();
	}

	private static class QLQuestionnaireBuilder {

		private Questionnaire questionaire;
		private Deque<Form> forms = new LinkedList<>();
		private Deque<QuestionCondition> conditions = new LinkedList<>();

		public QLQuestionnaireBuilder(QLQuestionnaire q) {
			questionaire = new Questionnaire();
		}

		public void begin(QLForm form) {
			Form qlForm;
			qlForm = new Form(form.getName());

			questionaire.addForm(qlForm);
			forms.push(qlForm);
		}

		public void end(QLForm form) {
			forms.pop();
		}

		public void begin(QLIFStatement ifstat) {
			conditions.push(new QuestionCondition(ifstat.getExpr()));
		}

		public void end(QLIFStatement ifstat) {
			conditions.pop();
		}

		private void add(Question qlQuestion) {
			conditions.stream().forEach(c -> qlQuestion.addCondition(c));
			forms.peek().addQuestion(qlQuestion);
		}

		public void add(QLQuestionComputed question) {
			add(new Question(question));
		}

		public void add(QLQuestionInput question) {
			add(new Question(question));
		}

		public Questionnaire build() {
			return questionaire;
		}
	}

	private static class QLQuestionnaireVisitor extends ASTNodeVisitorAdapter<Void, QLQuestionnaireBuilder> {

		private QLQuestionnaireVisitor() {

		}

		@Override
		public Void visit(QLForm node, QLQuestionnaireBuilder builder) {
			builder.begin(node);

			visit(node.getBody(), builder);

			builder.end(node);

			return null;
		}

		@Override
		public Void visit(QLIFStatement node, QLQuestionnaireBuilder builder) {
			builder.begin(node);
			visit(node.getBody(), builder);
			builder.end(node);

			return null;
		}

		@Override
		public Void visit(QLBlock node, QLQuestionnaireBuilder builder) {
			// First traverse the questions.
			for (QLQuestion q : node.getQuestions()) {
				q.accept(this, builder);
			}

			for (QLIFStatement statement : node.getIfStatements()) {
				statement.accept(this, builder);
			}

			return null;
		}

		@Override
		public Void visit(QLQuestionComputed node, QLQuestionnaireBuilder builder) {
			builder.add(node);
			return null;
		}

		@Override
		public Void visit(QLQuestionInput node, QLQuestionnaireBuilder builder) {
			builder.add(node);
			return null;
		}
	}
}

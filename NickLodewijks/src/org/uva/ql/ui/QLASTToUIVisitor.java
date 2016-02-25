package org.uva.ql.ui;

import java.util.Deque;
import java.util.LinkedList;

import org.uva.ql.ast.ASTNodeVisitorAdapter;
import org.uva.ql.ast.form.Block;
import org.uva.ql.ast.form.Form;
import org.uva.ql.ast.form.Questionnaire;
import org.uva.ql.ast.stat.ComputedQuestion;
import org.uva.ql.ast.stat.IFStat;
import org.uva.ql.ast.stat.InputQuestion;
import org.uva.ql.ast.stat.Question;
import org.uva.ql.domain.QLForm;
import org.uva.ql.domain.QLQuestion;
import org.uva.ql.domain.QLQuestionCondition;
import org.uva.ql.domain.QLQuestionaire;

public class QLASTToUIVisitor {

	public static QLQuestionaire create(Questionnaire q) {
		QLQuestionnaireBuilder builder;

		builder = new QLQuestionnaireBuilder(q);

		q.accept(new QLQuestionnaireVisitor(), builder);

		return builder.build();
	}

	private static class QLQuestionnaireBuilder {

		private QLQuestionaire questionaire;
		private Deque<QLForm> forms = new LinkedList<>();
		private Deque<QLQuestionCondition> conditions = new LinkedList<>();

		public QLQuestionnaireBuilder(Questionnaire q) {
			questionaire = new QLQuestionaire();
		}

		public void begin(Form form) {
			QLForm qlForm;
			qlForm = new QLForm(form.getName());

			questionaire.addForm(qlForm);
			forms.push(qlForm);
		}

		public void end(Form form) {
			forms.pop();
		}

		public void begin(IFStat ifstat) {
			conditions.push(new QLQuestionCondition(ifstat.getExpr()));
		}

		public void end(IFStat ifstat) {
			conditions.pop();
		}

		private void add(QLQuestion qlQuestion) {
			conditions.stream().forEach(c -> qlQuestion.addCondition(c));
			forms.peek().addQuestion(qlQuestion);
		}

		public void add(ComputedQuestion question) {
			add(new QLQuestion(question));
		}

		public void add(InputQuestion question) {
			add(new QLQuestion(question));
		}

		public QLQuestionaire build() {
			return questionaire;
		}
	}

	private static class QLQuestionnaireVisitor extends ASTNodeVisitorAdapter<Void, QLQuestionnaireBuilder> {

		private QLQuestionnaireVisitor() {

		}

		@Override
		public Void visit(Form node, QLQuestionnaireBuilder builder) {
			builder.begin(node);

			visit(node.getBody(), builder);

			builder.end(node);

			return null;
		}

		@Override
		public Void visit(IFStat node, QLQuestionnaireBuilder builder) {
			builder.begin(node);
			visit(node.getBody(), builder);
			builder.end(node);

			return null;
		}

		@Override
		public Void visit(Block node, QLQuestionnaireBuilder builder) {
			// First traverse the questions.
			for (Question q : node.getQuestions()) {
				q.accept(this, builder);
			}

			for (IFStat statement : node.getIfStatements()) {
				statement.accept(this, builder);
			}

			return null;
		}

		@Override
		public Void visit(ComputedQuestion node, QLQuestionnaireBuilder builder) {
			builder.add(node);
			return null;
		}

		@Override
		public Void visit(InputQuestion node, QLQuestionnaireBuilder builder) {
			builder.add(node);
			return null;
		}
	}
}

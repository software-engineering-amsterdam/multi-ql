package org.uva.ql.domain;

import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.stat.QLQuestionVisitor;

public class QuestionFactory implements QLQuestionVisitor<Question, Void> {

	public static Question create(QLQuestion question) {
		return question.accept(new QuestionFactory(), null);
	}

	private QuestionFactory() {

	}

	@Override
	public Question visit(QLQuestionComputed node, Void context) {
		return new Question(node);
	}

	@Override
	public Question visit(QLQuestionInput node, Void context) {
		return new Question(node);
	}
}
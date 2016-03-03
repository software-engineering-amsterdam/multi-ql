package org.uva.ql.domain;

import org.uva.ql.ast.stat.QLIFStatement;
import org.uva.ql.ast.stat.QLQuestion;
import org.uva.ql.ast.stat.QLQuestionComputed;
import org.uva.ql.ast.stat.QLQuestionInput;
import org.uva.ql.ast.stat.QLStatementVisitor;

public class QuestionFactory implements QLStatementVisitor<Question, Void> {

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

	@Override
	public Question visit(QLIFStatement node, Void context) {
		return null;
	}
}
package ql.ast.visitor;

import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;

public class QuestionVisitor<T> extends BasicVisitor<T> {
	private Context context;

	public QuestionVisitor(Context context) {
		this.context = context;
	}

	@Override
	public T visit(ComputedQuestion computedQuestion) {
		context.addQuestion(computedQuestion);
		return null;
	}

	@Override
	public T visit(InputQuestion inputQuestion) {
		context.addQuestion(inputQuestion);
		return null;
	}

	public Context getContext() {
		return context;
	}
}

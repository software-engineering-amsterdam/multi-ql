package ql.ast.visitor;

import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.InputQuestion;

public class DeclaredQuestionVisitor<T> extends BasicVisitor<T> {
	private Context context;

	public DeclaredQuestionVisitor(Context context) {
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

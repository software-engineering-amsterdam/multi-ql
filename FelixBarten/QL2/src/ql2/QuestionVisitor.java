package ql2;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;

public class QuestionVisitor<T> extends BaseVisitor<T> {
	
	private Context context; 
	
	public QuestionVisitor(Context context) {
		this.context = context;
	}

	@Override
	public T visit(InputQuestion node) {
		context.addQuestion(node);
		return null;
	}

	@Override
	public T visit(CalculatedQuestion node) {
		context.addQuestion(node);
		return null;
	}
	
	public Context getContext() {
		return context;
	}
	
	
}

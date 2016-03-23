package ql;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.Evaluation;

public class FormEvaluation {
	private Context context;

	public FormEvaluation(Context context) {
		this.context = context;
	}

	public void evaluateForm(Form form) {
		assert(context != null);
		Evaluation eval = new Evaluation(context);
		eval.visit(form);
	}

	// Only for debugging
	public void printData() {
		System.out.println("Identifier - value");
		context.getIdentifierToValueMap().forEach((identifier, value) -> System.out.println(identifier + ' ' + value));
		System.out.println();
	}

	public Context getContext() {
		return context;
	}

}

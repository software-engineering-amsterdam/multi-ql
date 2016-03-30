package ql;

import ql.ast.form.Form;
import ql.ast.visitor.Context;
import ql.ast.visitor.Evaluator;

public class FormEvaluator {
	private Context context;

	public FormEvaluator(Context context) {
		this.context = context;
	}

	public void evaluateForm(Form form) {
		assert(context != null);
		Evaluator eval = new Evaluator(context);
		eval.visit(form);
	}
	
	public Context getContext() {
		return context;
	}
}

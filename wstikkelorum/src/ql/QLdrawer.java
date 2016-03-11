package ql;

import ql.ast.form.Form;
import ql.ast.statement.Question;
import ql.ast.visitor.Context;
import ql.gui.QLFrame;

public class QLdrawer {
	private final Form form;
	private Context context;
	private QLFrame window;

	public QLdrawer(Form form, Context context) {
		this.form = form;
		this.context = context;
	}

	public void drawForm() {
		window = new QLFrame(form, context, this);
	}

	public void updateContextAndRedraw(Question question, Object value) {
		context.putValueForQuestion(question, value);
		window.updateQLFrame(context);
	}
}

package ql.ast.visitor;

import ql.ast.form.Form;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfStatement;
import ql.ast.statement.InputQuestion;
import ql.gui.UserInterface;
import ql.gui.VisibleUIElements;

public class GuiVisitor<T> extends Evaluator {
	private VisibleUIElements visibleQuestions;
	private UserInterface parent;

	public GuiVisitor(Context context, UserInterface parent) {
		super(context);
		this.parent = parent;
		visibleQuestions = new VisibleUIElements();
	}

	@Override
	public T visit(ComputedQuestion computedQuestion) {
		context.putValueForQuestion(computedQuestion, computedQuestion.getExpression().accept(this));
		visibleQuestions.addQuestion(computedQuestion);
		return null;
	}

	@Override
	public T visit(InputQuestion inputQuestion) {
		inputQuestion.getVariable().accept(this);
		visibleQuestions.addQuestion(inputQuestion, parent);
		return null;
	}

	@Override
	public T visit(IfStatement ifStatement) {
		Boolean condition = (Boolean) ifStatement.getCondition().accept(this);
		if (condition == null) {
			return null;
		}
		if (condition) {
			ifStatement.getBody().accept(this);
		}
		return null;
	}

	public VisibleUIElements getVisibleQuestions(Form form, Context newContext) {
		this.context = newContext;
		this.visit(form);
		return visibleQuestions;
	}

	public void setNewContext(Context newContext) {
		context = newContext;
	}
}

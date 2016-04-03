package ql.ast.visitor;

import ql.ast.form.Form;
import ql.ast.statement.IfStatement;
import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;
import ql.ast.value.BooleanValue;
import ql.gui.QLWindow;
import ql.gui.VisibleElements;

public class GuiVisitor<Value> extends Evaluator {
	private VisibleElements visibleQuestions;
	private QLWindow parent;

	public GuiVisitor(Context context, QLWindow parent) {
		super(context);
		this.parent = parent;
		visibleQuestions = new VisibleElements();
	}

	@Override
	public ql.ast.value.Value visit(ComputedQuestion computedQuestion) {
		context.putValueForQuestion(computedQuestion, computedQuestion.getExpression().accept(this));
		visibleQuestions.addQuestion(computedQuestion);
		return null;
	}

	@Override
	public ql.ast.value.Value visit(InputQuestion inputQuestion) {
		inputQuestion.getVariable().accept(this);
		visibleQuestions.addQuestion(inputQuestion, parent);
		return null;
	}

	@Override
	public ql.ast.value.Value visit(IfStatement ifStatement) {
		BooleanValue condition = (BooleanValue) ifStatement.getCondition().accept(this);
		if(condition == null){
			return null;
		}
		if (condition.getValue()) {
			ifStatement.getBody().accept(this);
		}
		return null;
	}

	public VisibleElements getVisibleQuestions(Form form, Context newContext) {
		this.context = newContext;
		this.visit(form);
		return visibleQuestions;
	}

	public void setNewContext(Context newContext) {
		context = newContext;
	}
}

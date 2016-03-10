package ql.ast.visitor;

import java.util.ArrayList;
import java.util.List;

import ql.QLdrawer;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfStatement;
import ql.ast.statement.InputQuestion;
import ql.gui.ComputedQuestionWidget;
import ql.gui.InputQuestionWidget;
import ql.gui.UIElement;

public class GuiVisitor<T> extends Evaluation {
	private List<UIElement> visibleQuestions;
	private QLdrawer qlDrawer;

	public GuiVisitor(Context context, QLdrawer qlDrawer) {
		super(context);
		this.qlDrawer = qlDrawer;
		visibleQuestions = new ArrayList<UIElement>();
	}

	@Override
	public T visit(ComputedQuestion computedQuestion) {
		Object value = computedQuestion.getExpression().accept(this);
		super.addValueForQuestion(computedQuestion, value);
		if (value == null) {
			visibleQuestions.add(new ComputedQuestionWidget(computedQuestion, null));
		} else {
			visibleQuestions.add(new ComputedQuestionWidget(computedQuestion, value.toString()));
		}
		return null;
	}

	@Override
	public T visit(InputQuestion inputQuestion) {
		Object value = inputQuestion.getVariable().accept(this);
		visibleQuestions.add(new InputQuestionWidget(inputQuestion, value, qlDrawer));
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

	public List<UIElement> getVisibleQuestions() {
		return visibleQuestions;
	}

	public void setNewContext(Context newContext) {
		context = newContext;
	}

}

package ql.ast.visitor;

import java.util.ArrayList;
import java.util.List;

import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfStatement;
import ql.ast.statement.InputQuestion;
import ql.gui.ComputedQuestionWidget;
import ql.gui.InputQuestionWidget;
import ql.gui.UIElement;

public class GuiVisitor<T> extends Evaluation{
	private List<UIElement> visibleQuestions;
	
	public GuiVisitor(Context context){
		super(context);
		visibleQuestions = new ArrayList<UIElement>();
	}
	
	@Override
	public T visit(ComputedQuestion computedQuestion){
		Object value = computedQuestion.getExpression().accept(this);
		super.addValueForQuestion(computedQuestion, value);
		visibleQuestions.add(new ComputedQuestionWidget(computedQuestion));
		return null;
	}
	
	@Override
	public T visit(InputQuestion inputQuestion){
		//TODO: get the value?? Store the value??
		visibleQuestions.add(new InputQuestionWidget(inputQuestion));
		return null;
	}
	
	@Override
	public T visit(IfStatement ifStatement){
		Boolean condition = (Boolean) ifStatement.getCondition().accept(this);
		if(condition == null){
			return null;
		}
		if(condition){
			ifStatement.getBody().accept(this);
		}
		return null;
	}
	
	public List<UIElement> getVisibleQuestions(){
		return visibleQuestions;
	}

}

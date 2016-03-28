package ql.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.InputQuestion;

public class VisibleQuestions {
	private List<UIElement> visibleQuestions;
	
	public VisibleQuestions(){
		visibleQuestions = new ArrayList<UIElement>();
	}
	
	public void addQuestion(InputQuestion inputQuestion, UserInterface parent){
		visibleQuestions.add(new InputQuestionWidget(inputQuestion, parent));
	}
	
	public void addQuestion(ComputedQuestion computedQuestion){
		visibleQuestions.add(new ComputedQuestionWidget(computedQuestion));
	}
	
	public int numberOfQuestions(){
		return visibleQuestions.size();
	}
	
	public void removeAllQuestions(){
		visibleQuestions.clear();
	}
	
	public Iterator<UIElement> getIterator(){
		return visibleQuestions.iterator();
	}
}

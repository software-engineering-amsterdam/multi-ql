package ql.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.InputQuestion;

public class VisibleQuestions {
	private List<UIElement> visibleQuestions;
	private List<InputQuestionWidget> inputQuestions;
	private List<ComputedQuestionWidget> computedQuestions;
	
	public VisibleQuestions(){
		visibleQuestions = new ArrayList<UIElement>();
		inputQuestions = new ArrayList<InputQuestionWidget>();
		computedQuestions = new ArrayList<ComputedQuestionWidget>();
	}
	
	public void addQuestion(InputQuestion inputQuestion, UserInterface parent){
		InputQuestionWidget questionWidget = new InputQuestionWidget(inputQuestion, parent);
		visibleQuestions.add(questionWidget);
		inputQuestions.add(questionWidget);
	}
	
	public void addQuestion(ComputedQuestion computedQuestion){
		ComputedQuestionWidget questionWidget = new ComputedQuestionWidget(computedQuestion);
		visibleQuestions.add(questionWidget);
		computedQuestions.add(questionWidget);
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
	
	public Iterator<InputQuestionWidget> getInputQuestionsIterator(){
		return inputQuestions.iterator();
	}
	
	public Iterator<ComputedQuestionWidget> getComputedQuestionsIterator(){
		return computedQuestions.iterator();
	}
}

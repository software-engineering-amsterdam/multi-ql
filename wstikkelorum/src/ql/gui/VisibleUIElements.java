package ql.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.InputQuestion;
import ql.issue.Issue;

public class VisibleUIElements {
	private List<UIElement> visibleUIElements;
	private List<InputQuestionWidget> inputQuestions;
	private List<ComputedQuestionWidget> computedQuestions;
	
	public VisibleUIElements(){
		visibleUIElements = new ArrayList<UIElement>();
		inputQuestions = new ArrayList<InputQuestionWidget>();
		computedQuestions = new ArrayList<ComputedQuestionWidget>();
	}
	
	public void addIssue(Issue issue){
		visibleUIElements.add(issue);
	}
	
	public void addQuestion(InputQuestion inputQuestion, UserInterface parent){
		InputQuestionWidget questionWidget = new InputQuestionWidget(inputQuestion, parent);
		visibleUIElements.add(questionWidget);
		inputQuestions.add(questionWidget);
	}
	
	public void addQuestion(ComputedQuestion computedQuestion){
		ComputedQuestionWidget questionWidget = new ComputedQuestionWidget(computedQuestion);
		visibleUIElements.add(questionWidget);
		computedQuestions.add(questionWidget);
	}
	
	public int numberOfUIElements(){
		return visibleUIElements.size();
	}
	
	public void removeAllUIElements(){
		visibleUIElements.clear();
		inputQuestions.clear();
		computedQuestions.clear();
	}
	
	public Iterator<UIElement> getIterator(){
		return visibleUIElements.iterator();
	}
	
	public Iterator<InputQuestionWidget> getInputQuestionsIterator(){
		return inputQuestions.iterator();
	}
	
	public Iterator<ComputedQuestionWidget> getComputedQuestionsIterator(){
		return computedQuestions.iterator();
	}
}

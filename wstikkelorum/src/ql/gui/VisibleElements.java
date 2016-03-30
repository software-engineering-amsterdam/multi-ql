package ql.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;
import ql.gui.questionWidgets.ComputedQuestionWidget;
import ql.gui.questionWidgets.InputQuestionWidget;
import ql.issue.Issue;

public class VisibleElements {
	private List<DrawableElement> visibleUIElements;
	private List<InputQuestionWidget> inputQuestions;
	private List<ComputedQuestionWidget> computedQuestions;
	
	public VisibleElements(){
		visibleUIElements = new ArrayList<DrawableElement>();
		inputQuestions = new ArrayList<InputQuestionWidget>();
		computedQuestions = new ArrayList<ComputedQuestionWidget>();
	}
	
	public void addIssue(Issue issue){
		visibleUIElements.add(issue);
	}
	
	public void addQuestion(InputQuestion inputQuestion, QLWindow parent){
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
	
	public Iterator<DrawableElement> getIterator(){
		return visibleUIElements.iterator();
	}
	
	public Iterator<InputQuestionWidget> getInputQuestionsIterator(){
		return inputQuestions.iterator();
	}
	
	public Iterator<ComputedQuestionWidget> getComputedQuestionsIterator(){
		return computedQuestions.iterator();
	}
}

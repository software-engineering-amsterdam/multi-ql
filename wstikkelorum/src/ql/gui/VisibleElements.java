package ql.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ql.ast.statement.question.ComputedQuestion;
import ql.ast.statement.question.InputQuestion;
import ql.gui.questionWidget.ComputedQuestionWidget;
import ql.gui.questionWidget.InputQuestionWidget;
import ql.gui.questionWidget.QuestionWidget;
import ql.issue.Issue;

public class VisibleElements {
	private List<DrawableElement> UIElements;
	private List<QuestionWidget> questions;
	private List<InputQuestionWidget> inputQuestions;
	
	public VisibleElements(){
		UIElements = new ArrayList<DrawableElement>();
		questions = new ArrayList<QuestionWidget>();
		inputQuestions = new ArrayList<InputQuestionWidget>();
	}
	
	public void addIssue(Issue issue){
		UIElements.add(issue);
	}
	
	public void addQuestion(InputQuestion inputQuestion, QLWindow parent){
		InputQuestionWidget questionWidget = new InputQuestionWidget(inputQuestion, parent);
		UIElements.add(questionWidget);
		questions.add(questionWidget);
		inputQuestions.add(questionWidget);
	}
	
	public void addQuestion(ComputedQuestion computedQuestion){
		ComputedQuestionWidget questionWidget = new ComputedQuestionWidget(computedQuestion);
		UIElements.add(questionWidget);
		questions.add(questionWidget);
	}
	
	public int numberOfUIElements(){
		return UIElements.size();
	}
	
	public void removeAllUIElements(){
		UIElements.clear();
	}
	
	public Iterator<DrawableElement> getUIElementIterator(){
		return UIElements.iterator();
	}

	public Iterator<QuestionWidget> getQuestionsIterator(){
		return questions.iterator();
	}
	
	public Iterator<InputQuestionWidget> getInputQuestionsIterator(){
		return inputQuestions.iterator();
	}
}

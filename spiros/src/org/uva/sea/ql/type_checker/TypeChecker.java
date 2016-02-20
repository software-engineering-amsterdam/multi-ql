package org.uva.sea.ql.type_checker;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.ComputedQuestionsVisitor;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.QuestionsVisitor;

public class TypeChecker {
	
	private final Form form;
	private final QuestionsVisitor questionsVisitor;
	private final ComputedQuestionsVisitor computedQuestionsVisitor;
	
	
	public TypeChecker(Form form) {
		this.form = form;
		this.questionsVisitor = new QuestionsVisitor(form);
		this.computedQuestionsVisitor = new ComputedQuestionsVisitor(form);		
	}
	
	public List<Question> getAllQuestions() {
		List<Question> questions = this.questionsVisitor.getQuestions();
		List<ComputedQuestion> computedQuestions = this.computedQuestionsVisitor.getComputedQuestions();
		questions.addAll(computedQuestions);
		return questions;
	}

	public Form getForm() {
		return form;
	}
	
	public void performTypeChecking() {	
		boolean duplicatedLabelsFound = checkForDuplicatedLabels();	
	}
	
	private boolean checkForDuplicatedLabels() {
		List<Question> questions = this.getAllQuestions();
		
		if (questions.isEmpty())
			System.out.println("Fuck ");
		List<String> labels = new ArrayList<String>();
		
		for (Question question: questions) {
			String label = question.getLabel();
			
			if (labels.contains(label)) {
				System.out.println("You broke it man!!! ");
				return false;
			}
			
			else 
				labels.add(label);
			
		}
		
		System.out.println("Everything ok! ");
		return true;
	}

}
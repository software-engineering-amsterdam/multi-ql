package org.uva.sea.ql.type_checker;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.expression.Expression;
import org.uva.sea.ql.ast.expression.Literal.StringLiteral;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.statement.ComputedQuestion;
import org.uva.sea.ql.ast.statement.ComputedQuestionsVisitor;
import org.uva.sea.ql.ast.statement.IfStatement;
import org.uva.sea.ql.ast.statement.IfStatementVisitor;
import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.ast.statement.QuestionsVisitor;
import org.uva.sea.ql.ast.type.BoolType;
import org.uva.sea.ql.ast.type.Type;
import org.uva.sea.ql.ast.type.UndefinedType;

import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.awt.EmbeddedFrame;

public class TypeChecker {
	
	private final Form form;
	private final QuestionsVisitor questionsVisitor;
	private final ComputedQuestionsVisitor computedQuestionsVisitor;
	private final IfStatementVisitor ifStatementVisitor;
	
	
	public TypeChecker(Form form) {
		this.form = form;
		this.questionsVisitor = new QuestionsVisitor(form);
		this.computedQuestionsVisitor = new ComputedQuestionsVisitor(form);
		this.ifStatementVisitor = new IfStatementVisitor(form);
	}
	
	public List<Question> getAllQuestions() {
		// mallon edw to VisitForm...
		
		List<Question> questions = this.questionsVisitor.getQuestions();
		List<ComputedQuestion> computedQuestions = this.computedQuestionsVisitor.getComputedQuestions();
		questions.addAll(computedQuestions);
		return questions;
	}

	public Form getForm() {
		return form;
	}
	
	public void performTypeChecking() {	
		//boolean duplicatedLabelsFound = checkForDuplicatedLabels();
		boolean booleanConditions = checkBooleanConditions();
	}
	
	private boolean checkBooleanConditions() {
		// TODO Auto-generated method stub
		List<IfStatement> ifStatements = this.ifStatementVisitor.getIfStatements();
		System.out.println("Size of IfStatements is " + ifStatements.size());
		for (IfStatement ifStatement: ifStatements) {
			Expression expression = ifStatement.getExpression();
			if (expression.getTypeOfExpression(this.form) instanceof BoolType)		// allakse to!
				System.out.println("This is boolean type: " +expression.getClass().toString());
			else if (expression.getTypeOfExpression(this.form) instanceof UndefinedType)
				//System.out.println("wtf");
				System.out.println("Undefined type: " + expression.getClass().toString());
			
			else
				//System.out.println("wtf");
				System.out.println("This is not boolean type: " + expression.getClass().toString());
			
		}
		return false;
	}

	private boolean checkForDuplicatedLabels() {
		List<Question> questions = this.getAllQuestions();
		
		if (questions.isEmpty())
			System.out.println("Fuck ");
		else
			System.out.println("The size of the questionsList is " + questions.size());
		List<String> labels = new ArrayList<String>();
		
		for (Question question: questions) {
			String label = question.getLabel();
			
			if (labels.contains(label)) {
				System.out.println("Duplicate label found ");
				return false;
			}
			
			else 
				labels.add(label);
			
		}
		
		System.out.println("No duplicates found ");
		return true;
	}

}
package org.uva.sea.ql.ast.statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.form.Form;



public class QuestionsVisitor extends StatementsVisitor {
	
	private final  List<Question> questions;

	public QuestionsVisitor(Form form) {
		super(form);
		this.questions = new ArrayList<Question>();
		
	}

	public List<Question> getQuestions() {
		List<Question> questions = new ArrayList<>();
		this.visitForm();
		Iterator<Question> iterator = this.questions.iterator();
		iterator.forEachRemaining(questions::add);
		return questions;
	}

	@Override
	public void visitComputedQuestion(ComputedQuestion computedQuestion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitQuestion(Question question) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfStatement(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public Void visitQuestion(Question question) {
//		this.questions.add(question);
//		//System.out.println("Question added");
//		return null;
//	}
	

}

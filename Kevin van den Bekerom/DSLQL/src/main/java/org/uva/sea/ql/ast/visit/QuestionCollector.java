package org.uva.sea.ql.ast.visit;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.LeftDFSVisitor;
import org.uva.sea.ql.ast.stat.*;

/* Visitor that extends LeftDFSVisitor and collects all questions in a block */

public class QuestionCollector extends LeftDFSVisitor<Void> {
	private List<Question> questions;
	
	private QuestionCollector() {
		questions = new ArrayList<Question>();
	}
	
	@Override
	public void visit(Question question, Void context) {
		questions.add(question);
	}
	
	@Override
	public void visit(ComputedQuestion question, Void context) {
		questions.add(question);
	}
	
	public static List<Question> getQuestions(ASTNode block) {
		QuestionCollector collector = new QuestionCollector();
		block.accept(collector, null);
		return collector.questions;
	}
	
	public static List<String> getQuestionIDs(ASTNode block) {
		QuestionCollector collector = new QuestionCollector();
		block.accept(collector, null);
		List<String> questionIDs = new ArrayList<String>();
		//questions.forEach(question -> questionIDs.add(question.getIdentifier()));
		
		for (Question q : collector.questions) {
			questionIDs.add(q.getIdentifier());
		}
		
		return questionIDs;
	}
}

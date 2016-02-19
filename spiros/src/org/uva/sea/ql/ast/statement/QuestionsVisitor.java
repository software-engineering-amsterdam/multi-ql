package org.uva.sea.ql.ast.statement;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;



public class QuestionsVisitor extends StatementsVisitor {
	
	private final  List<Question> questions;

	public QuestionsVisitor(Form form) {
		super(form);
		this.questions = new ArrayList<Question>();
	}

	public List<Question> getQuestions() {
		return this.questions;
	}
	
	@Override
	public void visitQuestion(Question question) {
		this.questions.add(question);	
	}

	@Override
	public ASTNode visit(Block block) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(ComputedQuestion computedQuestion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(Question question) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(IfStatement ifStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ASTNode visit(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}

package org.uva.sea.ql.ast.statement;

import java.util.ArrayList;
import java.util.Iterator;
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
		List<Question> questions = new ArrayList<>();
		this.visitForm();
		Iterator<Question> iterator = this.questions.iterator();
		iterator.forEachRemaining(questions::add);
		return questions;
	}
	
	@Override
	public Void visitQuestion(Question question) {
		this.questions.add(question);	
		return null;
	}

	@Override
	public Void visitBlock(Block block) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Void visitIfElseStatement(IfElseStatement ifElseStatement) {
		// TODO Auto-generated method stub
		return null;
	}


}

package org.uva.sea.ql.ast.statement;

import java.util.ArrayList;
import java.util.List;

import org.uva.sea.ql.ast.block.Block;
import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;

public class ComputedQuestionsVisitor extends StatementsVisitor {

	private final List<ComputedQuestion>  computedQuestions;
	
	
	public ComputedQuestionsVisitor(Form form) {
		super(form);
		this.computedQuestions = new ArrayList<ComputedQuestion>();
	}
	
	public List<ComputedQuestion> getComputedQuestions() {
		return this.computedQuestions;
	}
	
	@Override
	public Void visitComputedQuestion(ComputedQuestion computedQuestion) {
		this.computedQuestions.add(computedQuestion);
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

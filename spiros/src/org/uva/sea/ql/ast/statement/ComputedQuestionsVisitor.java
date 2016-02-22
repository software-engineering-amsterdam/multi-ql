package org.uva.sea.ql.ast.statement;

import java.util.ArrayList;
import java.util.Iterator;
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
		List<ComputedQuestion> questions = new ArrayList<>();
		this.visitForm();
		Iterator<ComputedQuestion> iterator = this.computedQuestions.iterator();
		iterator.forEachRemaining(questions::add);
		return questions;
	}
	
	@Override
	public Void visitComputedQuestion(ComputedQuestion computedQuestion) {
		this.computedQuestions.add(computedQuestion);
		//System.out.println("Computed Question added");
		return null;
	}

	
}

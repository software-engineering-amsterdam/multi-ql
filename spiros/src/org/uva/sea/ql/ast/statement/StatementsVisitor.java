package org.uva.sea.ql.ast.statement;

import java.util.List;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;


public abstract class StatementsVisitor implements StatementVisitor<ASTNode> {
	
	private final Form form;	// protected maybe...? ask...
	
	public StatementsVisitor(Form form) {
		this.form = form;
	}

	public Form getForm() {
		return form;
	}
	
	public void visitForm () {
		List<Statement> statements = this.form.getBlock().getStatements();
		for  (Statement statement: statements)
			statement.accept(this);	
	}
	
	public void visitQuestion(Question question) {	}		
	public void visitComputedQuestion(ComputedQuestion question) {	}
	
	public void visitIfStatement(IfStatement ifStatement) {
		List<Statement> statements = ifStatement.getBlock().getStatements();
		for  (Statement statement: statements)
			statement.accept(this);	
	}

}

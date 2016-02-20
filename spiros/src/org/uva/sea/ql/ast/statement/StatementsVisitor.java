package org.uva.sea.ql.ast.statement;

import java.util.List;

import org.uva.sea.ql.ast.form.Form;
import org.uva.sea.ql.ast.node.ASTNode;


public abstract class StatementsVisitor implements StatementVisitor<Void> {
	
	private final Form form;	// protected maybe...? ask...
	
	public StatementsVisitor(Form form) {
		this.form = form;
	}

	public Form getForm() {
		return form;
	}
	
	public Void visitForm () {
		List<Statement> statements = this.form.getBlock().getStatements();  //////
		System.out.println("Episkeptomai ti forma!");
		if (statements.isEmpty())
				System.out.println("H lista me ta statements einai empty");
		for  (Statement statement: statements)
			statement.accept(this);	
		return null;
	}
	
	public Void visitQuestion(Question question) {return null;	}		
	
	public Void visitComputedQuestion(ComputedQuestion question) {	 return null;}
	
	public Void visitIfStatement(IfStatement ifStatement) {
		List<Statement> statements = ifStatement.getBlock().getStatements();
		for  (Statement statement: statements)
			statement.accept(this);	
		return null;
	}

}

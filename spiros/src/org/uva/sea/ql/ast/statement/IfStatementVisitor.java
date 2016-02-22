package org.uva.sea.ql.ast.statement;

import java.util.List;

import org.uva.sea.ql.ast.form.Form;

public class IfStatementVisitor extends StatementsVisitor {

	private List<IfStatement> ifStatements;
	
	
	public IfStatementVisitor(Form form) {
		super(form);
		// TODO Auto-generated constructor stub
	}
	
	// check below.... goin to sleep . . . 

		
//	@Override
//	public Void visitIfStatement(IfStatement ifStatement) {
//		this.ifStatements.add(ifStatement);
//		List<Statement> statements = ifStatement.getBlock().getStatements();
//		for  (Statement statement: statements)
//			statement.accept(this);	
//		return null;
//	}
}

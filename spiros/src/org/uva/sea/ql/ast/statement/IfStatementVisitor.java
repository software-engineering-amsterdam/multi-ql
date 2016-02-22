package org.uva.sea.ql.ast.statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.uva.sea.ql.ast.form.Form;

public class IfStatementVisitor extends StatementsVisitor {

	private List<IfStatement> ifStatements;
	
	
	public IfStatementVisitor(Form form) {
		super(form);
		this.ifStatements = new ArrayList<IfStatement>();
	}
	
	public List<IfStatement> getIfStatements() {
		List<IfStatement> statements = new ArrayList<>();
		this.visitForm();
		Iterator<IfStatement> iterator = this.ifStatements.iterator();
		iterator.forEachRemaining(statements::add);
		return statements;
	}
		
	@Override
	public Void visitIfStatement(IfStatement ifStatement) {
		this.ifStatements.add(ifStatement);
		List<Statement> statements = ifStatement.getBlock().getStatements();
		for  (Statement statement: statements)
			statement.accept(this);	
		return null;
	}
}

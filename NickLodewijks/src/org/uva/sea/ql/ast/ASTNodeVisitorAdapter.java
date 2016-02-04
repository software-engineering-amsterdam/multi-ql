package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Add;
import org.uva.sea.ql.ast.form.Question;

public class ASTNodeVisitorAdapter implements ASTNodeVisitor {

	@Override
	public void visit(ASTNode node) {
		// Do nothing
	}

	@Override
	public void visit(VariableDecl node) {
		visit((ASTNode) node);
	}

	@Override
	public void visit(VariableIdentifier node) {
		visit((ASTNode) node);
	}

	@Override
	public void visit(Question node) {
		visit((ASTNode) node);
	}

	@Override
	public void visit(Add node) {
		visit((ASTNode) node);
	}

}

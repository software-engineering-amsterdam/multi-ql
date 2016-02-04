package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.expr.Add;
import org.uva.sea.ql.ast.form.Question;

public interface ASTNodeVisitor {

	public void visit(ASTNode node);

	public void visit(VariableDecl node);

	public void visit(VariableIdentifier node);

	public void visit(Question node);

	public void visit(Add node);

}

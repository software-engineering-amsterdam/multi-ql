package org.uva.sea.ql.ast.visitor.interfaces;

import org.uva.sea.ql.ast.ASTNode;
import org.uva.sea.ql.ast.VarDeclaration;
import org.uva.sea.ql.ast.VarIdentifier;
import org.uva.sea.ql.ast.expr.Add;

public interface QLNodeVisitor {
	//public void visit(Form form);
	//public void visit(Block block);
	public void visit(VarDeclaration varDeclaration);
	public void visit(VarIdentifier varId);
	public void visit(Add add);
	public void visit(ASTNode node);
	//public void visit(IFblock statement);
	//public void visit(Question question);
}

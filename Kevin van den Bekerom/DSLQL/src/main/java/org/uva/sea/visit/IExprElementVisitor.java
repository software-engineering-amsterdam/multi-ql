package org.uva.sea.visit;

public interface IExprElementVisitor {
	public void visit(NodeAdd add);
	public void visit(NodeIntLiteral intLiteral);
}

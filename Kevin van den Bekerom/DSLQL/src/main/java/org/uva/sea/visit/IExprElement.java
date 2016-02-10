package org.uva.sea.visit;

public interface IExprElement {
	public void accept(IExprElementVisitor visitor);
}

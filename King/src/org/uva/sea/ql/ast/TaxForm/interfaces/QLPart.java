package org.uva.sea.ql.ast.TaxForm.interfaces;

public interface QLPart {
	public void accept(QLNodeVisitor qlPartVisitor);
}

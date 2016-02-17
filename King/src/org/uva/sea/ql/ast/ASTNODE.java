package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.TaxForm.interfaces.QLNodeVisitor;

public abstract class ASTNODE {
	public abstract void accept(QLNodeVisitor visitor);
}

package org.uva.sea.ql.ast;

import org.uva.sea.ql.ast.visitor.interfaces.QLNodeVisitor;
import org.uva.sea.ql.semantic.SymbolTable;

public abstract class ASTNode {
	public abstract void accept(QLNodeVisitor visitor);
}

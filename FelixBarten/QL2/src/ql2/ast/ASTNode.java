package ql2.ast;

import ql2.BaseVisitor;

/**
 * Nodes for the Abstract Syntax tree must implement the accept method to use visitors.
 * @author felixbarten
 *
 */
public abstract class ASTNode {

	public abstract <T> T accept(BaseVisitor<T> visitor);
	
}

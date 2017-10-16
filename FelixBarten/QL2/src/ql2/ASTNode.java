package ql2;

public abstract class ASTNode {

	public abstract <T> T accept(BaseVisitor<T> visitor);
	
}

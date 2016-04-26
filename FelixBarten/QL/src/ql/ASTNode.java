package ql;

public abstract class ASTNode {

	public abstract <T> T accept(BaseVisitor<T> visitor);
	//TODO
}

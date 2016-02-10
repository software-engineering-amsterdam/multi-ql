package uva.ql.interfaces;

public interface IASTNode {
	public void accept(IASTNodeVisitor visitor);
}

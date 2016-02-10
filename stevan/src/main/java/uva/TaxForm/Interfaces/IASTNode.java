package uva.TaxForm.Interfaces;

public interface IASTNode {
	public void accept(IASTNodeVisitor visitor);
}

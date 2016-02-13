package nl.nicasso.ql.ast;

public abstract class ASTNode implements Traversable {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
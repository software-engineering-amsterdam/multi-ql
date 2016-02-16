package nl.nicasso.ql.ast;

public interface Traversable {
	
	public void accept(Visitor visitor);
	
}

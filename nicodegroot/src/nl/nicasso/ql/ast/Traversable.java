package nl.nicasso.ql.ast;

public interface Traversable {
	
	public <T> T accept(Visitor<T> visitor);
	
	public String toString();

}

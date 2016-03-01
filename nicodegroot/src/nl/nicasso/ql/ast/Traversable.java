package nl.nicasso.ql.ast;

import nl.nicasso.ql.visitor.Visitor;

public interface Traversable {
	
	public <T> T accept(Visitor<T> visitor);

}

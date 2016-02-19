package nl.nicasso.ql.ast;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.type.Type;

public interface Traversable {
	
	public Type accept(TypeChecker visitor);
		
	public void accept(Visitor visitor);
}

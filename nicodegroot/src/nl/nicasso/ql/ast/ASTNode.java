package nl.nicasso.ql.ast;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.type.Type;

public class ASTNode implements Traversable {

	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
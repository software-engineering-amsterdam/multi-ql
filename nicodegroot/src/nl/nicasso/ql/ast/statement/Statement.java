package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;

public abstract class Statement extends ASTNode implements Traversable {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
}
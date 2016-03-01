package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;

public abstract class Statement extends ASTNode implements Traversable {

	public Statement(CodeLocation location) {
		super(location);
	}

}
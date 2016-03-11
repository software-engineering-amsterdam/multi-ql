package nl.nicasso.ql.ast.statements;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.visitors.StatementVisitor;

public abstract class Statement extends ASTNode {

	public Statement(CodeLocation location) {
		super(location);
	}
	
	public abstract <T, U> T accept(StatementVisitor<T, U> visitor, U context);

}
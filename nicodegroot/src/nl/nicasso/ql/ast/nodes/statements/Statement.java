package nl.nicasso.ql.ast.nodes.statements;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.visitors.StatementVisitor;

public abstract class Statement extends ASTNode {

	public Statement(CodeLocation location) {
		super(location);
	}

	public abstract <T, U> T accept(StatementVisitor<T, U> visitor, U context);

}
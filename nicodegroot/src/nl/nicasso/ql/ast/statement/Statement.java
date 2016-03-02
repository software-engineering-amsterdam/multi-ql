package nl.nicasso.ql.ast.statement;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.visitor.StatementVisitor;

public abstract class Statement extends ASTNode {

	public Statement(CodeLocation location) {
		super(location);
	}
	
	public abstract <T> T accept(StatementVisitor<T> visitor);

}
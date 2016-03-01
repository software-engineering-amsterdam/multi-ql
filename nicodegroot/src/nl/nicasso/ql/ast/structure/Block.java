package nl.nicasso.ql.ast.structure;

import java.util.List;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.statement.Statement;
import nl.nicasso.ql.visitor.Visitor;

public class Block extends ASTNode implements Traversable {

	private final List<Statement> statements;

	public Block(List<Statement> statements, CodeLocation location) {
		super(location);
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
	
}

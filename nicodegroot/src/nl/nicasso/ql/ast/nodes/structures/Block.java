package nl.nicasso.ql.ast.nodes.structures;

import java.util.List;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.statements.Statement;
import nl.nicasso.ql.visitors.StructureVisitor;

public class Block extends ASTNode {

	private final List<Statement> statements;

	public Block(List<Statement> statements, CodeLocation location) {
		super(location);
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}

	public <T, U> T accept(StructureVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}

}

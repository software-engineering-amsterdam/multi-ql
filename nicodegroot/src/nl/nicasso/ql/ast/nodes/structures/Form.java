package nl.nicasso.ql.ast.nodes.structures;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.visitors.StructureVisitor;

public class Form extends ASTNode {

	private final Identifier identifier;
	private final Block block;

	public Form(Identifier identifier, Block block, CodeLocation location) {
		super(location);
		this.identifier = identifier;
		this.block = block;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public Block getBlock() {
		return block;
	}

	public <T, U> T accept(StructureVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}

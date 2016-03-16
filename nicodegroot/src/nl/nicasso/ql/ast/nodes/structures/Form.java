package nl.nicasso.ql.ast.nodes.structures;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.visitors.StructureVisitor;

public class Form extends ASTNode {

	private final Identifier id;
	private final Block block;
	
	public Form(Identifier id, Block block2, CodeLocation location) {
		super(location);
		this.id = id;
		this.block = block2;
	}

	public Identifier getId() {
		return id;
	}

	public Block getBlock() {
		return block;
	}
	
	public <T, U> T accept(StructureVisitor<T, U> visitor, U context) {
		return visitor.visit(this, null);
	}
}

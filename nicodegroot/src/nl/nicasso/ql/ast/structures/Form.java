package nl.nicasso.ql.ast.structures;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expressions.Identifier;
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
	
	public <T> T accept(StructureVisitor<T> visitor) {
		return visitor.visit(this);
	}
}

package nl.nicasso.ql.ast.structure;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.ast.expression.Identifier;
import nl.nicasso.ql.visitor.StructureVisitor;

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

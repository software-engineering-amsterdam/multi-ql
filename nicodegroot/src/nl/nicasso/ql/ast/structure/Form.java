package nl.nicasso.ql.ast.structure;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.expression.Identifier;

public class Form extends ASTNode implements Traversable {

	private final Identifier id;
	private final Block block;
	
	public Form(Identifier id, Block block2) {
		this.id = id;
		this.block = block2;
	}

	public Identifier getId() {
		return id;
	}

	public Block getBlock() {
		return block;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}

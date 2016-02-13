package nl.nicasso.ql.ast.structure;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.literal.IdentifierLit;

public class Form extends ASTNode {

	IdentifierLit id;
	Block block;
	
	public Form(IdentifierLit id, Block block2) {
		this.id = id;
		this.block = block2;
	}

	public IdentifierLit getId() {
		return id;
	}

	public Block getBlock() {
		return block;
	}
	
}

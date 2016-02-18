package nl.nicasso.ql.ast.structure;

import nl.nicasso.ql.TypeChecker;
import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.type.Type;

public class Form extends ASTNode implements Traversable {

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
	
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public Type accept(TypeChecker visitor) {
		return visitor.visit(this);
	}
}

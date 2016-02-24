package nl.nicasso.ql.ast.structure;

import nl.nicasso.ql.EvaluatorVisitor;
import nl.nicasso.ql.TypeCheckerVisitor;
import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.Traversable;
import nl.nicasso.ql.ast.Visitor;
import nl.nicasso.ql.ast.literal.IdentifierLit;
import nl.nicasso.ql.ast.literal.Literal;
import nl.nicasso.ql.ast.type.Type;

public class Form extends ASTNode implements Traversable {

	private final IdentifierLit id;
	private final Block block;
	
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
	public Type accept(TypeCheckerVisitor visitor) {
		return visitor.visit(this);
	}
	
	@Override
	public Literal accept(EvaluatorVisitor visitor) {
		return visitor.visit(this);
	}
}

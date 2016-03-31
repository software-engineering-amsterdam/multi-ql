package nl.nicasso.ql.ast.nodes;

public abstract class ASTNode {

	private final CodeLocation location;

	public ASTNode() {
		this.location = null;
	}

	public ASTNode(CodeLocation location) {
		this.location = location;
	}

	public CodeLocation getLocation() {
		return location;
	}

}
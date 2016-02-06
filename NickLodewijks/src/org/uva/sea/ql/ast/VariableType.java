package org.uva.sea.ql.ast;

public class VariableType extends ASTNode {

	private final String name;
	private final ValueType type;

	public VariableType(String name) {
		this.name = name;
		this.type = ValueType.getByName(name);
	}

	public String getName() {
		return name;
	}

	public ValueType getType() {
		return type;
	}

	@Override
	public void accept(ASTNodeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public Result validate() {
		if (type == null) {
			return Result.FALSE("Unknown variable type " + name);
		}
		
		return Result.TRUE();
	}
}

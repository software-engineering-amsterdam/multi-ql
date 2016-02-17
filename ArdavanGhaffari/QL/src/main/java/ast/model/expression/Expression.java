package ast.model.expression;

import ast.model.AbstractNode;
import ast.model.type.Type;

public class Expression extends AbstractNode {
	// value is always returned, it doesn't need to be saved as variable
	protected Type type;
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}

package uva.ql.ast.variables;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Generic;

public class VarGeneric extends Variable {

	private Type type = new Generic();
	
	public VarGeneric(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
}

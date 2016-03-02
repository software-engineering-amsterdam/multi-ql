package uva.ql.ast.variables;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Str;

public class VarStr extends Variable {

	private Type type = new Str();
	
	public VarStr(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	public Type getType() {
		return this.type;
	}
}

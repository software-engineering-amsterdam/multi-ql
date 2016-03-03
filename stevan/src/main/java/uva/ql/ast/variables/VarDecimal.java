package uva.ql.ast.variables;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Decimal;

public class VarDecimal extends Variable {

	private Type type = new Decimal();
	
	public VarDecimal(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	public Type getType() {
		return this.type;
	}
}

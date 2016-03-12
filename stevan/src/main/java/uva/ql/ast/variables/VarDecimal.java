package uva.ql.ast.variables;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Decimal;

public class VarDecimal extends Variable {

	private Decimal type = new Decimal();
	
	public VarDecimal(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
}

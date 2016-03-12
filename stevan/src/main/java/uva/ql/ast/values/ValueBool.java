package uva.ql.ast.values;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Bool;

public class ValueBool extends Values {

	private Bool type = new Bool();
	private boolean value;
	
	public ValueBool(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Boolean.parseBoolean(value);
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	public boolean getValue() {
		return this.value;
	}
}

package uva.ql.ast.values;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Bool;

public class ValueBool extends Value<Boolean> {

	private Bool type = new Bool();
	private boolean value;
	
	public ValueBool(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Boolean.getBoolean(value);
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	@Override
	public Boolean getValue() {
		return this.value;
	}

	@Override
	public Boolean eval() {
		return this.value;
	}
}

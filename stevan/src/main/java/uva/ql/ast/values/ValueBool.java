package uva.ql.ast.values;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Int;

public class ValueBool extends Values {

	private Type type = new Int();
	private boolean value;
	
	public ValueBool(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Boolean.parseBoolean(value);
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	public boolean getValue() {
		return this.value;
	}
}

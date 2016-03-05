package uva.ql.ast.values;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Int;

public class ValueInt extends Values {

	private Type type = new Int();
	private int value;
	
	public ValueInt(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Integer.parseInt(value);
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	public int getValue() {
		return this.value;
	}
}

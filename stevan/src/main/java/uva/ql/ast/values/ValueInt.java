package uva.ql.ast.values;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Int;

public class ValueInt extends Values<Integer> {

	private Int type = new Int();
	private int value;
	
	public ValueInt(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Integer.parseInt(value);
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
	public Integer getValue() {
		return this.value;
	}

}

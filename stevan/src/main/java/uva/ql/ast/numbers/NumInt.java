package uva.ql.ast.numbers;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.numbers.abstracts.Number;
import uva.ql.ast.numbers.types.Int;

public class NumInt extends Number {

	private Type type = new Int();
	private int value;
	
	public NumInt(Node parent, int value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = value;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public int getValue() {
		return this.value;
	}
}

package uva.ql.ast.numbers;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.numbers.abstracts.Number;
import uva.ql.ast.numbers.types.Dble;

public class NumDouble extends Number {

	private Type type = new Dble();
	private Double value;
	
	public NumDouble(Node parent, Double value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = value;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Double getValue() {
		return this.value;
	}
}

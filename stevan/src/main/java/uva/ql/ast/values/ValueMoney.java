package uva.ql.ast.values;

import java.math.BigDecimal;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Money;

public class ValueMoney extends Values {

	private Money type = new Money();
	private int value;
	
	public ValueMoney(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		BigDecimal val = new BigDecimal(value);
		val = val.multiply(new BigDecimal("100"));
		this.value = Integer.valueOf(val.intValue());
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	public int getValue() {
		return this.value;
	}
}

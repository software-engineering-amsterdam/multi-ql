package uva.ql.ast.values;

import java.math.BigDecimal;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.interfaces.IntEval;
import uva.ql.ast.types.Money;
import uva.ql.gui.visitors.IGUIVisitor;

public class ValueMoney extends Value<Integer> implements IntEval<Integer> {

	private Money type = new Money();
	private Integer value;
	
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
	
	@Override
	public Integer getValue() {
		return this.value;
	}
	
	@Override
	public Integer eval() {
		return getValue();
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitValueMoney(this, panel);
	}
}

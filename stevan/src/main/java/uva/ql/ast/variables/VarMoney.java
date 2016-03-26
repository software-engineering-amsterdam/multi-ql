package uva.ql.ast.variables;

import java.math.BigDecimal;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Money;
import uva.ql.gui.visitors.IGUIVisitor;

public class VarMoney extends Variable<Integer> {

	private Money type = new Money();
	private Integer value;
	
	public VarMoney(Node parent, String name, int startLine, int startColumn) {
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
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitVarMoney(this, panel);
	}

	@Override
	public Integer getValue() {
		return this.value / 100;
	}

	@Override
	public void setValue(Integer value) {
		BigDecimal val = new BigDecimal(value);
		val = val.multiply(new BigDecimal("100"));
		this.value = Integer.valueOf(val.intValue());
	}

	@Override
	public Integer eval() {
		return this.getValue();
	}
}

package uva.ql.ast.values;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.interfaces.IntEval;
import uva.ql.ast.types.Int;
import uva.ql.gui.visitors.IGUIVisitor;

public class ValueInt extends Value<Integer> implements IntEval<Integer> {

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
	
	@Override
	public Integer eval() {
		return getValue();
	}

	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitValueInt(this, panel);
	}
}

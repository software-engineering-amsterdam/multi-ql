package uva.ql.ast.variables;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Int;
import uva.ql.visitors.IGUIVisitor;

public class VarInt extends Variable<Integer> {

	private Int type = new Int();
	private Integer value = 0;
	
	public VarInt(Node parent, String name, int startLine, int startColumn) {
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
	public Integer getValue() {
		return this.value;
	}

	@Override
	public Integer eval() {
		return this.getValue();
	}
	
	@Override
	public void setValue(Integer value) {
		this.value = value;
		this.setChanged();
		this.notifyObservers(value);
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitVarInt(this, panel);
	}
}

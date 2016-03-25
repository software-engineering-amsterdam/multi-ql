package uva.ql.ast.values;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Bool;
import uva.ql.gui.visitors.IGUIVisitor;

public class ValueBool extends Value<Boolean> {

	private Bool type = new Bool();
	private boolean value;
	
	public ValueBool(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Boolean.getBoolean(value);
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
	public Boolean getValue() {
		return this.value;
	}
	
	@Override
	public void accept(IGUIVisitor visitor, JPanel panel) {
		visitor.visitValueBool(this, panel);
	}

	@Override
	public Boolean eval() {
		return this.value;
	}
}

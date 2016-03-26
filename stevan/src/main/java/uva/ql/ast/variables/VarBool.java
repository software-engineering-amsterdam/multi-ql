package uva.ql.ast.variables;

import javax.swing.JPanel;

import uva.ql.ast.EnumType;
import uva.ql.ast.Node;
import uva.ql.ast.types.Bool;
import uva.ql.gui.visitors.IGUIVisitor;

public class VarBool extends Variable<Boolean> {

	private Bool type = new Bool();
	private Boolean value = false;
	
	public VarBool(Node parent, String name, int startLine, int startColumn) {
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
		visitor.visitVarBool(this, panel);
	}
	
	@Override
	public Boolean getValue() {
		return this.value;
	}
	
	@Override
	public void setValue(Boolean value) {
		this.value = value;
	}

	@Override
	public Boolean eval() {
		return this.getValue();
	}

}

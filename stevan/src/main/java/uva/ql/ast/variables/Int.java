package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Int extends AVariable {

	private int value = 0;
	
	public Int(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.INT;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public void setValue(String value) {
		setValue(Integer.parseInt(value));
	}

}

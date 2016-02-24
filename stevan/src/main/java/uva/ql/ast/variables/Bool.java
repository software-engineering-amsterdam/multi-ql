package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Bool extends AVariable {

	private boolean value = false;
	
	public Bool(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.BOOLEAN;
	}

	public boolean isValue() {
		return this.value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}

package uva.ql.ast.variables;

import uva.ql.ast.AST;
import uva.ql.ast.AVariable;
import uva.ql.interfaces.IVariable;

public class Money extends AVariable {

	private double value = 0.0;
	
	public Money(AST ast) {
		super(ast);
	}

	@Override
	protected int getVarType0() {
		return IVariable.MONEY;
	}

	public double getValue() {
		return this.value;
	}

	public void setValue(double value) {
		this.value = value;
	}
	
	public double evalDouble() {
		return getValue();
	}
}

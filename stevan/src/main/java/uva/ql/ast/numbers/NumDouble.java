package uva.ql.ast.numbers;

import uva.ql.ast.ANumber;
import uva.ql.ast.AST;
import uva.ql.interfaces.INumber;

public class NumDouble extends ANumber {

	private double value = 0;
	
	public NumDouble(AST ast) {
		super(ast);
	}

	@Override
	protected int getNumType0() {
		return INumber.DOUBLE;
	}

	public double getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = Double.valueOf(value);
	}
	
	public void setValue(double value) {
		this.value = value;
	}
}

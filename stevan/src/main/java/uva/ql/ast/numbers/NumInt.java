package uva.ql.ast.numbers;

import uva.ql.ast.ANumber;
import uva.ql.ast.AST;
import uva.ql.interfaces.INumber;

public class NumInt extends ANumber {

	private int value = 0;
	
	public NumInt(AST ast) {
		super(ast);
	}

	@Override
	protected int getNumType0() {
		return INumber.INT;
	}

	public int getValue() {
		return value;
	}
	
	@Override
	public void setValue(String value) {
		this.value = Integer.valueOf(value);
	}

	public void setValue(int value) {
		this.value = value;
	}
}

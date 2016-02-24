package uva.ql.ast.numbers;

import uva.ql.ast.ANumber;
import uva.ql.ast.AST;
import uva.ql.interfaces.INumber;

public class NumInt<T> extends ANumber {

	private T value;
	
	public NumInt(AST ast) {
		super(ast);
	}

	@Override
	protected int getNumType0() {
		return INumber.INT;
	}

	@Override
	public <T> T getValue() {
		return (T) value;
	}
	
	@Override
	public void setValue(String value) {
		setValue((T) Integer.valueOf(value));
	}

	public void setValue(T value) {
		this.value = value;
	}
}

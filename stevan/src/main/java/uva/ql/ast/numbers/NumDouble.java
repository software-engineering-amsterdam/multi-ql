package uva.ql.ast.numbers;

import uva.ql.ast.ANumber;
import uva.ql.ast.AST;
import uva.ql.interfaces.INumber;

public class NumDouble<T> extends ANumber {

	private T value;
	
	public NumDouble(AST ast) {
		super(ast);
	}

	@Override
	protected int getNumType0() {
		return INumber.DOUBLE;
	}

	@Override
	public <T> T getValue() {
		return (T) value;
	}

	@Override
	public void setValue(String value) {
		setValue((T) Double.valueOf(value));
	}
	
	public void setValue(T value) {
		this.value = value;
	}
}

package ql.ast.literal;

public abstract class Literal<T> {

	private T value;

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public Literal(T value) {
		this.setValue(value);
	}
	
	
	
}

package ql.ast.literal;

public abstract class Literal {

	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Literal(Object value) {
		super();
		this.setValue(value);
	}
	
	
	
}

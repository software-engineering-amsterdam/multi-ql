package org.uva.sea.ql.ast.form;

public class Value {
	Object value;

	Value (Object value) {
		
	}

	public Object getValue() {
		return value;
	}

	public void updateValue(Object value, Context context) {
		this.value = value;
	}

}

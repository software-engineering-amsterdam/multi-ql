package org.uva.sea.ql.evaluator;


public class StrValue extends Value {

	private final String value;
	
	
	public StrValue(String value) {
		this.value = value;
	}


	public String getValue() {
		return value;
	}
	
}

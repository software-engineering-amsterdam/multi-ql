package org.uva.sea.ql.evaluator;


public class IntValue extends Value {
	
	private final Integer value;
	
	
	public IntValue(Integer value) {
		this.value = value;
	}


	public Integer getValue() {
		return value;
	}

}

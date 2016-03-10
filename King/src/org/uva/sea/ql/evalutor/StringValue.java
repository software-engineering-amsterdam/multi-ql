package org.uva.sea.ql.evalutor;

public class StringValue extends Value {

	private final String string;
	
	public StringValue(String string){
		this.string = string;
	}
	
	public String getValue(){
		return this.string;
	}

	@Override
	public Value equal(Value value) {
		return value.equalString(this);
	}

	@Override
	public Value equalString(StringValue str) {
		return new BooleanValue(str.string.equals(this.string));
	}

	@Override
	public Value notEqual(Value value) {
		return value.notEqualString(this);
	}

	@Override
	public Value notEqualString(StringValue str) {
		return new BooleanValue(!str.string.equals(this.string));
	}

	@Override
	public Value add(Value value) {
		return value.addString(this);
	}

	@Override
	public Value addString(StringValue str) {
		return new StringValue(str.string.concat(this.string));
	}
	

}

package nl.uva.ql.evaluator.value;

public class StringValue extends Value{

	private final String value;
	
	public StringValue(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return value;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof StringValue){
			return value.equals(((StringValue)obj).getValue());
		}
		return false;
	}
	
	@Override
	public Value add(Value arg){
		return arg.addString(this);
	}
	
	@Override
	public Value addString(StringValue arg){
		return new StringValue(arg.getValue() + this.getValue());
	}
	
	@Override
	public Value equal(Value arg){
		return arg.equalString(this);
	}
	
	@Override
	public Value equalString(StringValue arg){
		return new BooleanValue(arg.getValue().equals(this.getValue()));
	}
	
	@Override
	public Value notEqual(Value arg){
		return arg.notEqualString(this);
	}
	
	@Override
	public Value notEqualString(StringValue arg){
		return new BooleanValue(!arg.getValue().equals(this.getValue()));
	}
}

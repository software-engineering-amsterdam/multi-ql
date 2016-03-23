package nl.uva.ql.evaluator.value;

public class BooleanValue extends Value{
	
	private final Boolean value;
	
	public BooleanValue(Boolean value){
		this.value = value;
	}
	
	public Boolean getValue(){
		return this.value;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof BooleanValue){
			return value.equals(((BooleanValue)obj).getValue());
		}
		return false;
	}
	
	@Override
	public Value negate(){
		return new BooleanValue(!this.getValue());
	}
	
	@Override
	public Value and(Value arg){
		return arg.andBoolean(this);
	}
	
	@Override
	public Value andBoolean(BooleanValue arg){
		return new BooleanValue(arg.getValue() && this.getValue());
	}
	
	@Override
	public Value or(Value arg){
		return arg.orBoolean(this);
	}
	
	@Override
	public Value orBoolean(BooleanValue arg){
		return new BooleanValue(arg.getValue() || this.getValue());
	}
	
	@Override
	public Value equal(Value arg){
		return arg.equalBoolean(this);
	}
	
	@Override
	public Value equalBoolean(BooleanValue arg){
		return new BooleanValue(arg.getValue().equals(this.getValue()));
	}
	
	@Override
	public Value notEqual(Value arg){
		return arg.notEqualBoolean(this);
	}
	
	@Override
	public Value notEqualBoolean(BooleanValue arg){
		return new BooleanValue(!arg.getValue().equals(this.getValue()));
	}

}

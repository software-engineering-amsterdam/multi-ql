package semanticAction.evaluation;

public class BooleanValue extends Value<Boolean> {
	
	public BooleanValue(boolean booleanValue) {
		super(booleanValue);
	}

	public Value and(Value value) {
		return value.andBool(this);
	}
	public Value<Boolean> andBool(BooleanValue value) {
		return new BooleanValue(value.getValue() && getValue() );
	}
	public Value  or(Value value) {
		return value.orBool(this);
	} 
	public BooleanValue orBool(BooleanValue value) {
		return new BooleanValue(value.getValue() || getValue() );
	}
	
	public Value<Boolean> not() {
		return notBoolean();
	}
	public Value<Boolean> notBoolean() {
		return new BooleanValue(!getValue());
	}
	public Value<Boolean> equal(BooleanValue value){return value.equalBoolean(this);}
	public Value<Boolean> equalBoolean(BooleanValue value){return new BooleanValue(value.getValue()== getValue());}
	
	
	
}
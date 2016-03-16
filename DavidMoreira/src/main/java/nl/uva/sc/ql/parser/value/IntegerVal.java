package nl.uva.sc.ql.parser.value;

public class IntegerVal extends Value {

	int integer;
	
	public IntegerVal(int integer){
		this.integer = integer;
	}
	
	public int getInteger(){
		return this.integer;
	}
		
	public IntegerVal add(IntegerVal integerVal){
		return new IntegerVal(getInteger() + integerVal.getInteger());
	}
	
	public IntegerVal sub(IntegerVal integerVal){
		return new IntegerVal(getInteger() - integerVal.getInteger());
	}
	
	public IntegerVal mul(IntegerVal integerVal){
		return new IntegerVal(getInteger() * integerVal.getInteger());
	}
	
	public IntegerVal div(IntegerVal integerVal){
		// TODO: div by zero
		return new IntegerVal(getInteger() / integerVal.getInteger());
	}
	
	public BooleanVal greaterThan(IntegerVal integerVal){
		return new BooleanVal(getInteger() > integerVal.getInteger());
	}
	
	public BooleanVal greaterEqualsThan(IntegerVal integerVal){
		return new BooleanVal(getInteger() >= integerVal.getInteger());
	}
	
	public BooleanVal lessThan(IntegerVal integerVal){
		return new BooleanVal(getInteger() < integerVal.getInteger());
	}
	
	public BooleanVal lessEqualsThan(IntegerVal integerVal){
		return new BooleanVal(getInteger() <= integerVal.getInteger());
	}
	
	@Override
	public BooleanVal equal(Value value){
		return new BooleanVal(getInteger() == ((IntegerVal) value).getInteger());
	}
	
	@Override
	public BooleanVal different(Value value){
		return new BooleanVal(getInteger() != ((IntegerVal) value).getInteger());
	}

	@Override
	public Object getValue() {
		return this.integer;
	}
	
	@Override
	public String toString(){
		return this.integer + "";
	}
}

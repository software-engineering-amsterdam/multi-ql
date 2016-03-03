package semanticAction.evaluation;


//  @param <T> The return type 
public abstract class Value {
	
	public Value<T> add(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in add operations."); 
	}
	public Value<T> addInt(IntegerValue value) { 
		throw new  IllegalStateException(
				"Value is not of type integer and it can't be supported in addition."); 
	}
	
	public Value<T> sub(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in substaction operations."); 
	}
	public Value<T> subInt(IntegerValue value) { 
		throw new  IllegalStateException(
				"Value is not of type integer and it can't be supported in substraction."); 
	}
	public Value<T> time(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in multiplication operations."); 
	}

	public Value<T> timeInt(IntegerValue value) { 
		throw new  IllegalStateException(
				"Value is not of type integer and it can't be supported in multiplication."); 
	}
	public Value<T> div(Value<T> vaue){
		throw new IllegalStateException("not good type");
		
	}
	public Value<T> divisionInt(IntegerValue value){
		throw new IllegalStateException("type invalid");
	}
	
	//.........................
public Value<T> equal(Value<T> value){
	throw new IllegalStateException("Invalid type");
}
	
	public Value equalInt(IntegerValue value){
		throw new IllegalStateException("Invalid AbsType");
	}
	public Value equalBoolean(BooleanValue value){
		throw new IllegalStateException("Invalid AbsType");
	}
	
	public Value notEqual(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in (not equal) equation operations."); 
	}
	public Value notEqualInt(IntegerValue value) { 
		throw new  IllegalStateException( "Value is not of type integer ."); 
	}
	public Value notEqualBool(BooleanValue value) { 
		throw new  IllegalStateException("Value is not of type integer ."); 
	}
	
	public Value greaterThan(Value<T> value) { 
		throw new  IllegalStateException("Invalid value type; can't be supported ."); 
	}
	public Value greaterThanInt(IntegerValue value) { 
		throw new  IllegalStateException("Value is not of type integer ."); 
	}
	public Value<T> greaterEqual(Value<T> value){
		throw new IllegalStateException("invalid type");
	}
	public Value<T> greaterEqualInt(IntegerValue value){
		throw new IllegalStateException("Invalid type");
	}
	
	
	public Value<T> lessThan(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in (less than) equation operations."); 
	}
	public Value<T> lessThanInt(IntegerValue value) { 
		throw new  IllegalStateException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	
	public Value<T> lessEqual(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in (less or equal) equation operations."); 
	}
	public Value<T> lessEqualInt(IntegerValue value) { 
		throw new  IllegalStateException(
				"Value is not of type integer and it can't be supported in equality operation."); 
	}
	
	//..................................
	public Value<T> and(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type. Only boolean can be supported in logical operations with *and* operator."); 
	}

	public Value<T> andBool(BooleanValue value) { 
		throw new  IllegalStateException(
				"Value is not of type boolean and it can't be supported in logical operation."); 
	}
	public Value<T>  or(Value<T> value) { 
		throw new  IllegalStateException(
				"Invalid value type. Only boolean can be supported in logical operations with *or* operator."); 
	}
	public Value<T> orBool(BooleanValue value) { 
		throw new IllegalStateException(
				"Value is not of type boolean and it can't be supported in logical operation."); 
	}
	//.............................
	public Value<T> not() { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in logical operations with *not* operator."); 
	}
	public Value<T> notBoolean() { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in logical operations with *not* operator."); 
	}
	public Value<T> plus() { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in unary operations with *+* operator."); 
	}
	
	public Value<T> minus() { 
		throw new  IllegalStateException(
				"Invalid value type; can't be supported in unary operations with *-* operator."); 
	}	
}
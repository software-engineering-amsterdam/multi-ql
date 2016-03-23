package nl.uva.ql.evaluator.value;

public abstract class Value {
	
	public Boolean isUnknownValue(){
		return false;
	}
	
	public Value negate(){
		return new UnknownValue();
	}

	public Value add(Value arg){
		return new UnknownValue();
	}
	
	public Value addInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value addMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value addString(StringValue arg){
		return new UnknownValue();
	}
	
	public Value subtract(Value arg){
		return new UnknownValue();
	}
	
	public Value subtractInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value subtractMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value multiply(Value arg){
		return new UnknownValue();
	}
	
	public Value multiplyInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value multiplyMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value divide(Value arg){
		return new UnknownValue();
	}
	
	public Value divideInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value divideMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value and(Value arg){
		return new UnknownValue();
	}
	
	public Value andBoolean(BooleanValue arg){
		return new UnknownValue();
	}
	
	public Value or(Value arg){
		return new UnknownValue();
	}
	
	public Value orBoolean(BooleanValue arg){
		return new UnknownValue();
	}
	
	public Value equal(Value arg) {
		return new UnknownValue();
	}
	
	public Value equalBoolean(BooleanValue arg) {
		return new UnknownValue();
	}
	
	public Value equalString(StringValue arg) {
		return new UnknownValue();
	}
	
	public Value equalInteger(IntegerValue arg) {
		return new UnknownValue();
	}
	
	public Value equalMoney(MoneyValue arg) {
		return new UnknownValue();
	}
	
	public Value notEqual(Value arg) {
		return new UnknownValue();
	}
	
	public Value notEqualBoolean(BooleanValue arg) {
		return new UnknownValue();
	}
	
	public Value notEqualString(StringValue arg) {
		return new UnknownValue();
	}
	
	public Value notEqualInteger(IntegerValue arg) {
		return new UnknownValue();
	}
	
	public Value notEqualMoney(MoneyValue arg) {
		return new UnknownValue();
	}
	
	public Value greaterThan(Value arg){
		return new UnknownValue();
	}
	
	public Value greaterThanInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value greaterThanMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value greaterThanEqual(Value arg){
		return new UnknownValue();
	}
	
	public Value greaterThanEqualInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value greaterThanEqualMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value lessThan(Value arg){
		return new UnknownValue();
	}
	
	public Value lessThanInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value lessThanMoney(MoneyValue arg){
		return new UnknownValue();
	}
	
	public Value lessThanEqual(Value arg){
		return new UnknownValue();
	}
	
	public Value lessThanEqualInteger(IntegerValue arg){
		return new UnknownValue();
	}
	
	public Value lessThanEqualMoney(MoneyValue arg){
		return new UnknownValue();
	}
}

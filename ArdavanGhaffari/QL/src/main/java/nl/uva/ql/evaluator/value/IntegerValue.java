package nl.uva.ql.evaluator.value;

import java.math.BigDecimal;

public class IntegerValue extends Value{

	private final Integer value;
	
	public IntegerValue(Integer value){
		this.value = value;
	}
	
	public Integer getValue(){
		return this.value;
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof IntegerValue){
			return value.equals(((IntegerValue)obj).getValue());
		}
		return false;
	}
	
	@Override
	public Value add(Value arg){
		return arg.addInteger(this);
	}

	@Override
	public Value addInteger(IntegerValue arg){
		return new IntegerValue(arg.getValue() + this.getValue());
	}

	@Override
	public Value addMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().add(new BigDecimal(this.getValue())));
	}
	
	@Override
	public Value subtract(Value arg){
		return arg.subtractInteger(this);
	}
	
	@Override
	public Value subtractInteger(IntegerValue arg){
		return new IntegerValue(arg.getValue() - this.getValue());
	}
	
	@Override
	public Value subtractMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().subtract(new BigDecimal(this.getValue())));
	}
	
	@Override
	public Value multiply(Value arg){
		return arg.multiplyInteger(this);
	}
	
	@Override
	public Value multiplyInteger(IntegerValue arg){
		return new IntegerValue(arg.getValue() * this.getValue());
	}
	
	@Override
	public Value multiplyMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().multiply(new BigDecimal(this.getValue())));
	}
	
	@Override
	public Value divide(Value arg){
		return arg.divideInteger(this);
	}
	
	@Override
	public Value divideInteger(IntegerValue arg){
		return new IntegerValue(arg.getValue() / this.getValue());
	}
	
	@Override
	public Value divideMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().divide(new BigDecimal(this.getValue())));
	}
	
	@Override
	public Value equal(Value arg){
		return arg.equalInteger(this);
	}
	
	@Override
	public Value equalInteger(IntegerValue arg){
		return new BooleanValue(arg.getValue() == this.getValue());
	}
	
	@Override
	public Value equalMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(new BigDecimal(this.getValue())) == 0);
	}
	
	@Override
	public Value notEqual(Value arg){
		return arg.notEqualInteger(this);
	}
	
	@Override
	public Value notEqualInteger(IntegerValue arg){
		return new BooleanValue(arg.getValue() != this.getValue());
	}
	
	@Override
	public Value notEqualMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(new BigDecimal(this.getValue())) != 0);
	}
	
	@Override
	public Value greaterThan(Value arg){
		return arg.greaterThanInteger(this);
	}
	
	@Override
	public Value greaterThanInteger(IntegerValue arg){
		return new BooleanValue(arg.getValue() > this.getValue());
	}
	
	@Override
	public Value greaterThanMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(new BigDecimal(this.getValue())) > 0);
	}
	
	@Override
	public Value greaterThanEqual(Value arg){
		return arg.greaterThanEqualInteger(this);
	}
	
	@Override
	public Value greaterThanEqualInteger(IntegerValue arg){
		return new BooleanValue(arg.getValue() >= this.getValue());
	}
	
	@Override
	public Value greaterThanEqualMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(new BigDecimal(this.getValue())) >= 0);
	}
	
	@Override
	public Value lessThan(Value arg){
		return arg.lessThanInteger(this);
	}
	
	@Override
	public Value lessThanInteger(IntegerValue arg){
		return new BooleanValue(arg.getValue() < this.getValue());
	}
	
	@Override
	public Value lessThanMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(new BigDecimal(this.getValue())) < 0);
	}
	
	@Override
	public Value lessThanEqual(Value arg){
		return arg.lessThanEqualInteger(this);
	}
	
	@Override
	public Value lessThanEqualInteger(IntegerValue arg){
		return new BooleanValue(arg.getValue() <= this.getValue());
	}
	
	@Override
	public Value lessThanEqualMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(new BigDecimal(this.getValue())) <= 0);
	}
}

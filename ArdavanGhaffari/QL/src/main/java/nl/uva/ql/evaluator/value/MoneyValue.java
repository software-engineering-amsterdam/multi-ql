package nl.uva.ql.evaluator.value;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyValue extends Value {

	private final BigDecimal value;
	
	public MoneyValue(BigDecimal value){
		this.value = value.setScale(2, RoundingMode.HALF_UP);
	}
	
	public BigDecimal getValue(){
		return value;
	}
	
	@Override
	public String toString(){
		return value.toString();
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof MoneyValue){
			return value.equals(((MoneyValue)obj).getValue());
		}
		return false;
	}
	
	@Override
	public Value add(Value arg){
		return arg.addMoney(this);
	}

	@Override
	public Value addMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().add(this.getValue()));
	}

	@Override
	public Value addInteger(IntegerValue arg){
		return new MoneyValue(new BigDecimal(arg.getValue()).add(this.getValue()));
	}
	
	@Override
	public Value subtract(Value arg){
		return arg.subtractMoney(this);
	}
	
	@Override
	public Value subtractMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().subtract(this.getValue()));
	}
	
	@Override
	public Value subtractInteger(IntegerValue arg){
		return new MoneyValue(new BigDecimal(arg.getValue()).subtract(this.getValue()));
	}
	
	@Override
	public Value multiply(Value arg){
		return arg.multiplyMoney(this);
	}
	
	@Override
	public Value multiplyMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().multiply(this.getValue()));
	}
	
	@Override
	public Value multiplyInteger(IntegerValue arg){
		return new MoneyValue(new BigDecimal(arg.getValue()).multiply(this.getValue()));
	}
	
	@Override
	public Value divide(Value arg){
		return arg.divideMoney(this);
	}
	
	@Override
	public Value divideMoney(MoneyValue arg){
		return new MoneyValue(arg.getValue().divide(this.getValue()));
	}
	
	@Override
	public Value divideInteger(IntegerValue arg){
		return new MoneyValue(new BigDecimal(arg.getValue()).divide(this.getValue()));
	}
	
	@Override
	public Value equal(Value arg){
		return arg.equalMoney(this);
	}
	
	@Override
	public Value equalMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(this.getValue()) == 0);
	}
	
	@Override
	public Value equalInteger(IntegerValue arg){
		return new BooleanValue(new BigDecimal(arg.getValue()).compareTo(this.getValue()) == 0);
	}
	
	@Override
	public Value notEqual(Value arg){
		return arg.notEqualMoney(this);
	}
	
	@Override
	public Value notEqualMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(this.getValue()) != 0);
	}
	
	@Override
	public Value notEqualInteger(IntegerValue arg){
		return new BooleanValue(new BigDecimal(arg.getValue()).compareTo(this.getValue()) != 0);
	}
	
	@Override
	public Value greaterThan(Value arg){
		return arg.greaterThanMoney(this);
	}
	
	@Override
	public Value greaterThanMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(this.getValue()) > 0);
	}
	
	@Override
	public Value greaterThanInteger(IntegerValue arg){
		return new BooleanValue(new BigDecimal(arg.getValue()).compareTo(this.getValue()) > 0);
	}
	
	@Override
	public Value greaterThanEqual(Value arg){
		return arg.greaterThanEqualMoney(this);
	}
	
	@Override
	public Value greaterThanEqualMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(this.getValue()) >= 0);
	}
	
	@Override
	public Value greaterThanEqualInteger(IntegerValue arg){
		return new BooleanValue(new BigDecimal(arg.getValue()).compareTo(this.getValue()) >= 0);
	}
	
	@Override
	public Value lessThan(Value arg){
		return arg.lessThanMoney(this);
	}
	
	@Override
	public Value lessThanMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(this.getValue()) < 0);
	}
	
	@Override
	public Value lessThanInteger(IntegerValue arg){
		return new BooleanValue(new BigDecimal(arg.getValue()).compareTo(this.getValue()) < 0);
	}
	
	@Override
	public Value lessThanEqual(Value arg){
		return arg.lessThanEqualMoney(this);
	}
	
	@Override
	public Value lessThanEqualMoney(MoneyValue arg){
		return new BooleanValue(arg.getValue().compareTo(this.getValue()) <= 0);
	}
	
	@Override
	public Value lessThanEqualInteger(IntegerValue arg){
		return new BooleanValue(new BigDecimal(arg.getValue()).compareTo(this.getValue()) <= 0);
	}
}

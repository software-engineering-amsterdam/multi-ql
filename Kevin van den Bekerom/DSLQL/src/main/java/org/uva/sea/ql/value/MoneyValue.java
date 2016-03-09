package org.uva.sea.ql.value;

public class MoneyValue extends Value {
	private double value;
	
	public MoneyValue(double value) {
		this.value = value;
	}
	
	public Value add(Value rhs) {
		return rhs.addMoney(this);
	}
	
	public Value addInt(IntValue rhs) {
		return new MoneyValue(value + rhs.getValue());
	}
	
	public Value addMoney(MoneyValue rhs) {
		return new MoneyValue(value + rhs.getValue());
	}
	
	// sub
	public Value sub(Value rhs) {
		return rhs.subMoney(this);
	}
	
	public Value subInt(IntValue rhs) {
		return new MoneyValue(value - rhs.getValue());
	}
	
	public Value subMoney(MoneyValue rhs) {
		return new MoneyValue(value - rhs.getValue());
	}
	
	// mul
	public Value mul(Value rhs) {
		return rhs.mulMoney(this);
	}
	
	public Value mulInt(IntValue rhs) {
		return new MoneyValue(value * rhs.getValue());
	}
	
	public Value mulMoney(MoneyValue rhs) {
		return new MoneyValue(value * rhs.getValue());
	}
	
	// div
	public Value div(Value rhs) {
		return rhs.divMoney(this);
	}
	
	public Value divInt(IntValue rhs) {
		return new MoneyValue(value / rhs.getValue());
	}
	
	public Value divMoney(MoneyValue rhs) {
		return new MoneyValue(value / rhs.getValue());
	}
	
	// pos
	public Value pos() {
		return new MoneyValue(Math.abs(value));
	}
	
	// neg
	public Value neg() {
		return new MoneyValue(value * -1);
	}
	
	/* logic operations */
	
	// greater than
	public Value gt(Value rhs, boolean gEq) {
		return rhs.gtMoney(this, gEq);
	}
	
	public Value gtInt(IntValue rhs, boolean gEq) {
		return (gEq) 	? new BoolValue(value >= rhs.getValue()) 
						: new BoolValue(value > rhs.getValue());
	}
	
	public Value gtMoney(MoneyValue rhs, boolean gEq) {
		return (gEq) 	? new BoolValue(value >= rhs.getValue()) 
						: new BoolValue(value > rhs.getValue());
	}
	
	// less than
	public Value lt(Value rhs, boolean lEq) {
		return rhs.ltMoney(this, lEq);
	}
	
	public Value ltInt(IntValue rhs, boolean lEq) {
		return (lEq) 	? new BoolValue(value <= rhs.getValue()) 
						: new BoolValue(value < rhs.getValue());
	}
	
	public Value ltMoney(MoneyValue rhs, boolean lEq) {
		return (lEq) 	? new BoolValue(value <= rhs.getValue()) 
						: new BoolValue(value < rhs.getValue());
	}
	
	@Override
	public Double getValue() {
		return this.value;
	}
}

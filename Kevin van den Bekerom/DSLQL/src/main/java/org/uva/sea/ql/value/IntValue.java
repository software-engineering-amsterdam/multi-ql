package org.uva.sea.ql.value;

import java.math.BigDecimal;

public class IntValue extends Value {
	private int value;
	
	public IntValue(int value) {
		this.value = value;
	}
	
	// add
	public Value add(Value rhs) {
		return rhs.addInt(this);
	}
	
	public Value addInt(IntValue rhs) {
		return new IntValue(value + rhs.getValue());
	}
	
	public Value addMoney(MoneyValue rhs) {
		return new MoneyValue(value + rhs.getValue());
	}
	
	// sub
	public Value sub(Value rhs) {
		return rhs.subInt(this);
	}
	
	public Value subInt(IntValue rhs) {
		return new IntValue(value - rhs.getValue());
	}
	
	public Value subMoney(MoneyValue rhs) {
		return new MoneyValue(value - rhs.getValue());
	}
	
	// mul
	public Value mul(Value rhs) {
		return rhs.mulInt(this);
	}
	
	public Value mulInt(IntValue rhs) {
		return new IntValue(value * rhs.getValue());
	}
	
	public Value mulMoney(MoneyValue rhs) {
		return new MoneyValue(value * rhs.getValue());
	}
	
	// div
	public Value div(Value rhs) {
		return rhs.divInt(this);
	}
	
	public Value divInt(IntValue rhs) {
		return new IntValue(value / rhs.getValue());
	}
	
	public Value divMoney(MoneyValue rhs) {
		return new MoneyValue(value / rhs.getValue());
	}
	
	// pos
	public Value pos() {
		return new IntValue(Math.abs(value));
	}
	
	// neg
	public Value neg() {
		return new IntValue(value * -1);
	}
	
	/* logic operations */
	
	// greater than
	public Value gt(Value rhs, boolean gEq) {
		return rhs.gtInt(this, gEq);
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
		return rhs.ltInt(this, lEq);
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
	public Integer getValue() {
		return this.value;
	}
}

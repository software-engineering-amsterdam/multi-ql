package org.uva.sea.ql.value;


public abstract class Value {
	
	/* math operations */
	
	//add
	public Value add(Value rhs) {
		return null; //BadOperandException()
	}
	
	public Value addInt(IntValue rhs) {
		return null; //BadOperandException()
	}
	
	public Value addMoney(MoneyValue rhs) {
		return null;
	}
	
	//sub
	public Value sub(Value rhs) {
		return null; //BadOperandException()
	}
	
	public Value subInt(IntValue rhs) {
		return null; //BadOperandException()
	}
	
	public Value subMoney(MoneyValue rhs) {
		return null;
	}
	
	// mul
	public Value mul(Value rhs) {
		return null; //BadOperandException()
	}
	
	public Value mulInt(IntValue rhs) {
		return null; //BadOperandException()
	}
	
	public Value mulMoney(MoneyValue rhs) {
		return null;
	}
	
	// div
	public Value div(Value rhs) {
		return null; //BadOperandException()
	}
	
	public Value divInt(IntValue rhs) {
		return null; //BadOperandException()
	}
	
	public Value divMoney(MoneyValue rhs) {
		return null;
	}
	
	// pos
	public Value pos() {
		return null; //BadOperandException()
	}
	
	// neg 
	public Value neg() {
		return null; //BadOperandException()
	}
	
	/* logic operations */
	
	// greater than
	public Value gt(Value rhs, boolean gEq) {
		return null;
	}
	
	public Value gtInt(IntValue rhs, boolean gEq) {
		return null; //BadOperandException()
	}
	
	public Value gtMoney(MoneyValue rhs, boolean gEq) {
		return null;
	}
	
	// less than
	public Value lt(Value rhs, boolean lEq) {
		return null;
	}
	
	public Value ltInt(IntValue rhs, boolean lEq) {
		return null; //BadOperandException()
	}
	
	public Value ltMoney(MoneyValue rhs, boolean lEq) {
		return null;
	}
	
	
	
	public abstract Object getValue();
	
}

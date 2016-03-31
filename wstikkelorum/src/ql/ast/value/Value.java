package ql.ast.value;

import ql.ast.visitor.Visitable;

public abstract class Value implements Visitable{
	
	public abstract Object getValue();
	
	public Value OrExpression(BooleanValue booleanValue){
		return new NullValue();
	}
	
	public Value AndExpression(BooleanValue booleanValue){
		return new NullValue();
	}
	
	public Value Eq(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value Geq(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value Gt(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value LEq(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value LT(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value NEq(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value add(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value Sub(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value Mul(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value Div(IntegerValue integerValue){
		return new NullValue();
	}
	
	public Value Not(){
		return new NullValue();
	}	
	
	public Value Pos(){
		return new NullValue();
	}
	
	public Value Neg(){
		return new NullValue();
	}
}

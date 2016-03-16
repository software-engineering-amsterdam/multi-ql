package nl.uva.sc.ql.compiler.parser.value;

public class BooleanVal extends Value {

	boolean bool;
	
	public BooleanVal(boolean bool){
		this.bool = bool;
	}
	
	public boolean getBool(){
		return this.bool;
	}
	
	@Override
	public BooleanVal equal(Value value){
		return new BooleanVal(getBool() == ((BooleanVal) value).getBool());
	}
	
	@Override
	public BooleanVal different(Value value){
		return new BooleanVal(getBool() != ((BooleanVal) value).getBool());
	}
	
	public BooleanVal and(BooleanVal booleanVal){
		return new BooleanVal(getBool() && booleanVal.getBool());
	}
	
	public BooleanVal or(BooleanVal booleanVal){
		return new BooleanVal(getBool() || booleanVal.getBool());
	}

	public BooleanVal not() {
		return new BooleanVal(!getBool());
	}

	@Override
	public Object getValue() {
		return this.bool;
	}
	
	@Override
	public String toString(){
		return this.bool + "";
	}
}

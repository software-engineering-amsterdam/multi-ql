package nl.uva.sc.ql.compiler.parser.value;

public class StringVal extends Value {

	String string;
	
	public StringVal(String string){
		this.string = string;
	}
	
	public String getString(){
		return this.string;
	}
	
	@Override
	public BooleanVal equal(Value value){
		return new BooleanVal(getString() == ((StringVal) value).getString());
	}
	
	@Override
	public BooleanVal different(Value value){
		return new BooleanVal(getString() != ((StringVal) value).getString());
	}

	@Override
	public Object getValue() {
		return this.string;
	}
	
	@Override
	public String toString(){
		return this.string;
	}
}

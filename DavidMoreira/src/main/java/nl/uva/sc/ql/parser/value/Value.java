package nl.uva.sc.ql.parser.value;

public abstract class Value {	
	public abstract Object getValue();
	
	public abstract BooleanVal equal(Value value);
	public abstract BooleanVal different(Value value);
}

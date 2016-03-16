package sc.ql.value;

public abstract class Value {

	public abstract Value parse(String text);

	// Add
	public abstract Value add(Value other);

	public abstract Value add(NumberValue other);

	public abstract Value add(BooleanValue other);

	public abstract Value add(StringValue other);

	// Subtract
	public abstract Value subtract(Value other);

	public abstract Value subtract(NumberValue other);

	public abstract Value subtract(BooleanValue other);

	public abstract Value subtract(StringValue other);

	// Multiply
	public abstract Value mul(Value other);

	public abstract Value mul(NumberValue other);

	public abstract Value mul(BooleanValue other);

	public abstract Value mul(StringValue other);

	// Divide
	public abstract Value div(Value other);

	public abstract Value div(NumberValue other);

	public abstract Value div(BooleanValue other);

	public abstract Value div(StringValue other);

	// Greater than or equal
	public abstract BooleanValue greaterThanOrEqual(Value other);

	public abstract BooleanValue greaterThanOrEqual(NumberValue other);

	public abstract BooleanValue greaterThanOrEqual(BooleanValue other);

	public abstract BooleanValue greaterThanOrEqual(StringValue other);

	// Less than or equal
	public abstract BooleanValue lessThanOrEqual(Value other);

	public abstract BooleanValue lessThanOrEqual(NumberValue other);

	public abstract BooleanValue lessThanOrEqual(BooleanValue other);

	public abstract BooleanValue lessThanOrEqual(StringValue other);

	// Greater than
	public abstract BooleanValue greaterThan(Value other);

	public abstract BooleanValue greaterThan(NumberValue other);

	public abstract BooleanValue greaterThan(BooleanValue other);

	public abstract BooleanValue greaterThan(StringValue other);

	// Less than
	public abstract BooleanValue lessThan(Value other);

	public abstract BooleanValue lessThan(NumberValue other);

	public abstract BooleanValue lessThan(BooleanValue other);

	public abstract BooleanValue lessThan(StringValue other);

	// Equal
	public abstract BooleanValue equal(Value other);

	public abstract BooleanValue equal(NumberValue other);

	public abstract BooleanValue equal(BooleanValue other);

	public abstract BooleanValue equal(StringValue other);

	// Or
	public abstract BooleanValue or(Value other);

	public abstract BooleanValue or(NumberValue other);

	public abstract BooleanValue or(BooleanValue other);

	public abstract BooleanValue or(StringValue other);

	// And
	public abstract BooleanValue and(Value other);

	public abstract BooleanValue and(NumberValue other);

	public abstract BooleanValue and(BooleanValue other);

	public abstract BooleanValue and(StringValue other);

	public abstract BooleanValue not();

	public abstract NumberValue negative();

	public abstract NumberValue positive();
}

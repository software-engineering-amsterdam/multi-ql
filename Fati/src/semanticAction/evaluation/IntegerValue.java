package semanticAction.evaluation;

public class IntegerValue extends Value<Integer> {

	public IntegerValue(int integerValue) {
		super(integerValue);
	}

	public Value<Integer> add(Value<Integer> value) {
		return value.addInt(this);
	}

	public Value<Integer> addInt(IntegerValue value) {
		return new IntegerValue(value.getValue() + getValue());
	}

	public Value<Integer> sub(Value<Integer> value) {
		return value.subInt(this);
	}

	public Value<Integer> subInt(IntegerValue value) {
		return new IntegerValue(value.getValue() - getValue());
	}

	public Value<Integer> time(Value<Integer> value) {
		return value.timeInt(this);
	}

	public Value<Integer> timeInt(IntegerValue value) {
		return new IntegerValue(value.getValue() * getValue());
	}

	public Value<Integer> div(Value<Integer> value) {
		return value.divisionInt(this);
	}

	public Value<Integer> divisionInt(IntegerValue value) {
		return new IntegerValue(value.getValue() / getValue());
	}

	// ........

	public Value<Integer> equal(Value<Integer> value) {
		return value.equalInt(this);
	}

	public BooleanValue equalInt(IntegerValue value) {
		return new BooleanValue(value.getValue() == getValue());
	}

	public Value<Integer> notEqual(Value<Integer> value) {
		return value.notEqualInt(this);
	}

	public BooleanValue notEqualInt(IntegerValue value) {
		return new BooleanValue(value.getValue() != getValue());
	}

	public Value<Integer> greaterEqual(Value<Integer> value) {
		return value.greaterEqualInt(this);
	}

	public Value greaterEqualInt(IntegerValue value) {
		return new BooleanValue(value.getValue() >= value.getValue());
	}

	public Value<Integer> greaterThan(Value<Integer> value) {
		return value.greaterEqualInt(this);
	}

	public Value greaterThanInt(Value<Integer> value) {
		return new BooleanValue(value.getValue() > value.getValue());
	}

	public Value lessThan(Value value) {
		return value.lessThanInt(this);
	}

	public Value lessThanInt(IntegerValue value) {
		return new BooleanValue(value.getValue() < value.getValue());
	}

	public Value<Integer> lessEqual(Value<Integer> value) {
		return value.lessEqualInt(this);
	}

	public Value lessEqualInt(IntegerValue value) {
		return new BooleanValue(value.getValue() <= value.getValue());
	}

	// ...............................
	public Value<Integer> plus() {
		return new IntegerValue(getValue());
	}

	public Value<Integer> minus() {
		return new IntegerValue(-getValue());
	}

}
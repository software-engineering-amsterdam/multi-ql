package sc.ql.value;

public final class UnknownValue
    extends ValueAdapter
{

  public UnknownValue()
  {

  }

  @Override
  public UnknownValue parse(String text)
  {
    return new UnknownValue();
  }

  @Override
  public String toString()
  {
    return "undefined";
  }

  @Override
  public boolean equals(Object obj)
  {
    return obj instanceof UnknownValue;
  }

  @Override
  public int hashCode()
  {
    return 46;
  }

  @Override
  public Value add(Value other)
  {
    return new UnknownValue();
  }

  @Override
  public Value subtract(Value other)
  {
    return new UnknownValue();
  }

  @Override
  public Value mul(Value other)
  {
    return new UnknownValue();
  }

  @Override
  public Value div(Value other)
  {
    return new UnknownValue();
  }

  @Override
  public BooleanValue greaterThanOrEqual(Value other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue lessThanOrEqual(Value other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue greaterThan(Value other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue lessThan(Value other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue equal(Value other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue equal(NumberValue other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue equal(BooleanValue other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue equal(StringValue other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue or(Value other)
  {
    return BooleanValue.FALSE;
  }

  @Override
  public BooleanValue and(Value other)
  {
    return BooleanValue.FALSE;
  }
}
package sc.ql.value;

public abstract class ValueAdapter
    extends Value
{
  @Override
  public Value add(NumberValue other)
  {
    return error();
  }

  @Override
  public Value add(BooleanValue other)
  {
    return error();
  }

  @Override
  public Value add(StringValue other)
  {
    return error();
  }

  @Override
  public Value subtract(NumberValue other)
  {
    return error();
  }

  @Override
  public Value subtract(BooleanValue other)
  {
    return error();
  }

  @Override
  public Value subtract(StringValue other)
  {
    return error();
  }

  @Override
  public Value mul(NumberValue other)
  {
    return error();
  }

  @Override
  public Value mul(BooleanValue other)
  {
    return error();
  }

  @Override
  public Value mul(StringValue other)
  {
    return error();
  }

  @Override
  public Value div(NumberValue other)
  {
    return error();
  }

  @Override
  public Value div(BooleanValue other)
  {
    return error();
  }

  @Override
  public Value div(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue greaterThanOrEqual(NumberValue other)
  {
    return error();
  }

  @Override
  public BooleanValue greaterThanOrEqual(BooleanValue other)
  {
    return error();
  }

  @Override
  public BooleanValue greaterThanOrEqual(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue lessThanOrEqual(NumberValue other)
  {
    return error();
  }

  @Override
  public BooleanValue lessThanOrEqual(BooleanValue other)
  {
    return error();
  }

  @Override
  public BooleanValue lessThanOrEqual(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue greaterThan(NumberValue other)
  {
    return error();
  }

  @Override
  public BooleanValue greaterThan(BooleanValue other)
  {
    return error();
  }

  @Override
  public BooleanValue greaterThan(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue lessThan(NumberValue other)
  {
    return error();
  }

  @Override
  public BooleanValue lessThan(BooleanValue other)
  {
    return error();
  }

  @Override
  public BooleanValue lessThan(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue or(NumberValue other)
  {
    return error();
  }

  @Override
  public BooleanValue or(BooleanValue other)
  {
    return error();
  }

  @Override
  public BooleanValue or(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue and(NumberValue other)
  {
    return error();
  }

  @Override
  public BooleanValue and(BooleanValue other)
  {
    return error();
  }

  @Override
  public BooleanValue and(StringValue other)
  {
    return error();
  }

  @Override
  public BooleanValue not()
  {
    return error();
  }

  @Override
  public NumberValue negative()
  {
    return error();
  }

  @Override
  public NumberValue positive()
  {
    return error();
  }

  protected <T extends Value> T error()
  {
    throw new UnsupportedOperationException(getClass().getName());
  }
}

package sc.ql.ast;

public abstract class ValueType
    extends ASTNode
{
  public static final ValueType BOOLEAN = new BooleanType();
  public static final ValueType STRING = new StringType();
  public static final ValueType INTEGER = new IntegerType();

  public abstract <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context);

  public static final class BooleanType
      extends ValueType
  {

    public BooleanType()
    {

    }

    @Override
    public <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }

    @Override
    public boolean equals(Object obj)
    {
      return obj instanceof BooleanType;
    }

    @Override
    public int hashCode()
    {
      return 42;
    }

    @Override
    public String toString()
    {
      return "Boolean";
    }
  }

  public static final class IntegerType
      extends ValueType
  {

    public IntegerType()
    {

    }

    @Override
    public <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }

    @Override
    public boolean equals(Object obj)
    {
      return obj instanceof IntegerType;
    }

    @Override
    public int hashCode()
    {
      return 42;
    }

    @Override
    public String toString()
    {
      return "Integer";
    }
  }

  public static final class StringType
      extends ValueType
  {

    public StringType()
    {

    }

    @Override
    public <T, U> T accept(ValueTypeVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }

    @Override
    public boolean equals(Object obj)
    {
      return obj instanceof StringType;
    }

    @Override
    public int hashCode()
    {
      return 42;
    }

    @Override
    public String toString()
    {
      return "String";
    }
  }
}

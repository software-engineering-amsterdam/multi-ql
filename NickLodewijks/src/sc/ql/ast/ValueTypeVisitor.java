package sc.ql.ast;

import sc.ql.ast.ValueType.BooleanType;
import sc.ql.ast.ValueType.IntegerType;
import sc.ql.ast.ValueType.StringType;
import sc.ql.ast.ValueType.UnknownType;

public interface ValueTypeVisitor<T, U>
{
  public T visit(UnknownType type, U context);

  public T visit(BooleanType type, U context);

  public T visit(StringType type, U context);

  public T visit(IntegerType type, U context);
}

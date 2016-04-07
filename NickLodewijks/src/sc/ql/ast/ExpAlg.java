package sc.ql.ast;

public interface ExpAlg<T>
{
  public T add(T lhs, T rhs);

  public T sub(T lhs, T rhs);

  public T div(T lhs, T rhs);

  public T mul(T lhs, T rhs);

  public T eq(T lhs, T rhs);

  public T gteq(T lhs, T rhs);

  public T gt(T lhs, T rhs);

  public T lteq(T lhs, T rhs);

  public T lt(T lhs, T rhs);

  public T ne(T lhs, T rhs);

  public T or(T lhs, T rhs);

  public T and(T lhs, T rhs);

  public T neg(T lhs);

  public T not(T lhs);

  public T pos(T lhs);
}

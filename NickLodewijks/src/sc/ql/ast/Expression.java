package sc.ql.ast;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Expression
    extends ASTNode
{

  public abstract <T, U> T accept(ExpressionVisitor<T, U> visitor, U context);

  /**
   * Collect all free variables in specified expressions.
   * 
   * @param expr
   *          the expression to collect all free variables from.
   * @return the free variables used in the expression.
   */
  public Set<String> freeVariables()
  {
    Set<String> freeVariables;

    freeVariables = new HashSet<>();

    this.accept(new TopDown<Void, Void>()
                {
                  @Override
                  public Void visit(VariableExpr node, Void unused)
                  {
                    freeVariables.add(node.getVariableName());
                    return null;
                  }
                },
                null);

    return Collections.unmodifiableSet(freeVariables);
  }

  public static abstract class BinaryExpr
      extends Expression
  {
    private final Expression lhs;
    private final Expression rhs;

    public BinaryExpr(Expression lhs, Expression rhs)
    {
      this.lhs = lhs;
      this.rhs = rhs;
    }

    public final Expression left()
    {
      return lhs;
    }

    public final Expression right()
    {
      return rhs;
    }
  }

  public static class Add
      extends BinaryExpr
  {
    public Add(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Subtract
      extends BinaryExpr
  {
    public Subtract(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class And
      extends BinaryExpr
  {
    public And(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Or
      extends BinaryExpr
  {
    public Or(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Multiply
      extends BinaryExpr
  {
    public Multiply(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Divide
      extends BinaryExpr
  {
    public Divide(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Equal
      extends BinaryExpr
  {
    public Equal(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class NotEqual
      extends BinaryExpr
  {
    public NotEqual(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class GreaterThan
      extends BinaryExpr
  {
    public GreaterThan(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class GreaterThanOrEqual
      extends BinaryExpr
  {
    public GreaterThanOrEqual(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class LessThan
      extends BinaryExpr
  {
    public LessThan(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class LessThanOrEqual
      extends BinaryExpr
  {
    public LessThanOrEqual(Expression lhs, Expression rhs)
    {
      super(lhs,
            rhs);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static abstract class UnaryExpr
      extends Expression
  {
    private final Expression expr;

    public UnaryExpr(Expression expr)
    {
      this.expr = expr;
    }

    public final Expression expr()
    {
      return expr;
    }
  }

  public static class Negative
      extends UnaryExpr
  {
    public Negative(Expression expr)
    {
      super(expr);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Positive
      extends UnaryExpr
  {
    public Positive(Expression expr)
    {
      super(expr);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Not
      extends UnaryExpr
  {
    public Not(Expression expr)
    {
      super(expr);
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static final class VariableExpr
      extends Expression
  {
    private final String variableName;

    public VariableExpr(String variableName)
    {
      this.variableName = variableName;
    }

    public String getVariableName()
    {
      return variableName;
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static final class LiteralExpr
      extends Expression
  {
    private final Literal literal;

    public LiteralExpr(Literal literal)
    {
      this.literal = literal;
    }

    public Literal literal()
    {
      return literal;
    }

    @Override
    public <T, U> T accept(ExpressionVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }
}

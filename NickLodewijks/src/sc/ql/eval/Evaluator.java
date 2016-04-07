package sc.ql.eval;

import sc.ql.ast.Expression;
import sc.ql.ast.Expression.Add;
import sc.ql.ast.Expression.And;
import sc.ql.ast.Expression.Divide;
import sc.ql.ast.Expression.Equal;
import sc.ql.ast.Expression.GreaterThan;
import sc.ql.ast.Expression.GreaterThanOrEqual;
import sc.ql.ast.Expression.LessThan;
import sc.ql.ast.Expression.LessThanOrEqual;
import sc.ql.ast.Expression.LiteralExpr;
import sc.ql.ast.Expression.Multiply;
import sc.ql.ast.Expression.Negative;
import sc.ql.ast.Expression.Not;
import sc.ql.ast.Expression.NotEqual;
import sc.ql.ast.Expression.Or;
import sc.ql.ast.Expression.Positive;
import sc.ql.ast.Expression.Subtract;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.ExpressionVisitor;
import sc.ql.value.BooleanValue;
import sc.ql.value.Value;

public class Evaluator
    implements ExpressionVisitor<Value, Environment>
{
  @SuppressWarnings("unchecked")
  public static <T extends Value> T evaluate(Expression expr, Environment context)
  {
    try
    {
      return (T) expr.accept(new Evaluator(),
                             context);
    }
    catch (RuntimeException ex)
    {
      throw new RuntimeException(String.format("Failed to interpret expression '%s'",
                                               expr.getSourceText()),
                                 ex);
    }
  }

  private Evaluator()
  {

  }

  @Override
  public Value visit(Add node, Environment context)
  {
    return node.left().accept(this,
                              context).add(node.right().accept(this,
                                                               context));
  }

  @Override
  public Value visit(Subtract node, Environment context)
  {
    return node.left().accept(this,
                              context).subtract(node.right().accept(this,
                                                                    context));
  }

  @Override
  public Value visit(And node, Environment context)
  {
    return node.left().accept(this,
                              context).and(node.right().accept(this,
                                                               context));
  }

  @Override
  public Value visit(Or node, Environment context)
  {
    return node.left().accept(this,
                              context).or(node.right().accept(this,
                                                              context));
  }

  @Override
  public Value visit(Divide node, Environment context)
  {
    return node.left().accept(this,
                              context).div(node.right().accept(this,
                                                               context));
  }

  @Override
  public Value visit(Multiply node, Environment context)
  {
    return node.left().accept(this,
                              context).mul(node.right().accept(this,
                                                               context));
  }

  @Override
  public Value visit(Equal node, Environment context)
  {
    return node.left().accept(this,
                              context).equal(node.right().accept(this,
                                                                 context));
  }

  @Override
  public Value visit(NotEqual node, Environment context)
  {
    return node.left().accept(this,
                              context).equal(node.right().accept(this,
                                                                 context)).not();
  }

  @Override
  public Value visit(GreaterThanOrEqual node, Environment context)
  {
    return node.left().accept(this,
                              context).greaterThanOrEqual(node.right().accept(this,
                                                                              context));
  }

  @Override
  public Value visit(GreaterThan node, Environment context)
  {
    return node.left().accept(this,
                              context).greaterThan(node.right().accept(this,
                                                                       context));
  }

  @Override
  public BooleanValue visit(LessThanOrEqual node, Environment context)
  {
    return node.left().accept(this,
                              context).lessThanOrEqual(node.right().accept(this,
                                                                           context));
  }

  @Override
  public BooleanValue visit(LessThan node, Environment context)
  {
    return node.left().accept(this,
                              context).lessThan(node.right().accept(this,
                                                                    context));
  }

  @Override
  public BooleanValue visit(Not node, Environment context)
  {
    return node.expr().accept(this,
                              context).not();
  }

  @Override
  public Value visit(Positive node, Environment context)
  {
    return node.expr().accept(this,
                              context).positive();
  }

  @Override
  public Value visit(Negative node, Environment context)
  {
    return node.expr().accept(this,
                              context).negative();
  }

  @Override
  public Value visit(VariableExpr node, Environment context)
  {
    return context.getValue(node.getVariableName());
  }

  @Override
  public Value visit(LiteralExpr node, Environment context)
  {
    return node.literal().value();
  }
}

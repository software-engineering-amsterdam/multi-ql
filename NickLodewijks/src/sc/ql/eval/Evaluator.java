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
  public static <T extends Value> T evaluate(Expression expr, Environment env)
  {
    try
    {
      return (T) expr.accept(new Evaluator(),
                             env);
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
  public Value visit(Add node, Environment env)
  {
    return node.left().accept(this,
                              env).add(node.right().accept(this,
                                                           env));
  }

  @Override
  public Value visit(Subtract node, Environment env)
  {
    return node.left().accept(this,
                              env).subtract(node.right().accept(this,
                                                                env));
  }

  @Override
  public Value visit(And node, Environment env)
  {
    return node.left().accept(this,
                              env).and(node.right().accept(this,
                                                           env));
  }

  @Override
  public Value visit(Or node, Environment env)
  {
    return node.left().accept(this,
                              env).or(node.right().accept(this,
                                                          env));
  }

  @Override
  public Value visit(Divide node, Environment env)
  {
    return node.left().accept(this,
                              env).div(node.right().accept(this,
                                                           env));
  }

  @Override
  public Value visit(Multiply node, Environment env)
  {
    return node.left().accept(this,
                              env).mul(node.right().accept(this,
                                                           env));
  }

  @Override
  public Value visit(Equal node, Environment env)
  {
    return node.left().accept(this,
                              env).equal(node.right().accept(this,
                                                             env));
  }

  @Override
  public Value visit(NotEqual node, Environment env)
  {
    return node.left().accept(this,
                              env).equal(node.right().accept(this,
                                                             env)).not();
  }

  @Override
  public Value visit(GreaterThanOrEqual node, Environment env)
  {
    return node.left().accept(this,
                              env).greaterThanOrEqual(node.right().accept(this,
                                                                          env));
  }

  @Override
  public Value visit(GreaterThan node, Environment env)
  {
    return node.left().accept(this,
                              env).greaterThan(node.right().accept(this,
                                                                   env));
  }

  @Override
  public BooleanValue visit(LessThanOrEqual node, Environment env)
  {
    return node.left().accept(this,
                              env).lessThanOrEqual(node.right().accept(this,
                                                                       env));
  }

  @Override
  public BooleanValue visit(LessThan node, Environment env)
  {
    return node.left().accept(this,
                              env).lessThan(node.right().accept(this,
                                                                env));
  }

  @Override
  public BooleanValue visit(Not node, Environment env)
  {
    return node.expr().accept(this,
                              env).not();
  }

  @Override
  public Value visit(Positive node, Environment env)
  {
    return node.expr().accept(this,
                              env).positive();
  }

  @Override
  public Value visit(Negative node, Environment env)
  {
    return node.expr().accept(this,
                              env).negative();
  }

  @Override
  public Value visit(VariableExpr node, Environment env)
  {
    return env.getValue(node.getVariableName());
  }

  @Override
  public Value visit(LiteralExpr node, Environment env)
  {
    return node.literal().value();
  }
}

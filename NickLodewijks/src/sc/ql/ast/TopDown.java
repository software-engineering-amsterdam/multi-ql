package sc.ql.ast;

import sc.ql.ast.Expression.Add;
import sc.ql.ast.Expression.And;
import sc.ql.ast.Expression.BinaryExpr;
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
import sc.ql.ast.Expression.UnaryExpr;
import sc.ql.ast.Expression.VariableExpr;
import sc.ql.ast.Literal.BooleanLiteral;
import sc.ql.ast.Literal.IntegerLiteral;
import sc.ql.ast.Literal.StringLiteral;
import sc.ql.ast.Statement.Block;
import sc.ql.ast.Statement.ComputedQuestion;
import sc.ql.ast.Statement.IfThen;
import sc.ql.ast.Statement.NormalQuestion;
import sc.ql.ast.ValueType.BooleanType;
import sc.ql.ast.ValueType.IntegerType;
import sc.ql.ast.ValueType.StringType;
import sc.ql.ast.ValueType.UnknownType;

public class TopDown<T, U>
    implements ExpressionVisitor<T, U>, StatementVisitor<T, U>, ValueTypeVisitor<T, U>, LiteralVisitor<T, U>
{

  private T visit(BinaryExpr node, U context)
  {
    node.left().accept(this,
                       context);
    node.right().accept(this,
                        context);

    return null;
  }

  private T visit(UnaryExpr node, U context)
  {
    node.expr().accept(this,
                       context);

    return null;
  }

  @Override
  public T visit(Add node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(Subtract node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(Divide node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(Multiply node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(Equal node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(GreaterThanOrEqual node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(GreaterThan node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(LessThanOrEqual node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(LessThan node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(NotEqual node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(And node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(Or node, U context)
  {
    return visit((BinaryExpr) node,
                 context);
  }

  @Override
  public T visit(Negative node, U context)
  {
    return visit((UnaryExpr) node,
                 context);
  }

  @Override
  public T visit(Not node, U context)
  {
    return visit((UnaryExpr) node,
                 context);
  }

  @Override
  public T visit(Positive node, U context)
  {
    return visit((UnaryExpr) node,
                 context);
  }

  @Override
  public T visit(VariableExpr node, U context)
  {
    return null;
  }

  @Override
  public T visit(Block node, U context)
  {
    node.statements().forEach(statement -> statement.accept(this,
                                                            context));

    return null;
  }

  @Override
  public T visit(IfThen node, U context)
  {
    node.condition().accept(this,
                            context);
    node.then().accept(this,
                       context);

    return null;
  }

  @Override
  public T visit(LiteralExpr node, U context)
  {
    node.literal().accept(this,
                          context);

    return null;
  }

  @Override
  public T visit(BooleanLiteral node, U context)
  {
    return null;
  }

  @Override
  public T visit(IntegerLiteral node, U context)
  {
    return null;
  }

  @Override
  public T visit(StringLiteral node, U context)
  {
    return null;
  }

  @Override
  public T visit(NormalQuestion node, U context)
  {
    node.type().accept(this,
                       context);

    return null;
  }

  @Override
  public T visit(ComputedQuestion node, U context)
  {
    node.type().accept(this,
                       context);

    return null;
  }

  @Override
  public T visit(UnknownType type, U context)
  {
    return null;
  }

  @Override
  public T visit(BooleanType type, U context)
  {
    return null;
  }

  @Override
  public T visit(StringType type, U context)
  {
    return null;
  }

  @Override
  public T visit(IntegerType type, U context)
  {
    return null;
  }
}

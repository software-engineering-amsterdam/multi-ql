package sc.ql.ast;

import sc.ql.ast.Statement.Block;
import sc.ql.ast.Statement.ComputedQuestion;
import sc.ql.ast.Statement.IfThen;
import sc.ql.ast.Statement.NormalQuestion;

public interface StatementVisitor<T, U>
{
  public T visit(NormalQuestion node, U context);

  public T visit(ComputedQuestion node, U context);

  public T visit(IfThen node, U context);

  public T visit(Block node, U context);
}

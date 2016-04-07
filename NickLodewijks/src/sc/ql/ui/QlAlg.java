package sc.ql.ui;

import sc.ql.ast.Expression;
import sc.ql.ast.Form;
import sc.ql.ast.Statement.Question;
import sc.ql.eval.Environment;

public interface QlAlg<T, U, V>
{
  public T form(Form form);

  public U question(Question question, Expression condition, Expression computation, Environment env);

  public V labelWidget(Question question, Environment env);

  public V valueWidget(Question question, Environment env);
}

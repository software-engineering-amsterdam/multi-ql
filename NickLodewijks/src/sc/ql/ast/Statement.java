package sc.ql.ast;

import java.util.Collections;
import java.util.List;

public abstract class Statement
    extends ASTNode
{
  
  public abstract <T, U> T accept(StatementVisitor<T, U> visitor, U context);

  public static final class IfThen
      extends Statement
  {
    private final Expression condition;
    private final Statement then;

    public IfThen(Expression condition, Statement then)
    {
      this.condition = condition;
      this.then = then;
    }

    public Expression condition()
    {
      return condition;
    }

    public Statement then()
    {
      return then;
    }

    @Override
    public <T, U> T accept(StatementVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static final class Block
      extends Statement
  {
    private final List<Statement> statements;

    public Block(List<Statement> statements)
    {
      this.statements = statements;
    }

    public List<Statement> statements()
    {
      return Collections.unmodifiableList(statements);
    }

    @Override
    public <T, U> T accept(StatementVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static abstract class Question
      extends Statement
  {
    private final ValueType type;
    private final String name;
    private final String label;

    public Question(ValueType type, String name, String label)
    {
      this.type = type;
      this.name = name;
      this.label = label;
    }

    public ValueType type()
    {
      return type;
    }

    public String name()
    {
      return name;
    }

    public String label()
    {
      return label;
    }
  }

  public static final class ComputedQuestion
      extends Question
  {
    private final Expression computation;

    public ComputedQuestion(ValueType type, String name, String label, Expression computation)
    {
      super(type,
            name,
            label);

      this.computation = computation;
    }

    public Expression computation()
    {
      return computation;
    }

    @Override
    public <T, U> T accept(StatementVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static final class NormalQuestion
      extends Question
  {
    public NormalQuestion(ValueType type, String name, String label)
    {
      super(type,
            name,
            label);
    }

    @Override
    public <T, U> T accept(StatementVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }
}

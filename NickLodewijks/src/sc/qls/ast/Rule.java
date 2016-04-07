package sc.qls.ast;

import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;
import sc.ql.ast.ValueType;

public abstract class Rule
    extends ASTNode
{
  private final Widget widget;
  private final List<Property> properties;

  public Rule(List<Property> properties)
  {
    this(null,
         properties);
  }

  public Rule(Widget widget, List<Property> properties)
  {
    this.widget = widget;
    this.properties = properties;
  }

  public List<Property> properties()
  {
    return Collections.unmodifiableList(properties);
  }

  public Widget widget()
  {
    return widget;
  }

  public abstract <T, U> T accept(RuleVisitor<T, U> visitor, U context);

  public static class QuestionRule
      extends Rule
  {
    private final String name;

    public QuestionRule(String name, Widget widget, List<Property> properties)
    {
      super(widget,
            properties);
      this.name = name;
    }

    public String name()
    {
      return name;
    }

    @Override
    public <T, U> T accept(RuleVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class ValueTypeRule
      extends Rule
  {
    private final ValueType type;

    public ValueTypeRule(Widget widget, ValueType type, List<Property> properties)
    {
      super(widget,
            properties);
      this.type = type;
    }

    public ValueType type()
    {
      return type;
    }

    @Override
    public <T, U> T accept(RuleVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }
}

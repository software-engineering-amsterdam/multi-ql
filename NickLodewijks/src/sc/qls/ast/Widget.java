package sc.qls.ast;

import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;

public abstract class Widget
    extends ASTNode
{

  public abstract <T, U> T accept(WidgetVisitor<T, U> visitor, U context);

  public static class TextField
      extends Widget
  {
    public TextField()
    {

    }

    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class DefaultWidget
      extends Widget
  {
    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public abstract static class ListWidget
      extends Widget
  {
    private final List<String> values;
    private final String defaultValue;

    public ListWidget(List<String> values, String defaultValue)
    {
      this.values = values;
      this.defaultValue = defaultValue;
    }

    public List<String> values()
    {
      return Collections.unmodifiableList(values);
    }

    public String defaultValue()
    {
      return defaultValue;
    }
  }

  public static class Spinbox
      extends ListWidget
  {
    public Spinbox(List<String> values, String defaultValue)
    {
      super(values,
            defaultValue);
    }

    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class Slider
      extends ListWidget
  {
    public Slider(List<String> values, String defaultValue)
    {
      super(values,
            defaultValue);
    }

    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class RadioButton
      extends ListWidget
  {
    public RadioButton(List<String> values, String defaultValue)
    {
      super(values,
            defaultValue);
    }

    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class DropDown
      extends ListWidget
  {
    public DropDown(List<String> values, String defaultValue)
    {
      super(values,
            defaultValue);
    }

    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }

  public static class CheckBox
      extends ListWidget
  {
    public CheckBox(List<String> values, String defaultValue)
    {
      super(values,
            defaultValue);
    }

    @Override
    public <T, U> T accept(WidgetVisitor<T, U> visitor, U context)
    {
      return visitor.visit(this,
                           context);
    }
  }
}

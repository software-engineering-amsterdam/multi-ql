package sc.qls.ast;

import sc.qls.ast.Property.ColorProperty;
import sc.qls.ast.Property.FontNameProperty;
import sc.qls.ast.Property.FontSizeProperty;
import sc.qls.ast.Property.FontStyleProperty;
import sc.qls.ast.Property.HeightProperty;
import sc.qls.ast.Property.WidthProperty;

public interface PropertyVisitor<T, U>
{
  public T visit(HeightProperty property, U context);

  public T visit(WidthProperty property, U context);

  public T visit(ColorProperty property, U context);

  public T visit(FontStyleProperty property, U context);

  public T visit(FontSizeProperty property, U context);

  public T visit(FontNameProperty property, U context);
}

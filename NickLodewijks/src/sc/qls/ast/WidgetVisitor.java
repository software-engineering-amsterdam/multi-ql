package sc.qls.ast;

import sc.qls.ast.Widget.CheckBox;
import sc.qls.ast.Widget.DefaultWidget;
import sc.qls.ast.Widget.DropDown;
import sc.qls.ast.Widget.RadioButton;
import sc.qls.ast.Widget.Slider;
import sc.qls.ast.Widget.Spinbox;
import sc.qls.ast.Widget.TextField;

public interface WidgetVisitor<T, U>
{
  public T visit(DefaultWidget widget, U context);

  public T visit(RadioButton widget, U context);

  public T visit(DropDown widget, U context);

  public T visit(Slider widget, U context);

  public T visit(Spinbox widget, U context);

  public T visit(TextField widget, U context);

  public T visit(CheckBox widget, U context);
}

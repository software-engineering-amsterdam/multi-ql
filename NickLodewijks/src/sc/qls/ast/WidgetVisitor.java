package sc.qls.ast;

import sc.qls.ast.Widget.CheckBox;
import sc.qls.ast.Widget.DropDown;
import sc.qls.ast.Widget.RadioButton;
import sc.qls.ast.Widget.Slider;
import sc.qls.ast.Widget.Spinbox;
import sc.qls.ast.Widget.TextField;

public interface WidgetVisitor<T, U> {

	public T visit(RadioButton rb, U context);

	public T visit(DropDown rb, U context);

	public T visit(Slider rb, U context);

	public T visit(Spinbox rb, U context);

	public T visit(TextField rb, U context);

	public T visit(CheckBox rb, U context);
}

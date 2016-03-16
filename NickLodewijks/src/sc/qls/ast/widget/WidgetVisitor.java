package sc.qls.ast.widget;

public interface WidgetVisitor<T, U> {

	public T visit(RadioButton rb, U context);

	public T visit(DropDown rb, U context);

	public T visit(Slider rb, U context);

	public T visit(Spinbox rb, U context);

	public T visit(TextField rb, U context);

	public T visit(CheckBox rb, U context);
}

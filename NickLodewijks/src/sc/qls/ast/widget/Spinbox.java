package sc.qls.ast.widget;

public class Spinbox extends Widget {

	public Spinbox() {

	}

	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}

package sc.qls.ast.widget;

public class TextField extends Widget {

	public TextField() {
	}

	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}

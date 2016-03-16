package sc.qls.ast.widget;

public class Slider extends Widget {

	public Slider() {

	}

	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}

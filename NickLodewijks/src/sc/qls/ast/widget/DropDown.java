package sc.qls.ast.widget;

import java.util.Collections;
import java.util.List;

public class DropDown extends Widget {

	private final List<String> options;
	private final String defaultOption;

	public DropDown(List<String> options, String defaultOption) {
		this.options = options;
		this.defaultOption = defaultOption;
	}

	public List<String> getOptions() {
		return Collections.unmodifiableList(options);
	}

	@Override
	public <T, U> T accept(WidgetVisitor<T, U> visitor, U context) {
		return visitor.visit(this, context);
	}
}

package sc.qls.ast.widget;

import java.util.Collections;
import java.util.List;

public class QLSDropDown extends QLSWidgetType {

	private final List<String> options;
	private final String defaultOption;

	public QLSDropDown(List<String> options, String defaultOption) {
		this.options = options;
		this.defaultOption = defaultOption;
	}

	public List<String> getOptions() {
		return Collections.unmodifiableList(options);
	}
}

package org.uva.qls.ast.widget;

import java.util.Collections;
import java.util.List;

public class QLSCheckBox extends QLSWidgetType {

	private final List<String> options;
	private final String defaultOption;

	public QLSCheckBox(List<String> options, String defaultOption) {
		this.options = options;
		this.defaultOption = defaultOption;
	}

	public List<String> getOptions() {
		return Collections.unmodifiableList(options);
	}
}

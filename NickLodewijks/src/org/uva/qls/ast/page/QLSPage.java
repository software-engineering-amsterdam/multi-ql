package org.uva.qls.ast.page;

import java.util.Collections;
import java.util.List;

import org.uva.qls.ast.QLSASTNode;

public class QLSPage extends QLSASTNode {

	private final String name;
	private final List<QLSSection> sections;

	public QLSPage(String name, List<QLSSection> sections) {
		this.name = name;
		this.sections = sections;
	}

	public String getName() {
		return name;
	}

	public List<QLSSection> getSections() {
		return Collections.unmodifiableList(sections);
	}

}

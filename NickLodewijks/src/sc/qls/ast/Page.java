package sc.qls.ast;

import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;

public class Page extends ASTNode {

	private final String name;
	private final List<Section> sections;

	public Page(String name, List<Section> sections) {
		this.name = name;
		this.sections = sections;
	}

	public String getName() {
		return name;
	}

	public List<Section> getSections() {
		return Collections.unmodifiableList(sections);
	}

}

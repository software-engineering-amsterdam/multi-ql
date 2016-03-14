package sc.qls.ast.page;

import java.util.Collections;
import java.util.List;

import sc.qls.ast.QLSASTNode;
import sc.qls.ast.type.QLSTypeDef;

public class QLSSection extends QLSASTNode {

	private final String name;
	private final List<QLSQuestion> questions;
	private final List<QLSTypeDef> typeDefs;

	public QLSSection(String name, List<QLSQuestion> questions, List<QLSTypeDef> typeDefs) {
		this.name = name;
		this.questions = questions;
		this.typeDefs = typeDefs;
	}

	public String getName() {
		return name;
	}

	public boolean contains(String id) {
		return getById(id) != null;
	}

	public QLSQuestion getById(String id) {
		for (QLSQuestion qlsQuestion : questions) {
			if (qlsQuestion.getId().equals(id)) {
				return qlsQuestion;
			}
		}

		return null;
	}

	public int indexOf(String id) {
		return questions.indexOf(getById(id));
	}

	public List<QLSQuestion> getQuestions() {
		return Collections.unmodifiableList(questions);
	}

	public List<QLSTypeDef> getTypeDefs() {
		return Collections.unmodifiableList(typeDefs);
	}
}

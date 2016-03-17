package sc.qls.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;
import sc.qls.ast.Rule.QuestionRule;
import sc.qls.ast.Rule.ValueTypeRule;

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

	public List<Section> sections() {
		return Collections.unmodifiableList(sections);
	}

	public List<QuestionRule> questionRules() {
		List<QuestionRule> questions;

		questions = new ArrayList<>();

		for (Section section : sections) {
			section.rules()
					.forEach(r -> {
						r.accept(new RuleVisitor<Void, Void>() {

							@Override
							public Void visit(QuestionRule rule, Void context) {
								questions.add(rule);
								return null;
							}

							@Override
							public Void visit(ValueTypeRule rule, Void context) {
								return null;
							}

						}, null);
					});
		}

		return Collections.unmodifiableList(questions);
	}

	public boolean containsQuestions(String id) {
		for (Section section : sections) {
			if (section.contains(id)) {
				return true;
			}
		}

		return false;
	}

}

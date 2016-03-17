package sc.qls.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sc.ql.ast.ASTNode;
import sc.qls.ast.Rule.QuestionRule;
import sc.qls.ast.Rule.ValueTypeRule;

public class Section extends ASTNode {

	private final String name;
	private final List<Rule> rules;

	public Section(String name, List<Rule> rules) {
		this.name = name;
		this.rules = rules;
	}

	public String name() {
		return name;
	}

	public boolean contains(String id) {
		return getById(id) != null;
	}

	public QuestionRule getById(String id) {
		for (QuestionRule rule : getQuestionRules()) {
			if (rule.name().equals(id)) {
				return rule;
			}
		}

		return null;
	}

	private List<QuestionRule> getQuestionRules() {
		List<QuestionRule> questionRules;

		questionRules = new ArrayList<>();

		for (Rule rule : rules) {
			rule.accept(new RuleVisitor<Void, Void>() {

				@Override
				public Void visit(QuestionRule rule, Void context) {
					questionRules.add(rule);
					return null;
				}

				@Override
				public Void visit(ValueTypeRule rule, Void context) {
					return null;
				}
			}, null);
		}

		return questionRules;
	}

	public int indexOf(String id) {
		return rules.indexOf(getById(id));
	}

	public List<Rule> rules() {
		return Collections.unmodifiableList(rules);
	}
}

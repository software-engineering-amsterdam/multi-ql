package eu.bankersen.kevin.ql.form.analyzer.scanners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.CyclicDependency;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.ast.Form;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.IdentifierGatherer;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.UserQuestion;
import eu.bankersen.kevin.ql.form.ast.visitors.TopDownVisitor;

public class Dependencies {
	private final List<ScannerError> errorList;

	public Dependencies(Form form) {
		this.errorList = findDependencies(form);
	}

	public List<ScannerError> getErrors() {
		return errorList;
	}

	private List<ScannerError> findDependencies(Form form) {
		Map<String, Set<String>> relations = analyzeForm(form);

		List<ScannerError> errors = new ArrayList<>();
		for (String question : relations.keySet()) {
			Set<String> identifiers = relations.get(question);
			findAll(identifiers, relations);

			if (identifiers.contains(question)) {
				errors.add(new CyclicDependency(0, question, identifiers));
			}
		}
		return errors;
	}

	private void findAll(Set<String> identifiers, Map<String, Set<String>> relation) {
		int previousSize = 0;

		while (identifiers.size() != previousSize) {
			previousSize = identifiers.size();
			findRelations(identifiers, relation);
		}
	}

	private void findRelations(Set<String> identifiers, Map<String, Set<String>> relations) {
		for (String question : new HashSet<>(identifiers)) {

			if (relations.containsKey(question)) {
				identifiers.addAll(relations.get(question));
			}
		}
	}

	private Map<String, Set<String>> analyzeForm(Form form) {

		Map<String, Set<String>> relations = new HashMap<>();

		form.accept(new TopDownVisitor<Map<String, Set<String>>>() {

			@Override
			public void visit(ComputedQuestion question, Map<String, Set<String>> context) {

				context.put(question.name(), question.computation().accept(new IdentifierGatherer(), new HashSet<>()));
			}

			@Override
			public void visit(UserQuestion question, Map<String, Set<String>> context) {
			}

		}, relations);
		return relations;
	}
}
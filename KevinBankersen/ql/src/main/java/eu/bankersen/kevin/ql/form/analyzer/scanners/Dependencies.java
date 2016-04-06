package eu.bankersen.kevin.ql.form.analyzer.scanners;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.CyclicDependency;
import eu.bankersen.kevin.ql.form.analyzer.scanners.errors.ScannerError;
import eu.bankersen.kevin.ql.form.ast.expressions.Binary;
import eu.bankersen.kevin.ql.form.ast.expressions.Identifier;
import eu.bankersen.kevin.ql.form.ast.expressions.Literal;
import eu.bankersen.kevin.ql.form.ast.expressions.visitors.LeafVisitor;
import eu.bankersen.kevin.ql.form.ast.statements.ComputedQuestion;
import eu.bankersen.kevin.ql.form.ast.statements.Form;
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
		Map<String, Set<String>> relations = new HashMap<>();
		analyzeForm(form, relations);

		List<ScannerError> errors = new ArrayList<>();
		for (String declaration : relations.keySet()) {
			Set<String> computations = relations.get(declaration);
			findAll(computations, relations);

			if (computations.contains(declaration)) {
				errors.add(new CyclicDependency(0, declaration, computations));
			}
		}
		return errors;
	}

	private void findAll(Set<String> computations, Map<String, Set<String>> relations) {
		int previousSize = 0;

		while (computations.size() != previousSize) {
			previousSize = computations.size();
			findRelations(computations, relations);
		}
	}

	private void findRelations(Set<String> computations, Map<String, Set<String>> relations) {
		for (String declaration : computations.toArray(new String[computations.size()])) {

			if (relations.containsKey(declaration)) {
				computations.addAll(relations.get(declaration));
			}
		}
	}

	private void analyzeForm(Form form, Map<String, Set<String>> relations) {

		form.accept(new TopDownVisitor<Map<String, Set<String>>>() {

			@Override
			public void visit(ComputedQuestion question, Map<String, Set<String>> context) {

				context.put(question.name(), question.computation().accept(new LeafVisitor<Set<String>, Set<String>>() {

					@Override
					public Set<String> visit(Literal expression, Set<String> context) {
						return context;
					}

					@Override
					public Set<String> visit(Identifier expression, Set<String> context) {
						context.add(expression.name());
						return context;
					}

					@Override
					public Set<String> visitBinary(Binary expression, Set<String> context) {
						context.addAll(expression.lhs().accept(this, context));
						context.addAll(expression.rhs().accept(this, context));
						return context;
					}

				}, new HashSet<>()));
			}

			@Override
			public void visit(UserQuestion o, Map<String, Set<String>> context) {
			}

		}, relations);
	}
}
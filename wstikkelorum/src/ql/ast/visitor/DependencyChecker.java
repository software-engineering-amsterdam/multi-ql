package ql.ast.visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ql.ast.expression.VariableExpression;
import ql.ast.form.Form;
import ql.ast.statement.question.ComputedQuestion;
import ql.issue.problem.CyclicDependency;

public class DependencyChecker<T> extends BasicVisitor<T> {
	private HashMap<String, List<String>> directDependencies;
	private String currentQuestionIdentifier;
	private Context context;

	public DependencyChecker(Context context) {
		directDependencies = new HashMap<String, List<String>>();
		assert(context != null);
		this.context = context;
	}

	@Override
	public T visit(ComputedQuestion computedQuestion) {
		currentQuestionIdentifier = computedQuestion.getVariable().getIdentifier();
		directDependencies.put(computedQuestion.getVariable().getIdentifier(), new ArrayList<String>());
		computedQuestion.getExpression().accept(this);
		currentQuestionIdentifier = null;
		return null;
	}

	@Override
	public T visit(VariableExpression variableExpression) {
		if (currentQuestionIdentifier != null) {
			addDependencyToQuestion(variableExpression);
		}
		return null;
	}

	private void addDependencyToQuestion(VariableExpression variableExpression) {
		List<String> currentDependencies = directDependencies.get(currentQuestionIdentifier);
		// No duplicate identifiers in the dependency list
		if (!currentDependencies.contains(variableExpression.getIdentifier())) {
			currentDependencies.add(variableExpression.getIdentifier());
			directDependencies.put(currentQuestionIdentifier, currentDependencies);
		}
	}

	public void findCyclicDependencies(Form form) {
		assert(form != null);
		this.visit(form);
		List<String> visitedVariables = new ArrayList<String>();
		directDependencies.forEach((identifier, dependencies) -> recursiveDepencyCheck(identifier, identifier, visitedVariables));
	}

	private void recursiveDepencyCheck(String currentIdentifier, String originalIdentifier, List<String> visitedVariables) {
		List<String> dependenciesCurrentIdentifier = directDependencies.get(currentIdentifier);

		if (!visitedVariables.contains(originalIdentifier)) {
			visitedVariables.add(originalIdentifier);
		}

		if (dependenciesCurrentIdentifier == null) {
			return;//Terminal node
		}

		for (String dependency : dependenciesCurrentIdentifier) {
			if (visitedVariables.contains(dependency)) {
				context.addIssue(new CyclicDependency(originalIdentifier, dependency));
				return;
			}
			visitedVariables.add(dependency);
		}

		for (String dependency : dependenciesCurrentIdentifier) {
			recursiveDepencyCheck(dependency, originalIdentifier, visitedVariables);
		}
	}

	public Context getContext() {
		return context;
	}
}

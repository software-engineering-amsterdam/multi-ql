package eu.bankersen.kevin.ql.form.typechecker.analytics.dependancies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eu.bankersen.kevin.ql.form.typechecker.errors.CyclicDependencyError;
import eu.bankersen.kevin.ql.form.typechecker.errors.TypeCheckError;

public class DependancyChecker {

	private final List<TypeCheckError> errorList;

	private final Map<String, Set<String>> questionRelations;
	private final LinkedList<Set<String>> ifBlockIdentifiers;
	private final LinkedList<Set<String>> elseBlockIdentifiers;

	private final List<BlockContents> processedBlocks;
	private final LinkedList<BlockContents> openBlocks;

	private boolean ifBlock;

	public DependancyChecker() {
		this.errorList = new ArrayList<>();

		this.questionRelations = new HashMap<>();
		this.ifBlockIdentifiers = new LinkedList<>();
		this.ifBlockIdentifiers.add(new HashSet<>());

		this.elseBlockIdentifiers = new LinkedList<>();
		this.elseBlockIdentifiers.add(new HashSet<>());

		this.processedBlocks = new ArrayList<>();
		this.openBlocks = new LinkedList<>();
		this.ifBlock = true;
	}

	public List<TypeCheckError> getErrors() {
		errorList.clear();
		analyzeData();
		return errorList;
	}

	// Algorithm for calculating dependencies between questions.
	private void analyzeData() {
		Map<String, Set<String>> relations = computeDependencies();

		for (BlockContents block : processedBlocks) {
			errorList.addAll(block.scanForDependancies(relations));
		}
	}

	private Map<String, Set<String>> computeDependencies() {
		Map<String, Set<String>> computedDependencies = new HashMap<>();

		for (String var : questionRelations.keySet()) {
			Set<String> dependencies = questionRelations.get(var);
			searchMoreDependencies(dependencies);
			checkForErrors(dependencies, var);
			computedDependencies.put(var, dependencies);

		}
		return computedDependencies;
	}

	private void searchMoreDependencies(Set<String> dependencies) {
		int previousSize = 0;

		while (dependencies.size() != previousSize) {
			previousSize = dependencies.size();
			searchForDependancies(dependencies);
		}
	}

	private void searchForDependancies(Set<String> dependencies) {
		for (String var : dependencies.toArray(new String[dependencies.size()])) {

			if (questionRelations.containsKey(var)) {
				dependencies.addAll(questionRelations.get(var));
			}
		}
	}

	private void checkForErrors(Set<String> dependencies, String var) {
		if (dependencies.contains(var)) {
			errorList.add(new CyclicDependencyError(0, var, dependencies));
		}
	}

	// Tracking Identifiers.
	public void registerQuestion(String name) {
		registerQuestion(name, new HashSet<>());
	}

	public void registerQuestion(String name, Set<String> identifiers) {
		registerInBlock(name);
		defineQuestionRelation(name, identifiers);
	}

	public void openNewBlock(int line) {
		ifBlock = true;
		openBlocks.add(new BlockContents(line));
		ifBlockIdentifiers.add(new HashSet<>());
		elseBlockIdentifiers.add(new HashSet<>());
	}

	public void closeIfBlock() {
		ifBlock = false;
	}

	public void closeBlock(Set<String> condition) {
		Set<String> ifBlock = getIFBlockIdentifiers();
		Set<String> elseBlock = getElseBlockIdentifiers();

		updateBlock(condition, ifBlock, elseBlock);
	}

	private void updateBlock(Set<String> condition, Set<String> ifStat, Set<String> elseStat) {
		BlockContents data = openBlocks.pollLast();
		data.setIdentifiers(condition, ifStat, elseStat);
		processedBlocks.add(data);

		// Update the now last element
		if (!openBlocks.isEmpty()) {
			data = openBlocks.pollLast();
			data.addInnerBlockIdentifiers(ifStat, elseStat);
			openBlocks.addLast(data);
		}
	}

	private void registerInBlock(String name) {
		if (ifBlock) {
			registerInIfBlock(name);
		} else {
			registerInElseBlock(name);
		}
	}

	private void registerInIfBlock(String name) {
		Set<String> lastElement;
		lastElement = ifBlockIdentifiers.pollLast();
		lastElement.add(name);
		ifBlockIdentifiers.add(lastElement);
	}

	private void registerInElseBlock(String name) {
		Set<String> lastElement;
		lastElement = elseBlockIdentifiers.pollLast();
		lastElement.add(name);
		elseBlockIdentifiers.add(lastElement);
	}

	private void registerInIfBlock(Set<String> identifiers) {
		Set<String> lastElement;
		lastElement = ifBlockIdentifiers.pollLast();
		lastElement.addAll(identifiers);
		ifBlockIdentifiers.add(lastElement);
	}

	// Define a relationship between Question and identifiers.
	private void defineQuestionRelation(String question, Set<String> identifiers) {
		if (!identifiers.isEmpty()) {
			Set<String> data = identifiers;
			questionRelations.put(question, data);
			identifiers = new HashSet<>();
		}
	}

	private Set<String> getIFBlockIdentifiers() {
		Set<String> data = ifBlockIdentifiers.pollLast();
		addDataToSuperBlock(data);
		return data;
	}

	private Set<String> getElseBlockIdentifiers() {
		Set<String> data = elseBlockIdentifiers.pollLast();
		addDataToSuperBlock(data);
		return data;
	}

	private void addDataToSuperBlock(Set<String> data) {
		if (!data.isEmpty()) {
			registerInIfBlock(data);
		}
	}

	private class BlockContents {

		private final int line;
		private final Set<String> condition;
		private final Set<String> ifIdentifiers;
		private final Set<String> elseIdentifiers;

		BlockContents(int line) {
			this.line = line;
			this.condition = new HashSet<>();
			this.ifIdentifiers = new HashSet<>();
			this.elseIdentifiers = new HashSet<>();
		}

		public void setIdentifiers(Set<String> condition, Set<String> ifIdentifiers, Set<String> elseIdentifiers) {
			this.condition.addAll(condition);
			this.ifIdentifiers.addAll(ifIdentifiers);
			this.elseIdentifiers.addAll(elseIdentifiers);
		}

		public void addInnerBlockIdentifiers(Set<String> ifIdentifiers, Set<String> elseIdentifiers) {
			this.ifIdentifiers.addAll(ifIdentifiers);
			this.ifIdentifiers.addAll(elseIdentifiers);
		}

		public List<TypeCheckError> scanForDependancies(Map<String, Set<String>> questionDependancies) {
			return scanForRelations(questionDependancies);
		}

		private List<TypeCheckError> scanForRelations(Map<String, Set<String>> questionDependancies) {
			List<TypeCheckError> errorList = new ArrayList<>();

			for (String identifier : condition) {

				if (questionDependancies.containsKey(identifier)) {
					Set<String> conditionRelations = questionDependancies.get(identifier);
					findRelationWithOwnDependancies(conditionRelations, errorList);
				}
				findRelationWithDeclaration(identifier, errorList);
			}

			findRelationsBetweenIFandElse(questionDependancies, errorList);
			return errorList;
		}

		private void findRelationWithOwnDependancies(Set<String> conditionRelations, List<TypeCheckError> errorList) {

			if (containsAnyEqualObject(conditionRelations, ifIdentifiers)) {
				errorList.add(new CyclicDependencyError(line, condition, ifIdentifiers));
			}

			if (containsAnyEqualObject(conditionRelations, elseIdentifiers)) {
				errorList.add(new CyclicDependencyError(line, condition, elseIdentifiers));
			}
		}

		private void findRelationWithDeclaration(String identifier, List<TypeCheckError> errorList) {
			if (ifIdentifiers.contains(identifier)) {
				errorList.add(new CyclicDependencyError(line, condition, ifIdentifiers));
			}

			if (elseIdentifiers.contains(identifier)) {
				errorList.add(new CyclicDependencyError(line, condition, elseIdentifiers));
			}
		}

		private void findRelationsBetweenIFandElse(Map<String, Set<String>> conditionRelations,
				List<TypeCheckError> errorList) {

			for (String identifier : ifIdentifiers) {
				if (conditionRelations.containsKey(identifier)) {
					if (containsAnyEqualObject(conditionRelations.get(identifier), elseIdentifiers)) {
						errorList.add(new CyclicDependencyError(line, identifier, elseIdentifiers));
					}
				}
			}
		}

		private Boolean containsAnyEqualObject(Set<String> condition, Set<String> block) {
			return condition.stream().anyMatch(block::contains);
		}

		@Override
		public String toString() {
			return "Condition: " + condition + "\nIf " + ifIdentifiers + "\nElse: " + elseIdentifiers + "\n";
		}

	}
}

package org.uva.sea.ql.semantic;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.domain.Question;

public class QuestionCyclicDependencyManager {

	private Map<String, Set<String>> questionReferenceList;
	private Set<String> dependencies;
	private String identifier;

	public QuestionCyclicDependencyManager() {
		questionReferenceList = new HashMap<String, Set<String>>();
	}

	public void addQuestionDependency(Question question, Set<String> dependencies) {
		this.dependencies = dependencies;
		this.identifier = question.getVariableId().getIdentifier().getName();
		questionReferenceList.put(question.getVariableId().getIdentifier().getName(), dependencies);
	}

	public boolean hasCyclicDepency() {
		boolean isCyclic = false;
		for (String dependency : dependencies) {
			if (questionReferenceList.containsKey(dependency)) {
				Set<String> referenceDependency = questionReferenceList.get(dependency);
				if (referenceDependency.contains(this.identifier)) {
					isCyclic = true;
				}
			}
		}
		return isCyclic;
	}
}

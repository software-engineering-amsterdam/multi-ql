package org.uva.sea.ql.semantic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uva.sea.ql.ast.domain.Question;

public class QuestionCyclicDependencyManager {

	private Map<String, Set<String>> questionReferenceList;
	private List<String> cyclicMessages;

	public QuestionCyclicDependencyManager() {
		questionReferenceList = new HashMap<String, Set<String>>();
		cyclicMessages = new ArrayList<String>();
	}

	public void addQuestionDependency(Question question, Set<String> dependencies) {
		questionReferenceList.put(question.getVarIdentifierName(), dependencies);
	}

	public boolean hasCyclicDepency() {
		return !cyclicMessages.isEmpty();
	}
	
	public List<String> findCyclicDepency() {
		for (String node : questionReferenceList.keySet()) {
	        final Set<String> directNeighbors = questionReferenceList.get(node);
	        for (String directNeighbor : directNeighbors) {
	            Set<String> next = questionReferenceList.get(directNeighbor);
	            while (next != null) {
	                for (String n : next) {
	                    next = questionReferenceList.get(n);
	                    if (next != null && (next.contains(n) || next.contains(node))) {
	                    	cyclicMessages.add("The question '"+node+"' has cyclic dependency with question '"+directNeighbors+"'");
	    					
	                    }
	                }
	            }
	        }
	    }
		return Collections.unmodifiableList(cyclicMessages);
	}
	
	public void depthFirst(String dependencyIdentifier) {
		
	}
}

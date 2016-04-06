package org.uva.sea.ql.semantic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Message {

	private final Map<String,String> warnings;
	private final Map<String,Set<String>> errors;

	public Message() {
		warnings = new HashMap<>();
		errors = new HashMap<String, Set<String>>();
	}

	public void addWarning(String identifier,String msg) {
		warnings.put(identifier,msg);
	}

	public void addError(String identifier,String msg) {
		Set<String> errorSet;
		if(!errors.containsKey(identifier)){
			 errorSet = new HashSet<>();
		}else{
			errorSet = errors.get(identifier);
		}
		errorSet.add(msg);
		
		errors.put(identifier, errorSet);
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public boolean hasQuestion(String questionText) {
		return warnings.containsKey(questionText);
	}

	public Map<String,Set<String>> getErrors() {
		return errors;
	}

	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	public Map<String,String> getWarnings() {
		return warnings;
	}

	public boolean hasMessages() {
		return hasErrors() || hasWarnings();
	}

	public void print() {
		if (hasErrors()) {
			for (Entry<String, Set<String>> setErrors : errors.entrySet()) {
				for (String error : setErrors.getValue()) {
					System.err.println(error);
				}
				
			}
		}
		System.out.println("\n");
		if (hasWarnings()) {
			for (Map.Entry<String, String> warning : warnings.entrySet())
			{
			    System.out.println(warning.getValue());
			}
		}
	}

}

package org.uva.sea.ql.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

	private final Map<String,String> warnings;
	private final List<String> errors;

	public Message() {
		warnings = new HashMap<>();
		errors = new ArrayList<>();
	}

	public void addWarning(String identifier,String msg) {
		warnings.put(identifier,msg);
	}

	public void addError(String msg) {
		errors.add(msg);
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}
	
	public boolean hasQuestion(String questionText) {
		return warnings.containsKey(questionText);
	}

	public List<String> getErrors() {
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
			for (String error : errors) {
				System.err.println(error);

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

package org.uva.sea.ql.semantic;

import java.util.HashMap;
import java.util.Map;

public class Result {

	private final Map<String,String> warnings = new HashMap<>();
	private final Map<String,String> errors = new HashMap<>();

	public Result() {

	}

	public void addWarning(String var, String msg) {
		warnings.put(var, msg);
	}

	public void addError(String var, String msg) {
		errors.put(var, msg);
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	public Map<String, String> getWarnings() {
		return warnings;
	}

	public boolean hasMessages() {
		return hasErrors() || hasWarnings();
	}

	

	public void print() {
		if(hasErrors()){
			for (Map.Entry<String, String> error : errors.entrySet())
			{
			    System.err.println(error.getValue());
			    System.out.println("\n");
			}
		}
		
		if(hasWarnings()){
		for (Map.Entry<String, String> warning : warnings.entrySet())
			{
			    System.out.println(warning.getValue());
			    System.out.println("\n");
			}
		}
	}

}

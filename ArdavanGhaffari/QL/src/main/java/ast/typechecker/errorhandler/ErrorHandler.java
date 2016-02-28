package ast.typechecker.errorhandler;

import java.util.LinkedList;
import java.util.List;


public class ErrorHandler {
	private List<Error> errors;
	private List<Warning> warnings;
	
	public void addError(Error error) {
		if (errors == null) {
			errors = new LinkedList<>();
		}
		errors.add(error);
	}
	
	public boolean hasError() {
		if (errors != null && errors.size() > 0) {
			return true;
		}
		return false;
	}
	
	public void printErrors() {
			for (Error error: errors) {
				System.out.println(error.getMessage());
			}
	}
	
	public void addWarning(Warning warning) {
		if (warnings == null) {
			warnings = new LinkedList<>();
		}
		warnings.add(warning);
	}
	
	public void printWarnings() {
		if (warnings != null && warnings.size() > 0) {
			for (Warning warning: warnings) {
				System.out.println(warning.getMessage());
			}
		}
	}
	
	public List<Error> getErrors() {
		return errors;
	}
	
	public List<Warning> getWarnings() {
		return warnings;
	}
	
}

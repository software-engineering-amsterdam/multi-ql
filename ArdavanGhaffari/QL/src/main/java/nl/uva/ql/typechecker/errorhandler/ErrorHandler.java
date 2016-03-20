package nl.uva.ql.typechecker.errorhandler;

import java.util.LinkedList;
import java.util.List;

import nl.uva.ql.typechecker.errorhandler.error.Error;
import nl.uva.ql.typechecker.errorhandler.warning.Warning;


public class ErrorHandler {
	private List<Error> errors = new LinkedList<>();
	private List<Warning> warnings = new LinkedList<>();
	
	public void addError(Error error) {
		errors.add(error);
	}
	
	public boolean hasError() {
		if (errors.size() > 0) {
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
		warnings.add(warning);
	}
	
	public void printWarnings() {
		if (warnings.size() > 0) {
			for (Warning warning: warnings) {
				System.out.println(warning.getMessage());
			}
			System.out.println();
		}
	}
	
	public List<Error> getErrors() {
		return errors;
	}
	
	public List<Warning> getWarnings() {
		return warnings;
	}
	
}

package ast.typechecker.errorhandler;

import java.util.LinkedList;
import java.util.List;


public class ErrorHandler {
	private List<Error> errors;
	
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
	
}

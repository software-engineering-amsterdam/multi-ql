package eu.bankersen.kevin.ql.context.errors;

import java.util.Set;

public class ContextBuildException extends Exception {
    
    private final Set<TypeCheckingError> exceptions;
    
    public ContextBuildException(final Set<TypeCheckingError> errors) {
	this.exceptions = errors;
    }
    
    public final Set<TypeCheckingError> getErrors() {
	return exceptions;
    }

}

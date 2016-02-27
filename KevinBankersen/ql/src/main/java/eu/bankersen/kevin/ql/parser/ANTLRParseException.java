package eu.bankersen.kevin.ql.parser;

import java.util.List;

public class ANTLRParseException extends Exception {
    
    private final List<String[]> exceptions;
    
    public ANTLRParseException(final List<String[]> exceptions) {
	this.exceptions = exceptions;
    }
    
    public final List<String[]> getErrors() {
	return exceptions;
    }
}

package eu.bankersen.kevin.ql.parser;

import java.util.List;

public class ANTLRParseException extends Exception {

    private final List<ANTLRParseError> exceptions;

    public ANTLRParseException(final List<ANTLRParseError> exceptions) {
	this.exceptions = exceptions;
    }

    public final List<ANTLRParseError> getErrors() {
	return exceptions;
    }
}

package eu.bankersen.kevin.ql.form.parser;

import java.util.Iterator;
import java.util.List;

public class ANTLRParseException extends Exception {

	private final List<ANTLRParseError> exceptions;

	public ANTLRParseException(final List<ANTLRParseError> exceptions) {
		this.exceptions = exceptions;
	}

	public final Iterator<ANTLRParseError> getErrors() {
		return exceptions.iterator();
	}
}

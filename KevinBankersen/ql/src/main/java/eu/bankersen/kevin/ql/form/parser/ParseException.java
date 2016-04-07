package eu.bankersen.kevin.ql.form.parser;

import java.util.Iterator;
import java.util.List;

public class ParseException extends Exception {

	private final List<ParseError> exceptions;

	public ParseException(final List<ParseError> exceptions) {
		this.exceptions = exceptions;
	}

	public final Iterator<ParseError> getErrors() {
		return exceptions.iterator();
	}
}

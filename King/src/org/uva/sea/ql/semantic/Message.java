package org.uva.sea.ql.semantic;

import java.util.ArrayList;
import java.util.List;

public class Message {

	private final List<String> warnings = new ArrayList<>();
	private final List<String> errors = new ArrayList<>();

	public Message() {

	}

	public void addWarning(String msg) {
		warnings.add(msg);
	}

	public void addError(String msg) {
		errors.add(msg);
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

	public List<String> getErrors() {
		return errors;
	}

	public boolean hasWarnings() {
		return !warnings.isEmpty();
	}

	public List<String> getWarnings() {
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
			for (String warning : warnings) {
				System.out.println(warning);

			}
		}
	}

}

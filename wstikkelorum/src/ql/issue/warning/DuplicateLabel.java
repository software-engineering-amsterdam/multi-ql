package ql.issue.warning;

public class DuplicateLabel extends Warning {
	public DuplicateLabel(String label, int lineNumber) {
		super.errorMessage = String.format("Warning: Duplicate label. Text: %s. At line Number: %d", label, lineNumber);
	}
}

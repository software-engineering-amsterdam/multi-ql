package ql.issue;

public class DuplicateLabel extends Issue {
	public DuplicateLabel(String label, int lineNumber) {
		super.errorMessage = String.format(
				"Warning: Duplicate label. Text: %s. At line Number: %d",
				label, lineNumber);
	}
}

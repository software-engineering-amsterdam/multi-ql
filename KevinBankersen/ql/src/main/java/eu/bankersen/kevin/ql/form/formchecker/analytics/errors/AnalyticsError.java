package eu.bankersen.kevin.ql.form.formchecker.analytics.errors;

public abstract class AnalyticsError {

	private final String message;
	private final int line;

	public AnalyticsError(int line, String message) {
		this.line = line;
		this.message = message;
	}

	public String toString() {
		return line == 0 ? message : String.format("Line %s: %s", line, message);
	}

}

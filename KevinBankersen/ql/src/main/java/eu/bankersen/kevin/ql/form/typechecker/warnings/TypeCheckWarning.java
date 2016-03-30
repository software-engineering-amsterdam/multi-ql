package eu.bankersen.kevin.ql.form.typechecker.warnings;

public abstract class TypeCheckWarning {
    private final String message;
    private final int line;

    public TypeCheckWarning(int line, String message) {
	this.line = line;
	this.message = message;
    }

    public String toString() {
	return line == 0 ? message : String.format("Line %s: %s", line, message);
    }
}

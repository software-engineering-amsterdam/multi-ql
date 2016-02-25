package eu.bankersen.kevin.ql.context.errors;

public class AllreadyDeclaredError extends TypeCheckingError {

    public AllreadyDeclaredError(int line, String name) {
	super(line, "\"" + name + "\" already declared!");
    }

}

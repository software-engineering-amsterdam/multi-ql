package eu.bankersen.kevin.ql.form.formchecker.analytics.warnings;

public class AllreadyDeclared extends TypeCheckWarning {

	public AllreadyDeclared(int i, String name) {
		super(i, String.format("The question \"%s\" is declared multiple times!", name));
	}

}

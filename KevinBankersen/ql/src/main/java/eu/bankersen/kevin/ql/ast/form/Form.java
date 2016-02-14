package eu.bankersen.kevin.ql.ast.form;

import eu.bankersen.kevin.ql.symboltable.SymbolTabel;

public class Form {

    private String name;
    private Block body;

    public Form(final String name, final Block body) {
	this.name = name;
	this.body = body;
    }

    public final Boolean checkType() {
	body.checkType();
	
	return SymbolTabel.getErrors().size() == 0;
    }

    public final void result() {
	body.result();
    }
    @Override
    public final String toString() {

	StringBuilder sb;

	sb = new StringBuilder();
	sb.append("\nForm: " + name + "\n");
	sb.append(body);
	sb.append("\n");

	return sb.toString();
    }
}

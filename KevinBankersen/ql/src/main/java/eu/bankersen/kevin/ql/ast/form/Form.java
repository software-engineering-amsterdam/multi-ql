package eu.bankersen.kevin.ql.ast.form;

import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class Form {

    private final String name;
    private final Body body;

    public Form(String name, Body body) {
	this.name = name;
	this.body = body;
    }

    public Context checkType() {
	return body.checkType(new Context(name));
    }

    public SymbolTable evalForm(SymbolTable symbolTable) {
	return body.evalBody(symbolTable);
    }
    
    @Override
    public String toString() {

	StringBuilder sb;

	sb = new StringBuilder();
	sb.append("Results\nForm: " + name + "\n");
	sb.append(body);
	sb.append("\n");

	return sb.toString();
    }
}


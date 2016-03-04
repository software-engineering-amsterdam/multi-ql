package eu.bankersen.kevin.ql.ast.form;

import eu.bankersen.kevin.ql.ast.AcceptMethods;
import eu.bankersen.kevin.ql.context.Context;
import eu.bankersen.kevin.ql.context.SymbolTable;
import eu.bankersen.kevin.ql.context.SymbolTableBuilder;
import eu.bankersen.kevin.ql.oldcode.QLVisitor;

public class Form implements AcceptMethods {

    private final String name;
    private final Body body;

    public Form(String name, Body body) {
	this.name = name;
	this.body = body;
    }
    
    public Body body() {
	return body;
    }

    public SymbolTable evalForm(SymbolTable symbolTable) {
	return body.evalBody(symbolTable);
    }

    @Override
    public Context checkType(Context context) {
	return body.checkType(context);
    }

    public SymbolTableBuilder buildSymbolTable(SymbolTableBuilder builder) {
	return body.buildSymbolTable(builder);
    }
}


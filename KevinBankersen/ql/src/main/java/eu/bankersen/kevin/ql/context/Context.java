package eu.bankersen.kevin.ql.context;

import java.util.HashSet;
import java.util.Set;

import eu.bankersen.kevin.ql.ast.Type;
import eu.bankersen.kevin.ql.context.errors.TypeCheckingError;

public class Context {
    
    private final String formName;
    private final SymbolTable symbolTable;
    private final Set<TypeCheckingError> errorList;
    

    public Context(String formName) {
	this.formName = formName;
	this.symbolTable = new SymbolTable(formName);
	this.errorList = new HashSet<>();
    }
    
    public SymbolTable getSymbolTable() {
	return symbolTable;
    }
    
    public Symbol getSymbol(String symbol) {
	return symbolTable.getSymbol(symbol);
    }
    
    public Boolean checkID(String name) {
	return symbolTable.checkID(name);
    }
    
    public void addSymbol(String symbol, String question, Type type, Object value) {
	symbolTable.addSymbol(symbol, question, type, value);
    }
    
    public void updateSymbol(String name, Object value) {
	symbolTable.updateSymbol(name, value);
    }
    
    public void setVisibility(String name, Boolean active) {
	symbolTable.setVisibility(name, active);
    }
    
    public void addError(TypeCheckingError error) {
	errorList.add(error);
    }
    
    public Set<TypeCheckingError> getErrors() {
	return errorList;
	
    }

}
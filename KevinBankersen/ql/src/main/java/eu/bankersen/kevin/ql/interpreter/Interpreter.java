package eu.bankersen.kevin.ql.interpreter;


import java.util.ArrayList;
import java.util.List;

import com.esotericsoftware.minlog.Log;

import eu.bankersen.kevin.ql.ast.form.Form;
import eu.bankersen.kevin.ql.gui.ViewListener;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

public class Interpreter implements ViewListener {

    private final Form form;
    private SymbolTable symbolTable;
    private List<DataListener> dataListeners;

    public Interpreter(Form form, SymbolTable symbolTable) {
	this.form = form;
	this.symbolTable = symbolTable;
	this.dataListeners = new ArrayList<>();
	evalForm();
    }
    
    private void evalForm() {
	for (int i = 0; i < symbolTable.size(); i++) {
	    symbolTable = form.evalForm(symbolTable);
	}
	dataUpdate();
    }
    
    public SymbolTable getSymbolTable() {
	return symbolTable;
    }

    public void addDataListener(DataListener listener) {
	dataListeners.add(listener);
	this.evalForm();
    }

    private void dataUpdate() {
	dataListeners.forEach(listener -> listener.dataUpdate(symbolTable));
    }

    @Override
    public void viewUpdate(String name, Object value) {
	symbolTable.updateSymbol(name, value);
	this.evalForm();
	
	Log.info("Symbol Table Contents\n" + symbolTable.toString());
	
    }
}



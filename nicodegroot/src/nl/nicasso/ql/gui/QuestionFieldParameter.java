package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.symbolTable.SymbolTable;

public class QuestionFieldParameter {

	private Identifier identifier;
	private SymbolTable symboltable;
	private Observer main;
	private boolean enabled;
	
	public QuestionFieldParameter() {
		
	}
	
	public QuestionFieldParameter(Identifier identifier, SymbolTable symboltable, Observer main, boolean enabled) {
		this.identifier = identifier;
		this.symboltable = symboltable;
		this.main = main;
		this.enabled = enabled;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public void setSymboltable(SymbolTable symboltable) {
		this.symboltable = symboltable;
	}

	public void setMain(Observer main) {
		this.main = main;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Identifier getIdentifier() {
		return identifier;
	}

	public SymbolTable getSymboltable() {
		return symboltable;
	}

	public Observer getMain() {
		return main;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
}

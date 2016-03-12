package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.stateTable.StateTable;

public class QuestionFieldParameter {

	private Identifier identifier;
	private StateTable stateTable;
	private Observer main;
	private boolean enabled;
	
	public QuestionFieldParameter() {
		// CAN GO?!
	}
	
	public QuestionFieldParameter(Identifier identifier, StateTable stateTable, Observer main, boolean enabled) {
		this.identifier = identifier;
		this.stateTable = stateTable;
		this.main = main;
		this.enabled = enabled;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}

	public void setSymboltable(StateTable stateTable) {
		this.stateTable = stateTable;
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

	public StateTable getStateTable() {
		return stateTable;
	}

	public Observer getMain() {
		return main;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
}

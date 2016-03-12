package nl.nicasso.ql.stateTable;

import nl.nicasso.ql.values.Value;

public class StateTableEntry {

	private Value value;
	
	public StateTableEntry(Value value) {
		super();
		this.value = value;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
}

package nl.nicasso.ql.gui.evaluator.stateTable;

import nl.nicasso.ql.gui.evaluator.values.UnknownValue;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class StateTableEntry {

	private Value value;

	public StateTableEntry() {
		this.value = new UnknownValue();
	}

	public StateTableEntry(Value value) {
		this.value = value;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

}

package nl.nicasso.ql.gui.evaluator.stateTable;

import java.util.HashMap;
import java.util.Map;

import nl.nicasso.ql.ast.nodes.expressions.Identifier;
import nl.nicasso.ql.gui.evaluator.values.Value;

public class StateTable {

	private Map<Identifier, StateTableEntry> symbols;

	public StateTable() {
		super();
		this.symbols = new HashMap<Identifier, StateTableEntry>();
	}

	public Map<Identifier, StateTableEntry> getSymbols() {
		return symbols;
	}
	
	public void addState(Identifier key, StateTableEntry value) {
		symbols.put(key, value);
	}
	
	public StateTableEntry getEntry(Identifier key) {
		return symbols.get(key);
	}
	
	public Value getEntryValue(Identifier key) {
		StateTableEntry entry = symbols.get(key);
		return entry.getValue();
	}

}
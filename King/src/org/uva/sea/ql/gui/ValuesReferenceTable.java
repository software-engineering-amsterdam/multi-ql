package org.uva.sea.ql.gui;

import java.util.HashMap;
import java.util.Map;

import org.uva.sea.ql.evalutor.Value;

public class ValuesReferenceTable {
	private Map<String, Value> identifierValues;
	private Map<String, Value> identifierValuesCopy;
	public ValuesReferenceTable() {
		identifierValues = new HashMap<>();
		identifierValuesCopy = new HashMap<>(identifierValues);
	}
	
	public void addQLIdentifier(String qidentifier, Value qvalue){
		identifierValues.put(qidentifier, qvalue);
	}
	
	public Value getQLValue(String qidentifier){
		return identifierValues.get(qidentifier);
	}
	
	public Value getQLValueClone(String qidentifier){
		
		return identifierValuesCopy.get(qidentifier);
	}
	
	public Boolean questionAlreadyInReferenceTable(String identifier) {
		return identifierValues.containsKey(identifier);
	}
	
	public Boolean questionAlreadyInReferenceTableClone(String identifier) {
		return identifierValuesCopy.containsKey(identifier);
	}
	
	public void identifierValuesClone() {
		identifierValuesCopy = new HashMap<>(identifierValues);
	}
	
	public void clearIdentifierValues() {
		identifierValues.clear();
	}
	@Override
	public String toString() {
		return identifierValues.toString()+" :-> "+identifierValuesCopy.toString();
	}

}

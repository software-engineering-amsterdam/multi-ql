package nl.nicasso.ql.gui;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.Value;

public interface Observer {

	public boolean fieldValueChanged(Identifier identifier, Value value);
	
	public void updateAllPanels();
	
}
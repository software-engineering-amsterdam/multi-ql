package nl.nicasso.ql.gui.questionFields.listeners;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.Value;

public class FieldListener {
	
	Identifier identifier;
	
	public FieldListener(Identifier identifier) {
		this.identifier = identifier;
	}

	public void fieldValueChanged(Value newValue) {
		//System.out.println("SOMETHING CHANGED!: "+newValue.getValue().toString());
	}
	
}

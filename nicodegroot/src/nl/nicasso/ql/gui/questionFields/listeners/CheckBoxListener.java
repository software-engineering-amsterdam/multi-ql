package nl.nicasso.ql.gui.questionFields.listeners;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.values.BooleanValue;

public class CheckBoxListener extends FieldListener implements ItemListener {

	public CheckBoxListener(Identifier identifier) {
		super(identifier);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			fieldValueChanged(new BooleanValue(true));
		}
		fieldValueChanged(new BooleanValue(false));
		
	}

}

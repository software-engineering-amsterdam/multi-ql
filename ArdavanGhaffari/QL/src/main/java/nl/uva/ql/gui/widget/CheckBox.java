package nl.uva.ql.gui.widget;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JComponent;

import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.BooleanValue;
import nl.uva.ql.evaluator.value.Value;
import nl.uva.ql.gui.QLFrame;

public class CheckBox extends Widget implements ItemListener {
	
	private JCheckBox checkBox;

	public CheckBox(Evaluator evaluator, QLFrame frame) {
		super(evaluator, frame);
		checkBox = new JCheckBox();
		checkBox.addItemListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (checkBox.isSelected()) {
			valueChanged(new BooleanValue(true));
		} else {
			valueChanged(new BooleanValue(false));
		}
	}

	@Override
	public JComponent getComponent() {
		return checkBox;
	}

	@Override
	public void setValue(Value value) {
		Boolean checkBoxValue = ((BooleanValue)value).getValue();
		if (checkBoxValue) {
			checkBox.setSelected(true);
		} else {
			checkBox.setSelected(false);
		}
	}
}

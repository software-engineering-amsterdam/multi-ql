package nl.nicasso.ql.gui.widgets;

import java.awt.event.ItemListener;
import java.util.EventListener;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import nl.nicasso.ql.gui.evaluator.values.Value;

public class CheckboxWidget implements InterActiveWidget {

	private JCheckBox field;
	
	public CheckboxWidget(Boolean enabled) {
		field = new JCheckBox();
		field.setEnabled(enabled);
	}
	
	public void setValue(Value enabled) {
		field.setSelected((Boolean) enabled.getValue());
	}
	
	public Boolean getValue() {
		return field.isSelected();
	}
	
	public void addListener(EventListener listener) {
		field.addItemListener((ItemListener) listener);
	}
	
	public void addSelfToPanel(JPanel panel) {
		panel.add(field);
	}
	
}

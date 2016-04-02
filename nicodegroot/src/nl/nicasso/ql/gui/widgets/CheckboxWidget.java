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
	
	@Override
	public void setValue(Value enabled) {
		field.setSelected((Boolean) enabled.getValue());
	}
	
	@Override
	public Boolean getValue() {
		return field.isSelected();
	}
	
	@Override
	public void addListener(EventListener listener) {
		field.addItemListener((ItemListener) listener);
	}
	
	@Override
	public void addSelfToPanel(JPanel panel) {
		panel.add(field);
	}
	
}

package nl.nicasso.ql.gui.widgets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import nl.nicasso.ql.gui.evaluator.values.Value;

public class LabelWidget implements Widget {
	
	private JLabel field;
	
	public LabelWidget(String text) {
		field = new JLabel(text);
	}

	@Override
	public void setValue(Value text) {
		field.setText(text.getValue().toString());
	}

	@Override
	public Object getValue() {
		return field.getText();
	}

	@Override
	public void addSelfToPanel(JPanel panel) {
		panel.add(field);		
	}

}

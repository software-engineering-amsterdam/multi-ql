package nl.nicasso.ql.gui.widgets;

import javax.swing.JPanel;

import nl.nicasso.ql.gui.evaluator.values.Value;

public interface Widget {
	
	public void setValue(Value value);
	
	public Object getValue();
	
	public void addSelfToPanel(JPanel panel);
	
}

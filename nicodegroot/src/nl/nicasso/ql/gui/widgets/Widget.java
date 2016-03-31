package nl.nicasso.ql.gui.widgets;

import java.util.EventListener;

import javax.swing.JPanel;

import nl.nicasso.ql.gui.evaluator.values.Value;

public interface Widget {

	public void addListener(EventListener listener);
	
	public void setValue(Value value);
	
	public Object getValue();
	
	public void addSelfToPanel(JPanel panel);
	
}

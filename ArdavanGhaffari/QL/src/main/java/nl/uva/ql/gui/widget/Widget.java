package nl.uva.ql.gui.widget;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.evaluator.value.Value;
import nl.uva.ql.gui.QLFrame;

public abstract class Widget {
	private Evaluator evaluator;
	private QLFrame frame;
	private Identifier identifier;
	private Value value;
	
	public Widget(Evaluator evaluator, QLFrame frame){
		this.evaluator = evaluator;
		this.frame = frame;
	}
	
	public Value getValue() {
		return value;
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
	
	public void valueChanged(Value value) {
		this.value = value;
		evaluator.addValue(identifier, value);
		frame.update(evaluator, identifier);
	}
	
	public void giveError(String message) {
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public abstract JComponent getComponent();
	
	public abstract void setValue(Value value);
	
}

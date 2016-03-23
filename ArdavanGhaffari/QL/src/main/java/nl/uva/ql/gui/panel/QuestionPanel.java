package nl.uva.ql.gui.panel;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import nl.uva.ql.ast.literal.Identifier;
import nl.uva.ql.evaluator.Evaluator;
import nl.uva.ql.gui.widget.Widget;

public class QuestionPanel extends Panel{
	
	private JLabel label;
	protected Widget widget;
	
	public QuestionPanel(Widget widget, String labelText){
		this.widget = widget;
		this.label = new JLabel(labelText);
		this.label.setHorizontalAlignment(SwingConstants.LEFT);
		addToPanel(label);
		addToPanel(widget.getComponent(), "push, growx");
	}

	@Override
	public void update(Evaluator evaluator, Identifier identifier) {		
	}

}

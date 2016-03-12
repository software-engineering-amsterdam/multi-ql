package nl.nicasso.ql.gui.panels;

import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import nl.nicasso.ql.Evaluator;
import nl.nicasso.ql.ast.expressions.Expression;
import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.widgets.Label;
import nl.nicasso.ql.stateTable.StateTable;
import nl.nicasso.ql.values.Value;

public abstract class Panel {

	protected JPanel panel;
	protected StateTable stateTable;
	protected Expression condition;
	protected QuestionField field;

	public Panel() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	}
	
	protected void addQuestionLabel(Question q) {
		Label questionLabel = new Label("CQ: "+q.getLabel());
		questionLabel.setFont(new Font("Arial", 0, 100));
		panel.add(questionLabel.getWidget());
	}

	public void setVisible(boolean visible) {
		panel.setVisible(visible);
		panel.updateUI();
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	protected void addQuestionField(Question q, QuestionField field, Value value) {
		this.field = field;
		
		field.setValue(value.getValue());
		panel.add(field.getField());
	}
	
	public boolean update() {
		boolean updated = false;
		
		// Visibility
		Evaluator evaluator = new Evaluator(stateTable);
		Value visibility = condition.accept(evaluator);
		
		if (panel.isVisible() != (Boolean) visibility.getValue()) {
			//updated = true;
		}
		
		//System.out.println("VISIBILITY: "+visibility.getValue());
		setVisible((Boolean) visibility.getValue());
		
		return updated;
	}
	
}

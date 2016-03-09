package nl.nicasso.ql.gui.questionFields;

import javax.swing.JCheckBox;

import nl.nicasso.ql.ast.expressions.Identifier;
import nl.nicasso.ql.gui.questionFields.listeners.CheckBoxListener;

public class BooleanQuestionField extends QuestionField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8996503194626046477L;
	
	private Identifier identifier;
	
	JCheckBox field;

	public BooleanQuestionField(Identifier identifier) {
		field = new JCheckBox();
		this.identifier = identifier;
		field.addItemListener(new CheckBoxListener(identifier));
	}
	
	public void setValue(Object value) {
		field.setSelected((Boolean) value);
	}
	
	public JCheckBox getField() {
		return this.field;
	}
	
}

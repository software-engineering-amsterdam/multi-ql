package nl.nicasso.ql.gui.questionFields;

import javax.swing.JCheckBox;

public class BooleanQuestionField extends QuestionField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8996503194626046477L;
	
	JCheckBox field;

	public BooleanQuestionField() {
		field = new JCheckBox();
	}
	
	public JCheckBox getField() {
		return this.field;
	}
	
}

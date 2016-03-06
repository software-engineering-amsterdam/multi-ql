package nl.nicasso.ql.gui.questionFields;

import javax.swing.JCheckBox;

public class BooleanQuestionField extends QuestionField {

	JCheckBox field;

	public BooleanQuestionField() {
		field = new JCheckBox();
	}
	
	public JCheckBox getField() {
		return this.field;
	}
	
}

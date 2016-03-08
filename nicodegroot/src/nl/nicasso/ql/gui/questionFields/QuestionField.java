package nl.nicasso.ql.gui.questionFields;

import java.awt.Component;

public abstract class QuestionField extends Component {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2568908331809430726L;

	public QuestionField() {
		//throw new AssertionError("QuestionField");
	}
	
	public void setValue(Object value) {
		//throw new AssertionError("QuestionField setValue");
	}
	
	public Component getField() {
		throw new AssertionError("QuestionField getField");
	}
	
}

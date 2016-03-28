package org.uva.sea.ql.gui;

import javax.swing.JPanel;
import org.uva.sea.ql.gui.widget.QLQuestionText;
import org.uva.sea.ql.gui.widget.QLRadioButton;

public class QLViewSelectQuestion extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5263675203520807284L;
	private QLQuestionText qLQuestionText;
	private QLRadioButton qLRadioButton;

	public QLViewSelectQuestion(QLQuestionText qLQuestionText, QLRadioButton qLRadioButton) {
		this.qLQuestionText = qLQuestionText;
		this.qLRadioButton = qLRadioButton;
	}

	public QLQuestionText getqLQuestionText() {
		return qLQuestionText;
	}

	public QLRadioButton getqLRadioButton() {
		return qLRadioButton;
	}

}

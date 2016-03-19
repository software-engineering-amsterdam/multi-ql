package org.uva.sea.ql.gui;

import javax.swing.JPanel;
import org.uva.sea.ql.ast.domain.Question;
import org.uva.sea.ql.ast.expr.type.BooleanType;
import org.uva.sea.ql.gui.widget.QLQuestionText;
import org.uva.sea.ql.gui.widget.QLQuestionTextFeild;
import org.uva.sea.ql.gui.widget.QLRadioButton;
public class QLViewInputTextQuestion extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5263675203520807284L;
	private QLQuestionText qLQuestionText;
	private boolean editable = false;
	private QLQuestionTextFeild qLQuestionTextFeild;
	public QLViewInputTextQuestion(QLQuestionText qLQuestionText, QLQuestionTextFeild qLQuestionTextFeild, boolean readOnly) {
		this.qLQuestionText = qLQuestionText;
		this.qLQuestionTextFeild = qLQuestionTextFeild;
		this.editable = readOnly;
	}
	
	public void lockQLViewInputTextQuestion() {
			qLQuestionTextFeild.getQlComponent().setEditable(false);
	}

	public boolean isEditable() {
		return editable;
	}

	public QLQuestionTextFeild getqLQuestionTextFeild() {
		return qLQuestionTextFeild;
	}

	public QLQuestionText getqLQuestionText() {
		return qLQuestionText;
	}
	
	
	
	
	



}

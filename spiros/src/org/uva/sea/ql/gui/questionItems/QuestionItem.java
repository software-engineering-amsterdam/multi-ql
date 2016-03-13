package org.uva.sea.ql.gui.questionItems;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.gui.widgets.Widget;

public abstract class QuestionItem {

	private Question question;
	
	@SuppressWarnings({ "rawtypes" })
	private Widget widget;
	//private JComponent component;
	
	public QuestionItem(Question question, Widget widget) {
		this.widget = widget;
		this.question = question;
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public void setQuestion(Question question) {
		this.question = question;
	}
	
//	public void addToPanel(JPanel panel) {
//		panel.add(widget);
//		
//	}
	
	public void removeFromPanel () {
		
	}

	@SuppressWarnings("rawtypes")
	public Widget getWidget() {
		return widget;
	}

	

}

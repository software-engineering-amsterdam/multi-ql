package org.uva.sea.ql.gui.panels;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.uva.sea.ql.ast.statement.Question;
import org.uva.sea.ql.gui.widgets.Widget;

@SuppressWarnings("serial")
public class QuestionPanel {

	
	// questionLable instead of question maybe?
	private Question question;
	private Widget widget;
	private JPanel panel;
	
	public QuestionPanel(Question  question, Widget widget) {
		//super();
		this.question = question;
		this.widget = widget;
		GridLayout gridLayout = new GridLayout();
		gridLayout.setHgap(70);
		//this.panel = new JPanel(new GridLayout());
		this.panel = new JPanel(gridLayout);
		JLabel label = new JLabel(question.getLabel());
		panel.add(label);
		panel.add((Component) widget.getComponent());
	}
	
	public Question getQuestion() {
		return question;
	}
	
	
	public Widget getWidget() {
		return widget;
	}
	
	public JPanel getPanel() {
		return this.panel;
	}
	
	
}

package nl.nicasso.ql.gui.panels;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;

import nl.nicasso.ql.ast.statements.Question;
import nl.nicasso.ql.gui.widgets.Label;

public class QuestionPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8940568963006272156L;
	
	private JPanel panel;
	
	public QuestionPanel(Question q) {
		 panel = new JPanel(new BorderLayout());
		 
		 Label questionLabel = new Label(q.getId().getValue()+" - "+q.getLabel());
		 questionLabel.setFont(new Font("Arial", 0, 100));
		 panel.add(questionLabel.getWidget());
	}
	
	@Override
	public JPanel getPanel() {
		System.out.println("GETPANEL QUESTION");
		panel.setVisible(true);
		
		return this.panel;
	}
	
}

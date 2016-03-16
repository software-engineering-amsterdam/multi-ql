package org.uva.sea.ql.gui;

import java.awt.Component;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;

import org.uva.sea.ql.gui.questionItems.QuestionItem;

public class GUIPanel {

	private JPanel panel;
	
	public GUIPanel() {
		this.panel = new JPanel();
	}

	public void addToPanel(JComponent component) {
		panel.add(component);
	}

	public void removeFromPanel(JComponent component) {
		panel.remove(component);
	}

	public void addToPanel(QuestionItem questionItem) {
		System.out.println("Vazw to question item sto panel!");
		questionItem.addToPanel(panel);
		
	}
	
	public JPanel getPanel() {
		System.out.println("To panel exei components " + panel.getComponentCount());
		return this.panel;
	}
	
}

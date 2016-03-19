package org.uva.sea.ql.gui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class QLBlockViewNew extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3123222304069180952L;
	private JPanel block;
	public QLBlockViewNew() {
		block = new JPanel();
		block.setBackground(Color.YELLOW);
		block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
		
		
	}
	
	public void addQuestionView(QLViewSelectQuestion questionView) {
		JPanel blockInner = new JPanel();
		blockInner.add(questionView.getqLQuestionText().getQlComponent());
		blockInner.add(questionView.getqLRadioButton().getQlComponent());
		block.add(blockInner);
	}
	
	public void addQuestionView(QLViewInputTextQuestion textQuestionView) {
		if(!textQuestionView.isEditable()){
			textQuestionView.lockQLViewInputTextQuestion();
		}
		JPanel blockInner = new JPanel();
		blockInner.add(textQuestionView.getqLQuestionText().getQlComponent());
		blockInner.add(textQuestionView.getqLQuestionTextFeild().getQlComponent());
		
		block.add(blockInner);
		
	}
	
	public JPanel getqlBlock() {
		return block;
	}



}

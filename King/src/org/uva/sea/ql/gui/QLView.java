package org.uva.sea.ql.gui;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class QLView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6108360954460035261L;
	private JFrame viewFrame;
	private JPanel block;

	public QLView() {
		block = new JPanel();
		block.setLayout(new GridLayout(0, 1));
		viewFrame = new JFrame("QL");
		viewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		viewFrame.setSize(600, 400);
	}

	public void addQuestionView(QLViewSelectQuestion questionView) {
		JPanel blockInner = new JPanel();
		blockInner.setLayout(new GridLayout(0, 2));
		blockInner.add(questionView.getqLQuestionText().getQlComponent());
		blockInner.add(questionView.getqLRadioButton().getQlComponent());
		block.add(blockInner);
		viewFrame.add(block);
	}

	public void addQuestionView(QLViewInputTextQuestion textQuestionView) {
		if (!textQuestionView.isEditable()) {
			textQuestionView.lockQLViewInputTextQuestion();
		}
		JPanel blockInner = new JPanel();
		blockInner.setLayout(new GridLayout(0, 2));
		blockInner.add(textQuestionView.getqLQuestionText().getQlComponent());
		blockInner.add(textQuestionView.getqLQuestionTextFeild().getQlComponent());

		block.add(blockInner);
		viewFrame.add(block);
	}

	public void showQL() {
		viewFrame.setVisible(true);
	}

	public JFrame getQLFrame() {
		return viewFrame;
	}

	public void cleanQLView() {
		block.removeAll();
	}




	

}

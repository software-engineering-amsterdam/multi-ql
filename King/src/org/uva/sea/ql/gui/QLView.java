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
	private JPanel outterBlock;

	public QLView() {
		outterBlock = new JPanel();
		outterBlock.setLayout(new GridLayout(0, 1));
		viewFrame = new JFrame("QL");
		viewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		viewFrame.setSize(600, 400);
	}

	public void addQuestionView(QLViewSelectQuestion questionView) {
		JPanel blockInner = new JPanel();
		blockInner.setLayout(new GridLayout(0, 2));
		blockInner.add(questionView.getqLQuestionText().getQlComponent());
		blockInner.add(questionView.getqLRadioButton().getQlComponent());
		outterBlock.add(blockInner);
		viewFrame.add(outterBlock);
	}

	public void addQuestionView(QLViewInputTextQuestion textQuestionView) {
		JPanel blockInner = new JPanel();
		blockInner.setLayout(new GridLayout(0, 3));
		blockInner.add(textQuestionView.getqLQuestionText().getQlComponent());
		if (!textQuestionView.isEditable()) {
			textQuestionView.lockQLViewInputTextQuestion();
		}
		blockInner.add(textQuestionView.getqLQuestionTextFeild().getQlComponent());

		outterBlock.add(blockInner);
		viewFrame.add(outterBlock);
	}

	public void showQL() {
		viewFrame.setVisible(true);
	}


	public void cleanQLView() {
		outterBlock.removeAll();
	}




	

}

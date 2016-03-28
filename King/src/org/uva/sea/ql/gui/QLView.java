package org.uva.sea.ql.gui;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class QLView extends JFrame implements ItemListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6108360954460035261L;
	private JFrame viewFrame;
	private List<QLSelectedQuesionListener> radioBtnListeners = new ArrayList<QLSelectedQuesionListener>();
	private List<QLTextFeildQuesionListener> textInputListeners = new ArrayList<QLTextFeildQuesionListener>();
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
		questionView.getqLRadioButton().getQlComponent().addItemListener(this);
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
		textQuestionView.getqLQuestionTextFeild().getQlComponent().addKeyListener(this);
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

	public void addQLSelectedQuesionListener(QLSelectedQuesionListener listener) {
		radioBtnListeners.add(listener);

	}

	@Override
	public void itemStateChanged(ItemEvent event) {

		int state = event.getStateChange();
		JRadioButton btn = (JRadioButton) event.getSource();
		if (state == ItemEvent.SELECTED) {
			fireQLSelectedQuestion(btn, true);

		} else if (state == ItemEvent.DESELECTED) {
			fireQLSelectedQuestion(btn, false);
		}

	}

	private void fireQLSelectedQuestion(JRadioButton btn, boolean isSelected) {
		if (!radioBtnListeners.isEmpty()) {
			for (QLSelectedQuesionListener qlSelectedQuesionListener : radioBtnListeners) {
				qlSelectedQuesionListener.QLQuesionSelected(btn, isSelected);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		JTextField textField = (JTextField) e.getSource();
		fireQLTextInputQuestion(textField);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Here we do really need this at the moment

	}

	public void addQLTextFeildQuesionListener(QLTextFeildQuesionListener textInputListener) {
		textInputListeners.add(textInputListener);

	}

	private void fireQLTextInputQuestion(JTextField text) {
		if (!textInputListeners.isEmpty()) {
			for (QLTextFeildQuesionListener qLTextFeildQuesionListener : textInputListeners) {
				qLTextFeildQuesionListener.QLQuesionTextFeildInput(text);
			}
		}
	}

}

package org.uva.sea.ql.gui; 

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import org.uva.sea.ql.gui.widget.QLRadioButton;

public class QLView extends JFrame implements ItemListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6108360954460035261L;
	private JFrame viewFrame;
	private List<QLSelectedQuesionListener> listeners = new ArrayList<QLSelectedQuesionListener>();
	private JPanel block;
	public QLView(){
		block = new JPanel();
		block.setBackground(Color.YELLOW);
		block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
		viewFrame = new JFrame("QL");
		viewFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		viewFrame.setSize(600, 400);
	}
	
	
	public void addQuestionView(QLViewSelectQuestion questionView) {
		JPanel blockInner = new JPanel();
		questionView.getqLRadioButton().getQlComponent().addItemListener(this);
		blockInner.add(questionView.getqLQuestionText().getQlComponent());
		blockInner.add(questionView.getqLRadioButton().getQlComponent());
		block.add(blockInner);
		viewFrame.add(block);
	}
	
	public void addQuestionView(QLViewInputTextQuestion textQuestionView) {
		if(!textQuestionView.isEditable()){
			textQuestionView.lockQLViewInputTextQuestion();
		}
		JPanel blockInner = new JPanel();
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
		listeners.add(listener);
		
	}


	@Override
	public void itemStateChanged(ItemEvent event) {
			
			int state = event.getStateChange();
			JRadioButton btn = (JRadioButton) event.getSource();
	        if (state == ItemEvent.SELECTED) {
	        	fireQLSelectedQuestion(btn,true);
	 
	        } else if (state == ItemEvent.DESELECTED) {
	        	fireQLSelectedQuestion(btn,false);
	        }
		
	}


	private void fireQLSelectedQuestion(JRadioButton btn, boolean isSelected) {
		if(!listeners.isEmpty()){
			for (QLSelectedQuesionListener qlSelectedQuesionListener : listeners) {
				qlSelectedQuesionListener.QLQuesionSelected(btn,isSelected);
			}
		}
	}

}

package org.uva.sea.ql.gui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class QLBlockView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3123222304069180952L;
	private JPanel block;
	private QLViewQuestion questionView;
	private QLViewReadOnlyQuestion readOnlyquestionView;
	public QLBlockView() {
		block = new JPanel();
		block.setBackground(Color.YELLOW);
		block.setLayout(new BoxLayout(block, BoxLayout.Y_AXIS));
		
		
	}
	
	public void addQuestionView(QLViewQuestion questionView) {
		this.questionView = questionView;
		JPanel blockInner = new JPanel();
		blockInner.add(questionView.getqLabel());
		if(questionView.isComputedQuestion()){
			blockInner.add(questionView.getqComputed());
		}else{
			blockInner.add(questionView.getqIdentifier());
		}
		block.add(blockInner);
	}
	
	public void addQuestionView(QLViewReadOnlyQuestion readOnlyquestionView) {
		this.readOnlyquestionView = readOnlyquestionView;
		JPanel blockInner = new JPanel();
		blockInner.add(readOnlyquestionView.getqLabel());
		blockInner.add(readOnlyquestionView.getqComputed());
		block.add(blockInner);
	}
	
	public JPanel getqlBlock() {
		return block;
	}

	public QLViewQuestion getQuestionView() {
		return questionView;
	}
	
	public QLViewReadOnlyQuestion getReadOnlyQuestion() {
		return readOnlyquestionView;
	}

}

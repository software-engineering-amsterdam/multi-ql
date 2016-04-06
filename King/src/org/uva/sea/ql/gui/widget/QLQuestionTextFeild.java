package org.uva.sea.ql.gui.widget;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.uva.sea.ql.gui.QLTextFeildQuesionListener;

public class QLQuestionTextFeild extends Widget implements KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -797635111479794335L;
	private JTextField qlQuestionTextFeild;
	private List<QLTextFeildQuesionListener> textInputListeners = new ArrayList<QLTextFeildQuesionListener>();

	public QLQuestionTextFeild(String variable, String value) {
		qlQuestionTextFeild = new JTextField(5);
		qlQuestionTextFeild.setName(variable);
		qlQuestionTextFeild.setText(value);
		qlQuestionTextFeild.addKeyListener(this);
	}

	public void setQlQuestionTextFeild(JTextField qlQuestionTextFeild) {
		this.qlQuestionTextFeild = qlQuestionTextFeild;
	}

	@Override
	public JTextField getQlComponent() {
		return qlQuestionTextFeild;
	}
	public String getQlComponentText() {
		return qlQuestionTextFeild.getText();
	}
	
	public String getQlComponentName() {
		return qlQuestionTextFeild.getName();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(isNumeric(this.getQlComponentText())){
			fireQLTextInputQuestion();
		}else{
			JOptionPane.showMessageDialog(null, "Invalid entry. Please enter numerics");
		}
		

	}
	private boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Here we do really need this at the moment

	}

	public void addQLTextFeildQuesionListener(QLTextFeildQuesionListener textInputListener) {
		textInputListeners.add(textInputListener);

	}

	private void fireQLTextInputQuestion() {
		if (!textInputListeners.isEmpty()) {
			for (QLTextFeildQuesionListener qLTextFeildQuesionListener : textInputListeners) {
				qLTextFeildQuesionListener.QLQuesionTextFeildInput(this);
			}
		}
	}

}

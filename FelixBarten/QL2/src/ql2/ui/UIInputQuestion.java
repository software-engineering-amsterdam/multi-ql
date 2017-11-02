package ql2.ui;

import java.awt.FlowLayout;
import java.text.Format;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.NumberFormatter;

import ql2.ast.CalculatedQuestion;
import ql2.ast.InputQuestion;
import ql2.ast.type.BooleanType;
import ql2.ast.type.IntegerType;
import ql2.ast.type.QuestionType;
import ql2.ast.type.StringType;

public class UIInputQuestion extends JPanel {

	private static final long serialVersionUID = 1L;
	private InputQuestion question; 
	private QlGui parent;
	private JComponent questionField;

	public UIInputQuestion(InputQuestion question, QlGui gui) {
		this.questionField = null;
		this.question = question; 
		this.parent = gui;
		draw(); 
		
		parent.getPanel().add(this);
	}
	
	private void draw() {
		this.setName(question.getQuestionID());
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		this.setLayout(layout);
		JLabel questionLabel = new JLabel(stripQuotation(question.getQuestionText()));
		
		QuestionType type = question.getType();
				
		
		Object value = parent.getContext().getVariable(question.getQuestionID());

		// filter input based on type. 
		if (type instanceof BooleanType) {
			questionField = new JCheckBox();
			if (value != null && value instanceof Boolean) {
				((JCheckBox) questionField).setSelected((boolean) value);
			}
		} else if (type instanceof StringType) {
			questionField = new JTextField();
			questionField.setToolTipText(question.getQuestionID());
			((JTextField) questionField).setColumns(20);
			if (value != null && value instanceof String) {
				((JTextField) questionField).setText((String) value);
			}
		} else if (type instanceof IntegerType) {
			NumberFormat format = NumberFormat.getIntegerInstance();
			format.setGroupingUsed(false);
			format.isParseIntegerOnly();
			NumberFormatter formatter = new NumberFormatter(format);
			//formatter.setAllowsInvalid(false);
			
			questionField = new JFormattedTextField(formatter);
			questionField.setToolTipText(question.getQuestionID());
			((JFormattedTextField) questionField).setColumns(6);
			
			if (value != null && value instanceof Integer) {
				((JFormattedTextField) questionField).setValue(value);
				((JFormattedTextField) questionField).validate();
			}
		}
		
		this.add(questionLabel);
		this.add(questionField);
		this.revalidate();
	}
	
	private void putValue(String questionID) {
		Object value = parent.getContext().getVariable(questionID);
		
		if (value != null) {
			
		}
		
	}

	public Object getValue() {
		if (questionField instanceof JSpinner) {
			return ((JSpinner) questionField).getValue();
		}
		if (questionField instanceof JCheckBox) {
			return ((JCheckBox) questionField).isSelected();
		}
		// Due to type hierarchy this needs to be checked before JTextfield(parent)
		if (questionField instanceof JFormattedTextField) {
			try {
				if (((JFormattedTextField) questionField).getText().equals("")) {
					return 0;
				}
				return Integer.parseInt(((JFormattedTextField) questionField).getText());
			} catch (ClassCastException e) {
				// num cannot be cast or is larger than int. 
				return null;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
				return 0; 
			}
			
		}		
		if (questionField instanceof JTextField) {
			return ((JTextField) questionField).getText();
		}
		return null;
	}

	private String stripQuotation(String input) {
		return input.substring(1, input.length()-1);
	}

	public InputQuestion getQuestion() {
		return question;
	}
}

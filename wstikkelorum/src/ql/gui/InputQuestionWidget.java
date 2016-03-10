package ql.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ql.QLdrawer;
import ql.ast.statement.InputQuestion;
import ql.ast.statement.Question;
import ql.ast.visitor.Type;

public class InputQuestionWidget extends UIElement {
	private JComponent inputComponent;

	public InputQuestionWidget(InputQuestion inputQuestion, Object value,
			QLdrawer qlDrawer) {
		super(new JLabel(inputQuestion.getVariable().getIdentifier()), new JLabel(inputQuestion.getQuestionString()));

		if (inputQuestion.getVariable().getType() == Type.BOOLEAN) {
			if (value == null) {// because the cast will fail with null
				inputComponent = createRadioButton(null, qlDrawer, inputQuestion);
			} else {
				inputComponent = createRadioButton((boolean) value, qlDrawer, inputQuestion);
			}
			return;
		}

		if (inputQuestion.getVariable().getType() == Type.INT) {
			inputComponent = createIntegerInputField(value, qlDrawer,
					inputQuestion);
			return;
		}

		if (inputQuestion.getVariable().getType() == Type.STRING) {
			inputComponent = createStringInputField(value, qlDrawer,
					inputQuestion);
			return;
		}
	}

	private JComponent createStringInputField(Object value, QLdrawer qlDrawer,
			Question question) {
		JTextField textField = new JTextField(6);
		if (value == null) {
			textField.setText("");
		} else {
			textField.setText(value.toString());
		}
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				qlDrawer.updateContextAndRedraw(question, input);
			}
		});
		return textField;
	}

	private JComponent createIntegerInputField(Object value, QLdrawer qlDrawer, Question question) {
		JTextField textField = new JTextField(6);
		if (value == null) {
			textField.setText("");
		} else {
			textField.setText(value.toString());
		}
		textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String input = textField.getText();
				int intInput;
				try {
					intInput = Integer.parseInt(input);
					qlDrawer.updateContextAndRedraw(question, intInput);
				} catch (NumberFormatException nfe) {
					textField.setBackground(new Color(255, 0, 0));
				}

			}
		});
		return textField;
	}

	private JComponent createRadioButton(Boolean value, QLdrawer qlDrawer, Question question) {
		JRadioButton radioButton = new JRadioButton();
		if (value != null && value) {
			radioButton.setSelected(true);
		}
		radioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (radioButton.isSelected()) {
					qlDrawer.updateContextAndRedraw(question, true);
				} else {
					qlDrawer.updateContextAndRedraw(question, false);
				}

			}
		});
		return radioButton;
	}

	public JComponent getInputComponent() {
		return inputComponent;
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		// panel.add(this.getVariableLabel());
		panel.add(this.getQuestionStringLabel());
		panel.add(inputComponent);
		panel.setVisible(true);
		return panel;
	}
}

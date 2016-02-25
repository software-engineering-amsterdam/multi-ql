package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ast.form.Form;

public class QLFrame extends JFrame{

	public QLFrame(Form result){
		JLabel test = new JLabel(String.format("%d", result.getBody().getStatements().size()));
		add(test);
		this.setSize(800, 600);
		setVisible(true);
	}

}

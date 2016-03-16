package uva.ql.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uva.ql.ast.EnumType;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.gui.fields.DateSpinner;
import uva.ql.gui.fields.IntTextField;
import uva.ql.gui.fields.MoneyTextField;

public class Question extends JPanel {

	private JCheckBox checkBox;
	private JTextField textField;
	private DateSpinner spinner;
	//private DateSpinnerImproved spinner;
	
	public Question(String label, final Variable var) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.setBackground(new Color(0, 150, 0));
		setLayout(new GridLayout(1,2));
		add( new JLabel(label) );
		
		if (var.getType() == EnumType.BOOLEAN) {
			checkBox = new JCheckBox("Yes/No");
			checkBox.setName(var.getName());
			checkBox.setSelected(false);
			checkBox.setEnabled(true);
			
			checkBox.addActionListener( new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					//var.setValue(String.valueOf(checkBox.isSelected()));
					System.out.println(checkBox.isSelected());
				}
			});
			
			add(this.checkBox);
		}
		else if (var.getType() == EnumType.MONEY) {
			textField = new MoneyTextField(var);
			add(this.textField);
		}
		else if (var.getType() == EnumType.INTEGER) {
			textField = new IntTextField(var);
			add(this.textField);
		}
		else if (var.getType() == EnumType.DATE) {
			spinner = new DateSpinner(var);
			//spinner = new DateSpinnerImproved(var);
			
			add(this.spinner);
		}
	}
}

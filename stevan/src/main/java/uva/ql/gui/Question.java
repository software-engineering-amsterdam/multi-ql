package uva.ql.gui;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uva.ql.ast.EnumType;
import uva.ql.ast.variables.VarInt;
import uva.ql.ast.variables.VarMoney;
import uva.ql.ast.variables.Variable;
import uva.ql.gui.fields.DateSpinner;
import uva.ql.gui.fields.IntTextField;
import uva.ql.gui.fields.MoneyTextField;

public class Question<T> extends JPanel {

	private JCheckBox checkBox;
	private JTextField textField;
	private DateSpinner spinner;
	
	public Question(String label, Variable<T> var) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout(new GridLayout(1,2));
		add( new JLabel(label) );
		
		if (var.getType() == EnumType.BOOLEAN) {
			checkBox = new JCheckBox("Yes/No");
			checkBox.setName(var.getName());
			checkBox.setSelected(false);
			checkBox.setEnabled(true);
			add(this.checkBox);
		}
		else if (var.getType() == EnumType.MONEY) {
			textField = new MoneyTextField((VarMoney) var);
			add(this.textField);
		}
		else if (var.getType() == EnumType.INTEGER) {
			textField = new IntTextField((VarInt) var);
			add(this.textField);
		}
		else if (var.getType() == EnumType.DATE) {
			spinner = new DateSpinner(var);
			add(this.spinner);
		}
	}
}

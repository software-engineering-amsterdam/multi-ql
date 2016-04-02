package uva.ql.gui.fields;

import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import uva.ql.ast.variables.VarMoney;
import uva.ql.gui.fields.documentfilters.MoneyFilter;
import uva.ql.gui.fields.focuslisteners.MoneyFocusListener;

public class MoneyTextField<T> extends JTextField {
	
    public MoneyTextField(final VarMoney var) {
    	super();
    	this.setName(var.getName());
    	this.setText("0.00");
    	PlainDocument doc = (PlainDocument) this.getDocument();
    	doc.setDocumentFilter(new MoneyFilter(var, "^[-]?[0-9]+[.]?[0-9]{0,2}$"));
    	
    	this.addFocusListener(new MoneyFocusListener(this, var));
    }
}
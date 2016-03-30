package uva.ql.gui.fields;

import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import uva.ql.ast.variables.VarInt;
import uva.ql.gui.fields.documentfilters.IntFilter;
import uva.ql.gui.fields.focuslisteners.IntFocusListener;

public class IntTextField<T>  extends JTextField {
	
    public IntTextField(final VarInt var) {
    	super();
    	this.setName(var.getName());
    	this.setText("0");
    	PlainDocument doc = (PlainDocument) this.getDocument();
    	doc.setDocumentFilter(new IntFilter(var, "^[0-9]+$"));
    	
    	this.addFocusListener(new IntFocusListener(this, var));
    }
}

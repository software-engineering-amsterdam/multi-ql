package uva.ql.gui.fields;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.PlainDocument;

import uva.ql.ast.variables.Variable;
import uva.ql.gui.fields.documentfilters.IntFilter;

public class IntTextField<T>  extends JTextField {
	
    public IntTextField(final Variable<T> var) {
    	super();
    	this.setName(var.getName());
    	this.setText("0");
    	PlainDocument doc = (PlainDocument) this.getDocument();
    	doc.setDocumentFilter(new IntFilter(var, "^[0-9]+$"));
    	
    	this.addFocusListener(new FocusAdapter() {
    		
    		@Override
			public void focusGained(FocusEvent evt) {
    			SwingUtilities.invokeLater(new Runnable() {
    				
					@Override
					public void run() {
						selectAll();
					}
    			});
    		}
    	});
    }
}

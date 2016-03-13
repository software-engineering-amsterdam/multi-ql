package uva.ql.gui.fields.documentlisteners;

import java.awt.Component;
import java.awt.Container;

import javax.swing.event.DocumentListener;

import uva.ql.gui.fields.IntTextField;
import uva.ql.gui.fields.MoneyTextField;

public abstract class AbstractDocumentListener implements DocumentListener {

	public static void enablePanel( Container container, boolean enable) {
		Component[] components = container.getComponents();
		for (Component c : components) {
			c.setEnabled(enable);
			if (c instanceof Container) {
				enablePanel( (Container) c, enable );
			}
		}
	};
	
	public void resetPanel( Container container) {
		Component[] components = container.getComponents();
		for (Component c : components) {
			if (c instanceof Container) {
				resetPanel( (Container) c );
			}
			
			// MoneyTextField
			try {
				MoneyTextField textField = (MoneyTextField) c;
				textField.setText("0.00");
			} catch (ClassCastException e) {}
			
			// IntTextField
			try {
				IntTextField textField = (IntTextField) c;
				textField.setText("0");
			} catch (ClassCastException e) {}
		}
	};
}

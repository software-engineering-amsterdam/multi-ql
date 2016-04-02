package uva.ql.gui;

import java.awt.Component;
import java.awt.Container;

public abstract class EnableDisablePanel {

	public void enablePanel( Container container, boolean enable) {
		
		Component[] components = container.getComponents();
		
		for (Component c : components) {
			c.setEnabled(enable);
			if (c instanceof Container) {
				enablePanel( (Container) c, enable );
			}
		}
	}
}

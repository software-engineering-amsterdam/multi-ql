package uva.ql.gui;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JTextField;

import uva.ql.gui.fields.DateSpinner;
import uva.ql.gui.fields.IntTextField;

public class ComponentsInPanel {

	private Component[] components;
	private final List<List<String>> jsonData = new ArrayList<List<String>>(0);
	
	public List<List<String>> collectComponents(Container container) {
		
		this.components = container.getComponents();
		
		for (Component c : components) {
			List<String> list = new ArrayList<String>(0); 
			
			if (c.isEnabled()) {
				if (c instanceof IntTextField) {
					IntTextField itf = (IntTextField) c;
					list.add(itf.getName());
					list.add(itf.getText());
				}
				else if (c instanceof DateSpinner) {
					DateSpinner ds = (DateSpinner) c;
					list.add(ds.getName());
					list.add(ds.getValue().toString());
				}
				else if (c instanceof JTextField) {
					JTextField jtf = (JTextField) c;
					list.add(jtf.getName());
					list.add(jtf.getText());
				}
				else if (c instanceof JCheckBox) {
					JCheckBox jcb = (JCheckBox) c;
					boolean selected = jcb.isSelected();
					list.add(jcb.getName());
					list.add(Boolean.toString(selected));
				}
				else if (c instanceof Container) {
					collectComponents((Container) c);
				}
				
				if(list.size() > 0) {
					jsonData.add(list);
				}
			}
		}
		
		return jsonData;
	}
}

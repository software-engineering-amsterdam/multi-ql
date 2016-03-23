package eu.bankersen.kevin.ql.gui.widgets.input;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class DropdownWidget implements InputWidget {

    private final JPanel panel;
    private final JComboBox inputField;
    private Widget parent;

    DropdownWidget() {
	this.panel = new JPanel();

	String[] params = { "True", "False" };
	inputField = new JComboBox(params);
	inputField.setSelectedIndex(-1);
	inputField.setPreferredSize(new Dimension(120, 20));
	inputField.addActionListener(new ComboListener());

	panel.add(inputField);
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void setParent(Widget parent) {
	this.parent = parent;
	inputField.setEditable(parent.isComputed());
    }

    @Override
    public void updateWidgetValue(QLValue value) {

	if (!value.equals(new UndifinedValue())) {
	    if (value.value().toString().equalsIgnoreCase("true")) {
		inputField.setSelectedIndex(0);
	    } else if (value.value().toString().equalsIgnoreCase("false")) {
		inputField.setSelectedIndex(1);
	    }
	}
    }

    @Override
    public void updateParentWidget(String value) {
	parent.widgetUpdated(value);
    }

    class ComboListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JComboBox cb = (JComboBox) e.getSource();
	    String value = cb.getSelectedItem().toString();
	    updateParentWidget(value);
	}
    }
}
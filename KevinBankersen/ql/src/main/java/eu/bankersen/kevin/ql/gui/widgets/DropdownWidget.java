package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;

public class DropdownWidget implements InputWidget {

    private final JPanel panel;
    private final QLType type;
    private final JComboBox inputField;
    private final Widget parentWidget;

    DropdownWidget(QLType type, Widget parentWidget) {
	this.type = type;
	this.panel = new JPanel();
	this.parentWidget = parentWidget;

	String[] params = { "True", "False" };
	inputField = new JComboBox(params);
	inputField.setEditable(parentWidget.isComputed());
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
    public void notifyParentWidget(QLValue value) {
	parentWidget.widgetUpdated(value);
    }

    class ComboListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JComboBox cb = (JComboBox) e.getSource();
	    QLValue value = type.createQLValueFrom(cb.getSelectedItem().toString());
	    notifyParentWidget(value);
	}
    }
}
package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.object.type.QLType;
import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.ast.object.value.UndifinedValue;

public class DropdownWidget implements InputWidget {

    private final JPanel panel;
    private final QLType type;
    private final JComboBox inputField;
    private final List<Widget> widgetListeners;

    DropdownWidget(QLType type) {
	this.type = type;
	this.panel = new JPanel();
	widgetListeners = new ArrayList<>();

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
    public void setComputed(Boolean isComputed) {
	inputField.setEditable(!isComputed);
    }

    @Override
    public void updateWidget(QLValue value) {

	if (!value.equals(new UndifinedValue())) {
	    if (value.value().toString().equalsIgnoreCase("true")) {
		inputField.setSelectedIndex(0);
	    } else if (value.value().toString().equalsIgnoreCase("false")) {
		inputField.setSelectedIndex(1);
	    }
	}
    }

    @Override
    public void widgetUpdated(QLValue value) {
	widgetListeners.forEach(l -> l.widgetUpdated(value));
    }

    @Override
    public void addWidgetListener(Widget listener) {
	widgetListeners.add(listener);

    }

    class ComboListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    JComboBox cb = (JComboBox) e.getSource();
	    QLValue value = type.createQLValueFrom(cb.getSelectedItem().toString());
	    widgetUpdated(value);
	}
    }
}
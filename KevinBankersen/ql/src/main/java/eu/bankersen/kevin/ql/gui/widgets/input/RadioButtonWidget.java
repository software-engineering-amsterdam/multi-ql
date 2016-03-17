package eu.bankersen.kevin.ql.gui.widgets.input;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class RadioButtonWidget implements InputWidget {

    private final JPanel panel;
    private final JRadioButton trueToggle;
    private final JRadioButton falseToggle;
    private Widget parent;
    private final ButtonGroup group;

    public RadioButtonWidget() {
	this.panel = new JPanel();

	trueToggle = new JRadioButton("True");
	falseToggle = new JRadioButton("False");

	group = new ButtonGroup();
	group.add(trueToggle);
	group.add(falseToggle);

	trueToggle.addActionListener(new ToggleListerner());
	falseToggle.addActionListener(new ToggleListerner());

	panel.add(trueToggle);
	panel.add(falseToggle);
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void setParent(Widget parent) {
	this.parent = parent;
	trueToggle.setEnabled(!parent.isComputed());
	falseToggle.setEnabled(!parent.isComputed());
    }

    @Override
    public void updateWidgetValue(QLValue value) {

	if (!value.equals(new UndifinedValue())) {
	    if (value.value().equals(true)) {
		trueToggle.setSelected(true);
	    } else if (value.value().equals(false)) {
		falseToggle.setSelected(true);
	    }
	} else {
	    group.clearSelection();
	}
    }

    @Override
    public void updateParentWidget(String value) {
	parent.widgetUpdated(value);
    }

    class ToggleListerner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    AbstractButton aButton = (AbstractButton) actionEvent.getSource();
	    String value = aButton.getText();
	    updateParentWidget(value);
	}
    }

}

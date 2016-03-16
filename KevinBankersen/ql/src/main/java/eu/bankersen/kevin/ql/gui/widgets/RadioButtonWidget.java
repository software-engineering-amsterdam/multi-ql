package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;

public class RadioButtonWidget implements InputWidget {

    private final JPanel panel;
    private final QLType type;
    private final JRadioButton trueToggle;
    private final JRadioButton falseToggle;
    private final List<Widget> widgetListeners;
    private final ButtonGroup group;

    public RadioButtonWidget(QLType type) {
	this.type = type;
	this.panel = new JPanel();
	widgetListeners = new ArrayList<>();

	trueToggle = new JRadioButton("True");
	falseToggle = new JRadioButton("False");

	group = new ButtonGroup();
	group.add(trueToggle);
	group.add(falseToggle);

	ActionListener toggleListerner = new ActionListener() {
	    public void actionPerformed(ActionEvent actionEvent) {
		AbstractButton aButton = (AbstractButton) actionEvent.getSource();
		QLValue value = type.createQLValueFrom(aButton.getText());
		widgetUpdated(value);
	    }
	};

	trueToggle.addActionListener(toggleListerner);
	falseToggle.addActionListener(toggleListerner);

	panel.add(trueToggle);
	panel.add(falseToggle);
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void setComputed(Boolean isComputed) {
	trueToggle.setEnabled(!isComputed);
	falseToggle.setEnabled(!isComputed);
    }

    @Override
    public void updateWidget(QLValue value) {

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
    public void widgetUpdated(QLValue value) {
	widgetListeners.forEach(l -> l.widgetUpdated(value));
    }

    @Override
    public void addWidgetListener(Widget listener) {
	widgetListeners.add(listener);
    }
}

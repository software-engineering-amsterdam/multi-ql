package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private final Widget parentWidget;
    private final ButtonGroup group;

    public RadioButtonWidget(QLType type, Widget parentWidget) {
	this.type = type;
	this.panel = new JPanel();
	this.parentWidget = parentWidget;

	trueToggle = new JRadioButton("True");
	trueToggle.setEnabled(!parentWidget.isComputed());

	falseToggle = new JRadioButton("False");
	falseToggle.setEnabled(!parentWidget.isComputed());

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
    public void notifyParentWidget(QLValue value) {
	parentWidget.widgetUpdated(value);
    }

    class ToggleListerner implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
	    AbstractButton aButton = (AbstractButton) actionEvent.getSource();
	    QLValue value = type.createQLValueFrom(aButton.getText());
	    notifyParentWidget(value);
	}
    }

}

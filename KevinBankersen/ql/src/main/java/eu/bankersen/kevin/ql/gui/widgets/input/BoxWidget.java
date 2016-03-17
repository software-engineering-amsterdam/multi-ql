package eu.bankersen.kevin.ql.gui.widgets.input;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class BoxWidget implements InputWidget {

    private Widget parent;
    private final JPanel panel;
    private final JFormattedTextField inputField;

    public BoxWidget() {
	this.panel = new JPanel();
	inputField = new JFormattedTextField();
	inputField.setPreferredSize(new Dimension(120, 20));
	inputField.addKeyListener(new BoxListener());
	panel.add(inputField);
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void setParent(Widget parent) {
	this.parent = parent;
	inputField.setEditable(!parent.isComputed());
    }

    @Override
    public void updateWidgetValue(QLValue value) {

	if (value.equals(new UndifinedValue())) {
	    inputField.setText("");
	} else if (!inputField.isEditable()) {
	    inputField.setText(value.toString());
	}

    }

    @Override
    public void updateParentWidget(String value) {
	parent.widgetUpdated(value);
    }

    class BoxListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    JFormattedTextField field = (JFormattedTextField) e.getSource();
	    String value = field.getText();
	    updateParentWidget(value);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
    }

}

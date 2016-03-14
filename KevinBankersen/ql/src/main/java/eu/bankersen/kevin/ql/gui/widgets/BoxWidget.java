package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;

public class BoxWidget implements InputWidget {

    private final JPanel panel;
    private final QLType type;
    private final JFormattedTextField inputField;
    private final List<Widget> widgetListeners;
    private QLValue oldValue;

    public BoxWidget(QLType type) {
	this.type = type;
	this.panel = new JPanel();
	widgetListeners = new ArrayList<>();
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
    public void setComputed(Boolean isComputed) {
	inputField.setEditable(!isComputed);
    }

    @Override
    public void updateWidget(QLValue value) {

	if (value.equals(new UndifinedValue())) {
	    inputField.setText("");
	} else if (!inputField.isEditable()) {
	    inputField.setText(value.toString());
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

    class BoxListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    JFormattedTextField field = (JFormattedTextField) e.getSource();
	    QLValue value = type.createQLValueFrom(field.getText());
	    widgetUpdated(value);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
    }

}

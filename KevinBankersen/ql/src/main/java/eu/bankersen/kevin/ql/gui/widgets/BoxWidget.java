package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.types.QLType;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;

public class BoxWidget implements InputWidget {

    private Widget parentWidget;
    private final JPanel panel;
    private final QLType type;
    private final JFormattedTextField inputField;

    public BoxWidget(QLType type, Widget parentWidget) {
	this.type = type;
	this.panel = new JPanel();
	this.parentWidget = parentWidget;
	inputField = new JFormattedTextField();
	inputField.setEditable(!parentWidget.isComputed());
	inputField.setPreferredSize(new Dimension(120, 20));
	inputField.addKeyListener(new BoxListener());
	panel.add(inputField);
    }

    @Override
    public JPanel build() {
	return panel;
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
    public void notifyParentWidget(QLValue value) {
	parentWidget.widgetUpdated(value);
    }

    class BoxListener implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    JFormattedTextField field = (JFormattedTextField) e.getSource();
	    QLValue value = type.createQLValueFrom(field.getText());
	    notifyParentWidget(value);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}
    }

}

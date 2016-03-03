package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.Type;
import eu.bankersen.kevin.ql.context.Symbol;
import eu.bankersen.kevin.ql.context.SymbolTable;

public class BoxWidget implements Widget {

    private final String name;
    private final JPanel panel;
    private final Type type;
    private final JFormattedTextField inputField;
    private final List<Widget> widgetListeners;

    BoxWidget(Symbol data) {
	this.name = data.getName();
	this.type = data.getType();
	this.panel = new JPanel();
	widgetListeners = new ArrayList<>();

	inputField = new JFormattedTextField();
	inputField.setPreferredSize(new Dimension(120, 20));
	inputField.addKeyListener(new BoxListener());

	panel.add(inputField);
    }

    private Object returnValue(String data) {
	if (type.equals(Type.INTEGER)) {
	    try {
		return Integer.parseInt(data);
	    } catch (NumberFormatException e) {
		return 0;
	    }
	} else {
	    return data;
	}
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void dataUpdate(SymbolTable symbolTable) {
	Symbol data = symbolTable.getSymbol(name);

	if (!data.getValue().equals(Type.EMPTY)) {
	    inputField.setText(data.getValue().toString());
	} else {
	    inputField.setText("");
	}

	// Update the view.
	BoxWidget.this.panel.revalidate();
	BoxWidget.this.panel.repaint();

    }

    @Override
    public void widgetUpdate(Object value) {
	widgetListeners.forEach(l -> l.widgetUpdate(value));
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

	    if (type.equals(Type.BOOLEAN)) {
		if (field.getText().equalsIgnoreCase("true") || field.getText().equalsIgnoreCase("false")) {
		    widgetUpdate(Boolean.valueOf(field.getText()));
		}
	    } else {
		widgetUpdate(returnValue(field.getText()));
	    }

	    BoxWidget.this.panel.revalidate();
	    BoxWidget.this.panel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

    }
}

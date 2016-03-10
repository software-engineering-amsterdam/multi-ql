package eu.bankersen.kevin.ql.gui.widgets;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.type.Type;
import eu.bankersen.kevin.ql.typechecker.symboltable.Symbol;
import eu.bankersen.kevin.ql.typechecker.symboltable.SymbolTable;

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

	inputField = new JFormattedTextField(data.getValue());
	inputField.setPreferredSize(new Dimension(120, 20));
	inputField.setEditable(!data.isComputed());
	inputField.addKeyListener(new BoxListener());

	panel.add(inputField);
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void dataUpdate(SymbolTable symbolTable) {
	Symbol data = symbolTable.getSymbol(name);

	if (data.getValue() == null) {
	    inputField.setText("");
	} else if (data.isComputed()) {
	    inputField.setText(type.formatTypeToString(data.getValue().toString()));
	}
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

	    Object input = type.parseValue(field.getText());

	    widgetUpdate(input);
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

    }
}
package eu.bankersen.kevin.ql.gui.widgets.input;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class SliderWidget implements InputWidget {

    private Widget parent;
    private final JPanel panel;
    private final JSlider inputField;

    public SliderWidget() {
	this.panel = new JPanel();
	this.inputField = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);

	inputField.addChangeListener(new ChangeListener() {

	    @Override
	    public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		updateParentWidget(String.valueOf(source.getValue()));
	    }
	});

	// Turn on labels at major tick marks.
	inputField.setPreferredSize(new Dimension(120, 40));
	inputField.setMajorTickSpacing(50);
	inputField.setMinorTickSpacing(10);
	inputField.setPaintTicks(true);
	// inputField.setPaintTicks(true);
	inputField.setPaintLabels(true);

	panel.add(inputField);
    }

    @Override
    public JPanel build() {
	return panel;
    }

    @Override
    public void setParent(Widget parent) {
	this.parent = parent;
	inputField.setEnabled(!parent.isComputed());
    }

    @Override
    public void updateWidgetValue(QLValue value) {

	if (value.equals(new UndifinedValue())) {
	    inputField.setValue(50);
	} else if (!inputField.isEnabled()) {
	    inputField.setValue(Integer.valueOf(value.value().toString()));
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

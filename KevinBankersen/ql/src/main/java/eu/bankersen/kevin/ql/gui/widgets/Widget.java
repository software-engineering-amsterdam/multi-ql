package eu.bankersen.kevin.ql.gui.widgets;

import javax.swing.JPanel;

import eu.bankersen.kevin.ql.interpreter.DataListener;

public interface Widget extends DataListener {
    
    JPanel build();
    
    void widgetUpdate(Object value);
    
    void addWidgetListener(Widget listener);
}

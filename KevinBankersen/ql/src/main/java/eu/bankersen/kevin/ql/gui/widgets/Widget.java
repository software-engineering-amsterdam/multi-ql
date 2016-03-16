package eu.bankersen.kevin.ql.gui.widgets;

import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.values.QLValue;

public interface Widget {

    JPanel build();

    void widgetUpdated(QLValue value);

    boolean isComputed();
}

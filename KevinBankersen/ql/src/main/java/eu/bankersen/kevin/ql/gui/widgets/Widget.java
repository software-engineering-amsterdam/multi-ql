package eu.bankersen.kevin.ql.gui.widgets;

import javax.swing.JPanel;

public interface Widget {

    JPanel build();

    void widgetUpdated(String value);

    boolean isComputed();
}

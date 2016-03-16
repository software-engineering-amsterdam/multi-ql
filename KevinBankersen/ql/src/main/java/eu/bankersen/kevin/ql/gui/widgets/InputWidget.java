package eu.bankersen.kevin.ql.gui.widgets;

import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.values.QLValue;

public interface InputWidget {

    JPanel build();

    void notifyParentWidget(QLValue value);

    void updateWidgetValue(QLValue value);
}

package eu.bankersen.kevin.ql.gui.widgets.input;

import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public interface InputWidget {

    JPanel build();

    void setParent(Widget parent);

    void updateParentWidget(String value);

    void updateWidgetValue(QLValue value);
}

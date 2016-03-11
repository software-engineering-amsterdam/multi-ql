package eu.bankersen.kevin.ql.gui.widgets;

import javax.swing.JPanel;

import eu.bankersen.kevin.ql.ast.object.value.QLValue;

public interface InputWidget {

    JPanel build();

    void widgetUpdated(QLValue value);

    void setComputed(Boolean isComputed);

    void updateWidget(QLValue value);

    void addWidgetListener(Widget questionWidget);

}

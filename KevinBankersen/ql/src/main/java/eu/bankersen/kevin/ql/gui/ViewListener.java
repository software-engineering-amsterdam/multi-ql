package eu.bankersen.kevin.ql.gui;

import eu.bankersen.kevin.ql.ast.object.value.QLValue;

public interface ViewListener {

    void viewUpdate(String name, QLValue value);
}

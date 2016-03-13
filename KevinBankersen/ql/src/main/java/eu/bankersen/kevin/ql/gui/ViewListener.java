package eu.bankersen.kevin.ql.gui;

import eu.bankersen.kevin.ql.ast.values.QLValue;

public interface ViewListener {

    void viewUpdate(String name, QLValue value);
}

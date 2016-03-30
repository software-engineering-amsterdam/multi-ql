package eu.bankersen.kevin.ql.gui;

import eu.bankersen.kevin.ql.form.ast.values.Value;

public interface ViewListener {

    void viewUpdate(String name, Value value);
}

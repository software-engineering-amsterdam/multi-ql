package org.uva.sea.ql.gui.view.editor;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextArea;

/**
 * Created by roy on 3/28/16.
 */
class TextPane {
    private final TextArea textPane;

    TextPane() {
        textPane = new TextArea();
        textPane.setWrapText(true);
    }

    void addCaretChangedListener(ChangeListener<Number> listener){
        textPane.caretPositionProperty().addListener(listener);
    }

    TextArea getTextPane() {
        return textPane;
    }
}

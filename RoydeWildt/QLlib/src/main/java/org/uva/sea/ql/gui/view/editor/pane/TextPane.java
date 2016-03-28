package org.uva.sea.ql.gui.view.editor.pane;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextArea;

/**
 * Created by roy on 3/28/16.
 */
public class TextPane {
    private final TextArea textPane;

    public TextPane() {
        textPane = new TextArea();
        textPane.setWrapText(true);
    }

    public void addCaretChangedListener(ChangeListener<Number> listener){
        textPane.caretPositionProperty().addListener(listener);
    }

    public void addTextChangedListener(ChangeListener<String> listener){
        textPane.textProperty().addListener(listener);
    }

    public TextArea getTextPane() {
        return textPane;
    }
}

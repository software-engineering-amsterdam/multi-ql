package org.uva.sea.ql.gui.view.editor.pane;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.TextArea;

/**
 * Created by roy on 3/28/16.
 */
public class TextPane extends  TextArea{

    public TextPane() {
        this.setWrapText(true);
    }

    public void addCaretChangedListener(ChangeListener<Number> listener){
        this.caretPositionProperty().addListener(listener);
    }

    public void addTextChangedListener(ChangeListener<String> listener){
        this.textProperty().addListener(listener);
    }
}

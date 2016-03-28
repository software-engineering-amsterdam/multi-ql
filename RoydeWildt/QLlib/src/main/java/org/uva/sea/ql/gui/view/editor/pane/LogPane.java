package org.uva.sea.ql.gui.view.editor.pane;

import javafx.scene.control.TextArea;

/**
 * Created by roy on 3/28/16.
 */
public class LogPane {
    private final TextArea logPane;

    public LogPane() {
        logPane = new TextArea();
        logPane.setEditable(false);
    }

    public TextArea getLogPane() {
        return logPane;
    }

}

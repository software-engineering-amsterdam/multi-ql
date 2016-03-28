package org.uva.sea.ql.gui.view.editor.listener;

import java.util.EventListener;

/**
 * Created by roy on 3/28/16.
 */
public interface EditorChangedListener extends EventListener {
    void editorChanged(EditorChangedEvent editorChangedEvent);
}

package org.uva.sea.ql.gui.widget.string;

import org.uva.sea.ql.gui.widget.Widget;

/**
 * Created by roydewildt on 20/03/16.
 */
public abstract class StringWidget extends Widget {
    public abstract void setValue(String string);
    public abstract String getText();
}

package org.uva.sea.ql.gui.widget;

import org.uva.sea.ql.ast.tree.stat.Question;

/**
 * Created by roydewildt on 16/03/16.
 */
public interface Widget {
    void setInvalid();
    void unSetInvalid();
    Question getParentQuestion();
}

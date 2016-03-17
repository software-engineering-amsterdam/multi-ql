package org.uva.sea.ql.gui.widget;

import org.uva.sea.ql.evaluator.EvaluatedQuestion;

/**
 * Created by roydewildt on 16/03/16.
 */
public interface Widget {
    void setInvalid();
    void unSetInvalid();
    EvaluatedQuestion getParentQuestion();
}

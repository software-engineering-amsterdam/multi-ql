package org.uva.sea.ql.gui.widget.factory;

import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.gui.widget.Widget;
import org.uva.sea.ql.gui.widget.binary.BooleanWidget;
import org.uva.sea.ql.gui.widget.number.NumberWidget;
import org.uva.sea.ql.gui.widget.string.StringWidget;

/**
 * Created by roydewildt on 20/03/16.
 */
public abstract class WidgetFactory {
    public abstract BooleanWidget getBooleanWidget(EvaluatedQuestion parentQuestion);
    public abstract NumberWidget getNumberWidget(EvaluatedQuestion parentQuestion);
    public abstract StringWidget getStringWidget(EvaluatedQuestion parentQuestion);
}

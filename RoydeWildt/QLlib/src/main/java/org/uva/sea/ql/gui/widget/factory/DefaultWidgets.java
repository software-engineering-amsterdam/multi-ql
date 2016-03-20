package org.uva.sea.ql.gui.widget.factory;

import javafx.scene.Parent;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.gui.widget.Widget;
import org.uva.sea.ql.gui.widget.binary.BooleanWidget;
import org.uva.sea.ql.gui.widget.binary.CheckboxWidget;
import org.uva.sea.ql.gui.widget.number.NumberFieldWidget;
import org.uva.sea.ql.gui.widget.number.NumberWidget;
import org.uva.sea.ql.gui.widget.string.StringWidget;
import org.uva.sea.ql.gui.widget.string.TextFieldWidget;

/**
 * Created by roydewildt on 20/03/16.
 */
public class DefaultWidgets extends WidgetFactory {
    @Override
    public BooleanWidget getBooleanWidget(EvaluatedQuestion parentQuestion) {
        return new CheckboxWidget(parentQuestion);
    }

    @Override
    public NumberWidget getNumberWidget(EvaluatedQuestion parentQuestion) {
        return new NumberFieldWidget(parentQuestion);
    }

    @Override
    public StringWidget getStringWidget(EvaluatedQuestion parentQuestion) {
        return new TextFieldWidget(parentQuestion);
    }
}

package org.uva.sea.ql.gui.widget.number;

import javafx.scene.control.TextField;
import org.uva.sea.ql.evaluator.EvaluatedQuestion;
import org.uva.sea.ql.gui.widget.Widget;

/**
 * Created by roy on 3-3-16.
 */
public class MoneyFieldWidget extends NumberWidget {

    public MoneyFieldWidget(EvaluatedQuestion question, boolean readOnly) {
        this.setDisable(readOnly);
    }
    
}

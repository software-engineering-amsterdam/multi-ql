package eu.bankersen.kevin.ql.ast.types;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.BoxWidget;
import eu.bankersen.kevin.ql.gui.widgets.InputWidget;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class MoneyType extends QLType {

    @Override
    public InputWidget defaultWidget(Widget parentWidget) {
	return new BoxWidget(new MoneyType(), parentWidget);
    }

    @Override
    public String toString() {
	return "Money";
    }

    @Override
    public QLValue createQLValueFrom(String string) {
	return isValidBigDecimal(string) ? new MoneyValue(new BigDecimal(string)) : new UndifinedValue();
    }

    @Override
    public Boolean equals(QLType type) {
	return type.equals(this);
    }

    @Override
    public Boolean equals(MoneyType type) {
	return true;
    }

}

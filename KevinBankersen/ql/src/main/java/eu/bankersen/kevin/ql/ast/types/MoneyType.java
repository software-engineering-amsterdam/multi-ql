package eu.bankersen.kevin.ql.ast.types;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.BoxWidget;
import eu.bankersen.kevin.ql.gui.widgets.InputWidget;

public class MoneyType extends AbstractType {

    @Override
    public InputWidget defaultWidget() {
	return new BoxWidget(new MoneyType());
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

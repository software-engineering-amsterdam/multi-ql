package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.MoneyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.BoxInput;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;

public class MoneyType extends Type {

    @Override
    public QuestionWidget defaultWidget(Question question) {
	return new BoxInput(question);
    }

    @Override
    public String toString() {
	return "Money";
    }

    @Override
    public Value createQLValueFrom(String string) {
	try {
	    return new MoneyValue(string);
	} catch (NumberFormatException e) {
	    return new EmptyValue();
	}
    }

    @Override
    public boolean equals(Object obj) {
	return obj instanceof MoneyType;
    }

    @Override
    public int hashCode() {
	return 13;
    }

}

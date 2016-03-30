package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;

public class UndifinedType extends Type {

    private final Object value;

    public UndifinedType() {
	this.value = null;
    }

    @Override
    public String toString() {
	return "Unknown Type";
    }

    @Override
    public Value createQLValueFrom(String string) {
	return new EmptyValue();
    }

    @Override
    public QuestionWidget defaultWidget(Question question) {
	return null;
    }

    @Override
    public boolean equals(Object obj) {
	return obj instanceof UndifinedType;
    }

    @Override
    public int hashCode() {
	return 13;
    }
}

package eu.bankersen.kevin.ql.ast.types;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.input.InputWidget;

public class UndifinedType extends QLType {

    private final Object value;

    public UndifinedType() {
	this.value = null;
    }

    @Override
    public String toString() {
	return "Unknown Type";
    }

    @Override
    public QLValue createQLValueFrom(String string) {
	return new UndifinedValue();
    }

    @Override
    public InputWidget defaultWidget() {
	return null;
    }

    @Override
    public Boolean equals(QLType type) {
	return type.equals(this);
    }

    @Override
    public Boolean equals(UndifinedType type) {
	return true;
    }
}

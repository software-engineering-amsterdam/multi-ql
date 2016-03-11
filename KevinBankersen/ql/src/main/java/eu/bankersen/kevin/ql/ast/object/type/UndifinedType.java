package eu.bankersen.kevin.ql.ast.object.type;

import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.ast.object.value.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.InputWidget;

public class UndifinedType extends AbstractType {

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

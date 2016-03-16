package eu.bankersen.kevin.ql.ast.types;

import eu.bankersen.kevin.ql.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.InputWidget;
import eu.bankersen.kevin.ql.gui.widgets.RadioButtonWidget;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public class BooleanType extends QLType {

    @Override
    public InputWidget defaultWidget(Widget parentWidget) {
	return new RadioButtonWidget(new BooleanType(), parentWidget);
    }

    @Override
    public String toString() {
	return "Boolean";
    }

    @Override
    public QLValue createQLValueFrom(String string) {
	return isValidBoolean(string) ? new BooleanValue(string.equalsIgnoreCase("true")) : new UndifinedValue();
    }

    @Override
    public Boolean equals(QLType type) {
	return type.equals(this);
    }

    @Override
    public Boolean equals(BooleanType type) {
	return true;
    }

    @Override
    public QLType isSubtractSupported(QLType type) {
	return type.isSubtractSupported(this);
    }

    @Override
    public QLType isAddSupported(QLType type) {
	return type.isAddSupported(this);
    }

    @Override
    public QLType isDivideSupported(QLType type) {
	return type.isDivideSupported(this);
    }

    @Override
    public QLType isMultiplySupported(QLType type) {
	return type.isMultiplySupported(this);
    }

    @Override
    public QLType isAbsoluteSupported() {
	return new BooleanType();
    }

    @Override
    public QLType isNegateSupported() {
	return new BooleanType();
    }

    @Override
    public QLType isOrSupported(QLType type) {
	return type.isOrSupported(this);
    }

    @Override
    public QLType isOrSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public QLType isAndSupported(QLType type) {
	return type.isAndSupported(this);
    }

    @Override
    public QLType isAndSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public QLType isEqualSupported(QLType type) {
	return type.isEqualSupported(this);
    }

    @Override
    public QLType isEqualSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(QLType type) {
	return type.isGreaterOrEqualSupported(this);
    }

    @Override
    public QLType isGreaterSupported(QLType type) {
	return type.isGreaterSupported(this);
    }

    @Override
    public QLType isLowerOrEqualSupported(QLType type) {
	return type.isLowerOrEqualSupported(this);
    }

    @Override
    public QLType isLowerSupported(QLType type) {
	return type.isLowerSupported(this);
    }

    @Override
    public QLType isNotEqualSupported(QLType type) {
	return type.isNotEqualSupported(this);
    }

    @Override
    public QLType isNotEqualSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public QLType isNotSupported() {
	return new BooleanType();
    }

}

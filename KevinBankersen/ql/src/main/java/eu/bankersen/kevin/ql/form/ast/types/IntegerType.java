package eu.bankersen.kevin.ql.ast.types;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.UndifinedValue;
import eu.bankersen.kevin.ql.gui.widgets.input.InputWidget;
import eu.bankersen.kevin.ql.gui.widgets.input.SliderWidget;

public class IntegerType extends QLType {

    @Override
    public InputWidget defaultWidget() {
	return new SliderWidget();
    }

    @Override
    public String toString() {
	return "Integer";
    }

    @Override
    public QLValue createQLValueFrom(String string) {
	return isValidBigDecimal(string) ? new IntegerValue(new BigDecimal(string)) : new UndifinedValue();
    }

    @Override
    public Boolean equals(QLType type) {
	return type.equals(this);
    }

    @Override
    public Boolean equals(IntegerType type) {
	return true;
    }

    @Override
    public QLType isSubtractSupported(QLType type) {
	return type.isSubtractSupported(this);
    }

    @Override
    public QLType isSubtractSupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public QLType isAddSupported(QLType type) {
	return type.isAddSupported(this);
    }

    @Override
    public QLType isAddSupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public QLType isAddSupported(StringType type) {
	return new StringType();
    }

    @Override
    public QLType isDivideSupported(QLType type) {
	return type.isDivideSupported(this);
    }

    @Override
    public QLType isDivideSupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public QLType isDivideSupported(MoneyType type) {
	return new MoneyType();
    }

    @Override
    public QLType isMultiplySupported(QLType type) {
	return type.isMultiplySupported(this);
    }

    @Override
    public QLType isMultiplySupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public QLType isMultiplySupported(MoneyType type) {
	return new MoneyType();
    }

    @Override
    public QLType isAbsoluteSupported() {
	return new IntegerType();
    }

    @Override
    public QLType isNegateSupported() {
	return new IntegerType();
    }

    @Override
    public QLType isOrSupported(QLType type) {
	return type.isOrSupported(this);
    }

    @Override
    public QLType isAndSupported(QLType type) {
	return type.isAndSupported(this);
    }

    @Override
    public QLType isEqualSupported(QLType type) {
	return type.isEqualSupported(this);
    }

    @Override
    public QLType isEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(QLType type) {
	return type.isGreaterOrEqualSupported(this);
    }

    @Override
    public QLType isGreaterOrEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public QLType isGreaterSupported(QLType type) {
	return type.isGreaterSupported(this);
    }

    @Override
    public QLType isGreaterSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public QLType isLowerOrEqualSupported(QLType type) {
	return type.isLowerOrEqualSupported(this);
    }

    @Override
    public QLType isLowerOrEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public QLType isLowerSupported(QLType type) {
	return type.isLowerSupported(this);
    }

    @Override
    public QLType isLowerSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public QLType isNotEqualSupported(QLType type) {
	return type.isNotEqualSupported(this);
    }

    @Override
    public QLType isNotEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public QLType isNotSupported() {
	return new UndifinedType();
    }
}

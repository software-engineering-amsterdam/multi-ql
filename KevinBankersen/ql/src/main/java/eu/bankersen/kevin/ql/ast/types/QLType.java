package eu.bankersen.kevin.ql.ast.types;

import java.math.BigDecimal;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.gui.widgets.InputWidget;
import eu.bankersen.kevin.ql.gui.widgets.Widget;

public abstract class QLType {

    public abstract InputWidget defaultWidget(Widget parentWidget);

    public abstract QLValue createQLValueFrom(String string);

    public abstract Boolean equals(QLType type);

    public Boolean equals(UndifinedType type) {
	return false;
    }

    public Boolean equals(IntegerType type) {
	return false;
    }

    public Boolean equals(MoneyType type) {
	return false;
    }

    public Boolean equals(BooleanType type) {
	return false;
    }

    public Boolean equals(StringType type) {
	return false;
    }

    protected boolean isValidBigDecimal(String value) {
	if (!value.isEmpty()) {
	    try {
		BigDecimal test = new BigDecimal(value);
		return true;
	    } catch (NumberFormatException e) {
		return false;
	    }
	} else {
	    return false;
	}
    }

    protected boolean isValidBoolean(String value) {
	return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    public QLType isSubtractSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isSubtractSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isSubtractSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isSubtractSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isSubtractSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isAddSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isAddSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isAddSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isAddSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isAddSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isDivideSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isDivideSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isDivideSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isDivideSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isDivideSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isMultiplySupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isMultiplySupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isMultiplySupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isMultiplySupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isMultiplySupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isAbsoluteSupported() {
	return new UndifinedType();
    }

    public QLType isNegateSupported() {
	return new UndifinedType();
    }

    public QLType isOrSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isOrSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isOrSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isOrSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isOrSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isAndSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isAndSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isAndSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isAndSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isAndSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isEqualSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isGreaterOrEqualSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isGreaterOrEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isGreaterOrEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isGreaterOrEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isGreaterOrEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isGreaterSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isGreaterSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isGreaterSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isGreaterSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isGreaterSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isLowerOrEqualSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isLowerOrEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isLowerOrEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isLowerOrEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isLowerOrEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isLowerSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isLowerSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isLowerSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isLowerSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isLowerSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isNotEqualSupported(QLType type) {
	return new UndifinedType();
    }

    public QLType isNotEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public QLType isNotEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public QLType isNotEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public QLType isNotEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public QLType isNotSupported() {
	return new UndifinedType();
    }

}

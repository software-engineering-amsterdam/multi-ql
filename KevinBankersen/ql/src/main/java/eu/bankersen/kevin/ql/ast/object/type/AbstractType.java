package eu.bankersen.kevin.ql.ast.object.type;

import java.math.BigDecimal;

public abstract class AbstractType implements QLType {

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
	try {
	    BigDecimal test = new BigDecimal(value);
	    return true;
	} catch (NumberFormatException e) {
	    return false;
	}
    }

    protected boolean isValidBoolean(String value) {
	return value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false");
    }

    @Override
    public QLType isSubtractSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAbsoluteSupported() {
	return new UndifinedType();
    }

    @Override
    public QLType isNegateSupported() {
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(QLType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(StringType type) {
	return new UndifinedType();
    }

    @Override
    public QLType isNotSupported() {
	return new UndifinedType();
    }

}

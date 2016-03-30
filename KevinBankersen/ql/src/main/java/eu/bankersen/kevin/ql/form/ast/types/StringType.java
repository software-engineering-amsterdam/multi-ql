package eu.bankersen.kevin.ql.ast.types;

import eu.bankersen.kevin.ql.ast.values.QLValue;
import eu.bankersen.kevin.ql.ast.values.StringValue;
import eu.bankersen.kevin.ql.gui.widgets.input.BoxWidget;
import eu.bankersen.kevin.ql.gui.widgets.input.InputWidget;

public class StringType extends QLType {

    @Override
    public InputWidget defaultWidget() {
	return new BoxWidget();
    }

    @Override
    public String toString() {
	return "String";
    }

    @Override
    public QLValue createQLValueFrom(String string) {
	return new StringValue(string);
    }

    @Override
    public Boolean equals(QLType type) {
	return type.equals(this);
    }

    @Override
    public Boolean equals(StringType type) {
	return true;
    }

    @Override
    public QLType isSubtractSupported(QLType type) {
	return type.isSubtractSupported(this);
    }

    @Override
    public QLType isSubtractSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isSubtractSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(QLType type) {
	return type.isAddSupported(this);
    }

    @Override
    public QLType isAddSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAddSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(QLType type) {
	return type.isDivideSupported(this);
    }

    @Override
    public QLType isDivideSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isDivideSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(QLType type) {
	return type.isMultiplySupported(this);
    }

    @Override
    public QLType isMultiplySupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isMultiplySupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAbsoluteSupported() {
	return this;
    }

    @Override
    public QLType isNegateSupported() {
	return this;
    }

    @Override
    public QLType isOrSupported(QLType type) {
	return type.isOrSupported(this);
    }

    @Override
    public QLType isOrSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isOrSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(QLType type) {
	return type.isAndSupported(this);
    }

    @Override
    public QLType isAndSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isAndSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(QLType type) {
	return type.isEqualSupported(this);
    }

    @Override
    public QLType isEqualSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isEqualSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(QLType type) {
	return type.isGreaterOrEqualSupported(this);
    }

    @Override
    public QLType isGreaterOrEqualSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterOrEqualSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(QLType type) {
	return type.isGreaterSupported(this);
    }

    @Override
    public QLType isGreaterSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isGreaterSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(QLType type) {
	return type.isLowerOrEqualSupported(this);
    }

    @Override
    public QLType isLowerOrEqualSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerOrEqualSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(QLType type) {
	return type.isLowerSupported(this);
    }

    @Override
    public QLType isLowerSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isLowerSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(QLType type) {
	return type.isNotEqualSupported(this);
    }

    @Override
    public QLType isNotEqualSupported(IntegerType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(MoneyType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(BooleanType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isNotEqualSupported(StringType type) {
	// TODO Auto-generated method stub
	return new UndifinedType();
    }

    @Override
    public QLType isNotSupported() {
	return new UndifinedType();
    }

}

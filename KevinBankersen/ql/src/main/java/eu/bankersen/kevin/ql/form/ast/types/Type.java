package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;

public abstract class Type {

    public abstract QuestionWidget defaultWidget(Question question);

    public abstract Value createQLValueFrom(String string);

    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    public Type isSubtractSupported(Type type) {
	return new UndifinedType();
    }

    public Type isSubtractSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isSubtractSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isSubtractSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isSubtractSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isAddSupported(Type type) {
	return new UndifinedType();
    }

    public Type isAddSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isAddSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isAddSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isAddSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isDivideSupported(Type type) {
	return new UndifinedType();
    }

    public Type isDivideSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isDivideSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isDivideSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isDivideSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isMultiplySupported(Type type) {
	return new UndifinedType();
    }

    public Type isMultiplySupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isMultiplySupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isMultiplySupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isMultiplySupported(StringType type) {
	return new UndifinedType();
    }

    public Type isAbsoluteSupported() {
	return new UndifinedType();
    }

    public Type isNegateSupported() {
	return new UndifinedType();
    }

    public Type isOrSupported(Type type) {
	return new UndifinedType();
    }

    public Type isOrSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isOrSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isOrSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isOrSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isAndSupported(Type type) {
	return new UndifinedType();
    }

    public Type isAndSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isAndSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isAndSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isAndSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isEqualSupported(Type type) {
	return new UndifinedType();
    }

    public Type isEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isGreaterOrEqualSupported(Type type) {
	return new UndifinedType();
    }

    public Type isGreaterOrEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isGreaterOrEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isGreaterOrEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isGreaterOrEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isGreaterSupported(Type type) {
	return new UndifinedType();
    }

    public Type isGreaterSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isGreaterSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isGreaterSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isGreaterSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isLowerOrEqualSupported(Type type) {
	return new UndifinedType();
    }

    public Type isLowerOrEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isLowerOrEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isLowerOrEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isLowerOrEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isLowerSupported(Type type) {
	return new UndifinedType();
    }

    public Type isLowerSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isLowerSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isLowerSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isLowerSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isNotEqualSupported(Type type) {
	return new UndifinedType();
    }

    public Type isNotEqualSupported(IntegerType type) {
	return new UndifinedType();
    }

    public Type isNotEqualSupported(MoneyType type) {
	return new UndifinedType();
    }

    public Type isNotEqualSupported(BooleanType type) {
	return new UndifinedType();
    }

    public Type isNotEqualSupported(StringType type) {
	return new UndifinedType();
    }

    public Type isNotSupported() {
	return new UndifinedType();
    }

}

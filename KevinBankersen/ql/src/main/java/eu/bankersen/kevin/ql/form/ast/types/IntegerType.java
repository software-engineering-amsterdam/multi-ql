package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.IntegerValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;
import eu.bankersen.kevin.ql.gui.widgets.SliderInput;

public class IntegerType extends Type {

    @Override
    public QuestionWidget defaultWidget(Question question) {
	return new SliderInput(question);
    }

    @Override
    public String toString() {
	return "Integer";
    }

    @Override
    public Value createQLValueFrom(String string) {
	try {
	    return new IntegerValue(string);
	} catch (NumberFormatException e) {
	    return new EmptyValue();
	}
    }

    @Override
    public Type isSubtractSupported(Type type) {
	return type.isSubtractSupported(this);
    }

    @Override
    public Type isSubtractSupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public Type isAddSupported(Type type) {
	return type.isAddSupported(this);
    }

    @Override
    public Type isAddSupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public Type isAddSupported(StringType type) {
	return new StringType();
    }

    @Override
    public Type isDivideSupported(Type type) {
	return type.isDivideSupported(this);
    }

    @Override
    public Type isDivideSupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public Type isDivideSupported(MoneyType type) {
	return new MoneyType();
    }

    @Override
    public Type isMultiplySupported(Type type) {
	return type.isMultiplySupported(this);
    }

    @Override
    public Type isMultiplySupported(IntegerType type) {
	return new IntegerType();
    }

    @Override
    public Type isMultiplySupported(MoneyType type) {
	return new MoneyType();
    }

    @Override
    public Type isAbsoluteSupported() {
	return new IntegerType();
    }

    @Override
    public Type isNegateSupported() {
	return new IntegerType();
    }

    @Override
    public Type isOrSupported(Type type) {
	return type.isOrSupported(this);
    }

    @Override
    public Type isAndSupported(Type type) {
	return type.isAndSupported(this);
    }

    @Override
    public Type isEqualSupported(Type type) {
	return type.isEqualSupported(this);
    }

    @Override
    public Type isEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public Type isGreaterOrEqualSupported(Type type) {
	return type.isGreaterOrEqualSupported(this);
    }

    @Override
    public Type isGreaterOrEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public Type isGreaterSupported(Type type) {
	return type.isGreaterSupported(this);
    }

    @Override
    public Type isGreaterSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public Type isLowerOrEqualSupported(Type type) {
	return type.isLowerOrEqualSupported(this);
    }

    @Override
    public Type isLowerOrEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public Type isLowerSupported(Type type) {
	return type.isLowerSupported(this);
    }

    @Override
    public Type isLowerSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public Type isNotEqualSupported(Type type) {
	return type.isNotEqualSupported(this);
    }

    @Override
    public Type isNotEqualSupported(IntegerType type) {
	return new BooleanType();
    }

    @Override
    public Type isNotSupported() {
	return new UndifinedType();
    }

    @Override
    public boolean equals(Object obj) {
	return obj instanceof IntegerType;
    }

    @Override
    public int hashCode() {
	return 13;
    }
}

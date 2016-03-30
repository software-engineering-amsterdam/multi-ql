package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.BooleanValue;
import eu.bankersen.kevin.ql.form.ast.values.EmptyValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;
import eu.bankersen.kevin.ql.gui.widgets.RadioInput;

public class BooleanType extends Type {

    @Override
    public QuestionWidget defaultWidget(Question question) {
	return new RadioInput(question);
    }

    @Override
    public String toString() {
	return "Boolean";
    }

    @Override
    public Value createQLValueFrom(String string) {
	try {
	    return new BooleanValue(string);
	} catch (IllegalArgumentException e) {
	    return new EmptyValue();
	}
    }

    @Override
    public Type isSubtractSupported(Type type) {
	return type.isSubtractSupported(this);
    }

    @Override
    public Type isAddSupported(Type type) {
	return type.isAddSupported(this);
    }

    @Override
    public Type isDivideSupported(Type type) {
	return type.isDivideSupported(this);
    }

    @Override
    public Type isMultiplySupported(Type type) {
	return type.isMultiplySupported(this);
    }

    @Override
    public Type isAbsoluteSupported() {
	return new BooleanType();
    }

    @Override
    public Type isNegateSupported() {
	return new BooleanType();
    }

    @Override
    public Type isOrSupported(Type type) {
	return type.isOrSupported(this);
    }

    @Override
    public Type isOrSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public Type isAndSupported(Type type) {
	return type.isAndSupported(this);
    }

    @Override
    public Type isAndSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public Type isEqualSupported(Type type) {
	return type.isEqualSupported(this);
    }

    @Override
    public Type isEqualSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public Type isGreaterOrEqualSupported(Type type) {
	return type.isGreaterOrEqualSupported(this);
    }

    @Override
    public Type isGreaterSupported(Type type) {
	return type.isGreaterSupported(this);
    }

    @Override
    public Type isLowerOrEqualSupported(Type type) {
	return type.isLowerOrEqualSupported(this);
    }

    @Override
    public Type isLowerSupported(Type type) {
	return type.isLowerSupported(this);
    }

    @Override
    public Type isNotEqualSupported(Type type) {
	return type.isNotEqualSupported(this);
    }

    @Override
    public Type isNotEqualSupported(BooleanType type) {
	return new BooleanType();
    }

    @Override
    public Type isNotSupported() {
	return new BooleanType();
    }

    @Override
    public boolean equals(Object obj) {
	return obj instanceof BooleanType;
    }

    @Override
    public int hashCode() {
	return 13;
    }

}

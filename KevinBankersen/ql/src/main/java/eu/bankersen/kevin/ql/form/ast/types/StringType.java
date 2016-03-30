package eu.bankersen.kevin.ql.form.ast.types;

import eu.bankersen.kevin.ql.form.ast.stat.Question;
import eu.bankersen.kevin.ql.form.ast.values.StringValue;
import eu.bankersen.kevin.ql.form.ast.values.Value;
import eu.bankersen.kevin.ql.gui.widgets.BoxInput;
import eu.bankersen.kevin.ql.gui.widgets.QuestionWidget;

public class StringType extends Type {

    @Override
    public QuestionWidget defaultWidget(Question question) {
	return new BoxInput(question);
    }

    @Override
    public String toString() {
	return "String";
    }

    @Override
    public Value createQLValueFrom(String string) {
	return new StringValue(string);
    }

    @Override
    public Type isAddSupported(Type type) {
	return type.isAddSupported(this);
    }

    @Override
    public Type isAbsoluteSupported() {
	return this;
    }

    @Override
    public Type isNegateSupported() {
	return this;
    }

    @Override
    public Type isAndSupported(Type type) {
	return type.isAndSupported(this);
    }

    @Override
    public Type isAndSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public Type isEqualSupported(Type type) {
	return type.isEqualSupported(this);
    }

    @Override
    public Type isEqualSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public Type isGreaterOrEqualSupported(Type type) {
	return type.isGreaterOrEqualSupported(this);
    }

    @Override
    public Type isGreaterOrEqualSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public Type isGreaterSupported(Type type) {
	return type.isGreaterSupported(this);
    }

    @Override
    public Type isGreaterSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public Type isLowerOrEqualSupported(Type type) {
	return type.isLowerOrEqualSupported(this);
    }

    @Override
    public Type isLowerOrEqualSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public Type isLowerSupported(Type type) {
	return type.isLowerSupported(this);
    }

    @Override
    public Type isLowerSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public Type isNotEqualSupported(Type type) {
	return type.isNotEqualSupported(this);
    }

    @Override
    public Type isNotEqualSupported(StringType type) {
	return new BooleanType();
    }

    @Override
    public boolean equals(Object obj) {
	return obj instanceof StringType;
    }

    @Override
    public int hashCode() {
	return 13;
    }

}

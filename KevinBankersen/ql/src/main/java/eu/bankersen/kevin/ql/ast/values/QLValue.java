package eu.bankersen.kevin.ql.ast.values;

import eu.bankersen.kevin.ql.ast.types.QLType;

public interface QLValue {

    Object value();

    QLType getType();

    // Equality
    Boolean equals(QLValue type);

    Boolean equals(UndifinedValue type);

    Boolean equals(IntegerValue type);

    Boolean equals(MoneyValue type);

    Boolean equals(BooleanValue type);

    Boolean equals(StringValue type);

    // Math operations
    QLValue subtract(QLValue value);

    QLValue subtract(UndifinedValue value);

    QLValue subtract(IntegerValue value);

    QLValue subtract(MoneyValue value);

    QLValue subtract(BooleanValue value);

    QLValue subtract(StringValue value);

    QLValue add(QLValue value);

    QLValue add(UndifinedValue value);

    QLValue add(IntegerValue value);

    QLValue add(MoneyValue value);

    QLValue add(BooleanValue value);

    QLValue add(StringValue value);

    QLValue divide(QLValue value);

    QLValue divide(UndifinedValue value);

    QLValue divide(IntegerValue value);

    QLValue divide(MoneyValue value);

    QLValue divide(BooleanValue value);

    QLValue divide(StringValue value);

    QLValue multiply(QLValue value);

    QLValue multiply(UndifinedValue value);

    QLValue multiply(IntegerValue value);

    QLValue multiply(MoneyValue value);

    QLValue multiply(BooleanValue value);

    QLValue multiply(StringValue value);

    QLValue absolute();

    QLValue negate();

    // Boolean operations
    QLValue or(QLValue value);

    QLValue or(UndifinedValue value);

    QLValue or(IntegerValue value);

    QLValue or(MoneyValue value);

    QLValue or(BooleanValue value);

    QLValue or(StringValue value);

    QLValue and(QLValue value);

    QLValue and(UndifinedValue value);

    QLValue and(IntegerValue value);

    QLValue and(MoneyValue value);

    QLValue and(BooleanValue value);

    QLValue and(StringValue value);

    QLValue equal(QLValue value);

    QLValue equal(UndifinedValue value);

    QLValue equal(IntegerValue value);

    QLValue equal(MoneyValue value);

    QLValue equal(BooleanValue value);

    QLValue equal(StringValue value);

    QLValue greaterOrEqual(QLValue value);

    QLValue greaterOrEqual(UndifinedValue value);

    QLValue greaterOrEqual(IntegerValue value);

    QLValue greaterOrEqual(MoneyValue value);

    QLValue greaterOrEqual(BooleanValue value);

    QLValue greaterOrEqual(StringValue value);

    QLValue greater(QLValue value);

    QLValue greater(UndifinedValue value);

    QLValue greater(IntegerValue value);

    QLValue greater(MoneyValue value);

    QLValue greater(BooleanValue value);

    QLValue greater(StringValue value);

    QLValue lowerOrEqual(QLValue value);

    QLValue lowerOrEqual(UndifinedValue value);

    QLValue lowerOrEqual(IntegerValue value);

    QLValue lowerOrEqual(MoneyValue value);

    QLValue lowerOrEqual(BooleanValue value);

    QLValue lowerOrEqual(StringValue value);

    QLValue lower(QLValue value);

    QLValue lower(UndifinedValue value);

    QLValue lower(IntegerValue value);

    QLValue lower(MoneyValue value);

    QLValue lower(BooleanValue value);

    QLValue lower(StringValue value);

    QLValue notEqual(QLValue value);

    QLValue notEqual(UndifinedValue value);

    QLValue notEqual(IntegerValue value);

    QLValue notEqual(MoneyValue value);

    QLValue notEqual(BooleanValue value);

    QLValue notEqual(StringValue value);

    QLValue not();

}

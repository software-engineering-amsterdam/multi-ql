package eu.bankersen.kevin.ql.ast.object.type;

import eu.bankersen.kevin.ql.ast.object.value.QLValue;
import eu.bankersen.kevin.ql.gui.widgets.InputWidget;

public interface QLType {

    InputWidget defaultWidget();

    QLValue createQLValueFrom(String string);

    // Equality
    Boolean equals(QLType type);

    Boolean equals(UndifinedType type);

    Boolean equals(IntegerType type);

    Boolean equals(MoneyType type);

    Boolean equals(BooleanType type);

    Boolean equals(StringType type);

    // Math operations
    QLType isSubtractSupported(QLType type);

    QLType isSubtractSupported(IntegerType type);

    QLType isSubtractSupported(MoneyType type);

    QLType isSubtractSupported(BooleanType type);

    QLType isSubtractSupported(StringType type);

    QLType isAddSupported(QLType type);

    QLType isAddSupported(IntegerType type);

    QLType isAddSupported(MoneyType type);

    QLType isAddSupported(BooleanType type);

    QLType isAddSupported(StringType type);

    QLType isDivideSupported(QLType type);

    QLType isDivideSupported(IntegerType type);

    QLType isDivideSupported(MoneyType type);

    QLType isDivideSupported(BooleanType type);

    QLType isDivideSupported(StringType type);

    QLType isMultiplySupported(QLType type);

    QLType isMultiplySupported(IntegerType type);

    QLType isMultiplySupported(MoneyType type);

    QLType isMultiplySupported(BooleanType type);

    QLType isMultiplySupported(StringType type);

    QLType isAbsoluteSupported();

    QLType isNegateSupported();

    // Boolean operations
    QLType isOrSupported(QLType type);

    QLType isOrSupported(IntegerType type);

    QLType isOrSupported(MoneyType type);

    QLType isOrSupported(BooleanType type);

    QLType isOrSupported(StringType type);

    QLType isAndSupported(QLType type);

    QLType isAndSupported(IntegerType type);

    QLType isAndSupported(MoneyType type);

    QLType isAndSupported(BooleanType type);

    QLType isAndSupported(StringType type);

    QLType isEqualSupported(QLType type);

    QLType isEqualSupported(IntegerType type);

    QLType isEqualSupported(MoneyType type);

    QLType isEqualSupported(BooleanType type);

    QLType isEqualSupported(StringType type);

    QLType isGreaterOrEqualSupported(QLType type);

    QLType isGreaterOrEqualSupported(IntegerType type);

    QLType isGreaterOrEqualSupported(MoneyType type);

    QLType isGreaterOrEqualSupported(BooleanType type);

    QLType isGreaterOrEqualSupported(StringType type);

    QLType isGreaterSupported(QLType type);

    QLType isGreaterSupported(IntegerType type);

    QLType isGreaterSupported(MoneyType type);

    QLType isGreaterSupported(BooleanType type);

    QLType isGreaterSupported(StringType type);

    QLType isLowerOrEqualSupported(QLType type);

    QLType isLowerOrEqualSupported(IntegerType type);

    QLType isLowerOrEqualSupported(MoneyType type);

    QLType isLowerOrEqualSupported(BooleanType type);

    QLType isLowerOrEqualSupported(StringType type);

    QLType isLowerSupported(QLType type);

    QLType isLowerSupported(IntegerType type);

    QLType isLowerSupported(MoneyType type);

    QLType isLowerSupported(BooleanType type);

    QLType isLowerSupported(StringType type);

    QLType isNotEqualSupported(QLType type);

    QLType isNotEqualSupported(IntegerType type);

    QLType isNotEqualSupported(MoneyType type);

    QLType isNotEqualSupported(BooleanType type);

    QLType isNotEqualSupported(StringType type);

    QLType isNotSupported();

}

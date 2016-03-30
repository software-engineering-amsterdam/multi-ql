package nl.uva.ql.visitors;

import nl.uva.ql.ast.type.BooleanType;
import nl.uva.ql.ast.type.IntegerType;
import nl.uva.ql.ast.type.MoneyType;
import nl.uva.ql.ast.type.StringType;
import nl.uva.ql.ast.type.UnknownType;
import nl.uva.ql.gui.widget.Widget;

public interface TypeVisitor {
	public Widget visit(BooleanType booleanType);
	public Widget visit(IntegerType integerType);
	public Widget visit(MoneyType moneyType);
	public Widget visit(StringType stringType);
	public Widget visit(UnknownType unknownType);
}

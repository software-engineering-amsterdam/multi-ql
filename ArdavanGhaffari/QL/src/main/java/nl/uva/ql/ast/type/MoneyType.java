package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class MoneyType extends Type {
	public MoneyType() {
		super("Money");
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}
	
//	public boolean isCompatible(Type other) {
//		return other.isCompatibleWith(this);
//	}
//	
//	public boolean isCompatibleWith(DecimalType t) {
//		return true;
//	}
//
//	public boolean isCompatibleWith(IntegerType t) {
//		return true;
//	}
}

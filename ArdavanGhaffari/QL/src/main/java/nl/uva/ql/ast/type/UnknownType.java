package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class UnknownType extends Type{

	public UnknownType() {
		super("Unknown");
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}
	
}

package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class IntegerType extends Type{

	public IntegerType() {
		super("Integer");
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}

}

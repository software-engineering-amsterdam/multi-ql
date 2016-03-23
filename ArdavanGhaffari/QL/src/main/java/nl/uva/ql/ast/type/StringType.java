package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class StringType extends Type{

	public StringType() {
		super("String");
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}

}

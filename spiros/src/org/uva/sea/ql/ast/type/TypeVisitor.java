package org.uva.sea.ql.ast.type;

import org.uva.sea.ql.gui.widgets.Widget;

public interface TypeVisitor {

	public Widget visit(IntType intType);
	public Widget visit(BoolType boolType);
	public Widget visit(StrType strType);
	public Widget visit(UndefinedType undefinedType);	// needed????
	
}

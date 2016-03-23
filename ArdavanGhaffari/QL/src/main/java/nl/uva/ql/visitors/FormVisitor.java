package nl.uva.ql.visitors;

import nl.uva.ql.ast.Form;

public interface FormVisitor {
	public void visit(Form form);
}

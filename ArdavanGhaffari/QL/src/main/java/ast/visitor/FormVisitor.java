package ast.visitor;

import ast.model.Box;
import ast.model.Form;

public interface FormVisitor {
	public void visit(Form form);
	public void visit(Box box);
}

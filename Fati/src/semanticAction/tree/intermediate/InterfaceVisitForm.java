package semanticAction.tree.intermediate;

import semanticAction.tree.formNode.Form;



public interface InterfaceVisitForm<T>  {
	public T visit(Form form);
}
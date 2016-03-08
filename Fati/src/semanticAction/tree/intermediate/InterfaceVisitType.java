package semanticAction.tree.intermediate;

import semanticAction.tree.typeNode.BooleanQL_Type;
import semanticAction.tree.typeNode.IntegerQL_Type;
import semanticAction.tree.typeNode.NotSpecifiedType;
import semanticAction.tree.typeNode.StringQL_Type;

public interface InterfaceVisitType<T> {
	public T visit(IntegerQL_Type type);
	public T visit(StringQL_Type type);
	public T visit(BooleanQL_Type type);
	public T visit(NotSpecifiedType type);

}

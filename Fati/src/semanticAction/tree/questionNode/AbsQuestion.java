package semanticAction.tree.questionNode;

import semanticAction.tree.AbstractSyntaxTree;
import semanticAction.tree.intermediate.InterfaceVisitQuestion;


public abstract class AbsQuestion extends AbstractSyntaxTree {
	
	public abstract String toString();

	public abstract <T> T accept(InterfaceVisitQuestion<T> visitor);

}
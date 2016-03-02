package semanticAction.tree.typeNode;

import semanticAction.tree.AbstractSyntaxTree;
import semanticAction.tree.intermediate.InterfaceVisitType;


public abstract class AbsType extends AbstractSyntaxTree {
	public abstract <T> T accept(InterfaceVisitType<T> visitor);
	public abstract String toString();
	
	public abstract boolean isCompatibleToType(AbsType type);
			
	public boolean isCompatibleToBoolean() {
		return false;
	}
		
	public boolean isCompatibleToString() {
		return false;
	}
		
	public boolean isCompatibleToInteger() {
		return false;
	}

}

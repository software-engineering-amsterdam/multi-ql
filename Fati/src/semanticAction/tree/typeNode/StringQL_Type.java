package semanticAction.tree.typeNode;

import semanticAction.tree.intermediate.InterfaceVisitType;

public class StringQL_Type extends AbsType {
	@Override
	public String toString() {
		return "stringstring";
	}
		
	@Override
	public <T> T accept(InterfaceVisitType<T> visitor) {
		 return visitor.visit(this);
	}
	
	@Override
	public boolean isCompatibleToInteger() {
		return true;
	}
		
	@Override
	public boolean isCompatibleToType(AbsType type) {
		return type.isCompatibleToInteger();
	}
}



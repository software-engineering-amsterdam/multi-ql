package semanticAction.tree.typeNode;

import semanticAction.tree.intermediate.InterfaceVisitType;

public class IntegerQL_Type extends AbsType{
	@Override
	public String toString() {
		return "digits";
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



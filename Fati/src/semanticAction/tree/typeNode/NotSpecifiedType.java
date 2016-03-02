package semanticAction.tree.typeNode;

import semanticAction.tree.intermediate.InterfaceVisitType;

public class NotSpecifiedType extends AbsType {
public NotSpecifiedType() { }
	
	@Override
	public String toString() {
		return "notSpecified"; 
	}
	
	@Override
	public <T> T accept(InterfaceVisitType<T> visitor) {
		return visitor.visit(this);
	}
		
	@Override
	public boolean isCompatibleToType(AbsType type) {
		return false;
	}
}



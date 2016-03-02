package semanticAction.tree.typeNode;

import semanticAction.tree.intermediate.InterfaceVisitType;

public class BooleanQL_Type extends AbsType {

	public BooleanQL_Type() {}
	
	@Override
	public String toString() {
		return "bool";
	}
		
	@Override
	public <T> T accept(InterfaceVisitType<T> visitor) {
		return visitor.visit(this);
	}
	@Override
	public boolean isCompatibleToBoolean() {
		return true;
	}
	
	@Override
	public boolean isCompatibleToType(AbsType type) {
		return type.isCompatibleToBoolean();
	}
}

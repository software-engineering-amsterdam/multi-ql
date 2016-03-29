package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class IntegerType extends Type{

	public IntegerType() {
		super("Integer");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof IntegerType){
			return this.getName().equals(((IntegerType)obj).getName());
		}
		return false;
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}
	
	@Override
	public boolean isCompatible(Type arg){
		return arg.isCompatibleWithInteger(this);
	}
	
	@Override
	public boolean isCompatibleWithInteger(IntegerType arg){
		return true;
	}
	
	@Override
	public boolean isCompatibleWithMoney(MoneyType arg){
		return true;
	}
	
	@Override
	public boolean isNumericCompatible(Type arg){
		return arg.isNumericCompatibleWithInteger(this);
	}
	
	@Override
	public boolean isNumericCompatibleWithInteger(IntegerType arg){
		return true;
	}
	
	@Override
	public boolean isNumericCompatibleWithMoney(MoneyType arg){
		return true;
	}
}

package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class MoneyType extends Type {
	public MoneyType() {
		super("Money");
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}
	
	@Override
	public boolean isCompatible(Type arg){
		return arg.isCompatibleWithMoney(this);
	}
	
	@Override
	public boolean isCompatibleWithMoney(MoneyType arg){
		return true;
	}
	
	@Override
	public boolean isCompatibleWithInteger(IntegerType arg){
		return false;
	}
	
	@Override
	public boolean isNumericCompatible(Type arg){
		return arg.isNumericCompatibleWithMoney(this);
	}
	
	@Override
	public boolean isNumericCompatibleWithMoney(MoneyType arg){
		return true;
	}
	
	@Override
	public boolean isNumericCompatibleWithInteger(IntegerType arg){
		return true;
	}
}

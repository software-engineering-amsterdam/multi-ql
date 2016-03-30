package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public abstract class Type {
	private String name;
	
	public Type(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public boolean isUnknownType(){
		return false;
	}
	
	public abstract Widget accept(TypeVisitor typeVisitor);
	
	public boolean isCompatible(Type arg){
		return false;
	}
	
	public boolean isCompatibleWithBoolean(BooleanType arg){
		return false;
	}
	
	public boolean isCompatibleWithString(StringType arg){
		return false;
	}
	
	public boolean isCompatibleWithMoney(MoneyType arg){
		return false;
	}
	
	public boolean isCompatibleWithInteger(IntegerType arg){
		return false;
	}
	
	public boolean isBooleanCompatible(Type arg){
		return false;
	}
	
	public boolean isStringCompatible(Type arg){
		return false;
	}
	
	public boolean isNumericCompatible(Type arg){
		return false;
	}
	
	public boolean isNumericCompatibleWithMoney(MoneyType arg){
		return false;
	}
	
	public boolean isNumericCompatibleWithInteger(IntegerType arg){
		return false;
	}
}

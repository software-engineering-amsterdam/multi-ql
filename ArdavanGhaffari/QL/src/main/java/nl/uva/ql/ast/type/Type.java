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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		if (this.name.equals(((Type) obj).getName())) {
			return true;
		}
		return false;
	}
	
	public abstract Widget accept(TypeVisitor typeVisitor);
	
//  if you wanted to uncomment these, make the class abstract	
//	public abstract boolean isCompatible(Type t);
//	public boolean isCompatibleWith(DecimalType t) {
//		return false;
//	}
//	public boolean isCompatibleWith(BooleanType t)  {
//		return false;
//	}
//	
//	public boolean isCompatibleWith(StringType t) {
//		return false;
//	}
//	public boolean isCompatibleWith(IntegerType t) {
//		return false;
//	}
	
}

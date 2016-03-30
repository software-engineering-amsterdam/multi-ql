package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class BooleanType extends Type{

	public BooleanType() {
		super("Boolean");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof BooleanType){
			return this.getName().equals(((BooleanType)obj).getName());
		}
		return false;
	}

	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}
	
	@Override
	public boolean isCompatible(Type arg){
		return arg.isCompatibleWithBoolean(this);
	}
	
	@Override
	public boolean isBooleanCompatible(Type arg){
		return arg.isCompatibleWithBoolean(this);
	}
	
	@Override
	public boolean isCompatibleWithBoolean(BooleanType arg){
		return true;
	}
}

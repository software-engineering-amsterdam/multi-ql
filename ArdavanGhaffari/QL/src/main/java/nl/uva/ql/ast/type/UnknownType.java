package nl.uva.ql.ast.type;

import nl.uva.ql.gui.widget.Widget;
import nl.uva.ql.visitors.TypeVisitor;

public class UnknownType extends Type{

	public UnknownType() {
		super("Unknown");
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof UnknownType){
			return this.getName().equals(((UnknownType)obj).getName());
		}
		return false;
	}
	
	@Override
	public boolean isUnknownType(){
		return true;
	}
	
	@Override
	public Widget accept(TypeVisitor typeVisitor) {
		return typeVisitor.visit(this);
	}
	
}

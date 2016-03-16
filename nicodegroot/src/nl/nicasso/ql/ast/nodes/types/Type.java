package nl.nicasso.ql.ast.nodes.types;

import nl.nicasso.ql.ast.nodes.ASTNode;
import nl.nicasso.ql.ast.nodes.CodeLocation;
import nl.nicasso.ql.gui.evaluator.values.Value;
import nl.nicasso.ql.visitors.TypeVisitor;

public abstract class Type extends ASTNode {
	
	String type;
	
	public Type() {
		this.type = "Type";
	}
	
	public Value getDefaultValue() {
		throw new AssertionError("Type getDefaultValue");
	}
	
	public Type(CodeLocation location) {
		super(location);
		this.type = "Type";
	}
	
	public String getType() {
		return type;
	}
	
	@Override
	public boolean equals(Object ob) {
		if (!(ob instanceof Type)) {
			return false;
		}
		Type t2 = (Type) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }
	
	public abstract <T, U> T accept(TypeVisitor<T, U> visitor, U context);
}
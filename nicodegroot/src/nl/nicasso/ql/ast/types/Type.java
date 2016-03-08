package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.ASTNode;
import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.values.Value;

public class Type extends ASTNode {
	
	String type;
	
	public Type() {
		this.type = "Type";
	}
	
	public Value getDefaultValue() {
		throw new AssertionError("Type getDefaultValue");
	}
	
	public QuestionField getRelatedField() {
		throw new AssertionError("Type getRelatedField");
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
		Type t2 = (Type) ob;
		return type.equals(t2.getType());
	}
	
	@Override
	public int hashCode(){
	    return type.hashCode();
    }
}
package nl.nicasso.ql.ast.types;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.gui.questionFields.TextQuestionField;
import nl.nicasso.ql.values.StringValue;

public class StringType extends Type {

	private final String type;

	public StringType() {
		this.type = "String";
	}
	
	@Override
	public StringValue getDefaultValue() {
		return new StringValue("");
	}
	
	@Override
	public QuestionField getRelatedField() {
		return new TextQuestionField();
	}
	
	public StringType(CodeLocation location) {
		super(location);
		this.type = "String";
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

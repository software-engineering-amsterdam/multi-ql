package nl.nicasso.ql.ast.types;

import java.math.BigDecimal;

import nl.nicasso.ql.ast.CodeLocation;
import nl.nicasso.ql.gui.questionFields.MoneyQuestionField;
import nl.nicasso.ql.gui.questionFields.QuestionField;
import nl.nicasso.ql.values.MoneyValue;

public class MoneyType extends NumericType {

	private final String type;

	public MoneyType() {
		this.type = "Money";
	}
	
	@Override
	public MoneyValue getDefaultValue() {
		return new MoneyValue(new BigDecimal(0.00));
	}
	
	@Override
	public QuestionField getRelatedField() {
		return new MoneyQuestionField();
	}
	
	public MoneyType(CodeLocation location) {
		super(location);
		this.type = "Money";
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

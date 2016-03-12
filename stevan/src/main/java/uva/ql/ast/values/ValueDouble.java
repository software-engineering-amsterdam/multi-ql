package uva.ql.ast.values;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Dble;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ValueDouble extends Values {

	private Dble type = new Dble();
	private Double value;
	
	public ValueDouble(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Double.parseDouble(value);
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	public Double getValue() {
		return this.value;
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
		
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}

	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {}
}

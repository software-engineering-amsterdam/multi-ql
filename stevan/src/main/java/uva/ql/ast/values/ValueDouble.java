package uva.ql.ast.values;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.abstracts.Type;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Dble;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicQuestionDependenciesVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ValueDouble extends Values {

	private Type type = new Dble();
	private Double value;
	
	public ValueDouble(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Double.parseDouble(value);
	}
	
	@Override
	public Type getType() {
		return this.type;
	}
	
	@Override
	public String typeToString() {
		return this.type.getType();
	}
	
	public Double getValue() {
		return this.value;
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitValueDouble(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
	
	@Override
	public void accept(ICyclicQuestionDependenciesVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}
}

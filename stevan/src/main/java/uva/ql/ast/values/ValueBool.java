package uva.ql.ast.values;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.values.abstracts.Values;
import uva.ql.ast.values.types.Int;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class ValueBool extends Values {

	private Int type = new Int();
	private boolean value;
	
	public ValueBool(Node parent, String value, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
		this.value = Boolean.parseBoolean(value);
	}
	
	public boolean eval() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	public boolean getValue() {
		return this.value;
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {
		visitor.visitValueBool(this);
	}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}
}

package uva.ql.ast.variables;

import uva.ql.ast.EnumType;
import uva.ql.ast.abstracts.Node;
import uva.ql.ast.variables.abstracts.Variable;
import uva.ql.ast.variables.types.Bool;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public class VarBool extends Variable {

	private Bool type = new Bool();
	
	public VarBool(Node parent, String name, int startLine, int startColumn) {
		super(parent, name, startLine, startColumn);
	}
	
	@Override
	public EnumType evalType() {
		return this.getType();
	}
	
	@Override
	public EnumType getType() {
		return this.type.getType();
	}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}

	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
	
	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}
	
	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {}
}

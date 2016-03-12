package uva.ql.ast.values.abstracts;

import uva.ql.ast.abstracts.Node;
import uva.ql.ast.expressions.abstracts.Expression;
import uva.ql.ast.values.IValues;
import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public abstract class Values extends Expression implements IValues {

	public Values(Node parent, int startLine, int startColumn) {
		super(parent, startLine, startColumn);
	}
	
	@Override
	public void accept(IUndefinedQuestionVisitor visitor) {}
		
	@Override
	public void accept(IDupllicateLabelsVisitor visitor) {}

	@Override
	public void accept(ICyclicDependencyVisitor visitor) {}

	@Override
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor) {}
	
	@Override
	public void accept(IBinaryOperatorVisitor visitor) {}
	
	@Override
	public void accept(IArithmeticOperatorVisitor visitor) {}
}

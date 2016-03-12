package uva.ql.ast;

import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.INodeVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public interface INode {
	
	public void accept(INodeVisitor visitor);
	public void accept(IArithmeticOperatorVisitor visitor);
	public void accept(IBinaryOperatorVisitor visitor);
	public void accept(IUndefinedQuestionVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateLabelsVisitor visitor);
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor);
}

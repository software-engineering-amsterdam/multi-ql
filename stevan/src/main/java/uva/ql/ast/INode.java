package uva.ql.ast;

import uva.ql.visitors.interfaces.INodeVisitor;
import uva.ql.visitors.interfaces.typechecker.IArithmeticOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.IBinaryOperatorVisitor;
import uva.ql.visitors.interfaces.typechecker.ICyclicDependencyVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateLabelsVisitor;
import uva.ql.visitors.interfaces.typechecker.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.visitors.interfaces.typechecker.IUndefinedQuestionVisitor;

public interface INode {
	
	public void accept(INodeVisitor visitor);
	public void accept(IArithmeticOperatorVisitor visitor);
	public void accept(IBinaryOperatorVisitor visitor);
	public void accept(IUndefinedQuestionVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateLabelsVisitor visitor);
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor);
}

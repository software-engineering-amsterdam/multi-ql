package uva.ql.ast.conditionals;

import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;

public interface ICondition {

	public void accept(IBinaryOperatorVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor);
}

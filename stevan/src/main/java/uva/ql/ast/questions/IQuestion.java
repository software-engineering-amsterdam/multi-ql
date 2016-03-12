package uva.ql.ast.questions;

import uva.ql.interfaces.IArithmeticOperatorVisitor;
import uva.ql.interfaces.IBinaryOperatorVisitor;
import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;

public interface IQuestion {

	public void accept(IArithmeticOperatorVisitor visitor);
	public void accept(IBinaryOperatorVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateLabelsVisitor visitor);
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor);
}

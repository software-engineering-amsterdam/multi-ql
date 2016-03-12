package uva.ql.ast.variables;

import uva.ql.interfaces.ICyclicDependencyVisitor;
import uva.ql.interfaces.IDupllicateLabelsVisitor;
import uva.ql.interfaces.IDupllicateQuestionDifferentTypesVisitor;
import uva.ql.interfaces.IUndefinedQuestionVisitor;

public interface IVariable {

	public void accept(IUndefinedQuestionVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateLabelsVisitor visitor);
	public void accept(IDupllicateQuestionDifferentTypesVisitor visitor);
}

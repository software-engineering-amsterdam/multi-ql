package uva.ql.interfaces;

public interface INode {
	
	public void accept(INodeVisitor visitor);
	public void accept(IArithmeticOperatorVisitor visitor);
	public void accept(IUndefinedQuestionVisitor visitor);
	public void accept(ICyclicDependencyVisitor visitor);
	public void accept(IDupllicateLabelsVisitor visitor);
}

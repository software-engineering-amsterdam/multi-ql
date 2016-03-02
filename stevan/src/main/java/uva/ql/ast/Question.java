package uva.ql.ast;

import uva.ql.interfaces.INode;
import uva.ql.visitors.INodeVisitor;

public class Question extends ANode implements INode {

	private String label;
	private AExpression expression;
	
	Question(AST ast) {
		super(ast);
	}

	@Override
	protected int getNodeType0() {
		return ANode.QUESTION;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public AExpression getExpression() {
		return expression;
	}

	public void setExpression(AExpression expression) {
		this.expression = expression;
	}

	@Override
	public void accept(INodeVisitor visitor) {
		visitor.visitQuestion(this);
	}
}

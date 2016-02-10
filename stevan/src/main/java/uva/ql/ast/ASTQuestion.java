package uva.ql.ast;

import uva.ql.interfaces.IASTNode;
import uva.ql.interfaces.IASTNodeVisitor;

public class ASTQuestion extends ASTNode implements IASTNode {

	private String label;
	private ASTExpression expression;
	private boolean computed = false;
	
	ASTQuestion(AST ast) {
		super(ast);
	}

	@Override
	int getNodeType0() {
		return QUESTION;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isComputed() {
		return computed;
	}

	public void setComputed(boolean computed) {
		this.computed = computed;
	}

	public ASTExpression getExpression() {
		return expression;
	}

	public void setExpression(ASTExpression expression) {
		this.expression = expression;
	}

	@Override
	public void accept(IASTNodeVisitor visitor) {
		visitor.visit(this);
	}
}

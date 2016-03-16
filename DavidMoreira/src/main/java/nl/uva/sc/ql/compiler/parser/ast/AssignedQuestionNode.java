package nl.uva.sc.ql.compiler.parser.ast;

import nl.uva.sc.ql.compiler.typechecker.Visitor;

public class AssignedQuestionNode extends StatementNode {

	private QuestionNode variableNode;
	private ExpressionNode expression;
	
	public AssignedQuestionNode(QuestionNode variableNode, ExpressionNode expression){
		this.variableNode = variableNode;
		this.expression = expression;
	}
	
	public QuestionNode getVariableNode(){
		return this.variableNode;
	}
	
	public ExpressionNode getExpression(){
		return this.expression;
	}
	
	@Override
	public String getType() {
		return getVariableNode().getType();
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void dump() {
		System.out.println(this.getClass());
		getVariableNode().dump();
		getExpression().dump();
	}
}

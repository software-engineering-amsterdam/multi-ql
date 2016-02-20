package nl.uva.sc.ql.parser.ast;

public interface Visitor {
	public void visit(BooleanNode node);
	public void visit(StringNode node);
	public void visit(MoneyNode node);
	public void visit(StatementNode node);
	public void visit(RelationalExpressionNode node);
	public void visit(MathExpressionNode node);
	public void visit(LogicNode node);
	public void visit(IfNode node);
	public void visit(IfElseNode node);
	public void visit(ConditionBlockNode node);
}

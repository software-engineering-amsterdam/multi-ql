package nl.uva.sc.ql.parser;

import nl.uva.sc.ql.parser.ast.AssignVariableNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.ConditionBlockNode;
import nl.uva.sc.ql.parser.ast.IfElseNode;
import nl.uva.sc.ql.parser.ast.IfNode;
import nl.uva.sc.ql.parser.ast.LogicNode;
import nl.uva.sc.ql.parser.ast.MathExpressionNode;
import nl.uva.sc.ql.parser.ast.MoneyNode;
import nl.uva.sc.ql.parser.ast.RelationalExpressionNode;
import nl.uva.sc.ql.parser.ast.StatementNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.VariableNode;

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
	public void visit(AssignVariableNode node);
	public void visit(VariableNode node);
}

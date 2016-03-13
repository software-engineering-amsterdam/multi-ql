package nl.uva.sc.ql.gui;

import nl.uva.sc.ql.parser.ast.AdditionNode;
import nl.uva.sc.ql.parser.ast.AndNode;
import nl.uva.sc.ql.parser.ast.BooleanNode;
import nl.uva.sc.ql.parser.ast.DifferentNode;
import nl.uva.sc.ql.parser.ast.DivisionNode;
import nl.uva.sc.ql.parser.ast.EqualsNode;
import nl.uva.sc.ql.parser.ast.GreatEqualsThanNode;
import nl.uva.sc.ql.parser.ast.GreatThanNode;
import nl.uva.sc.ql.parser.ast.IdentifierNode;
import nl.uva.sc.ql.parser.ast.IntegerNode;
import nl.uva.sc.ql.parser.ast.LessEqualsThanNode;
import nl.uva.sc.ql.parser.ast.LessThanNode;
import nl.uva.sc.ql.parser.ast.MultiplicationNode;
import nl.uva.sc.ql.parser.ast.NotNode;
import nl.uva.sc.ql.parser.ast.OrNode;
import nl.uva.sc.ql.parser.ast.StringNode;
import nl.uva.sc.ql.parser.ast.SubtractionNode;

public interface VisitorExpression {
	public void visit(AdditionNode additionNode);
	public void visit(AndNode andNode);
	public void visit(BooleanNode booleanNode);
	public void visit(DifferentNode differentNode);
	public void visit(DivisionNode divisionNode);
	public void visit(EqualsNode equalsNode);
	public void visit(GreatEqualsThanNode greatEqualsThanNode);
	public void visit(GreatThanNode greatThanNode);
	public void visit(IdentifierNode identifierNode);
	public void visit(IntegerNode integerNode);
	public void visit(LessEqualsThanNode lessEqualsThanNode);
	public void visit(LessThanNode lessThanNode);
	public void visit(MultiplicationNode multiplicationNode);
	public void visit(NotNode notNode);
	public void visit(OrNode orNode);
	public void visit(StringNode stringNode);
	public void visit(SubtractionNode subtractionNode);
}
